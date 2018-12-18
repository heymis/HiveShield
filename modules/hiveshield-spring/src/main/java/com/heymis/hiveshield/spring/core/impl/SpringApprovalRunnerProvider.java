/*******************************************************************************
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.heymis.hiveshield.spring.core.impl;

import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import com.heymis.hiveshield.core.ApprovalRunnerProvider;
import com.heymis.hiveshield.core.ArrivalApprovalRunner;
import com.heymis.hiveshield.core.DepartureApprovalRunner;
import com.heymis.hiveshield.core.HiveshieldException;

/**
 * Spring Approval Runner Provider
 * 
 * @author Oliver Ou
 */
public class SpringApprovalRunnerProvider implements ApprovalRunnerProvider {

	private Logger log = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		log.debug("init|hashCode={}", this.hashCode());
		Objects.requireNonNull(applicationContext, "Empty applicationContext");
	}

	@PreDestroy
	public void destroy() {
		log.debug("init|hashCode={}", this.hashCode());
	}

	private ApplicationContext applicationContext;

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public <T extends ArrivalApprovalRunner<?>> T getArrivalApprovalRunner(String runnerBeanName, Class<T> type)
			throws HiveshieldException {
		return getApprovalRunner(runnerBeanName, type);
	}

	public <T extends DepartureApprovalRunner<?>> T getDepartureApprovalRunner(String runnerBeanName, Class<T> type)
			throws HiveshieldException {
		return getApprovalRunner(runnerBeanName, type);
	}

	private <T> T getApprovalRunner(String runnerBeanName, Class<T> type) {
		Objects.requireNonNull(type, "Empty type");
		if (StringUtils.isBlank(runnerBeanName)) {
			throw new HiveshieldException("Missing runnerBeanName in annotation");
		}
		if (!applicationContext.containsBean(runnerBeanName)) {
			throw new HiveshieldException("Missing runner bean: " + runnerBeanName);
		}
		Object bean = null;
		try {
			bean = applicationContext.getBean(runnerBeanName);
			if (bean == null) {
				throw new HiveshieldException("Missing runner bean: " + runnerBeanName);
			}
		} catch (Throwable e) {
			throw new HiveshieldException("Failed to get runner bean: " + runnerBeanName, e);
		}
		try {
			return type.cast(bean);
		} catch (ClassCastException e) {
			throw new HiveshieldException("Failed to cast runner bean: expected=" + type + ", actual=" + bean.getClass()
					+ ", runnerBeanName=" + runnerBeanName, e);
		}
	}

}

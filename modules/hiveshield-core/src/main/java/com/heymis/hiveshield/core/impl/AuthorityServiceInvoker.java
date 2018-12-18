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
package com.heymis.hiveshield.core.impl;

import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heymis.hiveshield.core.AclContext;
import com.heymis.hiveshield.core.ApprovalContext;
import com.heymis.hiveshield.core.ApprovalRunnerProvider;
import com.heymis.hiveshield.core.ApprovalStatus;
import com.heymis.hiveshield.core.ArrivalApprovalRunner;
import com.heymis.hiveshield.core.AuthorityService;
import com.heymis.hiveshield.core.DepartureApprovalRunner;
import com.heymis.hiveshield.core.HiveshieldException;
import com.heymis.hiveshield.core.ReturnAclContext;
import com.heymis.hiveshield.core.UserSession;

/**
 * Authority Service Invoker
 * 
 * @author Oliver Ou
 */
public class AuthorityServiceInvoker implements AuthorityService {

	private Logger log = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		log.debug("init|hashCode={}", this.hashCode());
		Objects.requireNonNull(approvalRunnerProvider, "Empty approvalRunnerProvider");
	}

	@PreDestroy
	public void destroy() {
		log.debug("init|hashCode={}", this.hashCode());
	}

	private ApprovalRunnerProvider approvalRunnerProvider;

	public ApprovalRunnerProvider getApprovalRunnerProvider() {
		return approvalRunnerProvider;
	}

	public void setApprovalRunnerProvider(ApprovalRunnerProvider approvalRunnerProvider) {
		this.approvalRunnerProvider = approvalRunnerProvider;
	}

	public void checkArrival(AclContext aclContext) throws HiveshieldException {
		Objects.requireNonNull(aclContext, "Empty aclContext");
		ArrivalApprovalRunner<?> runner = null;
		String runnerBeanName = aclContext.getRunnerBeanName();
		try {
			runner = approvalRunnerProvider.getArrivalApprovalRunner(runnerBeanName, ArrivalApprovalRunner.class);
		} catch (HiveshieldException e) {
			throw e;
		}
		String sessionId = UserSession.getSessionId();
		String userId = UserSession.getUserId();
		try {
			log.trace("Inspec arrival before invoking|sessionId={}|userId={}|aclContext={}", sessionId, userId,
					aclContext);
			ApprovalContext approvalContext = runner.inspectArrival(aclContext);
			validate(approvalContext, "arrival");
		} catch (Throwable e) {
			throw new HiveshieldException(e);
		}
	}

	public void checkDeparture(ReturnAclContext returnAclContext) throws HiveshieldException {
		Objects.requireNonNull(returnAclContext, "Empty returnAclContext");
		AclContext aclContext = returnAclContext.getAclContext();
		Objects.requireNonNull(aclContext, "Empty aclContext");
		DepartureApprovalRunner<?> runner = null;
		String runnerBeanName = aclContext.getRunnerBeanName();
		try {
			runner = approvalRunnerProvider.getDepartureApprovalRunner(runnerBeanName, DepartureApprovalRunner.class);
		} catch (HiveshieldException e) {
			throw e;
		}
		String sessionId = UserSession.getSessionId();
		String userId = UserSession.getUserId();
		try {
			log.trace("Inspec departure after invoking|sessionId={}|userId={}|aclContext={}", sessionId, userId,
					aclContext);
			ApprovalContext approvalContext = runner.inspectDeparture(returnAclContext);
			validate(approvalContext, "departure");
		} catch (Throwable e) {
			throw new HiveshieldException(e);
		}
	}

	private void validate(ApprovalContext approvalContext, String log) {
		if (approvalContext == null) {
			throw new HiveshieldException("Returned a null approvalContext in root " + log + " approval runner");
		}
		ApprovalStatus approvalStatus = approvalContext.getApprovalStatus();
		if (approvalStatus == null) {
			throw new HiveshieldException("Returned a null approvalStatus in root " + log + " approval runner");
		}
		if (ApprovalStatus.FINISHED.equals(approvalStatus) == false) {
			throw new HiveshieldException("Returned a non finished approvalStatus in root " + log + " approval runner");
		}
	}

}

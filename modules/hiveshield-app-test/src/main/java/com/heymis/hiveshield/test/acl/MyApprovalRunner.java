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
package com.heymis.hiveshield.test.acl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heymis.hiveshield.core.AclContext;
import com.heymis.hiveshield.core.ApprovalContext;
import com.heymis.hiveshield.core.ApprovalContextBuilder;
import com.heymis.hiveshield.core.ApprovalException;
import com.heymis.hiveshield.core.ArrivalApprovalRunner;
import com.heymis.hiveshield.core.DepartureApprovalRunner;
import com.heymis.hiveshield.core.HiveshieldException;
import com.heymis.hiveshield.core.ReturnAclContext;
import com.heymis.hiveshield.core.UserSession;

/**
 * 
 * @author Oliver Ou
 */
public class MyApprovalRunner
		implements ArrivalApprovalRunner<ApprovalContext>, DepartureApprovalRunner<ApprovalContext> {

	private Logger log = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		log.debug("init|hashCode={}", this.hashCode());
	}

	@PreDestroy
	public void destroy() {
		log.debug("init|hashCode={}", this.hashCode());
	}

	public ApprovalContext inspectArrival(AclContext aclContext) throws ApprovalException {
		String sessionId = UserSession.getSessionId();
		String userId = UserSession.getUserId();
		Class<?> invokeClz = aclContext.getClassName();
		String invokeMethod = aclContext.getMethodName();
		log.debug("lalala ~ arrival");
		ApprovalContext result = ApprovalContextBuilder.FINISHED().build();
		return result;
	}

	public ApprovalContext inspectDeparture(ReturnAclContext returnAclContext) throws ApprovalException {
		String sessionId = UserSession.getSessionId();
		String userId = UserSession.getUserId();
		AclContext aclContext = returnAclContext.getAclContext();
		Class<?> invokeClz = aclContext.getClassName();
		String invokeMethod = aclContext.getMethodName();
		log.debug("lalala ~ departure");
		ApprovalContext result = ApprovalContextBuilder.FINISHED().build();
		return result;
	}

}

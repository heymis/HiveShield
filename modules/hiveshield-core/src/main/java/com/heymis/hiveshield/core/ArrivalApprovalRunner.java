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
package com.heymis.hiveshield.core;

/**
 * Arrival Approval Runner
 * 
 * @param <C> Type of customized approval Context
 * @author Oliver Ou
 */
public interface ArrivalApprovalRunner<C extends ApprovalContext> {

	/**
	 * Inspect arrival approval
	 * 
	 * @param aclContext ACL context for providing approval information
	 * @return Customized approval Context; never null
	 * @throws ApprovalException Access denied
	 */
	public C inspectArrival(AclContext aclContext) throws ApprovalException;

}

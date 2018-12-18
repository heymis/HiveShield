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
 * Authority Service
 * 
 * @author Oliver Ou
 */
public interface AuthorityService {

	/**
	 * Convert arrival information to {@linkplain AclContext}.
	 * 
	 * @param aclContext ACL Context
	 * @throws ApprovalException In case of error
	 * @see AclContext
	 */
	public void checkArrival(AclContext aclContext) throws HiveshieldException;

	/**
	 * Convert departure information to {@linkplain ReturnAclContext}.
	 * 
	 * @param returnAclContext Return ACL Context
	 * @throws ApprovalException In case of error
	 */
	public void checkDeparture(ReturnAclContext returnAclContext) throws HiveshieldException;

}

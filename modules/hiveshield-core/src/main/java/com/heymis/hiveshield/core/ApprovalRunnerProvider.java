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
 * Approval Runner Provider
 * 
 * @author Oliver Ou
 */
public interface ApprovalRunnerProvider {

	/**
	 * Seek arrival approval runner bean
	 * 
	 * @param runnerBeanName Runner bean name for searching
	 * @param type           Type of arrival approval runner
	 * @return Arrival approval runner bean; never null
	 * @throws HiveshieldException Throws an exception if bean does NOT exist
	 */
	public <T extends ArrivalApprovalRunner<?>> T getArrivalApprovalRunner(String runnerBeanName, Class<T> type)
			throws HiveshieldException;

	/**
	 * Seek departure approval runner bean
	 * 
	 * @param runnerBeanName Runner bean name for searching
	 * @param type           Type of departure approval runner
	 * @return Departure approval runner bean; never null
	 * @throws HiveshieldException Throws an exception if bean does NOT exist
	 */
	public <T extends DepartureApprovalRunner<?>> T getDepartureApprovalRunner(String runnerBeanName, Class<T> type)
			throws HiveshieldException;

}

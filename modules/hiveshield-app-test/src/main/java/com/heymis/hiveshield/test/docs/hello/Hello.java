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
package com.heymis.hiveshield.test.docs.hello;

/**
 * @author Oliver Ou
 */
public interface Hello {

	public String getBusinessKey();

	public void setBusinessKey(String businessKey);

	public String getApplicationId();

	public void setApplicationId(String applicationId);

	public String getUuid();

	public void setUuid(String uuid);

}

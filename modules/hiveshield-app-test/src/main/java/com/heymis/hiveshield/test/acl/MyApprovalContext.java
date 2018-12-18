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

import com.heymis.hiveshield.core.ApprovalContext;
import com.heymis.hiveshield.core.ApprovalStatus;

/**
 * Customized Approval Context
 * 
 * @author Oliver Ou
 */
public class MyApprovalContext implements ApprovalContext {

	private ApprovalStatus approvalStatus;

	private String customerType = null;

	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Override
	public String toString() {
		return "MyApprovalContext [approvalStatus=" + approvalStatus + ", customerType=" + customerType + "]";
	}

}

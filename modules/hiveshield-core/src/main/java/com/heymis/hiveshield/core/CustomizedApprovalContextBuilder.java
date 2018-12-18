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
 * Customized Approval Context Builder
 * 
 * @author Oliver Ou
 * @param <C> Customized Approval Context
 */
public class CustomizedApprovalContextBuilder<C extends ApprovalContext> {

	private C approvalContext;

	private ApprovalContextBuilder parentBuilder;

	public C getApprovalContext() {
		return approvalContext;
	}

	private void setApprovalContext(C approvalContext) {
		this.approvalContext = approvalContext;
	}

	/**
	 * Accessed by {@link ApprovalContextBuilder#custom(Class)}
	 * 
	 * @param parentBuilder Parent Builder
	 */
	protected void setParentBuilder(ApprovalContextBuilder parentBuilder) {
		this.parentBuilder = parentBuilder;
	}

	/**
	 * Accessed by {@link ApprovalContextBuilder#custom(Class)}
	 * 
	 * @param parentBuilder Parent Builder
	 */
	protected static <C extends ApprovalContext> CustomizedApprovalContextBuilder<C> create(Class<C> type)
			throws HiveshieldException {
		C approvalContext;
		try {
			approvalContext = type.newInstance();
		} catch (Exception e) {
			throw new HiveshieldException("Failed to create instance of approvalContext, cause=" + e.getMessage(), e);
		}
		CustomizedApprovalContextBuilder<C> builder = new CustomizedApprovalContextBuilder<C>();
		builder.setApprovalContext(approvalContext);
		return builder;
	}

	public C build() {
		validateApprovalContext();
		return approvalContext;
	}

	public ApprovalContextBuilder join() {
		if (parentBuilder == null) {
			throw new HiveshieldException("Please tell developers about this bug.");
		}
		return parentBuilder;
	}

	public CustomizedApprovalContextBuilder<C> setApprovalStatus(ApprovalStatus approvalStatus) {
		validateApprovalContext();
		parentBuilder.setApprovalStatus(approvalStatus);
		return this;
	}

	public CustomizedApprovalContextBuilder<C> setApprovalStatusFinished() {
		validateApprovalContext();
		parentBuilder.setApprovalStatusFinished();
		return this;
	}

	public CustomizedApprovalContextBuilder<C> setApprovalStatusContinuable() {
		validateApprovalContext();
		parentBuilder.setApprovalStatusContinuable();
		return this;
	}

	private void validateApprovalContext() {
		if (parentBuilder.getApprovalContext() == null) {
			throw new HiveshieldException("Please tell developers about this bug.");
		}
	}

}

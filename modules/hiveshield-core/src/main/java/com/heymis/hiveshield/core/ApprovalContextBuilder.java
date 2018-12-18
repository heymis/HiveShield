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

import java.util.Objects;

import com.heymis.hiveshield.core.impl.BaseApprovalContext;

/**
 * Approval Context Builder
 * 
 * @author Oliver Ou
 */
public class ApprovalContextBuilder {

	private ApprovalContext approvalContext;

	private CustomizedApprovalContextBuilder<?> customizedBuilder;

	public ApprovalContext getApprovalContext() {
		return approvalContext;
	}

	private void setApprovalContext(ApprovalContext approvalContext) {
		this.approvalContext = approvalContext;
	}

	private void setCustomizedBuilder(CustomizedApprovalContextBuilder<?> customizedBuilder) {
		this.customizedBuilder = customizedBuilder;
	}

	public static ApprovalContextBuilder create() {
		ApprovalContextBuilder builder = new ApprovalContextBuilder();
		builder.setApprovalContext(new BaseApprovalContext());
		return builder;
	}

	public static <C extends ApprovalContext> CustomizedApprovalContextBuilder<C> custom(Class<C> type)
			throws HiveshieldException {
		ApprovalContextBuilder builder = new ApprovalContextBuilder();
		CustomizedApprovalContextBuilder<C> customizedBuilder = CustomizedApprovalContextBuilder.create(type);
		customizedBuilder.setParentBuilder(builder);
		builder.setCustomizedBuilder(customizedBuilder);
		// Sync approval context
		builder.setApprovalContext(customizedBuilder.getApprovalContext());
		return customizedBuilder;
	}

	public ApprovalContext build() {
		validateApprovalContext();
		return approvalContext;
	}

	public CustomizedApprovalContextBuilder<?> joinCustomized() {
		if (customizedBuilder == null) {
			throw new HiveshieldException("This builder is NOT customized");
		}
		return customizedBuilder;
	}

	public static ApprovalContextBuilder FINISHED() {
		return create().setApprovalStatus(ApprovalStatus.FINISHED);
	}

	public static ApprovalContextBuilder CONTINUABLE() {
		return create().setApprovalStatus(ApprovalStatus.CONTINUABLE);
	}

	public ApprovalContextBuilder setApprovalStatus(ApprovalStatus approvalStatus) {
		Objects.requireNonNull(approvalStatus, "Empty approvalStatus");
		validateApprovalContext();
		approvalContext.setApprovalStatus(approvalStatus);
		return this;
	}

	public ApprovalContextBuilder setApprovalStatusFinished() {
		validateApprovalContext();
		approvalContext.setApprovalStatus(ApprovalStatus.FINISHED);
		return this;
	}

	public ApprovalContextBuilder setApprovalStatusContinuable() {
		validateApprovalContext();
		approvalContext.setApprovalStatus(ApprovalStatus.CONTINUABLE);
		return this;
	}
	
	private void validateApprovalContext() {
		if (approvalContext == null) {
			throw new HiveshieldException("Please tell developers about this bug.");
		}
	}

}

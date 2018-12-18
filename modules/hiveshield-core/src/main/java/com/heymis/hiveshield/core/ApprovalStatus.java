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
 * Approval Status
 * 
 * @author Oliver Ou
 */
public enum ApprovalStatus {

	/**
	 * Mark this approval context can be passed to the next runner .
	 */
	CONTINUABLE(true),

	/**
	 * Mark this approval context is finished, <strong>DO NOT PASS</strong> it to the next runner.
	 */
	FINISHED(false);

	private final boolean continuable;

	private ApprovalStatus(boolean continuable) {
		this.continuable = continuable;
	}

	public static ApprovalStatus continueIf(boolean continuable) {
		return continuable ? CONTINUABLE : FINISHED;
	}

	public boolean isContinuable() {
		return this == CONTINUABLE;
	}

	public ApprovalStatus and(boolean value) {
		return value && continuable ? CONTINUABLE : FINISHED;
	}

}

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

import com.heymis.hiveshield.core.AclContext;
import com.heymis.hiveshield.core.ReturnAclContext;

/**
 * Simple Return ACL Context
 * 
 * @author Oliver Ou
 */
public class BaseReturnAclContext implements ReturnAclContext {

	private AclContext aclContext;

	private Class<?> returnType;

	private Object returnValue;

	public AclContext getAclContext() {
		return aclContext;
	}

	public void setAclContext(AclContext aclContext) {
		this.aclContext = aclContext;
	}

	public Class<?> getReturnType() {
		return returnType;
	}

	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}

	public Object getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(Object returnValue) {
		this.returnValue = returnValue;
	}

	@Override
	public String toString() {
		return "BaseReturnAclContext [aclContext=" + aclContext + ", returnType=" + returnType + ", returnValue="
				+ returnValue + "]";
	}

}

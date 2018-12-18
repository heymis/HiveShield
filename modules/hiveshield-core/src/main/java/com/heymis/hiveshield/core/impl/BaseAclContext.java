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

import java.util.List;

import com.heymis.hiveshield.core.AccessHint;
import com.heymis.hiveshield.core.AclContext;

/**
 * Simple ACL Context
 * 
 * @author Oliver Ou
 */
public class BaseAclContext implements AclContext {

	private Class<?> className;

	private String methodName;

	private List<Object> args;

	private String aclIdentity;

	private List<String> accesses;

	private AccessHint accessHint;

	private String runnerBeanName;

	public Class<?> getClassName() {
		return className;
	}

	public void setClassName(Class<?> className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public List<Object> getArgs() {
		return args;
	}

	public void setArgs(List<Object> args) {
		this.args = args;
	}

	public String getAclIdentity() {
		return aclIdentity;
	}

	public void setAclIdentity(String aclIdentity) {
		this.aclIdentity = aclIdentity;
	}

	public List<String> getAccesses() {
		return accesses;
	}

	public void setAccesses(List<String> accesses) {
		this.accesses = accesses;
	}

	public AccessHint getAccessHint() {
		return accessHint;
	}

	public void setAccessHint(AccessHint accessHint) {
		this.accessHint = accessHint;
	}

	public String getRunnerBeanName() {
		return runnerBeanName;
	}

	public void setRunnerBeanName(String runnerBeanName) {
		this.runnerBeanName = runnerBeanName;
	}

	@Override
	public String toString() {
		return "BaseAclContext [className=" + className + ", methodName=" + methodName + ", args=" + args
				+ ", securityIdentity=" + aclIdentity + ", accesses=" + accesses + ", accessHint=" + accessHint
				+ ", runnerBeanName=" + runnerBeanName + "]";
	}

}

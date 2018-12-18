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

import java.util.List;

/**
 * ACL Context
 * 
 * @author Oliver Ou
 */
public interface AclContext {

	/**
	 * Class name for approving
	 * 
	 * @return Class name
	 */
	public Class<?> getClassName();

	/**
	 * Class name for approving
	 * 
	 * @param className Class name
	 */
	public void setClassName(Class<?> className);

	/**
	 * Method name for approving
	 * @return Method name
	 */
	public String getMethodName();

	/**
	 * Method name for approving
	 * @param methodName Method name
	 */
	public void setMethodName(String methodName);
	
	public List<Object> getArgs();

	public void setArgs(List<Object> args);

	public String getAclIdentity();

	public void setAclIdentity(String aclIdentity);

	public List<String> getAccesses();

	public void setAccesses(List<String> accesses);
	
	public AccessHint getAccessHint();

	public void setAccessHint(AccessHint accessHint);
	
	public String getRunnerBeanName();

	public void setRunnerBeanName(String runnerBeanName);

}

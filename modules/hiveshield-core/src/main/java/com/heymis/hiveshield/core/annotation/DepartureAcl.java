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
package com.heymis.hiveshield.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.heymis.hiveshield.core.AccessHint;
import com.heymis.hiveshield.core.ArrivalApprovalRunner;
import com.heymis.hiveshield.core.Constants;

/**
 * <p>
 * Indicates executing the DepartureAcl after invoking this method.
 * </p>
 * 
 * @see ArrivalApprovalRunner
 * @see AccessHint
 * @author Oliver Ou
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DepartureAcl {

	/**
	 * ACL Identity
	 * 
	 * @return An ACL identity name for this ACL
	 */
	String aId();

	/**
	 * Pass conditions and access hint design for this ACL.
	 * 
	 * @return Pass conditions
	 * @see #accessHint()
	 * @see Constants#ACCESS_READ
	 */
	String[] accesses();

	/**
	 * Pass conditions and access hint design for this ACL.
	 * 
	 * @return Access hint
	 * @see #accesses()
	 */
	AccessHint accessHint() default AccessHint.UNION;
	
	/**
	 * Indicates return type
	 * 
	 * @return Class of return type
	 */
	Class<?> returnType() default Object.class;

	/**
	 * A bean name for seeking in context and executing this ArrivalAcl.
	 * 
	 * @return A bean name
	 */
	String approvalRunner() default Constants.DEFAULT_APPROVAL_RUNNER_BEAN_NAME;

}

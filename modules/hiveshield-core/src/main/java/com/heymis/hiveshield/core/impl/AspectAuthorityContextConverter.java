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

import java.util.Arrays;
import java.util.Objects;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heymis.hiveshield.core.AclContext;
import com.heymis.hiveshield.core.ApprovalException;
import com.heymis.hiveshield.core.AuthorityService;
import com.heymis.hiveshield.core.HiveshieldException;
import com.heymis.hiveshield.core.ReturnAclContext;
import com.heymis.hiveshield.core.annotation.ArrivalAcl;
import com.heymis.hiveshield.core.annotation.DepartureAcl;

/**
 * Aspect Authority Context Converter
 * 
 * @author Oliver Ou
 */
@Aspect
public final class AspectAuthorityContextConverter {

	private Logger log = LoggerFactory.getLogger(getClass());

	@PostConstruct
	public void init() {
		log.debug("init|hashCode={}", this.hashCode());
		validate();
	}

	@PreDestroy
	public void destroy() {
		log.debug("init|hashCode={}", this.hashCode());
	}

	private AuthorityService authorityService;

	public AuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(AuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	private void validate() {
		Objects.requireNonNull(authorityService, "Empty authorityService");
	}

	@Pointcut(value = "execution(public * *(..))")
	public void anyPublicMethod() {
	}

	@Before(value = "anyPublicMethod() && @annotation(arrivalAcl)")
	public void checkArrival(JoinPoint joinPoint, ArrivalAcl arrivalAcl) throws HiveshieldException {
		validate();
		BaseAclContext aclContext = new BaseAclContext();
		appendJoinPoint(aclContext, joinPoint);
		appendArrivalAcl(aclContext, arrivalAcl);
		try {
			authorityService.checkArrival(aclContext);
		} catch (ApprovalException e) {
			throw e;
		} catch (HiveshieldException e) {
			throw e;
		} catch (Throwable e) {
			throw new HiveshieldException(e);
		}
	}

	@Around(value = "anyPublicMethod() && @annotation(departureAcl)")
	public void checkDeparture(ProceedingJoinPoint joinPoint, DepartureAcl departureAcl)
			throws HiveshieldException, Throwable {
		validate();
		BaseAclContext aclContext = new BaseAclContext();
		appendJoinPoint(aclContext, joinPoint);
		BaseReturnAclContext returnAclContext = new BaseReturnAclContext();
		returnAclContext.setAclContext(aclContext);
		appendDepartureAcl(returnAclContext, departureAcl);

		try {
			Object returnValue = joinPoint.proceed();
			returnAclContext.setReturnValue(returnValue);
		} catch (Throwable e) {
			throw e;
		}
		try {
			authorityService.checkDeparture(returnAclContext);
		} catch (ApprovalException e) {
			throw e;
		} catch (HiveshieldException e) {
			throw e;
		} catch (Throwable e) {
			throw new HiveshieldException(e);
		}
	}

	private void appendDepartureAcl(ReturnAclContext returnAclContext, DepartureAcl departureAcl) {
		Objects.requireNonNull(returnAclContext, "Empty returnAclContext");
		AclContext aclContext = returnAclContext.getAclContext();
		Objects.requireNonNull(aclContext, "Empty aclContext");
		Objects.requireNonNull(departureAcl, "Empty departureAcl");

		if (ArrayUtils.isNotEmpty(departureAcl.accesses())) {
			aclContext.setAccesses(Arrays.asList(departureAcl.accesses()));
		}
		aclContext.setAclIdentity(departureAcl.aId());
		aclContext.setAccessHint(departureAcl.accessHint());
		aclContext.setRunnerBeanName(departureAcl.approvalRunner());
		returnAclContext.setReturnType(departureAcl.returnType());
	}

	private void appendArrivalAcl(AclContext aclContext, ArrivalAcl arrivalAcl) {
		Objects.requireNonNull(aclContext, "Empty aclContext");
		Objects.requireNonNull(arrivalAcl, "Empty arrivalAcl");
		if (ArrayUtils.isNotEmpty(arrivalAcl.accesses())) {
			aclContext.setAccesses(Arrays.asList(arrivalAcl.accesses()));
		}
		aclContext.setAclIdentity(arrivalAcl.aId());
		aclContext.setAccessHint(arrivalAcl.accessHint());
		aclContext.setRunnerBeanName(arrivalAcl.approvalRunner());
	}

	private void appendJoinPoint(AclContext aclContext, JoinPoint joinPoint) {
		Objects.requireNonNull(aclContext, "Empty aclContext");
		Objects.requireNonNull(joinPoint, "Empty joinPoint");
		Class<?> className = joinPoint.getSignature().getDeclaringType();
		aclContext.setClassName(className);
		String methodName = joinPoint.getSignature().getName();
		aclContext.setMethodName(methodName);
		appendArgs(aclContext, joinPoint);
	}

	private void appendArgs(AclContext aclContext, JoinPoint joinPoint) {
		Objects.requireNonNull(aclContext, "Empty aclContext");
		Objects.requireNonNull(joinPoint, "Empty joinPoint");
		Object args[] = joinPoint.getArgs();
		if (ArrayUtils.isEmpty(args)) {
			return;
		}
		aclContext.setArgs(Arrays.asList(args));
	}

}

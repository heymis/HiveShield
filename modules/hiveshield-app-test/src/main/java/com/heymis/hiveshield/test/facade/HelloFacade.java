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
package com.heymis.hiveshield.test.facade;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.heymis.hiveshield.core.UserSession;
import com.heymis.hiveshield.test.docs.hello.Hello;
import com.heymis.hiveshield.test.docs.hello.HelloService;

/**
 * @author Oliver Ou
 */
@Component
public class HelloFacade {
	
private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@PostConstruct
	public void init() {
		log.debug("init|hashCode={}", this.hashCode());
	}
	
	@PreDestroy
	public void destroy() {
		log.debug("init|hashCode={}", this.hashCode());
	}

	@Autowired
	private HelloService service;

	public Hello create(Hello hello) {
		String sessionId = UserSession.getSessionId();
		String userId = UserSession.getUserId();
		log.debug("Create hello|sessionId={}|userId={}|hello={}", sessionId, userId, hello);
		return service.create(hello);
	}

}

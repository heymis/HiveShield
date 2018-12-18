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
package com.heymis.hiveshield.test.docs.hello.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heymis.hiveshield.core.UserSession;
import com.heymis.hiveshield.test.docs.hello.Hello;
import com.heymis.hiveshield.test.docs.hello.HelloService;

/**
 * @author Oliver Ou
 */
public class BaseHelloService implements HelloService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@PostConstruct
	public void init() {
		log.debug("init|hashCode={}", this.hashCode());
	}

	@PreDestroy
	public void destroy() {
		log.debug("init|hashCode={}", this.hashCode());
	}

	public Hello create(Hello hello) {
		String sessionId = UserSession.getSessionId();
		String userId = UserSession.getUserId();
		log.debug("Create hello|sessionId={}|userId={}|hello={}", sessionId, userId, hello);
		return hello;
	}

	public List<Hello> query(Map<String, Object> restrictions, Map<String, Boolean> order) {
		String sessionId = UserSession.getSessionId();
		String userId = UserSession.getUserId();
		log.debug("Query hello|sessionId={}|userId={}|restrictions={}", sessionId, userId, restrictions);

		List<Hello> results = new LinkedList<Hello>();

		BaseHello hello1 = new BaseHello();
		hello1.setBusinessKey("com.example.docs");
		hello1.setApplicationId("hello");
		hello1.setUuid(UUID.randomUUID().toString());
		results.add(hello1);

		BaseHello hello2 = new BaseHello();
		hello2.setBusinessKey("com.example.docs");
		hello2.setApplicationId("hello");
		hello2.setUuid(UUID.randomUUID().toString());
		results.add(hello2);

		return results;
	}

}

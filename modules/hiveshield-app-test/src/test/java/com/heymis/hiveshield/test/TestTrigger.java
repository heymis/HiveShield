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
package com.heymis.hiveshield.test;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heymis.hiveshield.core.UserSession;
import com.heymis.hiveshield.test.docs.hello.impl.BaseHello;
import com.heymis.hiveshield.test.facade.HelloFacade;

import config.spring.SpringMainConfig;

/**
 * @author Oliver Ou
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringMainConfig.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTrigger {

	@Before
	public void setUp() throws Exception {
		UserSession.setSessionId(UUID.randomUUID().toString());
		UserSession.setUserId("Tester");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Autowired
	private HelloFacade facade;

	@Test
	public void test() {
		BaseHello hello = new BaseHello();
		hello.setBusinessKey("com.example.docs");
		hello.setApplicationId("hello");
		hello.setUuid(UUID.randomUUID().toString());
		facade.create(hello);
	}

}

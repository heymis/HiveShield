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
package config.spring.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.heymis.hiveshield.core.Constants;
import com.heymis.hiveshield.test.acl.MyApprovalRunner;
import com.heymis.hiveshield.test.docs.hello.HelloService;
import com.heymis.hiveshield.test.docs.hello.impl.BaseHelloService;

/**
 * @author Oliver Ou
 */
@Configuration
public class ProjectBeans {
	
	@Bean
	public HelloService helloService() {
		BaseHelloService service = new BaseHelloService();
		return service;
	}
	
	@Bean(Constants.DEFAULT_APPROVAL_RUNNER_BEAN_NAME)
	public MyApprovalRunner myFirstApprovalRunner() {
		MyApprovalRunner service = new MyApprovalRunner();
		return service;
	}
	
//	@Bean(Constants.DEFAULT_APPROVAL_RUNNER_BEAN_NAME)
//	public MyApprovalRunnerCustomizedAC myFirstApprovalRunner() {
//		MyApprovalRunnerCustomizedAC service = new MyApprovalRunnerCustomizedAC();
//		return service;
//	}
	

}

/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.heymis.hiveshield.core;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * User Session
 * 
 * @author Oliver Ou
 */
public final class UserSession {

	public static final String SESSION_ID = "com.heymis.hiveshield.core.sessionId";

	public static final String USER_ID = "com.heymis.hiveshield.core.userId";

	private static ThreadLocal<Map<String, Object>> loginThreadLocal = new ThreadLocal<Map<String, Object>>();

	public static Map<String, Object> getSessionValues() {
		return loginThreadLocal.get();
	}

	public static void setSessionValues(Map<String, Object> values) {
		loginThreadLocal.set(values);
	}

	public static void removeSessionValues() {
		loginThreadLocal.remove();
	}

	public static boolean hasSession() {
		return MapUtils.isNotEmpty(getSessionValues()) ? true : false;
	}

	public static void setUserId(String userId) {
		if (!hasSession()) {
			createNewSession();
		}
		if (StringUtils.isBlank(userId)) {
			throw new IllegalArgumentException("Empty userId");
		}
		getSessionValues().put(USER_ID, userId);
	}

	public static String getUserId() {
		return MapUtils.getString(getSessionValues(), USER_ID);
	}

	public static void setSessionId(String sessionId) {
		if (!hasSession()) {
			createNewSession();
		}
		if (StringUtils.isBlank(sessionId)) {
			throw new IllegalArgumentException("Empty sessionId");
		}
		getSessionValues().put(SESSION_ID, sessionId);
	}

	public static String getSessionId() {
		if (!hasSession()) {
			createNewSession();
		}
		return MapUtils.getString(getSessionValues(), SESSION_ID);
	}

	private static void createNewSession() {
		Map<String, Object> session = new LinkedHashMap<String, Object>();
		setSessionValues(session);
	}

}

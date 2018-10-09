package com.heymis.hiveshield.core;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

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

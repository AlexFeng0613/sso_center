package com.hsjc.central.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : zga
 * @date : 2015/11/24
 */
public class HttpUtil {

	/**
	 * ��ȡCookie����
	 * @param request request����
	 * @param name Cookie����
	 * @return ֵ
	 */
	public static String getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if ( cookies != null ) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * ���Cookie����
	 * @param response response����
	 * @param name Cookie����
	 * @param value ֵ
	 */
	public static void addCookie(HttpServletResponse response, String name, String value) {
		addCookie(response, name, value, 1 * 365 * 24 * 60 * 60);
	}

	/**
	 * ���Cookie����
	 * @param response response����
	 * @param name Cookie����
	 * @param value ֵ
	 * @param maxAge ����ʱ�䣨�룩
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		addCookie(response, name, value, maxAge, "/");
	}

	/**
	 * ���Cookie����
	 * @param response response����
	 * @param name Cookie����
	 * @param value ֵ
	 * @param maxAge ����ʱ�䣨�룩
	 * @param path ���·��
	 */
	public static void addCookie(HttpServletResponse response, String name, String value, int maxAge, String path) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);
		response.addCookie(cookie);
	}

	public static void removeCookie(HttpServletResponse response, String name) {
		addCookie(response, name, null, -1);
	}
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			return ip;
		}
		ip = request.getHeader("X-Forwarded-For");
		if (!StringUtils.isEmpty(ip) && !"unknown".equalsIgnoreCase(ip)) {
			// ��η���������ж��IPֵ����һ��Ϊ��ʵIP��
			int index = ip.indexOf(',');
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		} else {
			return request.getRemoteAddr();
		}
	}
}

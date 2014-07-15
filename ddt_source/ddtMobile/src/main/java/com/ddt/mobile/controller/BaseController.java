/*
 * @(#)BaseController.java 2014-6-19
 *
 */
package com.ddt.mobile.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import com.ddt.core.meta.User;
import com.ddt.core.service.UserService;

/**
 * BaseController.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-6-19
 * @since      1.0
 */
public class BaseController {
	
	@Autowired
	protected UserService userService;
	
	public User getUser(HttpServletRequest request) {
		String wx = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "wx", ""));
		User user = userService.getWxUserByWxNumber(wx);
		return user;
	}
	
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		
		if (StringUtils.isBlank(ip)) {
			return null;
		}
		
		Pattern pattern = Pattern.compile("((2[0-4]\\d|25[0-5]|1?\\d?\\d)\\.){3}(2[0-4]\\d|25[0-5]|1?\\d?\\d)");
		Matcher matcher = pattern.matcher(ip);
		if (!matcher.matches()) {
			return null;
		}
		return ip;
	}
}

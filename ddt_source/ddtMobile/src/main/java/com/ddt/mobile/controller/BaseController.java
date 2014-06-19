/*
 * @(#)BaseController.java 2014-6-19
 *
 */
package com.ddt.mobile.controller;

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
		User user = userService.getUserByName(wx);
		return user;
	}
}

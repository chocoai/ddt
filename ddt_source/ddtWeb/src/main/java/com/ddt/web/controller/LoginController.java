/*
 * @(#)LoginController.java 2013-10-23
 *
 * Copyright 2013 mircobuy, Inc. All rights reserved.
 */
package com.ddt.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ddt.core.common.SessionVariable;
import com.ddt.core.constants.StatusCode;
import com.ddt.core.meta.User;
import com.ddt.core.service.UserService;

/**
 * LoginController.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2013-10-23
 * @since      1.0
 */
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 主界面如果
	 * @return
	 */
	@RequestMapping({"", "login"})
	public String index() {
		return "login";
	}
	
	/**
	 * 登陆操作
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="login", method={RequestMethod.POST})
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("info");
		
//		String imageCode = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "imgCode", ""));
//		
//		if (!validateImageCode(request, imageCode)) {
//			view.addObject("status", StatusCode.VALID_CODE_ERROR);
//			view.addObject("result", "验证码错误");
//			return view;
//		}
		
		String mobile = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "username", ""));
		String password = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "password", ""));
		try {
			User c = userService.getWxUserByMobile(mobile);
			if (c == null) {
				view.addObject("status", StatusCode.USER_NAME_NOT_EXISTS);
				view.addObject("result", "用户名不存在");
				return view;
			}
			
			Subject subject = SecurityUtils.getSubject();
			if (!subject.isAuthenticated()) {
				login(subject, mobile, password);
			} else {
				SessionVariable sessionVariable = (SessionVariable) subject.getPrincipal();
				if (sessionVariable == null) {
					login(subject, mobile, password);
				} else {
					User user = sessionVariable.getUser();
					if (!user.getMobile().equalsIgnoreCase(mobile)) {
						subject.logout();
						login(subject, mobile, password);
					}
				}
			}
			view.addObject("status", StatusCode.OK);
		} catch (Exception e) {
			view.addObject("status", StatusCode.PASSWORD_ERROR);
			view.addObject("result", "密码错误");
		}
		
		return view;
	}
	
	
	/**
	 * 登出
	 * @return
	 */
	@RequestMapping("logout")
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:login";
	}
	
	/**
	 * 具体的登陆步骤，将调用JDBCAuthenticationRealm.doGetAuthenticationInfo(AuthenticationToken token)
	 * @param subject
	 * @param username
	 * @param password
	 */
	private void login(Subject subject, String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		subject.login(token);
	}
}

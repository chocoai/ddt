/*
 * @(#)BaseController.java 2013-10-30
 *
 * Copyright 2013 ddt, Inc. All rights reserved.
 */
package com.ddt.web.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.web.servlet.ModelAndView;

import com.ddt.core.common.SessionVariable;
import com.ddt.core.constants.Constants;
import com.ddt.core.meta.User;

/**
 * BaseController.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2013-10-30
 * @since      1.0
 */
public class BaseController {
	
	protected User getSession() {
		SessionVariable sessionVariable = (SessionVariable) SecurityUtils.getSubject().getSession().getAttribute(Constants.USER_SESSION_KEY);
		if (sessionVariable != null) {
			return sessionVariable.getUser();
		}
		
		return null;
	}
	
	protected void setSession(User user) {
		SessionVariable sessionVariable = (SessionVariable) SecurityUtils.getSubject().getSession().getAttribute(Constants.USER_SESSION_KEY);
		sessionVariable.setUser(user);
	}
	
	protected ModelAndView getBaseView(String ftl) {
		ModelAndView view = new ModelAndView(ftl);
		
		view.addObject("userSession", getSession());
		
		return view;
	}
	
	protected long getUserId() {
		User user = getSession();
		if (user == null) {
			return 0;
		}
		return user.getId();
	}
	
}

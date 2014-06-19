/*
 * @(#)ScoreController.java 2014-6-19
 *
 */
package com.ddt.mobile.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ddt.core.meta.User;

/**
 * ScoreController.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-6-19
 * @since      1.0
 */

@Controller
@RequestMapping("/score")
public class ScoreController extends BaseController {
	
	@RequestMapping("/mall")
	public ModelAndView mall(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		User user = getUser(request);
		
		return view;
	}
	
	@RequestMapping("/query")
	public ModelAndView query(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		User user = getUser(request);
		
		return view;
	}
	
	@RequestMapping("/sign")
	public ModelAndView sign(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		User user = getUser(request);
		
		return view;
	}
}

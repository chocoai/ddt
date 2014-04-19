/*
 * @(#)PlatformController.java
 */
package com.ddt.mobile.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * PlatformController.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @since      1.0
 */
@Controller
public class PlatformController {
	private static Logger log = Logger.getLogger(PlatformController.class);
	
	/**
	 * 產品列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/goods-list")
	public ModelAndView goodsList(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
	
	/**
	 * 產品詳情
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/good-detail")
	public ModelAndView goodDetail(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}
	
	
}

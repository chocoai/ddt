/*
 * @(#)IndexController.java
 */
package com.ddt.mobile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * IndexController.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @since      1.0
 */
@Controller
public class IndexController {
	
	@RequestMapping("index")
	public ModelAndView index() {
		return new ModelAndView("index");
	}
}

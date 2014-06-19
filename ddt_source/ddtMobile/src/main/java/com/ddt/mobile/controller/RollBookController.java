/*
 * @(#)RollBookController.java 2014-6-19
 *
 */
package com.ddt.mobile.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ddt.core.meta.RollBook;
import com.ddt.core.meta.User;
import com.ddt.core.service.RollBookService;

/**
 * RollBookController.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-6-19
 * @since      1.0
 */
@Controller
@RequestMapping("/rollbook")
public class RollBookController extends BaseController {
	
	@Autowired
	private RollBookService rollBookService;
	
	/**
	 * 获取点名册
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/myrollbook")
	public ModelAndView rollBookList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		User user = getUser(request);
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int limit = 20;
		int offset = (page - 1) * limit;
		
		List<RollBook> list = rollBookService.getRollBookList(user.getId(), "", limit, offset);
		int count = rollBookService.getRollBookCount(user.getId(), "");
		
		view.addObject("rollBooks", list);
		view.addObject("page", page);
		view.addObject("totalPage", (int) Math.ceil(count * 1.0 / limit));
		
		return view;
	}
	
	/**
	 * 我被点名
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/rolled")
	public ModelAndView rolled(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		User user = getUser(request);
		
		return view;
	}
	
	/**
	 * 开始点名
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/start")
	public ModelAndView start(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		User user = getUser(request);
		
		return view;
	}
	
	/**
	 * 结束点名
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/end")
	public ModelAndView end(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		User user = getUser(request);
		
		return view;
	}
}

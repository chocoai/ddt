/*
 * @(#)RollBookController.java 2014-4-24
 *
 */
package com.ddt.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ddt.core.meta.RollBook;
import com.ddt.core.meta.RollInfo;
import com.ddt.core.meta.User;
import com.ddt.core.meta.UserRollInfo;
import com.ddt.core.service.RollBookService;

/**
 * RollBookController.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-24
 * @since      1.0
 */
@Controller
@RequestMapping("/rollbook")
public class RollBookController extends BaseController {
	
	@Autowired
	private RollBookService rollBookService;
	
	@RequestMapping("list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 20);
		int offset = (page - 1) * limit;
		
		long userId = getUserId();
		
		List<RollBook> rollBooks = rollBookService.getRollBookList(userId, limit, offset);
		
		view.addObject("rollBooks", rollBooks);
		view.addObject("page", page);
		
		return view;
	}
	
	/**
	 * 点名的用户列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("rolluser")
	public ModelAndView rollUser(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 20);
		long rollBookId = ServletRequestUtils.getLongParameter(request, "roll_book_id", 0);
		int offset = (page - 1) * limit;
		
		long userId = getUserId();
		
		List<User> users = rollBookService.getRollBookUserList(userId, rollBookId, limit, offset);
		
		view.addObject("users", users);
		view.addObject("page", page);
		
		return view;
	}
	
	/**
	 * 点名情况
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("rollinfo")
	public ModelAndView rollInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 20);
		long rollBookId = ServletRequestUtils.getLongParameter(request, "roll_book_id", 0);
		int offset = (page - 1) * limit;
		
		long userId = getUserId();
		
		List<RollInfo> rollInfos = rollBookService.getRollInfoList(userId, rollBookId, limit, offset);
		
		view.addObject("rollInfos", rollInfos);
		view.addObject("page", page);
		
		return view;
	}
	
	/**
	 * 每一次用户的点名详情
	 */
	@RequestMapping("userrollinfo")
	public ModelAndView userRollInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 20);
		long rollInfoId = ServletRequestUtils.getLongParameter(request, "roll_info_id", 0);
		int offset = (page - 1) * limit;
		
		long userId = getUserId();
		
		List<UserRollInfo> userRollInfos = rollBookService.getUserRollInfoList(userId, rollInfoId, limit, offset);
		
		view.addObject("userRollInfos", userRollInfos);
		view.addObject("page", page);
		
		return view;
	}
}

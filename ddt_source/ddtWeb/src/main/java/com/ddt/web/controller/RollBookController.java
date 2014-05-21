/*
 * @(#)RollBookController.java 2014-4-24
 *
 */
package com.ddt.web.controller;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ddt.core.meta.RollBook;
import com.ddt.core.meta.RollBookInfo;
import com.ddt.core.meta.User;
import com.ddt.core.meta.UserRollInfo;
import com.ddt.core.service.RollBookService;
import com.ddt.core.utils.DateUtils;

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
		ModelAndView view = getBaseView("rollbook/main");
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 20);
		int offset = (page - 1) * limit;
		
		long userId = getUserId();
		
		List<RollBook> rollBooks = rollBookService.getRollBookList(userId, limit, offset);
		int count = rollBookService.getRollBookCount(userId);
		
		view.addObject("rollBooks", rollBooks);
		view.addObject("page", page);
		view.addObject("totalPage", (int) Math.round(count * 1.0 / limit));
		return view;
	}
	
	/**
	 * 查看点名册名单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("userlist")
	public ModelAndView userList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = getBaseView("rollbook/userlist");
		
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
	 * 查看点名册名单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("view")
	public ModelAndView view(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = getBaseView("rollbook/view");
		
		long rollBookId = ServletRequestUtils.getLongParameter(request, "roll_book_id", 0);
		
		long userId = getUserId();
		
		RollBook rollBook = rollBookService.getRollBookById(rollBookId, userId);
		
		view.addObject("rollBook", rollBook);
		
		return view;
	}
	
	/**
	 * 保存点名册
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("save")
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
		
		long rid = ServletRequestUtils.getLongParameter(request, "id", 0);
		String name = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "name", ""));
		String validStartDate = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "validStartDate", ""));
		String validEndDate = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "validEndDate", ""));
		int userCount = ServletRequestUtils.getIntParameter(request, "userCount", 0);
		
		long userId = getUserId();
		
		boolean isAdd = false;
		
		RollBook rollBook = rollBookService.getRollBookById(rid, userId);
		if (rollBook == null) {
			rollBook = new RollBook();
			isAdd = true;
		}
		
		rollBook.setName(name);
		rollBook.setUserCount(userCount);
		rollBook.setUserId(userId);
		rollBook.setRollStartTime(DateUtils.parseStringToDate(DateUtils.DATE_FORMAT, validStartDate));
		rollBook.setRollEndTime(DateUtils.parseStringToDate(DateUtils.DATE_FORMAT, validEndDate));
		
		if (isAdd) {
			rollBookService.addRollBook(rollBook);
		} else {
			rollBookService.updateRollBook(rollBook);
		}
		
		return new ModelAndView(new RedirectView("/rollbook/list"));
	}
	
	/**
	 * 删除点名册
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("del")
	public ModelAndView del(HttpServletRequest request, HttpServletResponse response) {
		
		long rid = ServletRequestUtils.getLongParameter(request, "rid", 0);
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		
		rollBookService.deleteRollBook(rid);
		
		return new ModelAndView(new RedirectView("/rollbook/list?page=" + page));
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
		
		List<RollBookInfo> rollInfos = rollBookService.getRollInfoList(userId, rollBookId, limit, offset);
		
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
	
	/**
	 * 模板下载
	 */
	@RequestMapping("template")
	public ResponseEntity<byte[]> template(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			File f = new File(RollBookController.class.getClassLoader().getResource("template.xlsx").toURI());
			
			HttpHeaders headers = new HttpHeaders();    
	        headers.setContentDispositionFormData("attachment", "template.xlsx");   
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
	        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(f),    
	                                          headers, HttpStatus.CREATED);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}

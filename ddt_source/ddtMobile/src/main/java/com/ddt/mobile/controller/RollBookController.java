/*
 * @(#)RollBookController.java 2014-6-19
 *
 */
package com.ddt.mobile.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ddt.core.location.Location;
import com.ddt.core.meta.RollBook;
import com.ddt.core.meta.RollBookInfo;
import com.ddt.core.meta.User;
import com.ddt.core.meta.UserRollInfo;
import com.ddt.core.service.LocationTool;
import com.ddt.core.service.RollBookInfoService;
import com.ddt.core.service.RollBookService;
import com.ddt.core.service.UserRollInfoService;
import com.ddt.core.utils.DateUtils;
import com.ddt.mobile.utils.RamdomUtils;

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
	
	@Autowired
	private RollBookInfoService rollBookInfoService;
	
	@Autowired
	private UserRollInfoService userRollInfoService;
	
	/**
	 * 获取点名册
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/myrollbook")
	public ModelAndView rollBookList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("roll.list");
		
		String wx = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "wx", ""));
		User user = getUser(request);
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int limit = 20;
		int offset = (page - 1) * limit;
		
		List<RollBook> list = rollBookService.getRollBookList(user.getId(), "", limit, offset);
		int count = rollBookService.getRollBookCount(user.getId(), "");
		
		view.addObject("rollBooks", list);
		view.addObject("wx", wx);
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
		ModelAndView view = new ModelAndView("rolled");
		
 		String wx = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "wx", ""));
		User user = getUser(request);
		
		long infoId = ServletRequestUtils.getLongParameter(request, "infoId", 0);
		
		RollBookInfo info = rollBookInfoService.getRollInfoById(infoId);
		
		if (info != null) {
			RollBook book = rollBookService.getRollBookById(info.getRollBookId(), info.getUserId());
			view.addObject("book", book);
		}
		
		//user表没有绑定
		User u = userService.getUserByWxNumber(user.getWxName());
		
		if (u == null) {
			view.addObject("bind", 1);
		} else {
			view.addObject("bind", 0);
		}
		
		view.addObject("info", info);
		view.addObject("userId", user.getId());
		view.addObject("wx", wx);
		
		return view;
	}
	
	/**
	 * 绑定
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bind")
	public ModelAndView bind(HttpServletRequest request, HttpServletResponse response) {
		
		User user = getUser(request);
		long infoId = ServletRequestUtils.getLongParameter(request, "infoId", 0);
		User u = userService.getUserByNameAndInfoId(user.getUserName(), infoId);
		
		if (u == null) {
			return null;
		}
		
		u.setMobile(user.getMobile());
		u.setWxName(user.getWxName());
		
		userService.updateUser(u);
		
		return userRolled(request, response);
	}
	
	/**
	 * 我被点名
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/userRolled")
	public ModelAndView userRolled(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("info");
		
		long infoId = ServletRequestUtils.getLongParameter(request, "infoId", 0);
		String wx = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "wx", ""));
		
		User user = userService.getUserByWxNumber(wx);
		long userId = user.getId();
		
		RollBookInfo info = rollBookInfoService.getRollInfoById(infoId);
		
		long current = System.currentTimeMillis();
		
		if (info != null) {
			if (info.getRollEndTime() != null && current > info.getRollEndTime().getTime()) {
				view.addObject("result", "本次点名已经结束");
			}
			if (current < info.getRollStartTime().getTime()) {
				view.addObject("result", "本次点名未开始");
			}
			
			UserRollInfo userRollInfo = userRollInfoService.getUserRollInfoByIds(infoId, userId);
			if (userRollInfo != null) {
				if (userRollInfo.getRollTime() != null) {
					view.addObject("result", "您已在" + DateUtils.parseDateToString(DateUtils.DATE_TIME_FORMAT, userRollInfo.getRollTime()) + "参与本次点名，不能重复点名");
				} else {
					userRollInfo.setRollTime(new Date());
					//计算经纬度
					String ip = getIpAddr(request);
					Location location = LocationTool.getLocation(ip);
					
					if (location != null) {
						userRollInfo.setX(location.getContentX());
						userRollInfo.setY(location.getContentY());
						double distance = LocationTool.getDistance(Double.valueOf(info.getY()), Double.valueOf(info.getX()), Double.valueOf(userRollInfo.getY()), Double.valueOf(userRollInfo.getX()));
						userRollInfo.setDistance(distance);
					}
					
					userRollInfoService.updateUserRollInfo(userRollInfo);
					view.addObject("result", "完成点名");
				}
			} else {
				userRollInfo = new UserRollInfo();
				userRollInfo.setRollBookInfoId(infoId);
				userRollInfo.setUserId(userId);
				userRollInfo.setRollTime(new Date());
				
				//计算经纬度
				String ip = getIpAddr(request);
				Location location = LocationTool.getLocation(ip);
				
				if (location != null) {
					userRollInfo.setX(location.getContentX());
					userRollInfo.setY(location.getContentY());
					double distance = LocationTool.getDistance(Double.valueOf(info.getY()), Double.valueOf(info.getX()), Double.valueOf(userRollInfo.getY()), Double.valueOf(userRollInfo.getX()));
					userRollInfo.setDistance(distance);
				}
				
				userRollInfoService.addUserRollInfo(userRollInfo);
				view.addObject("result", "完成点名");
			}
			
		} else {
			view.addObject("result", "点名册不存在");
		}
		
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
		ModelAndView view = new ModelAndView("start.end");
		view.addObject("flag", 0);
		
		long rid = ServletRequestUtils.getLongParameter(request, "rid", 0);
		User user = getUser(request);
		
		RollBook book = rollBookService.getRollBookById(rid, user.getId());
		
		if (book == null) {
			return null;
		}
		
		long current = System.currentTimeMillis();
		
		if (current < book.getValidStartTime().getTime()) {
			view.addObject("msg", "点名册尚未开始");
			return view;
		}
		
		if (current > book.getValidEndTime().getTime()) {
			view.addObject("msg", "点名册已结束点名");
			return view;
		}
		
		RollBookInfo info = new RollBookInfo();
		info.setRollBookId(book.getId());
		info.setRollStartTime(new Date());
		info.setRollUserCount(book.getUserCount());
		info.setUserId(user.getId());
		info.setRollCode(RamdomUtils.generateRamdomCode());
		
		//计算经纬度
		String ip = getIpAddr(request);
		Location location = LocationTool.getLocation(ip);
		
		if (location != null) {
			info.setX(location.getContentX());
			info.setY(location.getContentY());
		}
		
		rollBookInfoService.addRollBookInfo(info);
		
		List<User> users = userService.getRollBookUserList(rid, Integer.MAX_VALUE, 0);
		if (CollectionUtils.isNotEmpty(users)) {
			for (User u : users) {
				UserRollInfo userRollInfo = new UserRollInfo();
				userRollInfo.setRollBookInfoId(info.getId());
				userRollInfo.setUserId(u.getId());
				userRollInfoService.addUserRollInfo(userRollInfo);
			}
		}
		
		view.addObject("info", info);
		view.addObject("book", book);
		
		return view;
	}
	
	/**
	 * 点名情况
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("rolllist")
	public ModelAndView rollList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("roll.info.list");
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 20);
		int offset = (page - 1) * limit;
		String wx = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "wx", ""));
		
		long rid = ServletRequestUtils.getLongParameter(request, "rid", 0);
		User user = getUser(request);
		
		List<RollBook> rollBooks = rollBookService.getRollInfoList(user.getId(), rid, limit, offset);
		
		view.addObject("rollBooks", rollBooks);
		view.addObject("page", page);
		view.addObject("wx", wx);
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
		ModelAndView view = new ModelAndView("user.list");
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 1);
		int limit = ServletRequestUtils.getIntParameter(request, "limit", 20);
		long rollBookId = ServletRequestUtils.getLongParameter(request, "rid", 0);
		int offset = (page - 1) * limit;
		
		User user = getUser(request);
		
		List<UserRollInfo> userRollInfos = rollBookService.getUserRollInfoList(user.getId(), rollBookId, limit, offset);
		int count = rollBookService.getUserRollInfoCount(user.getId(), rollBookId, false);
		int unrolledCount = rollBookService.getUserRollInfoCount(user.getId(), rollBookId, true);
		
		view.addObject("users", userRollInfos);
		view.addObject("page", page);
		view.addObject("count", count);
		view.addObject("unrolledCount", unrolledCount);
		
		return view;
	}
	
	/**
	 * 查看点名册名单
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("remark")
	public ModelAndView remark(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = new ModelAndView("info");
		
		int userId = ServletRequestUtils.getIntParameter(request, "uid", 0);
		long infoId = ServletRequestUtils.getLongParameter(request, "rid", 0);
		String reason = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "reason", ""));
		
		if (StringUtils.isBlank(reason)) {
			view.addObject("result", "请选择原因！");
			return view;
		}
		
		UserRollInfo userRollInfo = userRollInfoService.getUserRollInfoByIds(infoId, userId);
		if (userRollInfo == null) {
			view.addObject("result", "请选择原因！");
			return view;
		}
		
		userRollInfo.setInfo(reason);
		userRollInfoService.updateUserRollInfo(userRollInfo);
		view.addObject("result", "备注成功");
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
		ModelAndView view = new ModelAndView("start.end");
		view.addObject("flag", 1);
		User user = getUser(request);
		long rid = ServletRequestUtils.getLongParameter(request, "rid", 0);
		
		RollBookInfo info = rollBookInfoService.getRollInfoById(rid);
		if (info == null) {
			return null;
		}
		
		RollBook book = rollBookService.getRollBookById(info.getRollBookId(), user.getId());
		
		if (book == null) {
			return null;
		}
		
		long current = System.currentTimeMillis();
		
		if (current < book.getValidStartTime().getTime()) {
			view.addObject("msg", "点名册尚未开始");
			return view;
		}
		
		if (current > book.getValidEndTime().getTime()) {
			view.addObject("msg", "点名册已结束点名");
			return view;
		}
		
		info.setRollEndTime(new Date());
		info.setRollCode("");
		rollBookInfoService.updateRollBookInfo(info);
		
		view.addObject("info", info);
		view.addObject("book", book);
		
		return view;
	}
}

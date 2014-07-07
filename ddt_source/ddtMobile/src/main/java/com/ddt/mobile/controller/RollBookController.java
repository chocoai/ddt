/*
 * @(#)RollBookController.java 2014-6-19
 *
 */
package com.ddt.mobile.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ddt.core.meta.RollBook;
import com.ddt.core.meta.RollBookInfo;
import com.ddt.core.meta.User;
import com.ddt.core.meta.UserRollInfo;
import com.ddt.core.service.RollBookInfoService;
import com.ddt.core.service.RollBookService;
import com.ddt.core.service.UserRollInfoService;
import com.ddt.core.utils.DateUtils;
import com.ddt.mobile.location.Location;
import com.ddt.mobile.tool.LocationTool;
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
		ModelAndView view = new ModelAndView("rolled");
		
		User user = getUser(request);
		
		long infoId = ServletRequestUtils.getLongParameter(request, "infoId", 0);
		
		RollBookInfo info = rollBookInfoService.getRollInfoById(infoId);
		
		if (info != null) {
			RollBook book = rollBookService.getRollBookById(info.getRollBookId(), info.getId());
			view.addObject("book", book);
		}
		
		view.addObject("info", info);
		view.addObject("userId", user.getId());
		
		return view;
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
		long userId = ServletRequestUtils.getLongParameter(request, "userId", 0);
		
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
				view.addObject("result", "您已在" + DateUtils.parseDateToString(DateUtils.DATE_TIME_FORMAT, userRollInfo.getRollTime()) + "参与本次点名，不能重复点名");
			} else {
				userRollInfo = new UserRollInfo();
				userRollInfo.setRollBookInfoId(infoId);
				userRollInfo.setUserId(userId);
				userRollInfo.setRollTime(new Date());
				
				//计算经纬度
				String ip = getIpAddr(request);
				Location location = LocationTool.getLocation(ip);
				
				if (location != null) {
					userRollInfo.setX(Double.valueOf(location.getContentX()));
					userRollInfo.setY(Double.valueOf(location.getContentY()));
					double distance = LocationTool.getDistance(info.getX(), info.getY(), userRollInfo.getX(), userRollInfo.getY());
					userRollInfo.setDistance(distance);
				}
				
				userRollInfoService.addUserRollInfo(userRollInfo);
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
			info.setX(Double.valueOf(location.getContentX()));
			info.setY(Double.valueOf(location.getContentY()));
		}
		
		rollBookInfoService.addRollBookInfo(info);
		
		view.addObject("info", info);
		view.addObject("book", book);
		
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
		
		RollBookInfo info = rollBookInfoService.getLatestRollInfoByRid(rid, user.getId());
		if (info == null) {
			return null;
		}
		
		info.setRollEndTime(new Date());
		info.setRollCode("");
		rollBookInfoService.updateRollBookInfo(info);
		
		view.addObject("info", info);
		view.addObject("book", book);
		
		return view;
	}
}

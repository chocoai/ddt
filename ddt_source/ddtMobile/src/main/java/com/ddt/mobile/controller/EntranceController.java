/*
 * @(#)EntranceController.java
 *
 */
package com.ddt.mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ddt.core.meta.User;
import com.ddt.core.service.RollBookService;
import com.ddt.core.service.UserService;
import com.ddt.core.utils.EncryptUtils;
import com.ddt.mobile.enums.EventType;
import com.ddt.mobile.enums.MenuKey;
import com.ddt.mobile.enums.MsgType;
import com.ddt.mobile.msg.TextMsg;
import com.ddt.mobile.utils.DocumentUtils;

/**
 * EntranceController.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @since      1.0
 */
@Controller
public class EntranceController {
	private static Logger log = Logger.getLogger(EntranceController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RollBookService rollBookService;
	
	@Value("${wx.token}")
	private String token;
	
	/**
	 * 微信接入信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/cgi-bin/platform")
	public ModelAndView entrance(HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();
		//get请求验证微信介入是否成功
		if ("get".equalsIgnoreCase(method)) {
			doGet(request, response);
		} else if ("post".equalsIgnoreCase(method)) {
			return doPost(request, response);
		}
		return null;
	}

	private ModelAndView doPost(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		//post请求普通微信用户发送信息
		Map<String, String> map = DocumentUtils.parseXml(request);
		//解析xml获取消息类型
		String toUserName = map.get("ToUserName");
		String fromUserName = map.get("FromUserName");
		String msgType = map.get("MsgType");
		String eventType = map.get("Event");
		String eventKey = map.get("EventKey");
		
		User user = addUserIfNotExists(fromUserName);
		
		if (MsgType.EVENT.getValue().equals(msgType)) {
			if (EventType.UNSUBSCRIBE.getType().equalsIgnoreCase(eventType)) {
				return null;
			} else if (EventType.SUBSCRIBE.getType().equalsIgnoreCase(eventType)) {
				response.setCharacterEncoding("UTF-8"); 
		        response.setContentType("text/xml");
				view = new ModelAndView("msg/reply.text");
				TextMsg textMsg = new TextMsg();
				textMsg.setContent("欢迎使用爱点名！");
				textMsg.setCreateTime(System.currentTimeMillis());
				textMsg.setFromUser(toUserName);
				textMsg.setMsgType(MsgType.TEXT);
				textMsg.setToUser(fromUserName);
				
				view.addObject("textMsg", textMsg);
				
			} else if (EventType.SCAN.getType().equalsIgnoreCase(eventType)) {
				view = new ModelAndView("msg/reply.text");
			} else if (EventType.CLICK.getType().equalsIgnoreCase(eventType)) {
				//验证是否注册
				if (!checkRegist(fromUserName)) {
					//没有注册 返回注册页面
					response.setCharacterEncoding("UTF-8"); 
			        response.setContentType("text/xml");
					view = new ModelAndView("msg/reply.text");
					TextMsg textMsg = new TextMsg();
					textMsg.setContent("请按照以下格式输入绑定用户信息：姓名+手机号码");
					textMsg.setCreateTime(System.currentTimeMillis());
					textMsg.setFromUser(toUserName);
					textMsg.setMsgType(MsgType.TEXT);
					textMsg.setToUser(fromUserName);
					
					view.addObject("textMsg", textMsg);
					return view;
				}
				
				if (MenuKey.KEY_I_CLICK.getValue().equalsIgnoreCase(eventKey)) {
					return new ModelAndView(new RedirectView("/rollbook/myrollbook?wx=" + fromUserName));
				} else if (MenuKey.KEY_I_CLICKED.getValue().equalsIgnoreCase(eventKey)) {
					return new ModelAndView(new RedirectView("/rollbook/rolled?wx=" + fromUserName));
				} else if (MenuKey.KEY_SCORE_MALL.getValue().equalsIgnoreCase(eventKey)) {
					return new ModelAndView(new RedirectView("/score/mall?wx=" + fromUserName));
				} else if (MenuKey.KEY_SCORE_QUERY.getValue().equalsIgnoreCase(eventKey)) {
					return new ModelAndView(new RedirectView("/score/query?wx=" + fromUserName));
				} else if (MenuKey.KEY_SIGN.getValue().equalsIgnoreCase(eventKey)) {
					return new ModelAndView(new RedirectView("/score/sign?wx=" + fromUserName));
				}
			} else if (EventType.LOCATION.getType().equalsIgnoreCase(eventType)) {
				view = new ModelAndView("msg/reply.text");
			}
		} else if (MsgType.IMAGE.getValue().equals(msgType)) {
			view = new ModelAndView("msg/reply.text");
		} else if (MsgType.LINK.getValue().equals(msgType)) {
			view = new ModelAndView("msg/reply.text");
		} else if (MsgType.LOCATION.getValue().equals(msgType)) {
			view = new ModelAndView("msg/reply.text");
		} else if (MsgType.TEXT.getValue().equals(msgType)) {
			view = new ModelAndView("msg/reply.text");
			String content = map.get("Content");
			
			if (StringUtils.isBlank(content)) {
				return view;
			}
			String[] contentArray = content.split("+");
			if (contentArray == null || contentArray.length < 2) {
				return view;
			}
			
			String userName = null;
			String mobile = null;
			String recommendName = null;
			if (contentArray.length >= 2) {
				userName = contentArray[0];
				mobile = contentArray[1];
				if (contentArray.length == 3) {
					recommendName = contentArray[2];
				}
			}
			User u = userService.getUserByMobile(mobile);
			//用户不为空，手机号码已经被注册
			if (u != null) {
				TextMsg textMsg = new TextMsg();
				textMsg.setContent("此手机号码已被注册！");
				textMsg.setCreateTime(System.currentTimeMillis());
				textMsg.setFromUser(toUserName);
				textMsg.setMsgType(MsgType.TEXT);
				textMsg.setToUser(fromUserName);
				
				view.addObject("textMsg", textMsg);
				return view;
			}
			
			user.setUserName(userName);
			user.setMobile(mobile);
			userService.updateWxUser(user);
			
			User copyUser = userService.getUserByWithNullWx(userName);
			boolean isAdd = false;
			if (copyUser == null) {
				copyUser = new User();
				isAdd = true;
			}
			
			copyUser.setUserName(userName);
			copyUser.setMobile(mobile);
			copyUser.setPassword(mobile);
			copyUser.setWxName(fromUserName);
			
			if (isAdd) {
				userService.insertUser(copyUser);
			} else {
				userService.updateUser(copyUser);
			}
			
			if (StringUtils.isNotBlank(recommendName)) {
				//TODO add recommend info
			}
			
			TextMsg textMsg = new TextMsg();
			textMsg.setContent("注册成功，您可以访问www.idianming.com.cn登陆上传您的点名册，初始用户名和密码是您的注册手机号");
			textMsg.setCreateTime(System.currentTimeMillis());
			textMsg.setFromUser(toUserName);
			textMsg.setMsgType(MsgType.TEXT);
			textMsg.setToUser(fromUserName);
			
			view.addObject("textMsg", textMsg);
			
		} else if (MsgType.VIDEO.getValue().equals(msgType)) {
			view = new ModelAndView("msg/reply.text");
		} else if (MsgType.VOICE.getValue().equals(msgType)) {
			view = new ModelAndView("msg/reply.text");
		}
		return view;
	}

	private User addUserIfNotExists(String fromUserName) {
		User user = null;
		//如果用户不存在，新增用户
		if (StringUtils.isNotBlank(fromUserName)) {
			user = userService.getWxUserByName(fromUserName);
			if (user == null) {
				user = new User();
				user.setWxName(fromUserName);
				userService.insertWxUser(user);
			}
		}
		return user;
	}

	private void doGet(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			//获取参数信息
			String signature = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "signature", ""));
			String timestamp = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "timestamp", ""));
			String nonce = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "nonce", ""));
			String echostr = StringUtils.trim(ServletRequestUtils.getStringParameter(request, "echostr", ""));
			String[] valueArray = new String[]{token, timestamp, nonce};
			//字典序排列
			Arrays.sort(valueArray);
			StringBuffer sb = new StringBuffer();
			for (String value : valueArray) {
				sb.append(value);
			}
			
			String encryptValue = EncryptUtils.encrypt(sb.toString(), "sha1");
			if (StringUtils.equalsIgnoreCase(signature, encryptValue)) {
				pw.print(echostr);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}

	private boolean checkRegist(String fromUserName) {
		User user = userService.getWxUserByName(fromUserName);
		if (StringUtils.isBlank(user.getMobile())) {
			return false;
		}
		return true;
	}
}

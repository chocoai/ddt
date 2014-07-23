/*
 * @(#)EntranceController.java
 *
 */
package com.ddt.mobile.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.ddt.core.meta.RollBookInfo;
import com.ddt.core.meta.User;
import com.ddt.core.service.RollBookInfoService;
import com.ddt.core.service.RollBookService;
import com.ddt.core.service.UserService;
import com.ddt.core.utils.EncryptUtils;
import com.ddt.mobile.constants.ReplyConstants;
import com.ddt.mobile.enums.EventType;
import com.ddt.mobile.enums.MenuKey;
import com.ddt.mobile.enums.MsgType;
import com.ddt.mobile.msg.TextMsg;
import com.ddt.mobile.utils.DocumentUtils;
import com.ddt.mobile.utils.ReplyUtils;


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
	
	@Autowired
	private RollBookInfoService rollBookInfoService;
	
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
		ModelAndView view = new ModelAndView("msg/reply.text");;
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
		        String welcome = ReplyUtils.get(ReplyConstants.SUBSCRIBE);
				buildTextMsg(view, toUserName, fromUserName, welcome);
				
			} else if (EventType.SCAN.getType().equalsIgnoreCase(eventType)) {
				view = new ModelAndView("msg/reply.text");
			} else if (EventType.CLICK.getType().equalsIgnoreCase(eventType)) {
				//验证是否注册
				if (!checkRegist(fromUserName)) {
					//没有注册 返回注册页面
					response.setCharacterEncoding("UTF-8"); 
			        response.setContentType("text/xml");
			        String c = ReplyUtils.get(ReplyConstants.REGISTER_TIPS);
					return buildTextMsg(view, toUserName, fromUserName, c);
				}
				
				if (MenuKey.KEY_I_CLICK.getValue().equalsIgnoreCase(eventKey)) {
					String reply = "点击查看我的<a href=\"http://mobile.idianming.com.cn/rollbook/myrollbook?wx=" + fromUserName + "\">点名列表</a>";
					buildTextMsg(view, toUserName, fromUserName, reply);
				} else if (MenuKey.KEY_I_CLICKED.getValue().equalsIgnoreCase(eventKey)) {
					String reply = "回复code，获取指定点名册进行点名。";
					buildTextMsg(view, toUserName, fromUserName, reply);
				} else if (MenuKey.KEY_SCORE_MALL.getValue().equalsIgnoreCase(eventKey)) {
					String reply = "点击进入<a href=\"http://mobile.idianming.com.cn/score/mall?wx=" + fromUserName + "\">积分商城</a>";
					buildTextMsg(view, toUserName, fromUserName, reply);
				} else if (MenuKey.KEY_SCORE_QUERY.getValue().equalsIgnoreCase(eventKey)) {
					String reply = "点击查看<a href=\"http://mobile.idianming.com.cn/score/query?wx=" + fromUserName + "\">我的积分</a>";
					buildTextMsg(view, toUserName, fromUserName, reply);
				} else if (MenuKey.KEY_SIGN.getValue().equalsIgnoreCase(eventKey)) {
					String reply = "点击<a href=\"http://mobile.idianming.com.cn/score/sign?wx=" + fromUserName + "\">签到</a>";
					buildTextMsg(view, toUserName, fromUserName, reply);
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
			
			if (content.indexOf("+") > 0) {
				registerUser(view, user, content, fromUserName, toUserName);
			} else {
				RollBookInfo info = rollBookInfoService.getRollBookInfoByRandCode(content);
				if (info == null) {
					String text = "验证码不存在！";
					return buildTextMsg(view, toUserName, fromUserName, text);
				} else {
					String text = "点击<a href=\"http://mobile.idianming.com.cn/rollbook/rolled?wx=" + fromUserName + "&infoId=" + info.getId() + "\">这里</a>，开始我的点名之旅。";
					return buildTextMsg(view, toUserName, fromUserName, text);
				}
			}
			
		} else if (MsgType.VIDEO.getValue().equals(msgType)) {
			view = new ModelAndView("msg/reply.text");
		} else if (MsgType.VOICE.getValue().equals(msgType)) {
			view = new ModelAndView("msg/reply.text");
		}
		return view;
	}

	private ModelAndView buildTextMsg(ModelAndView view, String toUserName, String fromUserName, String text) {
		TextMsg textMsg = new TextMsg();
		textMsg.setContent(text);
		textMsg.setCreateTime(System.currentTimeMillis());
		textMsg.setFromUser(toUserName);
		textMsg.setMsgType(MsgType.TEXT);
		textMsg.setToUser(fromUserName);
		
		view.addObject("textMsg", textMsg);
		return view;
	}

	private void registerUser(ModelAndView view, User user, String content, String fromUserName, String toUserName) {
		String[] contentArray = content.split("\\+");
		if (contentArray == null || contentArray.length != 3) {
			return;
		}
		
		String userName = null;
		String mobile = null;
		String password = null;
		if (contentArray.length == 3) {
			userName = contentArray[0];
			mobile = contentArray[1];
			password = contentArray[2];
		} else {
			String text = "输入注册信息有误。";
			buildTextMsg(view, toUserName, fromUserName, text);
			return;
		}
		
		Pattern pattern = Pattern.compile("1[3,4,5,6,7,8,9]\\d{9}");
		Matcher matcher = pattern.matcher(mobile);
		if (!matcher.matches()) {
			String text = "手机号码格式无效！";
			buildTextMsg(view, toUserName, fromUserName, text);
			return;
		}
		
		User u = userService.getWxUserByMobile(mobile);
		//用户不为空，手机号码已经被注册
		if (u != null) {
			String text = "此手机号码已被注册！";
			buildTextMsg(view, toUserName, fromUserName, text);
			return;
		}
		
		user.setUserName(userName);
		user.setMobile(mobile);
		user.setPassword(password);
		userService.updateWxUser(user);
		
		String c = "注册成功，您可以访问<a href=\"www.idianming.com.cn\">www.idianming.com.cn</a>登陆上传您的点名册，用户名是您的注册手机号";
		buildTextMsg(view, toUserName, fromUserName, c);
		
	}

	private User addUserIfNotExists(String fromUserName) {
		User user = null;
		//如果用户不存在，新增用户
		if (StringUtils.isNotBlank(fromUserName)) {
			user = userService.getWxUserByWxNumber(fromUserName);
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
		User user = userService.getWxUserByWxNumber(fromUserName);
		if (StringUtils.isBlank(user.getMobile())) {
			return false;
		}
		return true;
	}
}

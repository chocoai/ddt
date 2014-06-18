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

import com.ddt.core.meta.User;
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
	
	@Value("${wx.token}")
	private String token;
	
	/**
	 * 微信接入信息
	 * @param request
	 * @param response
	 */
	@RequestMapping("/cgi-bin/platform")
	public ModelAndView entrance(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView view = null;
		String method = request.getMethod();
		//get请求验证微信介入是否成功
		if ("get".equalsIgnoreCase(method)) {
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
		} else if ("post".equalsIgnoreCase(method)) {
			//post请求普通微信用户发送信息
			Map<String, String> map = DocumentUtils.parseXml(request);
			//解析xml获取消息类型
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String eventType = map.get("Event");
			String eventKey = map.get("EventKey");
			
			//如果用户不存在，新增用户
			if (StringUtils.isNotBlank(fromUserName)) {
				User user = userService.getWxUserByName(fromUserName);
				if (user == null) {
					user = new User();
					user.setWxName(fromUserName);
					userService.insertWxUser(user);
				}
			}
			
			if (MsgType.EVENT.getValue().equals(msgType)) {
				if (EventType.UNSUBSCRIBE.getType().equalsIgnoreCase(eventType)) {
					return null;
				} else if (EventType.SUBSCRIBE.getType().equalsIgnoreCase(eventType)) {
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
					if (MenuKey.KEY_I_CLICK.getValue().equalsIgnoreCase(eventKey)) {
						
					} else if (MenuKey.KEY_I_CLICKED.getValue().equalsIgnoreCase(eventKey)) {
						
					} else if (MenuKey.KEY_SCORE_MALL.getValue().equalsIgnoreCase(eventKey)) {
						
					} else if (MenuKey.KEY_SCORE_QUERY.getValue().equalsIgnoreCase(eventKey)) {
						
					} else if (MenuKey.KEY_SIGN.getValue().equalsIgnoreCase(eventKey)) {
						
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
				
				
			} else if (MsgType.VIDEO.getValue().equals(msgType)) {
				view = new ModelAndView("msg/reply.text");
			} else if (MsgType.VOICE.getValue().equals(msgType)) {
				view = new ModelAndView("msg/reply.text");
			}
		}
		return view;
	}
}

/*
 * @(#)EntranceController.java
 *
 */
package com.ddt.mobile.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.ddt.core.meta.User;
import com.ddt.core.service.UserService;
import com.ddt.core.utils.DocumentUtils;
import com.ddt.core.utils.EncryptUtils;
import com.ddt.mobile.enums.EventType;
import com.ddt.mobile.enums.MsgType;
import com.ddt.mobile.msg.TextMsg;

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
			InputStream is = null;
			try {
				is = request.getInputStream();
				Document document = DocumentUtils.buildDocument(is);
				XPath xpath = DocumentUtils.buildXpath();
				//解析xml获取消息类型
				String toUserName = xpath.evaluate("/ToUserName", document);
				String fromUserName = xpath.evaluate("/FromUserName", document);
				String msgType = xpath.evaluate("/MsgType", document);
				String eventType = xpath.evaluate("/Event", document);
				
				//如果用户不存在，新增用户
				User user = userService.getUserByName(fromUserName);
				if (user == null) {
					user = new User();
					user.setWxName(fromUserName);
					userService.insertWxUser(user);
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
						view = new ModelAndView("msg/reply.text");
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
					String content = xpath.evaluate("/Content", document);
					
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
				
				
				buildResponseByMsgType(msgType, eventType, user);
				
			} catch (ParserConfigurationException e) {
				log.error(e.getMessage(), e);
			} catch (SAXException e) {
				log.error(e.getMessage(), e);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			} catch (XPathExpressionException e) {
				log.error(e.getMessage(), e);
			} finally {
				try {
					if (is != null) {
						is.close();
					}
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return view;
	}
	
	/**
	 * 根据设置的信息类型构建数据
	 * @param msgType
	 * @param eventType
	 * @param user
	 */
	private void buildResponseByMsgType(String msgType, String eventType, User user) {
		if (MsgType.EVENT.getValue().equals(msgType)) {
			if (EventType.SUBSCRIBE.getType().equalsIgnoreCase(eventType)) {
				
			} else if (EventType.SCAN.getType().equalsIgnoreCase(eventType)) {
				
			} else if (EventType.CLICK.getType().equalsIgnoreCase(eventType)) {
				
			} else if (EventType.LOCATION.getType().equalsIgnoreCase(eventType)) {
				
			}
		} else if (MsgType.IMAGE.getValue().equals(msgType)) {
			
		} else if (MsgType.LINK.getValue().equals(msgType)) {
			
		} else if (MsgType.LOCATION.getValue().equals(msgType)) {
			
		} else if (MsgType.TEXT.getValue().equals(msgType)) {
			
		} else if (MsgType.VIDEO.getValue().equals(msgType)) {
			
		} else if (MsgType.VOICE.getValue().equals(msgType)) {
			
		}
	}
}

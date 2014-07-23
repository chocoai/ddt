/*
 * @(#)MenuTimer.java 2014-5-26
 *
 */
package com.ddt.core.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import com.ddt.core.utils.FreeMarkerUtils;
import com.ddt.core.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * MenuTimer.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-5-26
 * @since      1.0
 */
@Component
public class MenuTool {
	
	private static String appid = "wxde00310a2120b7f8";
	
	private static String secret = "7e07c2795860b06a1edf34eeeab0b358";
	
	private static String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	
	private static String createUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	
	private static String getUrl = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
	
	private static String deleteUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";
	
	private static String broadcastUrl = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=";
	
	private static long expiredTime;
	
	private static String accessToken;
	
	public static void  createMenu() {
		getAccessToken();
		
		StringBuffer create = new StringBuffer();
		create.append(createUrl).append(accessToken);
		InputStream is = null;
		try {
			File file = ResourceUtils.getFile("classpath:menu.json");
			is = new FileInputStream(file);
			String menu = StreamUtils.copyToString(is, Charset.forName("UTF-8"));
			HttpUtils.postContent(create.toString(), menu);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getMenu() {
		getAccessToken();
		StringBuffer get = new StringBuffer();
		get.append(getUrl).append(accessToken);
		
		HttpUtils.getContent(get.toString());
	}
	
	public static void deleteMenu() {
		getAccessToken();
		StringBuffer delete = new StringBuffer();
		delete.append(deleteUrl).append(accessToken);
		
		HttpUtils.getContent(delete.toString());
	}
	
	public static void broadcastMsg(List<String> openIdList, String content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("openIdList", openIdList);
		map.put("content", content);
		String postData = FreeMarkerUtils.parse("broadcast.ftl", map);
		
		getAccessToken();
		StringBuffer broadcast = new StringBuffer();
		broadcast.append(broadcastUrl).append(accessToken);
		
		HttpUtils.postContent(broadcast.toString(), postData);
	}
	
	@SuppressWarnings("unchecked")
	private static void getAccessToken() {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isBlank(accessToken) || System.currentTimeMillis() > expiredTime) {
			sb.append(accessTokenUrl);
			sb.append("&appid=").append(appid);
			sb.append("&secret=").append(secret);
			
			String value = HttpUtils.getContent(sb.toString());
			Gson gson = new Gson();
			Map<String, Object> map = gson.fromJson(value, Map.class);
			accessToken = String.valueOf(map.get("access_token"));
			expiredTime = new BigDecimal(String.valueOf(map.get("expires_in"))).longValue() * 1000 + System.currentTimeMillis();
		}
	}
	
	public static void main(String[] args) {
		createMenu();
	}
	
	@Autowired
	public void setAppid(@Value("${wx.appid}") String appid) {
		if (StringUtils.isNotBlank(appid)) {
			MenuTool.appid = appid;
		}
	}
	
	@Autowired
	public void setSecret(@Value("${wx.secret}") String secret) {
		if (StringUtils.isNotBlank(secret)) {
			MenuTool.secret = secret;
		}
	}
	
	@Autowired
	public void setAccessTokenUrl(@Value("${access.token.url}") String accessTokenUrl) {
		if (StringUtils.isNotBlank(accessTokenUrl)) {
			MenuTool.accessTokenUrl = accessTokenUrl;
		}
	}
	
	@Autowired
	public void setCreateUrl(@Value("${wx.create.url}") String createUrl) {
		if (StringUtils.isNotBlank(createUrl)) {
			MenuTool.createUrl = createUrl;
		}
	}

	@Autowired
	public void setGetUrl(@Value("${wx.get.url}") String getUrl) {
		if (StringUtils.isNotBlank(getUrl)) {
			MenuTool.getUrl = getUrl;
		}
	}
	
	@Autowired
	public void setDeleteUrl(@Value("${wx.delete.url}") String deleteUrl) {
		if (StringUtils.isNotBlank(deleteUrl)) {
			MenuTool.deleteUrl = deleteUrl;
		}
	}
	
	@Autowired
	public void setBroadcastUrl(@Value("${wx.broadcast.url}") String broadcastUrl) {
		if (StringUtils.isNotBlank(broadcastUrl)) {
			MenuTool.broadcastUrl = broadcastUrl;
		}
	}
}

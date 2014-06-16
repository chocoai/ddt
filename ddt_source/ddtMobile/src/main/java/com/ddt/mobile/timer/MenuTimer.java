/*
 * @(#)MenuTimer.java 2014-5-26
 *
 */
package com.ddt.mobile.timer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import com.ddt.mobile.utils.HttpUtils;
import com.google.gson.Gson;

/**
 * MenuTimer.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-5-26
 * @since      1.0
 */
@Component
public class MenuTimer {
	
	@Value("${wx.appid}")
	private String appid;
	
	@Value("${wx.secret}")
	private String secret;
	
	@Value("${access.token.url}")
	private String accessTokenUrl;
	
	@Value("${wx.create.url}")
	private String createUrl;
	
	@Value("${wx.get.url}")
	private String getUrl;
	
	@Value("${wx.delete.url}")
	private String deleteUrl;
	
	private static long expiredTime;
	
	private static String accessToken;
	
	@Scheduled(cron="0 0 * * * ?")
	public void createMenu() {
		getAccessToken();
		StringBuffer get = new StringBuffer();
		get.append(getUrl).append(accessToken);
		
		String value = HttpUtils.getContent(get.toString());
		if (!StringUtils.isBlank(value)) {
			return;
		}
		
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
	
	public void deleteMenu() {
		getAccessToken();
		StringBuffer delete = new StringBuffer();
		delete.append(deleteUrl).append(accessToken);
		
		HttpUtils.getContent(delete.toString());
	}
	
	@SuppressWarnings("unchecked")
	private void getAccessToken() {
		StringBuffer sb = new StringBuffer();
		if (StringUtils.isBlank(accessToken) || System.currentTimeMillis() > expiredTime) {
			sb.append(accessTokenUrl);
			sb.append("&appid=").append(appid);
			sb.append("&secret=").append(secret);
			
			String value = HttpUtils.getContent(sb.toString());
			Gson gson = new Gson();
			Map<String, Object> map = gson.fromJson(value, Map.class);
			accessToken = String.valueOf(map.get("access_token"));
			expiredTime = ((long) map.get("expires_in")) * 1000 + System.currentTimeMillis();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String value = "{\"access_token\":\"ACCESS_TOKEN\",\"expires_in\":7200}";
		Gson gson = new Gson();
		Map<String, Object> map = gson.fromJson(value, Map.class);
		String accessToken = String.valueOf(map.get("access_token"));
		System.out.println(accessToken);
	}
}

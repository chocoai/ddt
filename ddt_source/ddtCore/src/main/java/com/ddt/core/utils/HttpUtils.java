/*
 * @(#)HttpUtils.java 2014-5-26
 *
 */
package com.ddt.core.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

/**
 * HttpUtils.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-5-26
 * @since      1.0
 */
public class HttpUtils {
	
	public static String getContent(String uri) {
		try {
			HttpClient client = new DefaultHttpClient();
		
			HttpGet get = new HttpGet(uri);
		
			HttpResponse response = client.execute(get);
			
			StatusLine line = response.getStatusLine();
			
			if (line.getStatusCode() == HttpStatus.SC_OK) {
				String value = EntityUtils.toString(response.getEntity());
				return value;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String postContent(String uri, String value) {
		try {
			HttpClient client = new DefaultHttpClient();
			client.getParams().setParameter(HttpProtocolParams.HTTP_CONTENT_CHARSET,"UTF-8"); 
			
			HttpPost post = new HttpPost(uri);
			post.getParams().setParameter("charset", "UTF-8");
			post.setEntity(new StringEntity(value));
			HttpResponse response = client.execute(post);
			
			StatusLine line = response.getStatusLine();
			
			if (line.getStatusCode() == HttpStatus.SC_OK) {
				String v = EntityUtils.toString(response.getEntity());
				return v;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static String postContent(String uri, Map<String, String> params) {
		try {
			HttpClient client = new DefaultHttpClient();
		
			HttpPost post = new HttpPost(uri);
			
			List<NameValuePair> parameters = new ArrayList<>();
			
			if (params != null && params.size() > 0) {
				
				for (Entry<String, String> entry : params.entrySet()) {
					NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
					parameters.add(pair);
				}
				
				post.setEntity(new UrlEncodedFormEntity(parameters));
			}
			
			
			
			HttpResponse response = client.execute(post);
			
			StatusLine line = response.getStatusLine();
			
			if (line.getStatusCode() == HttpStatus.SC_OK) {
			
				return EntityUtils.toString(response.getEntity());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}

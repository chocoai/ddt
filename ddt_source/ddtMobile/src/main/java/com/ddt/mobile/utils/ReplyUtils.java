/*
 * @(#)ReplyUtils.java 2014-7-18
 *
 */
package com.ddt.mobile.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.ddt.mobile.constants.ReplyConstants;


/**
 * ReplyUtils.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-7-18
 * @since      1.0
 */
public class ReplyUtils {
	
	private static long lastModified = 0;
	
	private static Map<String, String> contents = new HashMap<>();
	
	public static void init() {
		try {
			File f = new File(ReplyUtils.class.getClassLoader().getResource("reply.properties").toURI());
			long modified = f.lastModified();
			if (modified > lastModified) {
				lastModified = modified;
				reload(f);
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	private static void reload(File f) {
		InputStream is = null;
		try {
			is = new FileInputStream(f);
			Properties properties = new Properties();
			properties.load(is);
			contents.put(ReplyConstants.SUBSCRIBE, properties.getProperty("subscribe.reply"));
			contents.put(ReplyConstants.REGISTER_TIPS, properties.getProperty("register.tips"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String get(String key) {
		init();
		return contents.get(key);
	}
	
}

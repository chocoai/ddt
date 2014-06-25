/*
 * @(#)RamdomUtils.java 2014-6-24
 *
 */
package com.ddt.mobile.utils;

/**
 * RamdomUtils.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-6-24
 * @since      1.0
 */
public class RamdomUtils {
	/**
	 * 生成随机6位数字验证码
	 * 
	 * @return
	 */
	public static String generateRamdomCode() {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 6; i++) {
			int value = (int) (Math.random() * 10);
			sb.append(value);
		}
		return sb.toString();
	}
}

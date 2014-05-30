/*
 * @(#)MenuKey.java 2014-5-26
 *
 */
package com.ddt.mobile.enums;

/**
 * MenuKey.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-5-26
 * @since      1.0
 */
public enum MenuKey {
	
	KEY_I_CLICK("KEY_I_CLICK"),KEY_I_CLICKED("KEY_I_CLICKED"),KEY_SCORE_QUERY("KEY_SCORE_QUERY"),KEY_SCORE_MALL("KEY_SCORE_MALL"),KEY_SIGN("KEY_SIGN");
	
private String value;
	
	MenuKey(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}

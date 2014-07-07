/*
 * @(#)StatusCode.java 2013-10-26
 *
 * Copyright 2012 ibookstar. All rights reserved.
 */
package com.ddt.core.constants;

/**
 * StatusCode.java
 * 
 * @author <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version 1.0 2013-10-26
 * @since 1.0
 */
public interface StatusCode {
	
	int USER_IS_INACTIVE = 0;
	
	int OK = 1;
	
	int USER_NAME_NOT_EXISTS = -1;
	
	int PASSWORD_ERROR = -2;
	
	int VALID_CODE_ERROR = -3;
	
	int USER_EXISTS = -4;
	
	int REGISTER_ERROR = -5;
	
	int NON_INACTIVE_USER = -6;
	
	int SEND_EMAIL_ERROR = -7;
	
	int OPERATE_ERROR = -8;

	int NON_SERVICE_PROVIDER = -9;

	int PWD_NOT_MATCH = -10;
	
	int FILE_KEY_IS_ERROR = -11;
	
	int NULL_NAME = -12;
	
	int NAME_EXISTS = -13;
	
	int ROLL_BOOK_NOT_EXISTS = -14;
}

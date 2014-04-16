/*
 * @(#)JdbcContextHolder.java 2014-03-17
 *
 * Copyright 2012 ibookstar. All rights reserved.
 */
package com.ddt.core.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * JdbcContextHolder.java
 * 
 * @author <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version 1.0 2014-03-17
 * @since 1.0
 */
@Component
public class JdbcContextHolder {
	private static final ThreadLocal<String> datasourceThreadLocal = new ThreadLocal<String>();
	
	private static final String MASTER = "master";
	
	private static final String SLAVE = "slave";
	
	private static int slaveCount;
	
	public static void setJdbcType(String jdbcType) {
		datasourceThreadLocal.set(jdbcType);
	}
	
	public static void setSlave() {
		if (slaveCount > 1) {
			int footer = (int) (Math.random() * slaveCount + 1);
			setJdbcType(SLAVE + footer);
		} else {
			setJdbcType(SLAVE);
		}
	}
	
	public static void setMaster() {
		setJdbcType(MASTER);
	}
	
	public static String getJdbcType() {
		return datasourceThreadLocal.get();
	}
	
	public static void clearType() {
		datasourceThreadLocal.remove();
	}
	
	@Autowired
	public void setSlaveCount(@Value("${db.slave.count}") int slaveCount) {
		JdbcContextHolder.slaveCount = slaveCount;
	}
	
	
}

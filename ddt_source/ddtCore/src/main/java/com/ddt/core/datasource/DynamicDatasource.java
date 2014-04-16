/*
 * @(#)DynamicDatasource.java 2014-03-17
 *
 * Copyright 2012 ibookstar. All rights reserved.
 */
package com.ddt.core.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.ddt.core.aop.JdbcContextHolder;

/**
 * DynamicDatasource.java
 * 
 * @author <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version 1.0 2014-03-17
 * @since 1.0
 */
public class DynamicDatasource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return JdbcContextHolder.getJdbcType();
	}
}

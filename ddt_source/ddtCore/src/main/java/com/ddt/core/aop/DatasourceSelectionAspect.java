/*
 * @(#)DatasourceSelectionAspect.java 2014-03-17
 *
 * Copyright 2013 mircobuy, Inc. All rights reserved.
 */
package com.ddt.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * DatasourceSelectionAspect.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-03-17
 * @since      1.0
 */
@Component
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
public class DatasourceSelectionAspect {
	
	/**
	 * order必须小于事物的值
	 * @param joinpoint
	 * @throws Throwable 
	 */
	@Around("execution(* com.ibookstar..*.service..*.*(..))")
	public Object changeDatasource(ProceedingJoinPoint joinpoint) throws Throwable {
		String method = joinpoint.getSignature().getName();
		Object[] args = joinpoint.getArgs();
		if (method.startsWith("get") || method.startsWith("select") || method.startsWith("query")
				|| method.startsWith("find")) {
			JdbcContextHolder.setSlave();
		} else if (method.startsWith("insert") || method.startsWith("delete") || method.startsWith("update")
				|| method.startsWith("add") || method.startsWith("save") || method.startsWith("remove")) {
			JdbcContextHolder.setMaster();
		}
		Object obj = null;
		if (args == null || args.length == 0) {
			obj = joinpoint.proceed();
		} else {
			obj = joinpoint.proceed(args); 
		}
		JdbcContextHolder.clearType();
		return obj;
	}
}

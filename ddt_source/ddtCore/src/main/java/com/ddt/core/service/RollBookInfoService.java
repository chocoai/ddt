/*
 * @(#)RollBookInfoService.java 2014-6-24
 *
 */
package com.ddt.core.service;

import com.ddt.core.meta.RollBookInfo;

/**
 * RollBookInfoService.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-6-24
 * @since      1.0
 */
public interface RollBookInfoService {
	
	void addRollBookInfo(RollBookInfo rollBookInfo);

	RollBookInfo getRollInfoById(long rInfoId, long userId);
	
	boolean deleteRollBookInfo(long rid, long userId);

	void updateRollBookInfo(RollBookInfo info);
}

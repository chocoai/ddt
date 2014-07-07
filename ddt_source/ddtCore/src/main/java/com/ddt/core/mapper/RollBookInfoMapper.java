/*
 * @(#)RollInfoMapper.java 2014-4-19
 *
 */
package com.ddt.core.mapper;

import java.util.List;
import java.util.Map;

import com.ddt.core.meta.RollBookInfo;

/**
 * RollInfoMapper.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-19
 * @since      1.0
 */
public interface RollBookInfoMapper {
	
	void addRollBookInfo(RollBookInfo rollBookInfo);

	RollBookInfo getRollInfoById(Map<String, Object> params);
	
	Integer deleteRollBookInfo(Map<String, Object> params);

	void updateRollBookInfo(RollBookInfo info);

	List<RollBookInfo> getRollBookInfosByUserId(Map<String, Object> params);

	RollBookInfo getLatestRollInfoByRid(Map<String, Object> params);

	RollBookInfo getRollBookInfoByRandCode(Map<String, Object> params);
}

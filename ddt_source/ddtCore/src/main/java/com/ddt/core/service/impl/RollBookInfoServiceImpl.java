/*
 * @(#)RollBookInfoServiceImpl.java 2014-6-24
 *
 */
package com.ddt.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddt.core.mapper.RollBookInfoMapper;
import com.ddt.core.meta.RollBookInfo;
import com.ddt.core.service.RollBookInfoService;

/**
 * RollBookInfoServiceImpl.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-6-24
 * @since      1.0
 */
@Service
public class RollBookInfoServiceImpl implements RollBookInfoService {
	
	@Autowired
	private RollBookInfoMapper rollBookInfoMapper;
	
	@Override
	public void addRollBookInfo(RollBookInfo rollBookInfo) {
		rollBookInfoMapper.addRollBookInfo(rollBookInfo);
	}

	@Override
	public RollBookInfo getRollInfoById(long rInfoId) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("rInfoId", rInfoId);
		
		return rollBookInfoMapper.getRollInfoById(params);
	}
	
	@Override
	public boolean deleteRollBookInfo(long rid, long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("rollBookId", rid);
		params.put("userId", userId);
		return rollBookInfoMapper.deleteRollBookInfo(params) > 0;
	}

	@Override
	public void updateRollBookInfo(RollBookInfo info) {
		rollBookInfoMapper.updateRollBookInfo(info);
	}

	@Override
	public List<RollBookInfo> getRollBookInfosByUserId(long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		return rollBookInfoMapper.getRollBookInfosByUserId(params);
	}

	@Override
	public RollBookInfo getLatestRollInfoByRid(long rid, long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("rid", rid);
		params.put("userId", userId);
		return rollBookInfoMapper.getLatestRollInfoByRid(params);
	}

	@Override
	public RollBookInfo getRollBookInfoByRandCode(String content) {
		Map<String, Object> params = new HashMap<>();
		params.put("content", content);
		return rollBookInfoMapper.getRollBookInfoByRandCode(params);
	}
}

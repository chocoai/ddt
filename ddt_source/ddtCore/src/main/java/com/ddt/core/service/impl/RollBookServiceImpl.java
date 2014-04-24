/*
 * @(#)RollBookServiceImpl.java 2014-4-24
 *
 */
package com.ddt.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddt.core.mapper.RollBookMapper;
import com.ddt.core.meta.RollBook;
import com.ddt.core.meta.RollInfo;
import com.ddt.core.meta.User;
import com.ddt.core.meta.UserRollInfo;
import com.ddt.core.service.RollBookService;

/**
 * RollBookServiceImpl.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-24
 * @since      1.0
 */
@Service
public class RollBookServiceImpl implements RollBookService {

	@Autowired
	private RollBookMapper rollBookMapper;

	@Override
	public List<RollBook> getRollBookList(long userId, int limit, int offset) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("userId", userId);
		params.put("limit", limit);
		params.put("offset", offset);
		
		return rollBookMapper.getRollBookList(params);
	}

	@Override
	public List<User> getRollBookUserList(long userId, long rollBookId, int limit, int offset) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("userId", userId);
		params.put("rollBookId", rollBookId);
		params.put("limit", limit);
		params.put("offset", offset);
		return rollBookMapper.getRollBookUserList(params);
	}

	@Override
	public List<RollInfo> getRollInfoList(long userId, long rollBookId, int limit, int offset) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("userId", userId);
		params.put("rollBookId", rollBookId);
		params.put("limit", limit);
		params.put("offset", offset);
		return rollBookMapper.getRollInfoList(params);
	}

	@Override
	public List<UserRollInfo> getUserRollInfoList(long userId, long rollInfoId, int limit, int offset) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("userId", userId);
		params.put("rollInfoId", rollInfoId);
		params.put("limit", limit);
		params.put("offset", offset);
		return rollBookMapper.getUserRollInfoList(params);
	}
}

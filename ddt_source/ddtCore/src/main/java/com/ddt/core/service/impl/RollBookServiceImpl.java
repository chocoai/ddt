/*
 * @(#)RollBookServiceImpl.java 2014-4-24
 *
 */
package com.ddt.core.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddt.core.mapper.RollBookMapper;
import com.ddt.core.meta.RollBook;
import com.ddt.core.meta.RollBookUser;
import com.ddt.core.meta.User;
import com.ddt.core.meta.UserRollInfo;
import com.ddt.core.service.RollBookInfoService;
import com.ddt.core.service.RollBookService;
import com.ddt.core.service.UserService;

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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RollBookInfoService rollBookInfoService;

	@Override
	public List<RollBook> getRollBookList(long userId, String queryValue, int limit, int offset) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("userId", userId);
		params.put("limit", limit);
		params.put("offset", offset);
		params.put("queryValue", queryValue);
		
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
	public List<RollBook> getRollInfoList(long userId, long rollBookId, int limit, int offset) {
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

	@Override
	public int getRollBookCount(long userId, String queryValue) {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("queryValue", queryValue);
		return rollBookMapper.getRollBookCount(params);
	}

	@Override
	public boolean deleteRollBook(long rid, long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("rollBookId", rid);
		params.put("userId", userId);
		//删除点名记录
		rollBookInfoService.deleteRollBookInfo(rid, userId);
		//删除点名册名单
		deleteRollBookUser(rid, userId);
		//删除点名册
		return rollBookMapper.deleteRollBook(params) > 0;
	}

	@Override
	public boolean deleteUserRollInfo(long rid, long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("rollBookId", rid);
		params.put("userId", userId);
		return rollBookMapper.deleteUserRollInfo(params) > 0;
	}

	@Override
	public boolean deleteRollBookUser(long rid, long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("rollBookId", rid);
		params.put("userId", userId);
		return rollBookMapper.deleteRollBookUser(params) > 0;
	}

	@Override
	public RollBook getRollBookById(long rollBookId, long userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", rollBookId);
		params.put("userId", userId);
		return rollBookMapper.getRollBookById(params);
	}

	@Override
	public void addRollBook(RollBook rollBook) {
		rollBookMapper.addRollBook(rollBook);
		List<User> users = userService.getRollBookUserList(rollBook.getGroupId(), Integer.MAX_VALUE, 0);
		
		if (CollectionUtils.isNotEmpty(users)) {
			for (User user : users) {
				RollBookUser rollBookUser = new RollBookUser();
				rollBookUser.setBookId(rollBook.getId());
				rollBookUser.setUserId(user.getId());
				rollBookMapper.addRollBookUser(rollBookUser);
			}
		}
	}

	@Override
	public void updateRollBook(RollBook rollBook) {
		rollBookMapper.updateRollBook(rollBook);
	}

	@Override
	public int getUserRollInfoCount(long userId, long rollInfoId, boolean unrolled) {
		Map<String, Object> params = new HashMap<>();
		params.put("rollInfoId", rollInfoId);
		params.put("userId", userId);
		params.put("unrolled", unrolled);
		return rollBookMapper.getUserRollInfoCount(params);
	}
}

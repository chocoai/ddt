/*
 * @(#)RollBookMapper.java 2014-4-19
 *
 */
package com.ddt.core.mapper;

import java.util.List;
import java.util.Map;

import com.ddt.core.meta.RollBook;
import com.ddt.core.meta.RollBookInfo;
import com.ddt.core.meta.RollBookUser;
import com.ddt.core.meta.User;
import com.ddt.core.meta.UserRollInfo;

/**
 * RollBookMapper.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-19
 * @since      1.0
 */
public interface RollBookMapper {

	List<RollBook> getRollBookList(Map<String, Object> params);

	List<User> getRollBookUserList(Map<String, Object> params);

	List<RollBookInfo> getRollInfoList(Map<String, Object> params);

	List<UserRollInfo> getUserRollInfoList(Map<String, Object> params);

	int getRollBookCount(Map<String, Object> params);

	boolean deleteRollBook(Map<String, Object> params);

	boolean deleteRollBookInfo(Map<String, Object> params);

	boolean deleteUserRollInfo(Map<String, Object> params);

	boolean deleteRollBookUser(Map<String, Object> params);

	RollBook getRollBookById(Map<String, Object> params);

	void addRollBook(RollBook rollBook);

	void updateRollBook(RollBook rollBook);

	void addRollBookUser(RollBookUser rollBookUser);

	void addRollBookInfo(RollBookInfo rollBookInfo);

}

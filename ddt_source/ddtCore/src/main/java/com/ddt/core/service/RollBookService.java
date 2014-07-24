/*
 * @(#)RollBookService.java 2014-4-24
 *
 */
package com.ddt.core.service;

import java.util.List;

import com.ddt.core.meta.RollBook;
import com.ddt.core.meta.User;
import com.ddt.core.meta.UserRollInfo;

/**
 * RollBookService.java
 *
 * @author     <A HREF="mailto:ruan635@163.com">Roy</A>
 * @version    1.0 2014-4-24
 * @since      1.0
 */
public interface RollBookService {

	List<RollBook> getRollBookList(long userId, String queryValue, int limit, int offset);

	List<User> getRollBookUserList(long userId, long rollBookId, int limit, int offset);

	List<RollBook> getRollInfoList(long userId, long rollBookId, int limit, int offset);

	List<UserRollInfo> getUserRollInfoList(long userId, long rollInfoId, int limit, int offset);

	int getRollBookCount(long userId, String queryValue);

	boolean deleteRollBook(long rid, long userId);
	
	boolean deleteUserRollInfo(long rid, long userId);
	
	boolean deleteRollBookUser(long rid, long userId);

	RollBook getRollBookById(long rollBookId, long userId);

	void addRollBook(RollBook rollBook);

	void updateRollBook(RollBook rollBook);

	int getUserRollInfoCount(long id, long rollBookId, boolean unrolled);

}

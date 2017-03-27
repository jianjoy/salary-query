package com.jianjoy.dao;

import com.jianjoy.model.Account;

/**
 * 账户数据库操作接口
 * @author zhoujian
 *
 */
public interface IAccountDao {

	/**
	 * 获取账户信息
	 * @param user
	 * @param md5Pass
	 * @return
	 */
	Account getAccount(String user, String md5Pass);

}

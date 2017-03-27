package com.jianjoy.dao;

import com.jianjoy.model.LoginLog;

/**
 * 登录日志数据操作接口
 * @author zhoujian
 *
 */
public interface ILoginLogDao {

	/**
	 * 保存登录日志
	 * @param log
	 */
	void save(LoginLog log);

}

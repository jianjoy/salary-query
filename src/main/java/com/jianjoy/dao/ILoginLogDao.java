package com.jianjoy.dao;

import java.util.List;

import com.jianjoy.dao.dbbean.LoginLogDbDataModel;
import com.jianjoy.model.Account;
import com.jianjoy.model.LoginLog;
import com.jianjoy.model.Pager;

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
	
	/**
	 * 检索登录日志
	 * @param accountInfo
	 * @param ip
	 * @param startTime
	 * @param endTime
	 * @param pager
	 * @return
	 */
	List<LoginLogDbDataModel> query(Account accountInfo,String ip,String startTime,String endTime,Pager pager);
	
	


}

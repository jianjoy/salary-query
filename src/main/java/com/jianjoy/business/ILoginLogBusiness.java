package com.jianjoy.business;

import java.util.List;

import com.jianjoy.model.Account;
import com.jianjoy.model.BusinessResult;
import com.jianjoy.model.LoginLog;
import com.jianjoy.model.Pager;

/**
 * 登录日志操作业务逻辑接口
 * @author zhoujian
 *
 */
public interface ILoginLogBusiness {

	void save(LoginLog log);
	
	BusinessResult<List<LoginLog>> query(Account accountInfo,String ip,String startTime,String endTime,Pager pager);
	
}

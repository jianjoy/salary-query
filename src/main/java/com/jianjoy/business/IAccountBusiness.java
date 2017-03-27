package com.jianjoy.business;

import com.jianjoy.model.Account;
import com.jianjoy.model.BusinessResult;

/**
 * 账户操作业务逻辑接口
 * @author zhoujian
 *
 */
public interface IAccountBusiness {

	/**
	 * 登录
	 * @param user
	 * @param pass
	 * @param ip
	 * @return
	 */
	BusinessResult<Account> login(String user,String pass,String ip);
	
	/**
	 * 添加账户
	 * @param account
	 * @return
	 */
	BusinessResult<Boolean> addAccount(Account account);
	
	/**
	 * 更新账户状态
	 * @param status
	 * @return
	 */
	BusinessResult<Boolean> updateAccountStatus(int status);
	
	/**
	 * 更改密码
	 * @param oldPass
	 * @param newPass
	 * @return
	 */
	BusinessResult<Boolean> updatePass(String oldPass,String newPass);
	
	/**
	 * 重置密码
	 * @param accountId
	 * @return
	 */
	BusinessResult<Boolean> resetPass(int accountId);
	
	
	
	
}

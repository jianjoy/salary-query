package com.jianjoy.business;

import com.jianjoy.model.Account;
import com.jianjoy.model.AccountRoleType;
import com.jianjoy.model.BusinessResult;
import com.jianjoy.model.Pager;

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
	 * 更新账户状态
	 * @param accId
	 * @param status
	 * @return
	 */
	BusinessResult<Boolean> updateAccountStatus(int accId,int status);
	
	/**
	 * 更改密码
	 * @param accountId
	 * @param newPass
	 * @return
	 */
	BusinessResult<Boolean> updatePass(int accountId,String newPass);
	
	/**
	 * 验证账户
	 * @param email
	 * @return
	 */
	BusinessResult<Account> validateAccount(String email);
	
	
	/**
	 * 获取账户信息列表
	 * @param pager
	 * @return
	 */
	String getAccountList(Pager pager);
	
	/**
	 * 添加账户
	 * @param uname
	 * @param upass
	 * @param empId
	 * @param type
	 * @return
	 */
	BusinessResult<Boolean> addAccount(String uname,String upass,int empId,AccountRoleType type);
	
	/**
	 * 发送重置密码邮件
	 * @param email
	 * @return
	 */
	String sendResetPassMail(String email);
	
	
}

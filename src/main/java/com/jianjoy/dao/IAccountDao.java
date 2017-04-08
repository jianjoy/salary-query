package com.jianjoy.dao;

import java.util.List;

import com.jianjoy.dao.dbbean.AccountInfoDbDataModel;
import com.jianjoy.model.Account;
import com.jianjoy.model.Pager;

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
	
	/**
	 * 获取账户信息
	 * @param pager
	 * @return
	 */
	List<AccountInfoDbDataModel> getListByPage(Pager pager);
	
	/**
	 * 修改账户密码
	 * @param accountId
	 * @param newPass
	 */
	void updatePass(int accountId,String newPass);
	
	/**
	 * 保存账户信息
	 * @param uname
	 * @param upass
	 * @param empId
	 * @param type
	 */
	void saveAccount(String uname,String upass,int empId,int type);
	
	/**
	 * 修改账户状态
	 * @param accId
	 * @param status
	 */
	void updateAccountStatus(int accId,int status);

}

package com.jianjoy.business;

import java.sql.Timestamp;
import com.jianjoy.dao.AccountDaoImpl;
import com.jianjoy.dao.IAccountDao;
import com.jianjoy.dao.ILoginLogDao;
import com.jianjoy.dao.LoginLogDaoImpl;
import com.jianjoy.model.Account;
import com.jianjoy.model.BusinessResult;
import com.jianjoy.model.LoginLog;
import com.jianjoy.utils.Md5Utils;
import com.jianjoy.utils.StringUtils;

public class AccountBusinessImpl implements IAccountBusiness {
	
	private IAccountDao accountDao = new AccountDaoImpl();
	private ILoginLogDao loginLogDao = new LoginLogDaoImpl();

	@Override
	public BusinessResult<Account> login(String user, String pass,String ip) {
		BusinessResult<Account> result = new BusinessResult<>();
		if(StringUtils.isEmpty(user)||StringUtils.isEmpty(pass)||StringUtils.isEmpty(ip)){
			result.setError("用户名或密码错误，登录失败！");
			return result;
		}
		Account account = accountDao.getAccount(user,Md5Utils.getMd5(pass));
		if(account!=null){
			LoginLog log = new LoginLog();
			log.setAccount(account);
			log.setLoginIp(ip);
			log.setLoginTime(new Timestamp(System.currentTimeMillis()));
			loginLogDao.save(log);
			result.setData(account);
		}else{
			result.setError("用户名或密码错误，登录失败！");
		}
		return result;
	}

	@Override
	public BusinessResult<Boolean> addAccount(Account account) {
		return null;
	}

	@Override
	public BusinessResult<Boolean> updateAccountStatus(int status) {
		return null;
	}

	@Override
	public BusinessResult<Boolean> updatePass(String oldPass, String newPass) {
		return null;
	}

	@Override
	public BusinessResult<Boolean> resetPass(int accountId) {
		return null;
	}

}

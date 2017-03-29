package com.jianjoy.dao.dbbean;

import java.sql.Timestamp;

import com.jianjoy.model.Account;
import com.jianjoy.model.LoginLog;

public class LoginLogDbDataModel  extends AbstractDbDataBean{

	private LoginLog  loginLog = new LoginLog();
	
	@Override
	public void convertData(Object[] data) {
		if (data.length >= 4) {
			// log.id,acc.uname,log.login_ip,log.login_time
			loginLog.setId(Long.parseLong(data[0].toString()));
			Account account = new Account();
			account.setUname(data[1].toString());
			loginLog.setAccount(account);
			loginLog.setLoginIp(data[2].toString());
			if(data[3]!=null&&data[3] instanceof Timestamp){
				loginLog.setLoginTime((Timestamp)data[3]);
			}
		}
	}
	
	public LoginLog getLoginLog() {
		return loginLog;
	}

}

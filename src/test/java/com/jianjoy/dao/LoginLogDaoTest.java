package com.jianjoy.dao;

import java.sql.Timestamp;

import org.junit.Test;

import com.jianjoy.model.Account;
import com.jianjoy.model.LoginLog;

public class LoginLogDaoTest {

	private IAccountDao accountDao = new AccountDaoImpl();
	
	private ILoginLogDao loginLogDao = new LoginLogDaoImpl();
	
	@Test
	public void testSave(){
		Account acc = accountDao.getAccount("zhojian", "9be40402f45736bcb9502225fad5ec9b");
		LoginLog log = new LoginLog();
		log.setAccount(acc);
		log.setLoginIp("23.23.23.234");
		log.setLoginTime(new Timestamp(System.currentTimeMillis()));
		loginLogDao.save(log);
	}
}

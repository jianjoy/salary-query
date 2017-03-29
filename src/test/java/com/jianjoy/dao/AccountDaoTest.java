package com.jianjoy.dao;

import org.junit.Test;

import com.jianjoy.model.Account;

public class AccountDaoTest {

	private IAccountDao accountDao = new AccountDaoImpl();
	
	@Test
	public void testGetAccount(){
		Account acc = accountDao.getAccount("admin", "e10adc3949ba59abbe56e057f20f883e");
		System.out.println(acc.getId());
	}
	
	
}

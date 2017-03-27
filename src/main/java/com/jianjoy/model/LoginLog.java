package com.jianjoy.model;

import java.sql.Timestamp;

/**
 * 登录日志
 * @author zhoujian
 *
 */
public class LoginLog {

	
	/**
	 * 自增标识
	 */
	private long id;
	
	/**
	 * 账户信息
	 */
	private Account account;
	
	/**
	 * 登录ip
	 */
	private String loginIp;
	
	/**
	 * 登录时间
	 */
	private Timestamp loginTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Timestamp getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	
}

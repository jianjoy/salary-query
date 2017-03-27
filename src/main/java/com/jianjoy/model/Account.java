package com.jianjoy.model;

import java.sql.Timestamp;

/**
 * 账户信息
 * @author zhoujian
 *
 */
public class Account {

	/**
	 * 自增标识
	 */
	private int id;
	
	/**
	 * 用户名
	 */
	private String uname;
	
	/**
	 * 密码
	 */
	private String pass;
	
	/**
	 * 员工信息
	 */
	private EmployeeInfo employeeInfo;
	
	/**
	 * 系统创建时间戳
	 */
	private Timestamp ctime;
	
	/**
	 * 账户状态:1=>可用,0=>禁用
	 */
	private int status;
	
	
	/**
	 * 账户角色
	 */
	private AccountRoleType roleType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public EmployeeInfo getEmployeeInfo() {
		return employeeInfo;
	}

	public void setEmployeeInfo(EmployeeInfo employeeInfo) {
		this.employeeInfo = employeeInfo;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public void setRoleType(AccountRoleType roleType) {
		this.roleType = roleType;
	}
	
	public AccountRoleType getRoleType() {
		return roleType;
	}
}

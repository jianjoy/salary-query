package com.jianjoy.model;

import java.sql.Timestamp;

/**
 * 员工信息
 * @author zhoujian
 *
 */
public class EmployeeInfo {
	
	/**
	 * 主键标识
	 */
	private int id;
	
	/**
	 * 所属部门
	 */
	private String department;
	
	
	/**
	 * 员工统一编号
	 */
	private String identityNo;
	
	/**
	 * 注册日期
	 */
	private String regDate;
	
	/**
	 * 员工姓名
	 */
	private String name;
	
	/**
	 * 性别
	 */
	private int sex;
	
	
	/**
	 * 系统创建时间戳
	 */
	private Timestamp ctime;
	
	
	/**
	 * 员工邮箱
	 */
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Timestamp getCtime() {
		return ctime;
	}

	public void setCtime(Timestamp ctime) {
		this.ctime = ctime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}

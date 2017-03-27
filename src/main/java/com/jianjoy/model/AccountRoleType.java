package com.jianjoy.model;

/**
 * 账户角色
 * @author zhoujian
 *
 */
public enum AccountRoleType {

	ADMIN(1),EMPLOYEE(2),FINANCE(3);

	private AccountRoleType(int t){
		this.type = t;
	}
	private int type;
	public int getTypeValue(){
		return type;
	}
	
	public static AccountRoleType findRoleType(int typeVal){
		for(AccountRoleType type:AccountRoleType.values()){
			if(type.getTypeValue()==typeVal){
				return type;
			}
		}
		return null;
	}
	
}

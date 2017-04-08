package com.jianjoy.dao.dbbean;

import java.sql.Timestamp;

import com.jianjoy.model.Account;
import com.jianjoy.model.AccountRoleType;
import com.jianjoy.model.EmployeeInfo;

public class AccountInfoDbDataModel extends AbstractDbDataBean<Account> {

	
	private Account model = new Account();
	
	@Override
	public void convertData(Object[] data) {
		//acc.id as id,acc.uname as uname,acc.account_role_type,acc.status,e.department,e.identity_no,e.name,e.sex,e.ctime,e.email
	   if(data!=null&&data.length>=10){
		   model.setId(Integer.parseInt(data[0].toString()));
		   model.setUname(String.valueOf(data[1]));
		   model.setRoleType(AccountRoleType.findRoleType(Integer.parseInt(data[2].toString())));
		   model.setStatus(Integer.parseInt(data[3].toString()));
		   EmployeeInfo e = new EmployeeInfo();
		   e.setDepartment(String.valueOf(data[4].toString()));
		   e.setIdentityNo(String.valueOf(data[5].toString()));
		   e.setName(String.valueOf(data[6].toString()));
		   e.setSex(Integer.parseInt(data[7].toString()));
		   if(data[8]!=null){
			   e.setCtime((Timestamp)data[8]);
		   }
		   e.setEmail(data[9].toString());
		   model.setEmployeeInfo(e);
	   }
	
	}

	public Account getModel() {
		return model;
	}
}

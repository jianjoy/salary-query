package com.jianjoy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.jianjoy.log.DbAccess;
import com.jianjoy.model.Account;
import com.jianjoy.model.AccountRoleType;
import com.jianjoy.model.EmployeeInfo;

public class AccountDaoImpl implements IAccountDao {

	
	@Override
	public Account getAccount(String user, String md5Pass) {
		Account account = null;
		String sql = "select account.id as aid,account.uname as uname,account.account_role_type as roleType,employee_info.department as dp,employee_info.identity_no,employee_info.sex as sex,employee_info.email as email,employee_info.name as realName,employee_info.id as eid from account inner join employee_info on employee_info.id=account.emp_id where uname=? and pass=? and status=1";
		DBBean bean = new DBBean();
		try{
			Connection conn = JDBCUtils.connect();
			bean.setConn(conn);
			PreparedStatement pst = conn.prepareStatement(sql);
			bean.setPrest(pst);
			pst.setString(1, user);
			pst.setString(2, md5Pass);
			ResultSet rs = pst.executeQuery();
			bean.setRs(rs);
			if(rs.next()){
				account = new Account();
				account.setId(rs.getInt(1));
				account.setStatus(1);
				account.setUname(rs.getString(2));
				account.setRoleType(AccountRoleType.findRoleType(rs.getInt(3)));
				EmployeeInfo emp = new EmployeeInfo();
				account.setEmployeeInfo(emp);
				emp.setDepartment(rs.getString(4));
				emp.setIdentityNo(rs.getString(5));
				emp.setSex(rs.getInt(6));
				emp.setEmail(rs.getString(7));
				emp.setName(rs.getString(8));
				emp.setId(rs.getInt(9));
			}
		}catch(Exception e){
			DbAccess.getLogger().error(e);
		}finally{
			JDBCUtils.closeDB(bean);
		}
		return account;
	}

}

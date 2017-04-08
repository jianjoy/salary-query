package com.jianjoy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jianjoy.dao.dbbean.AccountInfoDbDataModel;
import com.jianjoy.log.DbAccess;
import com.jianjoy.model.Account;
import com.jianjoy.model.AccountRoleType;
import com.jianjoy.model.EmployeeInfo;
import com.jianjoy.model.Pager;

public class AccountDaoImpl extends BasePageDao implements IAccountDao {

	
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
				account.setPass(md5Pass);
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

	@Override
	public List<AccountInfoDbDataModel> getListByPage(Pager pager) {
		String sql = "select acc.id as id,acc.uname as uname,acc.account_role_type,acc.status,e.department,e.identity_no,e.name,e.sex,e.ctime,e.email from account acc inner join employee_info e on e.id=acc.emp_id";
		return findByPager(AccountInfoDbDataModel.class, sql, null, pager, true);
	}

	@Override
	public void updatePass(int accountId, String newPass) {
		String sql = "update account set pass=? where id=? ";
		List<Object> params = new ArrayList<>();
		params.add(newPass);
		params.add(accountId);
		JDBCUtils.executeUpdate(JDBCUtils.connect(), sql, params);
	}

	@Override
	public void saveAccount(String uname, String upass, int empId, int type) {
		String sql = "insert into account(uname,pass,emp_id,account_role_type,status) values(?,?,?,?,?)";
		List<Object> params = new ArrayList<>();
		params.add(uname);
		params.add(upass);
		params.add(empId);
		params.add(type);
		params.add(1);
		JDBCUtils.executeUpdate(JDBCUtils.connect(), sql, params);
	}

	@Override
	public void updateAccountStatus(int accId, int status) {
		String sql = "update account set status =? where id=?";
		List<Object> params = new ArrayList<>();
		params.add(status);
		params.add(accId);
		JDBCUtils.executeUpdate(JDBCUtils.connect(), sql, params);
	}

	@Override
	public Account getAccount(String email) {
		Account account = null;
		String sql = "select account.id as aid,account.uname as uname,account.account_role_type as roleType,employee_info.department as dp,employee_info.identity_no,employee_info.sex as sex,employee_info.email as email,employee_info.name as realName,employee_info.id as eid from account inner join employee_info on employee_info.id=account.emp_id where employee_info.email=? and status=1";
		DBBean bean = new DBBean();
		try{
			Connection conn = JDBCUtils.connect();
			bean.setConn(conn);
			PreparedStatement pst = conn.prepareStatement(sql);
			bean.setPrest(pst);
			pst.setString(1, email);
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

package com.jianjoy.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.jianjoy.log.DbAccess;
import com.jianjoy.model.LoginLog;

public class LoginLogDaoImpl implements ILoginLogDao{

	@Override
	public void save(LoginLog log) {
		String sql = "insert into login_log(account_id,login_ip,login_time) values(?,?,?)";
		DBBean bean = new DBBean();
		try{
			Connection con = JDBCUtils.connect();
			List<Object> params = new ArrayList<>();
			params.add(log.getAccount().getId());
			params.add(log.getLoginIp());
			params.add(log.getLoginTime());
			JDBCUtils.executeUpdate(con, sql, params);
		}catch(Exception e){
			DbAccess.getLogger().error(e);
		}finally{
			JDBCUtils.closeDB(bean);
		}
	}

}

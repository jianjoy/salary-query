package com.jianjoy.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.jianjoy.dao.dbbean.LoginLogDbDataModel;
import com.jianjoy.log.DbAccess;
import com.jianjoy.model.Account;
import com.jianjoy.model.LoginLog;
import com.jianjoy.model.Pager;
import com.jianjoy.utils.StringUtils;

public class LoginLogDaoImpl extends BasePageDao implements ILoginLogDao{

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

	@Override
	public List<LoginLogDbDataModel> query(Account accountInfo, String ip,
			String startTime, String endTime, Pager pager) {
		StringBuilder sqlBuilder = new StringBuilder("select log.id,acc.uname,log.login_ip,log.login_time from login_log log inner join account acc on acc.id=log.account_id where 1=1 ");
		List<Object> params = new ArrayList<>();
		if(accountInfo!=null){
			if(accountInfo.getId()>0){
				sqlBuilder.append(" and acc.id=").append(accountInfo.getId());
				params.add(accountInfo.getId());
			}
			if(StringUtils.hasLength(accountInfo.getUname())){
				sqlBuilder.append(" and acc.uname like '%"+accountInfo.getUname()+"%'");
				params.add(accountInfo.getUname());
			}
		}
		if(StringUtils.hasLength(ip)){
			sqlBuilder.append(" and log.login_ip like '%"+ip+"%'");
			params.add(ip);
		}
		if(StringUtils.hasLength(startTime)){
			sqlBuilder.append(" and log.login_time >= '"+startTime+"' ");
			params.add(startTime);
		}
		if(StringUtils.hasLength(endTime)){
			sqlBuilder.append(" and log.login_time <= '"+endTime+"' ");
			params.add(endTime);
		}
		return findByPager(LoginLogDbDataModel.class, sqlBuilder.toString(), params, pager, Boolean.TRUE);
	}
	
	public static void main(String[] args) {
		Pager pager = new Pager();
		pager.setPageSize(2);
		List<LoginLogDbDataModel> list = new LoginLogDaoImpl().query(null, null, null, null, pager);
		System.out.println(pager);
	}

}

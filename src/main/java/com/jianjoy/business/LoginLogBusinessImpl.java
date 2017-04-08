package com.jianjoy.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jianjoy.dao.ILoginLogDao;
import com.jianjoy.dao.LoginLogDaoImpl;
import com.jianjoy.dao.dbbean.LoginLogDbDataModel;
import com.jianjoy.model.Account;
import com.jianjoy.model.JTablePage;
import com.jianjoy.model.LoginLog;
import com.jianjoy.model.Pager;

public class LoginLogBusinessImpl implements ILoginLogBusiness {

	private ILoginLogDao logDao = new LoginLogDaoImpl();

	@Override
	public void save(LoginLog log) {
		logDao.save(log);
	}

	@Override
	public String query(Account accountInfo, String ip,
			String startTime, String endTime, Pager pager) {
		List<Map<String, Object>> logs = new ArrayList<>();
		List<LoginLogDbDataModel> dbList = logDao.query(accountInfo, ip,startTime, endTime, pager);
		if(dbList!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(LoginLogDbDataModel dbBean :dbList){
				Map<String,Object> data = new HashMap<String, Object>();
				LoginLog log = dbBean.getModel();
				data.put("id", log.getId());
				String uname = "";
				if(log.getAccount()!=null){
					uname = log.getAccount().getUname();
				}
				data.put("uname", uname);
				data.put("loginTime", dateFormat.format(log.getLoginTime()));
				data.put("loginIp", log.getLoginIp());
				logs.add(data);
			}
		}
		JTablePage jTablePage = new JTablePage(logs, pager);
		return jTablePage.buildJsonString();
	}

}

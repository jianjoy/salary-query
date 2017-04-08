package com.jianjoy.business;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.jianjoy.dao.AccountDaoImpl;
import com.jianjoy.dao.IAccountDao;
import com.jianjoy.dao.ILoginLogDao;
import com.jianjoy.dao.LoginLogDaoImpl;
import com.jianjoy.dao.dbbean.AccountInfoDbDataModel;
import com.jianjoy.model.Account;
import com.jianjoy.model.AccountRoleType;
import com.jianjoy.model.BusinessResult;
import com.jianjoy.model.EmployeeInfo;
import com.jianjoy.model.JTablePage;
import com.jianjoy.model.LoginLog;
import com.jianjoy.model.Pager;
import com.jianjoy.utils.Md5Utils;
import com.jianjoy.utils.StringUtils;

public class AccountBusinessImpl implements IAccountBusiness {
	
	private IAccountDao accountDao = new AccountDaoImpl();
	private ILoginLogDao loginLogDao = new LoginLogDaoImpl();

	@Override
	public BusinessResult<Account> login(String user, String pass,String ip) {
		BusinessResult<Account> result = new BusinessResult<>();
		if(StringUtils.isEmpty(user)||StringUtils.isEmpty(pass)||StringUtils.isEmpty(ip)){
			result.setError("用户名或密码错误，登录失败！");
			return result;
		}
		Account account = accountDao.getAccount(user,Md5Utils.getMd5(pass));
		if(account!=null){
			LoginLog log = new LoginLog();
			log.setAccount(account);
			log.setLoginIp(ip);
			log.setLoginTime(new Timestamp(System.currentTimeMillis()));
			loginLogDao.save(log);
			result.setData(account);
		}else{
			result.setError("用户名或密码错误，登录失败！");
		}
		return result;
	}



	@Override
	public BusinessResult<Boolean> resetPass(int accountId) {
		return null;
	}

	@Override
	public String getAccountList(Pager pager) {
		List<Map<String, Object>> dataList = new ArrayList<>();
		List<AccountInfoDbDataModel> dbList = accountDao.getListByPage(pager);
		if(dbList!=null){
			for(AccountInfoDbDataModel db:dbList){
				Account acc = db.getModel();
				Map<String, Object> map = new HashMap<>();
				map.put("id", acc.getId());
				map.put("uname", acc.getUname());
				EmployeeInfo e = acc.getEmployeeInfo();
				map.put("roleType", acc.getRoleType()==AccountRoleType.ADMIN?"管理员":(acc.getRoleType()==AccountRoleType.FINANCE?"财务":"员工"));
				map.put("status", acc.getStatus()==1?"可用":"暂停");
				map.put("department", e.getDepartment());
				map.put("identityNo", e.getIdentityNo());
				map.put("name", e.getName());
				map.put("sex", e.getSex()==1?"男":"女");
				map.put("email", e.getEmail());
				dataList.add(map);
			}
		}
		JTablePage jtp = new JTablePage(dataList, pager);
		return jtp.buildJsonString();
	}

	@Override
	public BusinessResult<Boolean> updatePass(int accountId, String newPass) {
		BusinessResult<Boolean> result = new BusinessResult<>();
		accountDao.updatePass(accountId, Md5Utils.getMd5(newPass));
		result.setData(Boolean.TRUE);
		return result;
	}

	@Override
	public BusinessResult<Boolean> addAccount(String uname, String upass, int empId, AccountRoleType type) {
		BusinessResult<Boolean> result = new BusinessResult<>();
		String md5Pass = Md5Utils.getMd5(upass);
		accountDao.saveAccount(uname, md5Pass, empId, type.getTypeValue());
		result.setData(true);
		return result;
	}

	@Override
	public BusinessResult<Boolean> updateAccountStatus(int accId, int status) {
		BusinessResult<Boolean> result = new BusinessResult<>();
		accountDao.updateAccountStatus(accId, status);
		result.setData(true);
		return result;
	}

}

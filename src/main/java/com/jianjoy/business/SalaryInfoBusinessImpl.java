package com.jianjoy.business;

import java.util.ArrayList;
import java.util.List;

import com.jianjoy.dao.ISalaryInfoDao;
import com.jianjoy.dao.SalaryInfoDaoImpl;
import com.jianjoy.dao.dbbean.SalaryInfoDbDataModel;
import com.jianjoy.model.JTablePage;
import com.jianjoy.model.Pager;
import com.jianjoy.model.SalaryInfoExcelModel;

public class SalaryInfoBusinessImpl implements ISalaryInfoBusiness {

	
	private ISalaryInfoDao salaryInfoDao = new SalaryInfoDaoImpl();
	
	@Override
	public String query(int userId, String beginTime,
			String endTime, Pager pager) {
		List<SalaryInfoDbDataModel> modelList = salaryInfoDao.query(userId, beginTime, endTime, pager);
		List<SalaryInfoExcelModel> dl = new ArrayList<>();
		for(SalaryInfoDbDataModel m:modelList){
			dl.add(m.getModel());
		}
		JTablePage jTablePage = new JTablePage(dl, pager);
		return jTablePage.buildJsonString();
	}
}

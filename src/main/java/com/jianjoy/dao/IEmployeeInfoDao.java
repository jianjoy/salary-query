package com.jianjoy.dao;

import java.util.List;

import com.jianjoy.dao.dbbean.EmployeeInfoDbDataModel;
import com.jianjoy.model.EmployeeInfo;
import com.jianjoy.model.Pager;

/**
 * 员工信息数据操作接口
 * @author zhoujian
 *
 */
public interface IEmployeeInfoDao {

	
	List<EmployeeInfoDbDataModel> query(String department,String name,String email,Pager pager);
	
	void save(EmployeeInfo e);
	
}

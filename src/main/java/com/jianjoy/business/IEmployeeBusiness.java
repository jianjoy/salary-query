package com.jianjoy.business;

import com.jianjoy.model.BusinessResult;
import com.jianjoy.model.EmployeeInfo;
import com.jianjoy.model.Pager;

/**
 * 员工信息操作业务逻辑接口
 * @author zhoujian
 *
 */
public interface IEmployeeBusiness {

	BusinessResult<Boolean> add(EmployeeInfo info);
	
	 String query(String department, String name,String email,Pager pager);
	
}

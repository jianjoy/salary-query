package com.jianjoy.business;

import com.jianjoy.model.BusinessResult;
import com.jianjoy.model.EmployeeInfo;

/**
 * 员工信息操作业务逻辑接口
 * @author zhoujian
 *
 */
public interface IEmployeeBusiness {

	BusinessResult<Boolean> add(EmployeeInfo info);
	
	
	
}

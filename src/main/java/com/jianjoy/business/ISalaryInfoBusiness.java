package com.jianjoy.business;

import com.jianjoy.model.Pager;

/**
 * 工资信息操作业务逻辑接口
 * @author zhoujian
 *
 */
public interface ISalaryInfoBusiness {

	 String query(int userId,String beginTime,String endTime,Pager pager);
	
}

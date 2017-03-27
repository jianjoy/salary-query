package com.jianjoy.business;

import java.util.List;

import com.jianjoy.model.BusinessResult;
import com.jianjoy.model.Pager;
import com.jianjoy.model.SalaryInfo;

/**
 * 工资信息操作业务逻辑接口
 * @author zhoujian
 *
 */
public interface ISalaryInfoBusiness {

	 BusinessResult<List<SalaryInfo>> query(int userId,String beginTime,String endTime,Pager pager);
	
	 BusinessResult<Boolean> batchSave(String file);
	 
}

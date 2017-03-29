package com.jianjoy.dao;

import java.util.List;

import com.jianjoy.model.SalaryInfoExcelModel;

/**
 * 工资信息数据操作接口
 * @author zhoujian
 *
 */
public interface ISalaryInfoDao {

	int saveData(List<SalaryInfoExcelModel> ml);
	
}

package com.jianjoy.dao;

import java.util.List;

import com.jianjoy.dao.dbbean.SalaryInfoDbDataModel;
import com.jianjoy.model.Pager;
import com.jianjoy.model.SalaryInfoExcelModel;

/**
 * 工资信息数据操作接口
 * @author zhoujian
 *
 */
public interface ISalaryInfoDao {

	/**
	 * 工资入库保存接口
	 * @param ml
	 * @return
	 */
	int saveData(List<SalaryInfoExcelModel> ml);
	
	/**
	 * 工资信息查询接口
	 * @param empId
	 * @param startDate
	 * @param endDate
	 * @param pager
	 * @return
	 */
    List<SalaryInfoDbDataModel> query(int empId,String startDate,String endDate,Pager pager);
	
}

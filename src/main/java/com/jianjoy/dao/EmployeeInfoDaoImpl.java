package com.jianjoy.dao;

import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.jianjoy.dao.dbbean.EmployeeInfoDbDataModel;
import com.jianjoy.model.Pager;

public class EmployeeInfoDaoImpl extends BasePageDao implements IEmployeeInfoDao{

	/* (non-Javadoc)
	 */
	@Override
	public List<EmployeeInfoDbDataModel> query(String department, String name,
			String email, Pager pager) {
		StringBuilder sqlBuilder = new StringBuilder("select id,department,identity_no,reg_date,name,sex,ctime,email from employee_info where 1=1 ");
		if(StringUtils.isNotBlank(department)){
			sqlBuilder.append(" and department like '%"+department+"%' ");
		}
		if(StringUtils.isNotBlank(name)){
			sqlBuilder.append(" and name like '%"+name+"%' ");
		}
		if(StringUtils.isNotBlank(email)){
			sqlBuilder.append(" and email like '%"+email+"%' ");
		}
		return findByPager(EmployeeInfoDbDataModel.class, sqlBuilder.toString(), null, pager, true);
	}

}

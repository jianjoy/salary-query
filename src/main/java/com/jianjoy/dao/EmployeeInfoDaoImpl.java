package com.jianjoy.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import com.jianjoy.dao.dbbean.EmployeeInfoDbDataModel;
import com.jianjoy.model.EmployeeInfo;
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

	@Override
	public void save(EmployeeInfo e) {
		String insertSql = "insert into employee_info(department,identity_no,reg_date,name,sex,email) values(?,?,?,?,?,?)";
		List<Object> params = new ArrayList<>();
		params.add(e.getDepartment());
		params.add(e.getIdentityNo());
		params.add(e.getRegDate());
		params.add(e.getName());
		params.add(e.getSex());
		params.add(e.getEmail());
		JDBCUtils.executeUpdate(JDBCUtils.connect(), insertSql, params);
	}

}

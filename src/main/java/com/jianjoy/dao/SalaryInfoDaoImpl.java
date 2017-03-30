package com.jianjoy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.jianjoy.dao.dbbean.SalaryInfoDbDataModel;
import com.jianjoy.log.Business;
import com.jianjoy.model.Pager;
import com.jianjoy.model.SalaryInfoExcelModel;
import com.jianjoy.utils.StringUtils;

public class SalaryInfoDaoImpl extends BasePageDao implements ISalaryInfoDao {

	@Override
	public int saveData(List<SalaryInfoExcelModel> ml) {
		int rows = -1;
		DBBean bean = new DBBean();
		StringBuilder sqlBuilder = new StringBuilder("insert into salary_info(emp_id,salary,bonus,absent_salary_deduction,sick_leave_salary_deduction,personal_pension_payment,personal_medical_insurance_payment,personal_provident_fund_payment,personal_income_tax,real_salary,salary_date) values ");
		boolean flag = false;
		for(SalaryInfoExcelModel model:ml){
			sqlBuilder.append("\n");
			if(flag){
				sqlBuilder.append(",");
			}
			sqlBuilder.append("(")
			.append("'").append(model.getEmpId()).append("'").append(",")
			.append("'").append(model.getSalary()).append("'").append(",")
			.append("'").append(model.getBonus()).append("'").append(",")
			.append("'").append(model.getAbsentSalaryDeduction()).append("'").append(",")
			.append("'").append(model.getSickLeaveSalaryDeduction()).append("'").append(",")
			.append("'").append(model.getPersonPensionPayment()).append("'").append(",")
			.append("'").append(model.getPersonMedicalInsurancePayment()).append("'").append(",")
			.append("'").append(model.getPersonalProvidentFund()).append("'").append(",")
			.append("'").append(model.getPersonalIncomeTax()).append("'").append(",")
			.append("'").append(model.getRealSalary()).append("'").append(",")
			.append("'").append(model.getSalaryDate()).append("'")
			.append(")");
			
			if(!flag){
				flag=true;
			}
		}
		try{
			Connection con = JDBCUtils.connect();
			bean.setConn(con);
			String sql =sqlBuilder.toString();
			PreparedStatement pst = con.prepareStatement(sql);
			bean.setPrest(pst);
			rows=pst.executeUpdate();
		}catch(Exception e){
			Business.getLogger().error(e);
		}finally{
			JDBCUtils.closeDB(bean);
		}
		return rows;
	}

	@Override
	public List<SalaryInfoDbDataModel> query(int empId, String startDate,
			String endDate, Pager pager) {
		StringBuilder sqlBuilder = new StringBuilder("select id,emp_id,salary,bonus,absent_salary_deduction,sick_leave_salary_deduction,personal_pension_payment,personal_medical_insurance_payment,personal_provident_fund_payment,personal_income_tax,real_salary,salary_date from salary_info where 1=1 ");
		List<Object> params = new ArrayList<>();
		String sql = sqlBuilder.toString();
		if(empId>0){
			sqlBuilder.append(" and emp_id= "+empId);
			params.add(empId);
		}
		if(StringUtils.hasLength(startDate)){
			sqlBuilder.append(" and salary_date >= '"+startDate+"'");
			params.add(startDate);
		}
		if(StringUtils.hasLength(endDate)){
			sqlBuilder.append(" and salary_date <= '"+endDate+"'");
			params.add(endDate);
		}
		return findByPager(SalaryInfoDbDataModel.class, sql, params, pager, true);
	}
	
	
	

}

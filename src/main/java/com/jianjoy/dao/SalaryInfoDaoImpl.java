package com.jianjoy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import com.jianjoy.log.Business;
import com.jianjoy.model.SalaryInfoExcelModel;

public class SalaryInfoDaoImpl implements ISalaryInfoDao {

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

}

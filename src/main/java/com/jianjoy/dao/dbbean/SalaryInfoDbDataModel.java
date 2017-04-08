package com.jianjoy.dao.dbbean;

import com.jianjoy.model.SalaryInfoExcelModel;

public class SalaryInfoDbDataModel extends AbstractDbDataBean<SalaryInfoExcelModel> {

	private SalaryInfoExcelModel model = new SalaryInfoExcelModel();
	
	@Override
	public void convertData(Object[] data) {
		//id,emp_id,salary,bonus,absent_salary_deduction,sick_leave_salary_deduction,personal_pension_payment,personal_medical_insurance_payment,personal_provident_fund_payment,personal_income_tax,real_salary,salary_date
		if(data!=null&&data.length>=12){
			model.setId(Integer.parseInt(data[0].toString()));
			model.setEmpId(Integer.parseInt(data[1].toString()));
			model.setSalary(Double.parseDouble(data[2].toString()));
			model.setBonus(Double.parseDouble(data[3].toString()));
			model.setAbsentSalaryDeduction(Double.parseDouble(data[4].toString()));
			model.setSickLeaveSalaryDeduction(Double.parseDouble(data[5].toString()));
			model.setPersonPensionPayment(Double.parseDouble(data[6].toString()));
			model.setPersonMedicalInsurancePayment(Double.parseDouble(data[7].toString()));
			model.setPersonalProvidentFund(Double.parseDouble(data[8].toString()));
			model.setPersonalIncomeTax(Double.parseDouble(data[9].toString()));
			model.setRealSalary(Double.parseDouble(data[10].toString()));
			model.setSalaryDate(data[11].toString());
		}
	}
	
	public SalaryInfoExcelModel getModel() {
		return model;
	}

}

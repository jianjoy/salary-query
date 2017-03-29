package com.jianjoy.model;

/**
 * 工资信息实体封装
 * @author zhoujian
 *
 */
public class SalaryInfoExcelModel {

	/**
	 * 员工ID
	 */
	private int empId;
	
	
	/**
	 * 薪资
	 */
	private double salary;
	
	/**
	 * 奖金
	 */
	private double bonus;
	
	/**
	 * 事假扣薪
	 */
	private double absentSalaryDeduction;
	
	
	/**
	 * 病假扣薪
	 */
	private double sickLeaveSalaryDeduction;

	
	/**
	 * 个人养老金
	 */
	private double personPensionPayment;
	
	
	/**
	 * 个人医保
	 */
	private double personMedicalInsurancePayment;
	
	/**
	 * 个人所得税
	 */
	private double personalIncomeTax;
	
	/**
	 * 薪资日期
	 */
	private String salaryDate;
	
	/**
	 * 个人公积金
	 */
	private double personalProvidentFund;
	
	/**
	 * 实际工资
	 */
	private double realSalary;

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public double getAbsentSalaryDeduction() {
		return absentSalaryDeduction;
	}

	public void setAbsentSalaryDeduction(double absentSalaryDeduction) {
		this.absentSalaryDeduction = absentSalaryDeduction;
	}

	public double getSickLeaveSalaryDeduction() {
		return sickLeaveSalaryDeduction;
	}

	public void setSickLeaveSalaryDeduction(double sickLeaveSalaryDeduction) {
		this.sickLeaveSalaryDeduction = sickLeaveSalaryDeduction;
	}

	public double getPersonPensionPayment() {
		return personPensionPayment;
	}

	public void setPersonPensionPayment(double personPensionPayment) {
		this.personPensionPayment = personPensionPayment;
	}

	public double getPersonMedicalInsurancePayment() {
		return personMedicalInsurancePayment;
	}

	public void setPersonMedicalInsurancePayment(
			double personMedicalInsurancePayment) {
		this.personMedicalInsurancePayment = personMedicalInsurancePayment;
	}

	public double getPersonalIncomeTax() {
		return personalIncomeTax;
	}

	public void setPersonalIncomeTax(double personalIncomeTax) {
		this.personalIncomeTax = personalIncomeTax;
	}

	public String getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
	}

	public double getPersonalProvidentFund() {
		return personalProvidentFund;
	}

	public void setPersonalProvidentFund(double personalProvidentFund) {
		this.personalProvidentFund = personalProvidentFund;
	}

	public double getRealSalary() {
		return realSalary;
	}

	public void setRealSalary(double realSalary) {
		this.realSalary = realSalary;
	}
	
	
	
}

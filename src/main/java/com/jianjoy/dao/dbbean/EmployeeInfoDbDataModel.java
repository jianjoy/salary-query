package com.jianjoy.dao.dbbean; 

import java.sql.Date;
import java.sql.Timestamp;

import com.jianjoy.model.EmployeeInfo;

/** 
 * @author  zhoujian
 *
 * @version 创建时间：2017年4月1日 下午5:29:49
 * 
 */
public class EmployeeInfoDbDataModel extends AbstractDbDataBean {
	
	private EmployeeInfo model = new EmployeeInfo();

	/* (non-Javadoc)
	 * @see com.jianjoy.dao.dbbean.AbstractDbDataBean#convertData(java.lang.Object[])
	 */
	@Override
	public void convertData(Object[] data) {
		//id,department,identity_no,reg_date,name,sex,ctime,email
		if(data!=null&&data.length>=8){
			model.setId(Integer.parseInt(data[0].toString()));
			model.setDepartment(String.valueOf(data[1]));
			model.setIdentityNo(String.valueOf(data[2]));
			if(data[3]!=null){
				model.setRegDate((Date)data[3]);
			}
			model.setName(String.valueOf(data[4]));
			model.setSex(Integer.parseInt(data[5].toString()));
			if(data[6]!=null){
				model.setCtime((Timestamp)data[6]);
			}
			model.setEmail(String.valueOf(data[7]));
		}
	}
	
	/**
	 * @return the model
	 */
	public EmployeeInfo getModel() {
		return model;
	}

}
 
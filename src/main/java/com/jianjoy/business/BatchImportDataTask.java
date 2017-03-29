package com.jianjoy.business;


import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

import com.jianjoy.dao.ISalaryInfoDao;
import com.jianjoy.dao.SalaryInfoDaoImpl;
import com.jianjoy.log.Business;
import com.jianjoy.model.SalaryInfoExcelModel;
import com.jianjoy.utils.ExcelUtils;

/**
 * 批量导入数据，后台任务
 * @author zhoujian
 *
 */
public class BatchImportDataTask implements Runnable{

	private BatchImportDataTask(){
		Business.getLogger().info("init BatchImportDataTask");
	}
	
	private static final BatchImportDataTask instance = new BatchImportDataTask();
	
	public static LinkedBlockingDeque<File> fileQueue = new LinkedBlockingDeque<>();
	
	private ISalaryInfoDao salaryInfoDao = new SalaryInfoDaoImpl();
	
	
	public static BatchImportDataTask getInstance(){ 
		return instance;
	}
	
	public static void addToQueue(File f){
		if(f!=null){
			fileQueue.add(f);
			Business.getLogger().info("file [{}] add to queue",f);
		}
	}


	@Override
	public void run() {
		File f = null;
		try {
			while((f=fileQueue.take())!=null){
				Business.getLogger().info("take file:{}!",f);
				List<String[]> datas = ExcelUtils.parseData(f);
				if(datas!=null){
					int totalRows = datas.size();
					if(totalRows>1){
						List<SalaryInfoExcelModel> modelList = new ArrayList<>();
						for(int i=1;i<totalRows;i++){
							String[]columns = datas.get(i);
							SalaryInfoExcelModel model = convertToModel(columns);
							if(model!=null){
								modelList.add(model);
							}
						}
						if(modelList.size()>0){
							//数据入库
							int rows = salaryInfoDao.saveData(modelList);
							Business.getLogger().info("save file:{} into db,rows:{}",f,rows);
							TimeUnit.SECONDS.sleep(1);
						}
					}
				}
				Business.getLogger().info("parse file:{} end!",f);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 数据实体拼装
	 * @param columns [员工编号, 薪资, 奖金, 事假, 病假, 个人养老, 个人医保, 个人住房公积金, 个人所得税, 实发工资, 工资月份]
	 * @return
	 */
	public SalaryInfoExcelModel convertToModel(String[] columns){
		int columnValidateNum = 11;
		if(columns.length<columnValidateNum)return null;
		SalaryInfoExcelModel model = new SalaryInfoExcelModel();
		try{
			model = new SalaryInfoExcelModel();
			model.setEmpId(Integer.parseInt(columns[0]));
			model.setSalary(Double.parseDouble(columns[1]));
			model.setBonus(Double.parseDouble(columns[2]));
			model.setAbsentSalaryDeduction(Double.parseDouble(columns[3]));
			model.setSickLeaveSalaryDeduction(Double.parseDouble(columns[4]));
			model.setPersonPensionPayment(Double.parseDouble(columns[5]));
			model.setPersonMedicalInsurancePayment(Double.parseDouble(columns[6]));
			model.setPersonalProvidentFund(Double.parseDouble(columns[7]));
			model.setPersonalIncomeTax(Double.parseDouble(columns[8]));
			model.setRealSalary(Double.parseDouble(columns[9]));
			model.setSalaryDate(columns[10]);
		}catch(Exception e){
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			Business.getLogger().error("columns {} convert faild,ex:{}",Arrays.toString(columns),sw.toString());
			model = null;
		}
		return model;
	}
	
}

package com.jianjoy.utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.jianjoy.log.Business;

/**
 * excel操作工具
 * @author zhoujian
 *
 */
public class ExcelUtils {
	/**
	 * 解析excel文件数据
	 * @param excelFile
	 * @return
	 */
	public static List<String[]> parseData(File excelFile){
		if(excelFile==null||!excelFile.exists()||!(excelFile.getName().endsWith(".xls")||excelFile.getName().endsWith(".xlsx"))){
			return null;
		}
		List<String[]> dataList = new ArrayList<>(20000);
		Workbook workBook = null;
		try {
			workBook = WorkbookFactory.create(excelFile);
			org.apache.poi.ss.usermodel.Sheet sheet = workBook.getSheetAt(0);
			int lastRowIndex = sheet.getLastRowNum();
			if(lastRowIndex>=0){
				Row firstRow = sheet.getRow(0);
				int columns = firstRow.getPhysicalNumberOfCells();
				if(columns>0){
					for(int i=0;i<=lastRowIndex;i++){
						Row row = sheet.getRow(i);
						String [] values = new String[columns];
						for(int j=0;j<columns;j++){
							Cell cell = row.getCell(j);
						    String cellValue = getCellValue(cell);
						    values[j]=cellValue;
						}
						dataList.add(values);
					}
				}
			}
		}catch(Exception e){
			Business.getLogger().error(e);
		}finally{
			if(workBook!=null)
				try {
					workBook.close();
				} catch (IOException e) {
					Business.getLogger().error(e);
				}
		}
		return dataList;
	}
	private static String getCellValue(Cell cell) {
		String cellValue = "";
		DecimalFormat df = new DecimalFormat("#.##");  
		switch (cell.getCellType()) {  
		case HSSFCell.CELL_TYPE_STRING:  
		    cellValue = cell.getRichStringCellValue().getString().trim();  
		    break;  
		case HSSFCell.CELL_TYPE_NUMERIC:  
		    cellValue = df.format(cell.getNumericCellValue()).toString();  
		    break;  
		case HSSFCell.CELL_TYPE_BOOLEAN:  
		    cellValue = String.valueOf(cell.getBooleanCellValue()).trim();  
		    break;  
		case HSSFCell.CELL_TYPE_FORMULA:  
		    cellValue = cell.getCellFormula();  
		    break;  
		}
		return cellValue;
	}
	public static void main(String[] args) {
		List<String[]> dataList = ExcelUtils.parseData(new File("model.xlsx"));
		System.out.println(Arrays.toString(dataList.get(1)));
	}
}

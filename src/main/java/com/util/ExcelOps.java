package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.application.Base;

public class ExcelOps {
	
private ExcelOps() {}
	
	public static ExcelOps instance;
	
	public static ExcelOps getInstance () {
		if(instance==null) {
			instance=new ExcelOps();
		}return instance;
	}
//#############################################################################################################################

	public List<HashMap<String,String>> getExcelToHashMap(String inputExcelLocation) throws InvalidFormatException, IOException {
		List<HashMap<String,String>> inputSheet = new ArrayList<HashMap<String,String>>();
		File file = new File(inputExcelLocation);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		
		XSSFSheet sheet = wb.getSheet(wb.getSheetName(wb.getActiveSheetIndex()));
		int rowCount = sheet.getLastRowNum()+1;
		int colCount = sheet.getRow(0).getLastCellNum();
		
		for(int i=1;i<rowCount-1;i++) {
			HashMap<String,String> innerList = new HashMap<String,String>();
			String checkValue = sheet.getRow(i).getCell(16).toString();
			if(checkValue.equals("Yes")) {
			for(int j=0;j<colCount;j++) {
				String key = sheet.getRow(0).getCell(j).toString();
				String value = sheet.getRow(i).getCell(j).toString();
				innerList.put(key, value);				
			}
			inputSheet.add(innerList);
			}
		}
		wb.close();
		return inputSheet;
	}
//#########################################################################################################################################
	
	public void setCellValue(String siteName,String colName,String cellValue) throws InvalidFormatException, IOException {
		String inputExcelLocation = Base.inputSheetLocation;
		File file = new File(inputExcelLocation);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(wb.getActiveSheetIndex());
		
		int colCount = sheet.getRow(0).getLastCellNum();
		int rowCount = sheet.getLastRowNum()+1;
		int colNo = 0;
		int rowNo = 0;
		
//		System.out.println(rowCount+" & "+colCount);
		
		for(int i=0;i<colCount;i++) {
			String actualName = sheet.getRow(0).getCell(i).toString();
			if(colName.equals(actualName)) {
				colNo = i;
				break;
			}
		}
		
		
		for(int i=0;i<rowCount;i++) {
			String actualName = sheet.getRow(i).getCell(7).toString();
			if(siteName.equals(actualName)) {
				rowNo = i;
				break;
			}
		}
		
//		System.out.println("Row No : "+rowNo+" & ColNo : "+colNo);
		
		sheet.getRow(rowNo).createCell(colNo).setCellValue(cellValue);
		FileOutputStream out = new FileOutputStream(inputExcelLocation.replace('\"', '/'));
//		System.out.println("............");
		wb.write(out);
		wb.close();
	}
	
//##################################################################################################################################

}

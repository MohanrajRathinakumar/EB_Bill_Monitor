package com.process;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.application.Base;
import com.pom.DriverAction;
import com.util.ExcelOps;

public class PreSetup {
	
private PreSetup() {}

public static PreSetup instance;

public static PreSetup getInstance () {
	if(instance==null) {
		instance=new PreSetup();
	}return instance;
}
//#############################################################################################################################
	
	public List<HashMap<String,String>> loadTempVariables(String inputSheetLocation) throws InvalidFormatException, IOException {
		List<HashMap<String,String>> inputExcelList = null;
		try {
		inputExcelList = ExcelOps.getInstance().getExcelToHashMap(inputSheetLocation);
		}catch(FileNotFoundException e) {
			System.out.println("Close the input Sheet if opened. Or check the input sheet available on Documents Location\n"+e);
		}return inputExcelList;
	}
	
//----------------------------------------------------------------------------------------------------------------------------------
	public void oneTimeSetup() {
		
	}
	
//----------------------------------------------------------------------------------------------------------------------------------
	
	public void checkPreSetup() throws InvalidFormatException, IOException {
		if(Base.driver==null) {
			Base.driver = DriverAction.getInstance().getWebDriver();
		}
		Base.inputExcelList =  loadTempVariables(Base.inputSheetLocation);
	}
}

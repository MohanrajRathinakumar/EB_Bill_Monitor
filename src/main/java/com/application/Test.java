package com.application;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.util.ExcelOps;

public class Test {

	public static void main(String[] args) throws InvalidFormatException, IOException {
		
		ExcelOps.getInstance().setCellValue("VFS-JN St", PV.CHECK, "Test");
		ExcelOps.getInstance().setCellValue("VFS-JN St", PV.BILL_STATUS, "Done");

	}

}

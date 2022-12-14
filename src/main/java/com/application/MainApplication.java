package com.application;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.WebDriver;

import com.pom.AddConsumerPage;
import com.pom.DriverAction;
import com.process.AddConsumerNo;
import com.process.BillDetails;
import com.process.BillSummary;
import com.process.LoginProcess;
import com.process.PreSetup;
import com.process.RemoveConsumer;
import com.util.ExcelOps;

public class MainApplication {

	public static void main(String[] args) throws InvalidFormatException, IOException, InterruptedException {
		
		PreSetup.getInstance().checkPreSetup();
		LoginProcess.getInstance().doLogin();
		System.out.println(Base.inputExcelList);
		
		for(int i=0;i<Base.inputExcelList.size();i++) {
			String serviceNo = Base.inputExcelList.get(i).get(PV.SERVICE_NO).replaceAll("-", "");
			String SiteName = Base.inputExcelList.get(i).get(PV.SITE_NAME);
			boolean flag = AddConsumerNo.getInstance().addConsumerNo(serviceNo);
			if(flag) {
				String billStatus = BillDetails.getInstance().getBillStatus(serviceNo);
				if(billStatus.equals(PV.NOT_PAID)) {
					System.err.println("Bill Status for "+SiteName+" : "+billStatus);
					BillSummary.getInstance().downloadBillCopy(serviceNo, SiteName);
					RemoveConsumer.getInstance().removeConsumerNo(serviceNo);
					System.out.println("\t>Consumer Number Removed from your account..");
					ExcelOps.getInstance().setCellValue(SiteName, PV.BILL_STATUS, PV.NOT_PAID);
//					ExcelOps.getInstance().setCellValue(SiteName, PV.CHECK, PV.DONE);
				}else {
					RemoveConsumer.getInstance().removeConsumerNo(serviceNo);
					if(billStatus.equals(PV.PAID)) {
						System.out.println("Bill Status for "+SiteName+" : "+billStatus);
						ExcelOps.getInstance().setCellValue(SiteName, PV.CHECK, PV.DONE);
						ExcelOps.getInstance().setCellValue(SiteName, PV.BILL_STATUS, PV.PAID);
					}
//					System.out.println("\t>Consumer Number Removed Again..");
				}
				
			}
			
		}
		
		System.out.println("======================================================================================================");
		System.out.println("Completed...");
		System.out.println("======================================================================================================");
		
	}

}

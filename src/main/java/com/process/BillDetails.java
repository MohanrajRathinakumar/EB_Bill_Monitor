package com.process;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.application.Base;
import com.application.PV;
import com.pom.BillDetailsPage;

public class BillDetails {

private BillDetails() {}
	
	public static BillDetails instance;
	
	public static BillDetails getInstance () {
		if(instance==null) {
			instance=new BillDetails();
		}return instance;
	}
//#############################################################################################################################
	
	public boolean checkConsumerFound(String consumerNo) {
		String xpath = "//*[text()='"+consumerNo+"']";
		boolean flag=false;
		try {
		if(Base.driver.findElement(By.xpath(xpath)).isDisplayed()) {
			flag = true;
		}}catch(NoSuchElementException e) {
//			System.out.println("Exception Handled : While Checking ConsumerNo in Page");
		}
		return flag;
	}
	
//-----------------------------------------------------------------------------------------------------------------------------
	
	public String getPaymentStatusOnCurrentPage(String consumerNo) {
		String billStatus=null;
		if(checkConsumerFound(consumerNo)) {
			String xpathBill = "//*[text()='"+consumerNo+"']/parent::*/following-sibling::*[5]//button";
			String status = Base.driver.findElement(By.xpath(xpathBill)).getText();
//			System.out.println("Bill Status on Page : "+status);
			if(status.contains(PV.ADVANCE)) {
				billStatus =  PV.PAID;
			}else if(status.contains(PV.BILL)) {
				billStatus =  PV.NOT_PAID;
			}
		}else {
			billStatus =  PV.NOT_FOUND;
		}return billStatus;		
	}
	
//------------------------------------------------------------------------------------------------------------------------------

	public String getBillStatus(String consumerNo) {
		PageFactory.initElements(Base.driver, BillDetailsPage.getInstance());
		Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(BillDetailsPage.getInstance().billDetailsBtn));
		BillDetailsPage.getInstance().billDetailsBtn.click();
		
		boolean nextPage = true;
		boolean billFoundStatus = false;
		String billStatus = null;
		
		while(nextPage && (!billFoundStatus)) {
			PageFactory.initElements(Base.driver, BillDetailsPage.getInstance());
			Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(BillDetailsPage.getInstance().tableElements));
			billStatus = getPaymentStatusOnCurrentPage(consumerNo);
			if(billStatus.equals(PV.PAID)) {
				billFoundStatus=true;
				break;
			}else if(billStatus.equals(PV.NOT_PAID)) {
				billFoundStatus=true;
				break;
			}else if(billStatus.equals(PV.NOT_FOUND)) {
				if(BillDetailsPage.getInstance().moveToNextPage()) {
					
				}else {
					nextPage=false;
					break;
				}
			}
		}
		return billStatus;
	}

}

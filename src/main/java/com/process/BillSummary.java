package com.process;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.application.Base;
import com.pom.BillSummaryPage;
import com.util.ScreenShotUtil;


public class BillSummary {


private BillSummary() {}
	
	public static BillSummary instance;
	
	public static BillSummary getInstance () {
		if(instance==null) {
			instance=new BillSummary();
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
	
	public void downloadBillCopy(String consumerNo,String siteName) {
		PageFactory.initElements(Base.driver, BillSummaryPage.getInstance());
		Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(BillSummaryPage.getInstance().accountSummaryBtn));
		BillSummaryPage.getInstance().accountSummaryBtn.click();
		
		boolean nextPage = true;
		boolean consumerFoundStatus = false;
		
		while(nextPage && (!consumerFoundStatus)) {
			PageFactory.initElements(Base.driver, BillSummaryPage.getInstance());
			Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(BillSummaryPage.getInstance().tableElements));
			consumerFoundStatus = checkConsumerFound(consumerNo);
			if(consumerFoundStatus) {
				String xpathBillBtn = "//*[text()='"+consumerNo+"']/parent::*/following-sibling::*[3]//button";
				Base.driver.findElement(By.xpath(xpathBillBtn)).click();
				Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(BillSummaryPage.getInstance().billHistoryTable));
				ScreenShotUtil.getInstance().getFullPageScreenShot(Base.driver, Base.billOutputLocationFullPage, siteName);
				System.out.println("\t>Bill Copy Downloaded !");
				
			}else if(!consumerFoundStatus) {
				if(BillSummaryPage.getInstance().moveToNextPage()) {
					
				}else {
					nextPage=false;
					break;
				}

			}
		}
		
	}

}

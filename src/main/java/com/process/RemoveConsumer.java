package com.process;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.application.Base;
import com.pom.RemoveConsumerPage;
import com.pom.AddConsumerPage;

public class RemoveConsumer {
	
private RemoveConsumer() {}
	
	public static RemoveConsumer instance;
	
	public static RemoveConsumer getInstance () {
		if(instance==null) {
			instance=new RemoveConsumer();
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
	
	public void removeConsumerNo(String consumerNo) {
		PageFactory.initElements(Base.driver, RemoveConsumerPage.getInstance());
		Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(RemoveConsumerPage.getInstance().removeConsumerBtn));
		RemoveConsumerPage.getInstance().removeConsumerBtn.click();
		
		boolean nextPage = true;
		boolean consumerFoundStatus = false;
		
		while(nextPage && (!consumerFoundStatus)) {
			PageFactory.initElements(Base.driver, RemoveConsumerPage.getInstance());
			Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOfAllElements(RemoveConsumerPage.getInstance().tableElements));
			consumerFoundStatus = checkConsumerFound(consumerNo);
			if(consumerFoundStatus) {
				String xpathRemoveBtn = "//*[text()='"+consumerNo+"']/parent::*/parent::*/following-sibling::*[3]//button";
				Base.driver.findElement(By.xpath(xpathRemoveBtn)).click();
			}else if(!consumerFoundStatus) {
				if (RemoveConsumerPage.getInstance().moveToNextPage()) {

				} else {
					nextPage = false;
					break;
				}
			}
		}
		PageFactory.initElements(Base.driver, AddConsumerPage.getInstance());
		try {
		AddConsumerPage.getInstance().addConsumerBtn.click();
		}catch(Exception e) {
//			System.out.println("\tException Handled on Remove Customer..");
		}
		Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(AddConsumerPage.getInstance().addConsumerBtn));
	}

}

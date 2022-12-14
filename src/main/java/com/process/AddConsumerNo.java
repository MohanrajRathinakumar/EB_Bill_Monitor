package com.process;

import java.time.Duration;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.application.Base;
import com.pom.AddConsumerPage;

public class AddConsumerNo {
	
private AddConsumerNo() {
		
	}
	
	public static AddConsumerNo instance;
	
	public static AddConsumerNo getInstance () {
		if(instance==null) {
			instance=new AddConsumerNo();
		}return instance;
	}
//#############################################################################################################################

	public boolean addConsumerNo(String consumerNo) throws InterruptedException {
		PageFactory.initElements(Base.driver, AddConsumerPage.getInstance());
//		Base.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(AddConsumerPage.getInstance().addConsumerBtn));
//		Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.visibilityOf(AddConsumerPage.getInstance().addConsumerBtn));
		AddConsumerPage.getInstance().addConsumerBtn.click();
		Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.elementToBeClickable(AddConsumerPage.getInstance().consumerNoInputBox));
		AddConsumerPage.getInstance().consumerNoInputBox.sendKeys(consumerNo.replaceAll("-", ""));
		AddConsumerPage.getInstance().checkDetailBtn.click();
//		Base.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));		
		boolean flag = false;
		
		try {
		if(AddConsumerPage.getInstance().confirmBtn.isDisplayed()) {
//			System.out.println(consumerNo+" - was added Successfully !");
			AddConsumerPage.getInstance().confirmBtn.click();
			Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOf(AddConsumerPage.getInstance().confirmBtn));
			flag=true;
		}else if(AddConsumerPage.getInstance().invalidConsumerMsg.isDisplayed()) {
			System.err.println("\t>"+consumerNo+" - Addition failed. Check Consumer Number is valid");
		}}catch(Exception e) {
//			System.out.println("\t>Handling Exception in Consumer No Addition : "+consumerNo+" \n");
			e.printStackTrace();
		}return flag;
		
	}
	
}

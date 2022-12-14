package com.pom;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddConsumerPage {
	
	public static AddConsumerPage instance = null;
	
	private AddConsumerPage() {}
	
	public static AddConsumerPage getInstance() {
			if (instance == null) {
			instance = new AddConsumerPage();
		}
		return instance;
	}
//###################################################################################################################################	

	@FindBy(xpath="//*[contains(@href,'create')]")
	public  WebElement addConsumerBtn;
	
	@FindBy(id="form:consumerRef")
	public  WebElement consumerNoInputBox;
	
	@FindBy(xpath="//*[contains(text(),'Check detail')]")
	public  WebElement checkDetailBtn;
	
	@FindBy(xpath="//*[contains(text(),'Invalid Consumer No')]")
	public  WebElement invalidConsumerMsg;
	
	@FindBy(xpath="//*[contains(text(),'Confirm')]")
	public  WebElement confirmBtn;
	
	@FindBy(xpath="//*[contains(text(),'Consumer No. successfully added.....')]")
	public  WebElement successMessage;

}

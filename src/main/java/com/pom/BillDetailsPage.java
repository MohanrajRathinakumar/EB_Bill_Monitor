package com.pom;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.application.Base;

public class BillDetailsPage {
	
	
	public static BillDetailsPage instance = null;
	
	private BillDetailsPage() {}
	
	public static BillDetailsPage getInstance() {
			if (instance == null) {
			instance = new BillDetailsPage();
		}
		return instance;
	}
//###################################################################################################################################	

	@FindBy(xpath="//*[contains(@href,'paymentDetail')]")
	public WebElement billDetailsBtn;
	
	@FindBy(xpath="//*[contains(text(),'Consumer No')]/parent::*/ancestor::table[1]/tbody/tr")
	public List<WebElement> tableElements;
	
	@FindBy(xpath="//*[contains(text(),'Consumer No')]/parent::*/ancestor::table[1]/tfoot/tr/td/span[contains(@class,'next')]")
	public WebElement nextPageBtn;
	
//-------------------------------------------------------------------------------------------------------------------------
	
	public boolean nextPageStatus() {
		boolean flag = false;
		String defaultTxt = "ui-paginator-next ui-state-default ui-corner-all";
		String txt = nextPageBtn.getAttribute("class").replace(defaultTxt, "");
		if(!txt.contains("disabled")) {
			flag = true;
		} 
		return flag;
	}
	
//--------------------------------------------------------------------------------------------------------------------------
	
	public boolean moveToNextPage() {
		PageFactory.initElements(Base.driver, instance);
		boolean flag=false;
		try {
			if(nextPageStatus()) {
				nextPageBtn.click();
//				System.out.println("\t>Moving to Next Page..");
				String xpathFor1stElement = "//*[contains(text(),'Consumer No')]/parent::*/ancestor::table[1]/tbody/tr[1]/td[1]";
				WebElement firstElement = Base.driver.findElement(By.xpath(xpathFor1stElement));				
//				Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOfAllElements(tableElements));
				Base.getWait(Duration.ofSeconds(30)).until(ExpectedConditions.invisibilityOf(firstElement));
				flag=true;
			}else{
				System.out.println("\t>Next Page is Not Available..");
			}
			}catch(Exception e) {
			e.printStackTrace();
		}return flag;
	}
//--------------------------------------------------------------------------------------------------------------------------

}

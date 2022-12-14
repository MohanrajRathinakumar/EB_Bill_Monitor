package com.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage {
	
	public static LoginPage instance =null;
	
	private LoginPage() {

	}
	
	public static LoginPage getInstance() {
		if(instance==null) {
			instance= new LoginPage();
		}return instance;
	}
	
//#################################################################################################################
	
	@FindBy(id="userName")
	public WebElement userID;
	
	@FindBy(id="password")
	public WebElement password;
	
	@FindBy(name="submit")
	public WebElement loginBtn;
	
//	@FindBy(xpath="//*[@id='header1']/div/table/tbody/tr[1]/td[1]/a[4]")
//	public WebElement logoutBtn;
	
	@FindBy(linkText ="Logout")
	public WebElement logoutBtn;
}

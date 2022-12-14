package com.process;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.application.Base;
import com.pom.LoginPage;

public class LoginProcess {
	
	private LoginProcess() {
		
	}
	
	public static LoginProcess instance;
	
	public static LoginProcess getInstance () {
		if(instance==null) {
			instance=new LoginProcess();
		}return instance;
	}
//#############################################################################################################################
	
	public void doLogin() {
		PageFactory.initElements(Base.driver, LoginPage.getInstance());
		Base.driver.navigate().to(Base.directUrl);
		Base.getWait(Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOf(LoginPage.getInstance().loginBtn));
		LoginPage.getInstance().userID.sendKeys(Base.userID);
		LoginPage.getInstance().password.sendKeys(Base.password);
		System.out.println("Please enter captcha, then click on Login Button...");
		Base.getWait(Duration.ofSeconds(60)).until(ExpectedConditions.visibilityOf(LoginPage.getInstance().logoutBtn));
		if(LoginPage.getInstance().logoutBtn.isDisplayed()) {
			System.out.println("Logged in Succesfully !");
		}
	}
	
}

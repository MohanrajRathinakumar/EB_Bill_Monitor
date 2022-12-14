package com.pom;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverAction {
	
	private DriverAction() {}
	
	public static DriverAction instance;
	
	public static DriverAction getInstance() {
		if(instance==null) {
			instance= new DriverAction();
		}return instance;
	}
	
//-------------------------------------------------------------------------------------------------------------------------
	
	public WebDriver getWebDriver() {
		WebDriver driver = null;
		if(driver==null) {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 1);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--enable-notifications");
			options.addArguments("start-maximized");
			options.setExperimentalOption("prefs", prefs);

			WebDriverManager.chromedriver().setup();
			if (driver == null) {
				driver = new ChromeDriver(options);
			}
		}
		return driver;
	}
//-----------------------------------------------------------------------------------------------------------------------------
	
}

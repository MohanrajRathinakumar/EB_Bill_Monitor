package com.util;

import org.openqa.selenium.WebDriver;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.core.Snapshot;

public class ScreenShotUtil {
	

private ScreenShotUtil() {}
	
	public static ScreenShotUtil instance;
	
	public static ScreenShotUtil getInstance () {
		if(instance==null) {
			instance=new ScreenShotUtil();
		}return instance;
	}
//#############################################################################################################################
	
	public void getFullPageScreenShot(WebDriver driver,String outputLocationWithoutFileName,String fileName) {
		Capture capture = Capture.FULL_SCROLL;
		Snapshot ss = Shutterbug.shootPage(driver, capture, 500, true).withName(fileName);
		ss.save(outputLocationWithoutFileName);
	}

}

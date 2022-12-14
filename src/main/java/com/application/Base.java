package com.application;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pom.DriverAction;

public class Base {

	public static String inputFileName = "InputSheetForTNEB.xlsx";
	public static String myDocumentLocation=(System.getProperty("user.home") + File.separatorChar + "Documents/").replace('\"','/');
	public static String inputSheetLocation = (myDocumentLocation+inputFileName).replace('\"', '/');
	public static String billOutputLocation = (myDocumentLocation+"BillOutput/").replace("\"", "/");
	public static String billOutputLocationFullPage = (myDocumentLocation+"BillOutput/fullPage/").replace("\"", "/");
	public static List<HashMap<String,String>> inputExcelList;
	public static List<String> consumerNosList;
	public static String url="https://www.tangedco.gov.in/";
	public static String directUrl="https://www.tnebnet.org/awp/login";
	public static String userID="tneb.mr";
	public static String password="v8675927401";
	
	public static WebDriver driver = null;
	
	public static WebDriverWait getWait(Duration duration) {
		WebDriverWait wait = new WebDriverWait(driver,duration);
		return wait;
	}
	
}

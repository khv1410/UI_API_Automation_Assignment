package com.bd.appconfig;

import java.io.File;

import org.ini4j.Ini;
import org.openqa.selenium.WebDriver;

public class Config {
	public static String vUrl, vBrowsername, vProjectName, vBrowserDriverPath, vProjPath, vScreenshotPath, vTestName, vExpectedResult, vTestDataFile, vRunningTestCase;
	public static String strExpectedResult, strActualResult, strStatus;
	public static int vObjectWaitTime, vPageLoadTime;
	public static WebDriver wd;
	
	public Config() {
		try{
			vProjPath = System.getProperty("user.dir");
			String vConfigFilePath = vProjPath + "\\src\\main\\resources\\config\\ProjectConfig.ini";
			
			Ini ini = new Ini(new File(vConfigFilePath));
			vUrl = ini.get("ProjectData", "url"); 
			vProjectName = ini.get("ProjectData", "projectname");
			vObjectWaitTime = Integer.parseInt(ini.get("ProjectData", "objectwaittime"));
			vPageLoadTime = Integer.parseInt(ini.get("ProjectData", "pageload"));
			vBrowserDriverPath = vProjPath + "\\src\\main\\resources\\browserdriver\\";
			vScreenshotPath = vProjPath + "\\src\\main\\resources\\reports\\ErrorScreenshots\\";
			vTestDataFile = ini.get("ProjectData", "testdatafile");
			vBrowsername = ini.get("browser", "browsername");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

package com.bd.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.bd.appconfig.Config;
import com.bd.utilities.GenericFunctions;

import io.github.bonigarcia.wdm.WebDriverManager;



public class DriverBase extends Config{
	static WebDriver driver = null;
	static GenericFunctions gn = new GenericFunctions();
	public static ExtentTest test;
	public static ExtentReports report;	
	public static ExtentHtmlReporter htmlReporter;
	public static void invokeDriver() {
		try {
			if (Config.vBrowsername.equalsIgnoreCase("Chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (Config.vBrowsername.equalsIgnoreCase("Firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else if (Config.vBrowsername.equalsIgnoreCase("IE")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
			}
			driver.manage().window().maximize();
			driver.get(Config.vUrl);
			gn.fnWaitForPageLoad();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static WebDriver getDriver(){
		return driver;
		
	}
	
	public static void setInstance() {
    	driver.close();
    	driver = null;
    }
	
	@BeforeClass
	public void reportTest() {
		htmlReporter = new ExtentHtmlReporter(Config.vProjPath+"\\src\\main\\resources\\reports\\BlazeDemoResults.html");
        report = new ExtentReports();
        report.attachReporter(htmlReporter);
	}
	
	@Test
	public void FindFlightsTest() throws Exception {
		test = report.createTest("FindFlightsTest");
		gn.fnInvokeScenarioMethod("FindFlights", "executeScenario");
	}
	
	@Test
	public void BookTicketTest() throws Exception {
		test = report.createTest("BookTicketTest");
		gn.fnInvokeScenarioMethod("BookFlight", "executeScenario");
	}
	
	@Test
	public void FlightDetailsTest() throws Exception {
		test = report.createTest("FlightDetailsTest");
		gn.fnInvokeScenarioMethod("FlightDetails", "executeScenario");
	}
	
	@AfterClass
	public void flushTest() {
		report.flush();
	}
		
}

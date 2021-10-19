package com.bd.executionclasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.bd.driver.DriverBase;
import com.bd.utilities.GenericFunctions;


public class FlightDetails extends DriverBase{
	GenericFunctions gn = new GenericFunctions();
	
	@BeforeMethod
	public void startExecution() {
		//report = new com.relevantcodes.extentreports.ExtentReports(Config.vProjPath+"\\src\\main\\resources\\reports\\BlazeDemoResults.html");
		//test = report.startTest("FlightDetailsTest");
		
	}
	
	@Test
	public void FlightDetailsTest() throws Exception {
		System.out.println("FlightDetailsTest");
		gn.fnInvokeScenarioMethod("FlightDetails", "executeScenario");
	}
	
	@AfterMethod
	public void endExecution() {
		//report.endTest(test);
		//report.flush();
	}
}

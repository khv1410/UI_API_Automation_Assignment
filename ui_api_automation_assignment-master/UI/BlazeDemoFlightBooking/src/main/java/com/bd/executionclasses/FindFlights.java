package com.bd.executionclasses;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.bd.appconfig.Config;
import com.bd.driver.DriverBase;
import com.bd.utilities.GenericFunctions;


public class FindFlights extends DriverBase{
	GenericFunctions gn = new GenericFunctions();
	
	@Test
	public void FindFlightsTest() throws Exception {
		test = report.createTest("FindFlightsTest");
		System.out.println("FindFlightsTest");
		gn.fnInvokeScenarioMethod("FindFlights", "executeScenario");
	}
	@Test
	public void BookTicketTest() throws Exception {
		test = report.createTest("BookTicketTest");
		System.out.println("BookTicketTest");
		gn.fnInvokeScenarioMethod("BookFlight", "executeScenario");
	}
	@Test
	public void FlightDetailsTest() throws Exception {
		test = report.createTest("FlightDetailsTest");
		System.out.println("FlightDetailsTest");
		gn.fnInvokeScenarioMethod("FlightDetails", "executeScenario");
	}

	/*
	 * @AfterMethod public void endExecution() { report.flush(); }
	 */
}

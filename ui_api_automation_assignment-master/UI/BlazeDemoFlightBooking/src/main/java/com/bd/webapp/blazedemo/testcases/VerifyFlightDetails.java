package com.bd.webapp.blazedemo.testcases;

import com.bd.driver.DriverBase;
import com.bd.utilities.GenericFunctions;
import com.bd.utilities.GenericKeywords;

public class VerifyFlightDetails {
	GenericKeywords gk = new GenericKeywords();
	GenericFunctions gn = new GenericFunctions();
	
	public void executeTestCase() {
		String vTestDataFile = "BookingTestData.json";
		DriverBase.invokeDriver();
		gk.verifyTitle(gn.fnReadJsonData("ExpHomePage", vTestDataFile));
		gk.selectDropdownValue("ddlFromPort_HomePage", gn.fnReadJsonData("FromCity", vTestDataFile));
		gk.selectDropdownValue("ddlToPort_HomePage",gn.fnReadJsonData("ToCity", vTestDataFile));
		gk.clickObject("btnFindFlight_HomePage");
		gk.verifyTitle(gn.fnReadJsonData("ExpReservePage", vTestDataFile));
		gk.clickObject("btnChoose_ReservePage");
		gk.verifyTitle(gn.fnReadJsonData("ExpPurchasePage", vTestDataFile));
		gk.containsText("lblFlightName_PurchasePage", gn.fnReadJsonData("BookingFlightName", vTestDataFile));
		gk.containsText("lblFlightNumber_PurchasePage", gn.fnReadJsonData("BookingFlightNumber", vTestDataFile));
		gk.containsText("lblFlightTotalCost_PurchasePage", gn.fnReadJsonData("BookingTotalCost", vTestDataFile));
		DriverBase.setInstance();
	
	}
}

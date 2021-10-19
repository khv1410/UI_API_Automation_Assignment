package com.bd.webapp.blazedemo.testcases;

import com.bd.driver.DriverBase;
import com.bd.utilities.GenericFunctions;
import com.bd.utilities.GenericKeywords;

public class GetFlight {
	GenericKeywords gk = new GenericKeywords();
	GenericFunctions gn = new GenericFunctions();
	public void executeTestCase() {
		DriverBase.invokeDriver();
		String vTestDataFile = "BookingTestData.json";
		gk.verifyTitle(gn.fnReadJsonData("ExpHomePage", vTestDataFile));
		gk.selectDropdownValue("ddlFromPort_HomePage", gn.fnReadJsonData("FromCity", vTestDataFile));
		gk.selectDropdownValue("ddlToPort_HomePage",gn.fnReadJsonData("ToCity", vTestDataFile));
		gk.clickObject("btnFindFlight_HomePage");
		gk.verifyTitle(gn.fnReadJsonData("ExpReservePage", vTestDataFile));
		gk.verifyWebElementsCount("tblFlightAirline_ReservePage",5);
		DriverBase.setInstance();
	}
}

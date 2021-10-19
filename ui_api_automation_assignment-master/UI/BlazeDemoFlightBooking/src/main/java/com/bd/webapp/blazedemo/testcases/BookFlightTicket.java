package com.bd.webapp.blazedemo.testcases;

import com.bd.driver.DriverBase;
import com.bd.utilities.GenericFunctions;
import com.bd.utilities.GenericKeywords;

public class BookFlightTicket {
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
		gk.enterValue("txtPName_PurchasePage", gn.fnReadJsonData("PName", vTestDataFile));
		gk.enterValue("txtPAddress_PurchasePage", gn.fnReadJsonData("PAddress", vTestDataFile));
		gk.enterValue("txtPCity_PurchasePage", gn.fnReadJsonData("PCity", vTestDataFile));
		gk.enterValue("txtPState_PurchasePage", gn.fnReadJsonData("PState", vTestDataFile));
		gk.enterValue("txtPZipCode_PurchasePage", gn.fnReadJsonData("PZip", vTestDataFile));
		gk.selectDropdownValue("ddlPCardType", gn.fnReadJsonData("PCardType", vTestDataFile));
		gk.enterValue("txtPCCNumber_PurchasePage", gn.fnReadJsonData("PCCNumber", vTestDataFile));
		gk.enterValue("txtPCCMonth_PurchasePage", gn.fnReadJsonData("PCCMonth", vTestDataFile));
		gk.enterValue("txtPCCYear_PurchasePage", gn.fnReadJsonData("PCCYear", vTestDataFile));
		gk.enterValue("txtPNameOnCard_PurchasePage", gn.fnReadJsonData("PNameOnCard", vTestDataFile));
		gk.clickObject("btnPurchaseFilght");
		gk.verifyTitle(gn.fnReadJsonData("ExpConfirmationPage", vTestDataFile));
		gk.verifyText("lblBookingConfMessage", gn.fnReadJsonData("BookingConfirmationMessage", vTestDataFile));
		gk.verifyObjExist("lblBookingConfID");
		DriverBase.setInstance();
	
	}
}

package com.bd.webapp.blazedemo.testscenarios;

import com.bd.utilities.GenericFunctions;

public class FlightDetails {
	
	GenericFunctions gn = new GenericFunctions();
	public void executeScenario() {
		String arrTestCase[] = gn.fnReadJsonData("FlightDetails","TestScenarios.json").split(",");
		for(int i=0; i<arrTestCase.length; i++){
			try {
				gn.fnInvokeTestCaseMethod(arrTestCase[i], "executeTestCase");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}	
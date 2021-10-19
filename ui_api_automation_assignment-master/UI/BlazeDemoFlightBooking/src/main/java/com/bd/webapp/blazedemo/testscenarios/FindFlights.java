package com.bd.webapp.blazedemo.testscenarios;

import com.bd.utilities.GenericFunctions;

public class FindFlights {
	GenericFunctions gn = new GenericFunctions();
	public void executeScenario() {
		String arrTestCase[] = gn.fnReadJsonData("FindFlights","TestScenarios.json").split(",");
		
		for(int i=0; i<arrTestCase.length; i++){
			try {
				gn.fnInvokeTestCaseMethod(arrTestCase[i], "executeTestCase");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

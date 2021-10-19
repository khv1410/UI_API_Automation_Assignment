package com.bd.utilities;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.bd.appconfig.Config;
import com.bd.driver.DriverBase;

public class GenericKeywords {
	private List<WebElement> elemsList = null;
	GenericFunctions gn = new GenericFunctions();
	Robot robo = null;
	public static boolean bool = true;

	public void verifyTitle(String vExpTitle) {
		Config.strExpectedResult = "URL should be launch";
		Config.vTestName = "verifyTitle_" + vExpTitle;
		try {
			String vActTitle = DriverBase.getDriver().getTitle();

			if (vActTitle.equals(vExpTitle)) {
				Config.strActualResult = vExpTitle + " URL is launched";

				DriverBase.test.log(Status.PASS, Config.strActualResult);

			} else {
				Config.strActualResult = vActTitle + " URL is not launched";

				gn.fnCaptureScreenshsot(Config.vScreenshotPath);
				DriverBase.test.log(Status.FAIL, Config.strActualResult);

			}

		} catch (Exception e) {
			Config.strActualResult = e.getMessage();

			gn.fnCaptureScreenshsot(Config.vScreenshotPath);
			DriverBase.test.log(Status.FAIL, Config.strActualResult);

			e.printStackTrace();
		}

	}

	public void selectDropdownValue(String vObject, String vExp) {
		Config.vTestName = "selectDropdownValue_" + vExp;
		try {
			elemsList = gn.getORLocator(vObject);
			Config.strExpectedResult = "Value should be select from object";
			if (elemsList.size() == 1 && elemsList.get(0).isEnabled()) {
				Select sel = new Select(elemsList.get(0));
				sel.selectByVisibleText(vExp);
				Thread.sleep(2000);
				String vAct = sel.getFirstSelectedOption().getText();
				if (vAct.equals(vExp)) {
					Config.strActualResult = "Value " + vAct + " is selected from the dropdown";

					DriverBase.test.log(Status.PASS, Config.strActualResult);

				} else {
					Config.strActualResult = "Value " + vAct + " is not selected from the dropdown";

					gn.fnCaptureScreenshsot(Config.vScreenshotPath);
					DriverBase.test.log(Status.FAIL, Config.strActualResult);

				}
			} else {
				Config.strActualResult = "Object is not found";

				gn.fnCaptureScreenshsot(Config.vScreenshotPath);
				DriverBase.test.log(Status.FAIL, Config.strActualResult);

			}
		} catch (Exception e) {
			Config.strActualResult = e.getMessage();

			gn.fnCaptureScreenshsot(Config.vScreenshotPath);
			DriverBase.test.log(Status.FAIL, Config.strActualResult);

			e.printStackTrace();
		}
	}

	public void clickObject(String vObject) {
		Config.vTestName = "clickObject";
		try {
			elemsList = gn.getORLocator(vObject);
			Config.strExpectedResult = "Object should be click.";
			if (elemsList.get(0).isEnabled()) {
				elemsList.get(0).click();
				Config.strActualResult = "Object is clicked";

				DriverBase.test.log(Status.PASS, Config.strActualResult);

			} else {
				System.out.println("Object not enabled");
				Config.strActualResult = "Object not enabled";

				gn.fnCaptureScreenshsot(Config.vScreenshotPath);
				DriverBase.test.log(Status.FAIL, Config.strActualResult);

			}
		} catch (Exception e) {
			Config.strActualResult = e.getMessage();

			gn.fnCaptureScreenshsot(Config.vScreenshotPath);
			DriverBase.test.log(Status.FAIL, Config.strActualResult);

			e.printStackTrace();
		}

	}

	public void verifyWebElementsCount(String vObject, int vSize) {
		Config.vTestName = "verifyWebElementsCount";
		try {
			elemsList = gn.getORLocator(vObject);
			Config.strExpectedResult = "Object count should match.";
			if (elemsList.size() == vSize) {
				Config.strActualResult = "Object count is matched.";

				DriverBase.test.log(Status.PASS, Config.strActualResult);

			} else {
				Config.strActualResult = "Object count is not matched.";

				gn.fnCaptureScreenshsot(Config.vScreenshotPath);
				DriverBase.test.log(Status.FAIL, Config.strActualResult);

			}

		} catch (Exception e) {
			Config.strActualResult = e.getMessage();

			gn.fnCaptureScreenshsot(Config.vScreenshotPath);
			DriverBase.test.log(Status.FAIL, Config.strActualResult);

			e.printStackTrace();
		}
	}

	public void verifyText(String vObject, String vExpText) {
		Config.vTestName = "verifyText_" + vExpText;
		String vActualText = "";
		try {
			elemsList = gn.getORLocator(vObject);
			Config.strExpectedResult = vExpText + " should match with actual text";
			if (elemsList.get(0).isEnabled()) {
				vActualText = elemsList.get(0).getText().trim();

				if (vActualText.isEmpty())
					vActualText = elemsList.get(0).getAttribute("value");
				if (vExpText.equals(vActualText)) {
					Config.strActualResult = vExpText + " is matched with " + vActualText;

					DriverBase.test.log(Status.PASS, Config.strActualResult);

				} else {
					Config.strActualResult = vExpText + " is not matched with " + vActualText;

					gn.fnCaptureScreenshsot(Config.vScreenshotPath);
					DriverBase.test.log(Status.FAIL, Config.strActualResult);

				}

			} else {
				Config.strActualResult = "Object does not exist";

				gn.fnCaptureScreenshsot(Config.vScreenshotPath);
				DriverBase.test.log(Status.FAIL, Config.strActualResult);

			}

		} catch (Exception e) {
			Config.strActualResult = e.getMessage();

			gn.fnCaptureScreenshsot(Config.vScreenshotPath);
			DriverBase.test.log(Status.FAIL, Config.strActualResult);

			e.printStackTrace();
		}
	}

	public void enterValue(String vObject, String vExpStringVal) {
		Config.vTestName = "enterValue" + vExpStringVal;
		try {
			elemsList = gn.getORLocator(vObject);
			Config.strExpectedResult = vExpStringVal + " should be enter in an object";
			if (elemsList.get(0).isEnabled()) {
				fnClearTextByJavaScript(elemsList.get(0));
				elemsList.get(0).click();
				elemsList.get(0).sendKeys(vExpStringVal);
				String vActVal = elemsList.get(0).getAttribute("value");

				if (vActVal.equals(vExpStringVal)) {
					Config.strActualResult = "Value " + vActVal + " is entered in the text box";

					DriverBase.test.log(Status.PASS, Config.strActualResult);

				} else {
					Config.strActualResult = "Value " + vExpStringVal + " is not entered in the text box";

					gn.fnCaptureScreenshsot(Config.vScreenshotPath);
					DriverBase.test.log(Status.FAIL, Config.strActualResult);

				}
			} else {
				Config.strActualResult = "Object is not enabled";

				gn.fnCaptureScreenshsot(Config.vScreenshotPath);
				DriverBase.test.log(Status.FAIL, Config.strActualResult);

			}
		} catch (Exception e) {
			Config.strActualResult = e.getMessage();

			gn.fnCaptureScreenshsot(Config.vScreenshotPath);
			DriverBase.test.log(Status.FAIL, Config.strActualResult);

			e.printStackTrace();
		}
	}

	public void enterValue(String vObject, int vExpIntVal) {
		Config.vTestName = "enterValue" + vExpIntVal;
		String vExpVal = String.valueOf(vExpIntVal);
		try {
			elemsList = gn.getORLocator(vObject);
			Config.strExpectedResult = vExpIntVal + " should be enter in an object";
			if (elemsList.get(0).isEnabled()) {
				fnClearTextByJavaScript(elemsList.get(0));
				elemsList.get(0).click();
				elemsList.get(0).sendKeys(vExpVal);
				String vActVal = elemsList.get(0).getAttribute("value");

				if (vActVal.equals(vExpVal)) {
					Config.strActualResult = "Value " + vActVal + " is entered in the text box";

					DriverBase.test.log(Status.PASS, Config.strActualResult);

				} else {
					Config.strActualResult = "Value " + vExpIntVal + " is not entered in the text box";

					gn.fnCaptureScreenshsot(Config.vScreenshotPath);
					DriverBase.test.log(Status.FAIL, Config.strActualResult);

				}
			} else {
				Config.strActualResult = "Object is not enabled";

				gn.fnCaptureScreenshsot(Config.vScreenshotPath);
				DriverBase.test.log(Status.FAIL, Config.strActualResult);

			}
		} catch (Exception e) {
			Config.strActualResult = e.getMessage();

			gn.fnCaptureScreenshsot(Config.vScreenshotPath);
			DriverBase.test.log(Status.FAIL, Config.strActualResult);

			e.printStackTrace();
		}
	}

	public void verifyObjExist(String vObject) {
		Config.vTestName = "verifyObjExist";
		try {
			elemsList = gn.getORLocator(vObject);
			Config.strExpectedResult = "Object should exist";
			if (elemsList.size() == 1 && elemsList.get(0).isEnabled()) {
				Config.strActualResult = "Object is exist";
				DriverBase.test.log(Status.PASS, Config.strActualResult);

			} else {
				Config.strActualResult = "Object does not exist";

				gn.fnCaptureScreenshsot(Config.vScreenshotPath);
				DriverBase.test.log(Status.FAIL, Config.strActualResult);

			}
		} catch (Exception e) {
			Config.strActualResult = e.getMessage();

			gn.fnCaptureScreenshsot(Config.vScreenshotPath);
			DriverBase.test.log(Status.FAIL, Config.strActualResult);

			e.printStackTrace();
		}
	}

	public void containsText(String vObject, String vExpText) {
		Config.vTestName = "containsText_" + vExpText;
		String vActualText = "";
		try {
			elemsList = gn.getORLocator(vObject);
			Config.strExpectedResult = vExpText + " should contain in actual text";
			if (elemsList.get(0).isEnabled()) {
				vActualText = elemsList.get(0).getText().trim();

				if (vActualText.isEmpty())
					vActualText = elemsList.get(0).getAttribute("value");
				if (vActualText.contains(vExpText)) {
					Config.strActualResult = vExpText + " is contained in " + vActualText;

					DriverBase.test.log(Status.PASS, Config.strActualResult);

				} else {
					Config.strActualResult = vExpText + " is not contained in " + vActualText;

					gn.fnCaptureScreenshsot(Config.vScreenshotPath);
					DriverBase.test.log(Status.FAIL, Config.strActualResult);

				}

			} else {
				Config.strActualResult = "Object does not exist";

				gn.fnCaptureScreenshsot(Config.vScreenshotPath);
				DriverBase.test.log(Status.FAIL, Config.strActualResult);

			}

		} catch (Exception e) {
			Config.strActualResult = e.getMessage();

			gn.fnCaptureScreenshsot(Config.vScreenshotPath);
			DriverBase.test.log(Status.FAIL, Config.strActualResult);

			e.printStackTrace();
		}
	}

	public void fnClearTextByJavaScript(WebElement vElem) {
		JavascriptExecutor js = (JavascriptExecutor) DriverBase.getDriver();
		js.executeScript("arguments[0].value = '';", vElem);
	}

}

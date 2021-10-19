package com.bd.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.bd.appconfig.Config;
import com.bd.driver.DriverBase;


public class GenericFunctions {
	public WebElement element; 
	public static String vORkey, vORValue, vCurrentScenarioName, vCurrentScenarioMethodName, vCurrentTestCaseName, vCurrentTestCaseMethodName; //This string will capture the value of OR key
	public static WebDriverWait wait;
	
	public void DynamicWaitforObj(int vWait, By obj){
		WebDriverWait wait = new WebDriverWait(DriverBase.getDriver(), vWait);
		try{	
			element = wait.until(ExpectedConditions.presenceOfElementLocated(obj));
			element = wait.until(ExpectedConditions.visibilityOfElementLocated(obj));
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void fnCaptureScreenshsot(String ScreenshotPath){	
		File directory = new File(ScreenshotPath);
		if(!directory.exists()){
			directory.mkdir();			
		}
		TakesScreenshot scrshot = ((TakesScreenshot)DriverBase.getDriver());
		File SrcFile = scrshot.getScreenshotAs(OutputType.FILE);
		ScreenshotPath=ScreenshotPath+Config.vTestName+".png";
		File DestFile=new File(ScreenshotPath);
		try{
			FileUtils.copyFile(SrcFile, DestFile);
	    }catch(IOException e){
	    	e.printStackTrace();
		}
        
	}
	
			
	public void fnWaitForPageLoad() throws Exception {
        Boolean flag = false;
        while(!flag){
        	try{
        		if(((JavascriptExecutor)DriverBase.getDriver()).executeScript("return document.readyState").toString().equals("complete"));
        			flag = true;
        	}catch(NullPointerException e){
        		e.printStackTrace();
        		break;
        	}
        }
    }
	
	
	public String fnReadJsonData(String vKey, String vFileName) {
		String val="";
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(Config.vProjPath+"\\src\\main\\resources\\webapp\\blazedemo\\"+vFileName));
			JSONObject jsonObject = (JSONObject) obj;
			val = jsonObject.get(vKey).toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		return val;
	}
	
	
	public void fnInvokeScenarioMethod(String scenarioName, String methodName) throws Exception{
		try{
			vCurrentScenarioName = scenarioName;
			if(methodName.equals("executeScenario")){
				vCurrentScenarioMethodName = methodName;
			}
			
			Class oScnClass = Class.forName("com.bd.webapp.blazedemo.testscenarios."+scenarioName.trim());
			Object oScnObject = oScnClass.newInstance();
			Method oMethodObject = oScnClass.getMethod(methodName, null);
			oMethodObject.invoke(oScnObject);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void fnInvokeTestCaseMethod(String testcaseName, String methodName) throws Exception{
		try{
			vCurrentTestCaseName = testcaseName;
			if(methodName.equals("executeTestCase")){
				vCurrentTestCaseMethodName = methodName;
			}
			
			Class oScnClass = Class.forName("com.bd.webapp.blazedemo.testcases."+vCurrentTestCaseName);
			Object oScnObject = oScnClass.newInstance();
			Method oMethodObject = oScnClass.getMethod(methodName, null);
			oMethodObject.invoke(oScnObject);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<WebElement> getORLocator(String vObj) throws Exception{
		String str[] = fnReadObjectValueFromOR(vObj);
		List<WebElement> elems = fnFindWebElements(str);
		return elems;
	}
	
	public String[] fnReadObjectValueFromOR(String vOKey) throws Exception{
		FileInputStream fs = new FileInputStream(Config.vProjPath+"\\src\\main\\resources\\webapp\\blazedemo\\ObjectRepository.properties");
		Properties prop1 = new Properties();
		prop1.load(fs);
		vORkey = vOKey;
		String refOR = prop1.getProperty(vOKey);
		vORValue = refOR;
		String[] str=refOR.split("@@@");
		return str;
	}
	
	public List<WebElement> fnFindWebElements(String str[]){
		List<WebElement> vElems = null;
		switch (str[0])	{
			case "xpath":
				DynamicWaitforObj(Config.vObjectWaitTime, By.xpath(str[1]));
				vElems = DriverBase.getDriver().findElements(By.xpath(str[1]));
				break;
			case "name":
				DynamicWaitforObj(Config.vObjectWaitTime, By.name(str[1]));
				vElems = DriverBase.getDriver().findElements(By.name(str[1]));
				break;
			case "id":
				DynamicWaitforObj(Config.vObjectWaitTime, By.id(str[1]));
				vElems = DriverBase.getDriver().findElements(By.id(str[1]));
				break;
			case "linkText":
				DynamicWaitforObj(Config.vObjectWaitTime, By.linkText(str[1]));
				vElems = DriverBase.getDriver().findElements(By.linkText(str[1]));
				break;
			case "partialLinkText":
				DynamicWaitforObj(Config.vObjectWaitTime, By.partialLinkText(str[1]));
				vElems = DriverBase.getDriver().findElements(By.partialLinkText(str[1]));
				break;
			case "className":
				DynamicWaitforObj(Config.vObjectWaitTime, By.className(str[1]));
				vElems = DriverBase.getDriver().findElements(By.className(str[1]));
				break;
			case "cssSelector":
				DynamicWaitforObj(Config.vObjectWaitTime, By.cssSelector(str[1]));
				vElems = DriverBase.getDriver().findElements(By.cssSelector(str[1]));
				break;
			default: System.out.println("Invalid Object String Passed in OR Property File");
				
			}
		return vElems;
	}
}

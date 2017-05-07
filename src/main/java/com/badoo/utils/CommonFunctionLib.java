///**
// *
// */
//package com.badoo.utils;
//
//import io.appium.java_client.MobileBy;
//import io.appium.java_client.android.AndroidDriver;
//import io.appium.java_client.ios.IOSDriver;
//import io.appium.java_client.ios.IOSElement;
//import io.appium.java_client.remote.MobileBrowserType;
//import io.appium.java_client.remote.MobileCapabilityType;
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.CookieStore;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.params.ClientPNames;
//import org.apache.http.client.params.CookiePolicy;
//import org.apache.http.client.protocol.ClientContext;
//import org.apache.http.cookie.ClientCookie;
//import org.apache.http.impl.client.BasicCookieStore;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.cookie.BasicClientCookie2;
//import org.apache.http.protocol.BasicHttpContext;
//import org.apache.http.protocol.HttpContext;
//import org.openqa.selenium.*;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.interactions.Action;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.remote.RemoteWebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.AddressException;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.io.*;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author Amit Rawat
// *
// */
//public class CommonFunctionLib {
//	public AndroidDriver ad;
//	public WebDriver driver;
//
//	WebDriverWait wait;
////	Properties properties;
//	DesiredCapabilities objCapabilities;
//	public String AppPath;
//	/**
//	 * These objects are made non-static. The objects of this class are re-created at some point of time.
//	 */
//	public Boolean locationServiceEnabled;
//	public Boolean doFullReset;
//	FileWriter fw;
//	PrintWriter pw;
//
//
//	//Constructor with WebDriver argument
//	public CommonFunctionLib(IOSDriver driver)
//	{
//		this.driver = driver;
//		this.locationServiceEnabled = Boolean.parseBoolean(ConfigManager.getProperties().getProperty("locationServiceEnabled").trim().toLowerCase());
//		doFullReset = true;
//	}
//	//Constructor with no argument
//	public CommonFunctionLib()
//	{
//
//	}
//	public void WebdriverWaitForPage(String time )
//	{
//		driver.manage().timeouts().implicitlyWait(Long.parseLong(time), TimeUnit.SECONDS);
//	}
//
//	public void WebdriverWaitForPage()
//	{
//		WebdriverWaitForPage(ConfigManager.getProperties().getProperty("globalTimeOut"));
//	}
//    public AndroidDriver StartIOSDriver(boolean noresetFlag){
//        return this.StartDriver();
//    }
//
//
//	public AndroidDriver StartDriver()
//	{
//        String deviceType = ConfigManager.getProperties().getProperty("deviceType").toLowerCase();
//		String Screenshotpath = System.getProperty("user.dir") +  "/Screenshots/";
//        AppPath = System.getProperty("user.dir") +  "/src/main/resources/Badoo.apk";
//
//		try
//		{
//
//			switch (deviceType)
//			{
//			case "AndroidNative":
//				//iOSAppPath = ConfigManager.getProperties().getProperty("AppPath").trim();
//
//				objCapabilities.setCapability(MobileCapabilityType.APP,"/Users/amit.rawat/Desktop/Badoo.apk");
//				objCapabilities.setCapability(MobileCapabilityType.APP_PACKAGE,"com.badoo.mobile");
//				objCapabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,".android.BadooActivity");
//				objCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"4d00c81001584085");
//				objCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0.1");
//				objCapabilities.setCapability(MobileCapabilityType.LAUNCH_TIMEOUT,"300000");
//				objCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
//				objCapabilities.setCapability("noReset", false);
////                if(DriverNoResetFlag){
////                    objCapabilities.setCapability("noReset", DriverNoResetFlag);
////                }
////                if(locationServiceEnabled=true){
////                    objCapabilities.setCapability("autoAcceptAlerts", false);
////                }
//                System.out.println("Starting the Appium Driver");
//				ad = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), objCapabilities);
//                System.out.println("Appium Driver Successfully Started");
//                break;
//
//			case "AndroidWeb":
//				objCapabilities.setCapability(MobileCapabilityType.BROWSER_NAME, MobileBrowserType.CHROME);
//				objCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"4d00c81001584085");
//				objCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.0.1");
//				objCapabilities.setCapability(MobileCapabilityType.LAUNCH_TIMEOUT,"300000");
//				objCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
//
//				System.out.println("Starting the Appium Driver");
//				ad = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), objCapabilities);
//				System.out.println("Appium Driver Successfully Started");
//				break;
//
//			default:
//				System.out.println("None of the Driver Type Case Matched");
//				return null;
//			}
//
//			return ad;
//		}
//		catch (Exception e)
//		{
//			AddToLog("CurrentTestClassLog", "error", "Failed to start driver: '" +deviceType + "'. Error Type: "+this.getStackTraceAsString(e));
//			return null;
//		}
//	}
//
//	public void ShutDownDriver()
//	{
//		if (ad!=null) {
//			try
//			{
//				try {Thread.sleep(2500);} catch (InterruptedException e1) {}
//				ad.closeApp();
//				ad.quit();
//			}
//			catch(WebDriverException e){
//				try {Thread.sleep(2500);} catch (InterruptedException e1) {}
//			}
//		}
//
//	}
//    public WebElement FindElement(WebElement parentelement, By mobBy ,int timeoutInSeconds )
//    {
//        WebElement webElement;
//        WebDriverWait wait;
//        try
//        {
//            webElement =   parentelement.findElement(mobBy);
//            return webElement;
//        }catch(Exception e){
//            AddToLog("CurrentTestCaseLog", "error", "Failed to find element: '" + "Locator" + "'. Error Type: "+e.getClass());
//            return null;
//        }
//    }
//	public WebElement FindElement(WebElement parentelement, MobileLocator LocatorType, String LocatorString )
//	{
//
//		WebElement webElement;
//		WebDriverWait wait;
//		try
//		{
//			switch (LocatorType)
//			{
//			case ByAccessibilityId:
//					webElement =   ((IOSDriver) parentelement).findElementByAccessibilityId(LocatorString);
//					break;
//			case ByXPath:
//				webElement =   parentelement.findElement(MobileBy.xpath(LocatorString));
//				break;
//			case ByClassName:
//				webElement =  parentelement.findElement(MobileBy.className(LocatorString));
//				break;
//			case ByIosUIAutomation:
//				webElement =   parentelement.findElement(MobileBy.IosUIAutomation(LocatorString));
//				break;
//			case ByName:
//				webElement =   parentelement.findElement(By.name(LocatorString));
//				break;
//			default:
//				return null;
//
//			}
//			return webElement;
//		}
//		catch(Exception e){
//			AddToLog("CurrentTestCaseLog", "error", "Failed to find element: '" + LocatorString + "'. Error Type: "+e.getClass());
//			return null;
//		}
//	}
//    public WebElement FindElement(By mobBy ,int timeoutInSeconds ){
//        WebElement webElement;
//        WebDriverWait wait;
//        try {
//            if (timeoutInSeconds > 0) {
//                wait = new WebDriverWait(driver, timeoutInSeconds);
//                webElement = wait.until(
//                        ExpectedConditions.presenceOfElementLocated(mobBy));
//            } else {
//                webElement = driver.findElement(mobBy);
//            }
//            return webElement;
//        }catch (Exception e)
//        {
//            AddToLog("CurrentTestCaseLog", "error", "Failed to find element: '" + "Locator" + "'. Error Type: "+e.getClass());
//            return null;
//        }
//    }
//
//	public WebElement FindElement(MobileLocator LocatorType, String LocatorString ,int timeoutInSeconds )
//	{
//		WebElement webElement;
//		WebDriverWait wait;
//		try
//		{
//			switch (LocatorType)
//			{
//			case ByAccessibilityId:
//				if (timeoutInSeconds > 0)
//				{
//					wait = new WebDriverWait(driver,timeoutInSeconds);
//					webElement = wait.until(
//							ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(LocatorString)));
//					//                        	webElement = driver.findElementByAccessibilityId(LocatorString);
//					//                        	new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(webElement));
//				}else{
//					webElement =   driver.findElement(MobileBy.AccessibilityId(LocatorString));
//				}
//				break;
//			case ByXPath:
//				if (timeoutInSeconds > 0)
//				{
//					wait = new WebDriverWait(driver,timeoutInSeconds);
//					webElement =  wait.until(
//							ExpectedConditions.visibilityOfElementLocated(MobileBy.xpath(LocatorString)));
//
//					//                        	webElement = driver.findElement(By.xpath(LocatorString));
//					//                        	new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(webElement));
//				}else{
//					webElement = driver.findElement(MobileBy.xpath(LocatorString));
//				}
//				break;
//			case ByClassName:
//				if (timeoutInSeconds > 0)
//				{
//					wait = new WebDriverWait(driver,timeoutInSeconds);
//					webElement =  wait.until(
//							ExpectedConditions.visibilityOfElementLocated(MobileBy.className(LocatorString)));
//					//                        	webElement = driver.findElementByClassName(LocatorString);
//					//                        	new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(webElement));
//				}else{
//					webElement = driver.findElement(MobileBy.className(LocatorString));
//				}
//				break;
//			case ByIosUIAutomation:
//				if (timeoutInSeconds > 0)
//				{
//					wait = new WebDriverWait(driver,timeoutInSeconds);
//					webElement =  wait.until(
//							ExpectedConditions.visibilityOfElementLocated(MobileBy.IosUIAutomation(LocatorString)));
//					//                        	webElement = driver.findElementByIosUIAutomation(LocatorString);
//					//                        	new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(webElement));
//				}else{
//					webElement =  driver.findElement(MobileBy.IosUIAutomation(LocatorString));
//				}
//				break;
//			case ByName:
//				if (timeoutInSeconds > 0)
//				{
//					wait = new WebDriverWait(driver,timeoutInSeconds);
//					webElement =  wait.until(
//							ExpectedConditions.visibilityOfElementLocated(MobileBy.name(LocatorString)));
//					//                        	webElement = driver.findElementByIosUIAutomation(LocatorString);
//					//                        	new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.visibilityOf(webElement));
//				}else{
//					webElement =  driver.findElement(MobileBy.name(LocatorString));
//				}
//			default:
//				return null;
//
//			}
//			return webElement;
//		}
//		catch(Exception e){
//            return null;
//		}
//	}
//
//
//	public WebElement getElement(By locator, int timeoutInSeconds )
//	{
//		WebElement webElement;
//		WebDriverWait wait;
//		try{
//			if (timeoutInSeconds > 0)
//			{
//				wait = new WebDriverWait(ad,timeoutInSeconds);
//				webElement = wait.until(
//						ExpectedConditions.presenceOfElementLocated(locator));
//			}else{
//				webElement =   ad.findElement(locator);
//			}
//		}catch(Exception e){
//			webElement = null;
//		}
//		return webElement;
//	}
//
//
//    //Writing overloaded getElement() method
//    public WebElement getElement(By mobBy, Integer timeoutInSeconds)
//    {
//        WebElement webElement;
//        WebDriverWait wait;
//
//        try
//        {
//
//
//            if (timeoutInSeconds > 0) {
//                wait = new WebDriverWait(driver, timeoutInSeconds);
//                webElement = wait.until(
//                        ExpectedConditions.presenceOfElementLocated(mobBy));
//
//
//            } else {
//
//                webElement = driver.findElement(mobBy);
//
//            }
//
//            return webElement;
//
//
//        }catch (Exception e)
//        {
//
//            return null;
//
//        }
//
//
//
//    }
//    public boolean ClearInputField(WebElement webElement){
//        boolean state = false;
//        try{
//            IOSElement mobileWebElement = (IOSElement) webElement;
//            mobileWebElement.clear();
//            return true;
//        }catch(Exception e){
//            return false;
//        }
//    }
//    public boolean SendKeys_Web(WebElement webElement, String value){
//        boolean state = false;
//        webElement.clear();
//        try{
//            webElement.sendKeys(value);
//            state = true;
//        }catch(Exception e){
//            AddToLog("CurrentTestCaseLog", "error", "Error occurred while sendkeys on WebElement: " + getStackTraceAsString(e));
//            state = false;
//        }
//        return state;
//    }
//    /*
//    This method closes the keyboard,we just need to pass the keyname which closes the keyboard.
//    In some cases it could be "return","Done", "Next"
//     */
//    public boolean hideKeyboard(String keyScreenName){
//        boolean state = true;
//        try{
//            driver.findElementByXPath("//UIAKeyboard[1]/UIAButton[@label='" +keyScreenName+"']").click();
//        }catch(Exception e){
//            AddToLog("CurrentTestCaseLog", "info", "Error occurred in hiding the keyboard: " + getStackTraceAsString(e));
//            state = false;
//        }
//        return state;
//    }
//	public boolean SendKeys(WebElement webElement, String value){
//		boolean state = false;
//		IOSElement mobileWebElement;
////        this.ClearInputField(webElement);
//		try{
//			mobileWebElement = (IOSElement) webElement;
//            mobileWebElement.setValue(value);
//			state = true;
//		}
//		catch(ClassCastException e1){
//			webElement.sendKeys(value);
//			state = true;
//		}
//		catch(Exception e){
//			AddToLog("CurrentTestCaseLog", "info", "Error occurred while sendkeys on WebElement: " + e.getClass());
//            state = false;
//		}
//		return state;
//
//	}
//
//    public boolean IOSTap(WebElement webElement)
//    {
//
//        try {
//            IOSElement elemIOS = (IOSElement) webElement;
//
//            elemIOS.tap(1, 5);
//
//            return true;
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//            return false;
//        }
//
//    }
//
//	public boolean WebDriverClick (WebElement webElement)
//	{
//		boolean state = false;
//		try
//		{
//			webElement.click();
//			try
//			{
//				Thread.sleep(2000);
//
//				//        			AddToLog("CurrentTestCaseLog", "info", "Passed. Successfully clicked.");
//				state  =true;
//			}
//			catch(InterruptedException e){}
//		}
//		catch(Exception e)
//		{
//			AddToLog("CurrentTestCaseLog", "info", "Error occurred while clicking on WebElement: " + e.getClass());
//
//		}
//		return state;
//	}
//
//	public String GetElementAttributeValue(WebElement objWebElement, String attribute)
//	{
//		try
//		{
//			AddToLog("CurrentTestCaseLog", "info", "Info: Get '"+attribute+"' Attribute value for '"+getElementXPath(objWebElement)+"' object ");
//			return objWebElement.getAttribute(attribute);
//		}
//		catch(org.openqa.selenium.NoSuchElementException e)
//		{
//			AddToLog("CurrentTestCaseLog", "info", "Error: caught 'ElementNotFoundException' exception. Failed to get '"+ attribute +"' value for '"+objWebElement+"' on '"+driver.getTitle()+"' page");
//			return "";
//		}
//		catch(ElementNotVisibleException e)
//		{
//			AddToLog("CurrentTestCaseLog", "info", "Error: caught 'ElementNotVisibleException' exception. Failed to get '"+ attribute +"' value for '"+objWebElement+"' on '"+driver.getTitle()+"' page");
//			return "";
//		}
//		catch(WebDriverException e)
//		{
//			AddToLog("CurrentTestCaseLog", "info", "Error: caught 'WebDriverException' exception. Failed to get '"+ attribute +"' value for '"+objWebElement+"' on '"+driver.getTitle()+"' page");
//			return "";
//		}
//		catch(NullPointerException e5)
//		{
//			AddToLog("CurrentTestCaseLog", "info", "Info. Caught 'NullPointerException' exception while try to get Element Attribute ("+attribute+") value on '"+driver.getTitle()+"'.");
//			return "";
//		}
//		catch(Exception e)
//		{
//			System.out.println("Failed to find object ("+objWebElement+") property'"+attribute+"' value.");
//			AddToLog("CurrentTestCaseLog", "info", "Failed to get '"+ attribute +"' value. Error Message: "+e.getMessage());
//			return "";
//		}
//	}
//
//
//	public boolean AcceptAlert()
//	{
//		try
//		{
//			Alert alert = driver.switchTo().alert();
//			alert.accept();
//			return true;
//		}
//		catch(Exception e){ return false;}
//	}
//	public boolean declineAlert()
//	{
//		try
//		{
//			Alert alert = driver.switchTo().alert();
//			alert.dismiss();
//			return true;
//		}
//		catch(Exception e){ return false;}
//	}
//	public void SwipeRight(WebElement element){
//		//Executing swipe on in the case of iOS simulators. Skipping it for Android Chrome as this swipe will not yet implemented on it.
//		try{
//			if((!GetDriverInfo().get("DriverName").toLowerCase().equals("androidchrome"))
//					&& (!GetDriverInfo().get("DriverName").toLowerCase().equals("chrome"))
//					&& (!GetDriverInfo().get("DriverName").toLowerCase().equals("chrome-mac"))
//					&& (!GetDriverInfo().get("DriverName").toLowerCase().equals("safari"))){
//				double browser_top_offset = 0.0;
//				if(GetDriverInfo().get("DriverType").trim().equalsIgnoreCase("mobile")){
//					browser_top_offset = 0;
//				}else if(GetDriverInfo().get("DriverType").trim().equalsIgnoreCase("tablet")){
//					browser_top_offset = 80;
//				}
//				RemoteWebElement remoteelem = ((RemoteWebElement)element);
//				JavascriptExecutor js = (JavascriptExecutor)driver;
//				String script = "return Math.max(document.documentElement.clientHeight, window.innerHeight || 0)";
//				Long pageheight1=(Long)js.executeScript(script);
//				Long pagewidth1=(Long)js.executeScript("return Math.max(document.documentElement.clientWidth, window.innerWidth || 0)");
//				//       		Long pageheight2=(Long)js.executeScript("return window.innerHeight");
//				Point eloc =  remoteelem.getLocation();
//				double yloc = eloc.getY();
//				double xstartloc = eloc.getX();
//				double xendloc = eloc.getX() + remoteelem.getSize().width;
//				double swipe_startxratio = xstartloc/pagewidth1;
//				double swipe_endxratio = xendloc/pagewidth1;
//				double elemheight = remoteelem.getSize().getHeight()/2;
//				double yratio = (yloc + elemheight/2 + browser_top_offset)/pageheight1;
//				if(swipe_startxratio < 0.1){swipe_startxratio = 0.1;}
//				if(swipe_endxratio > 0.9){swipe_endxratio = 0.9;}
//				HashMap<String, Double> swipeObject = new HashMap<String, Double>();
//				swipeObject.put("startX", swipe_endxratio);
//				swipeObject.put("startY", yratio);
//				swipeObject.put("endX", swipe_startxratio);
//				swipeObject.put("endY", yratio);
//				swipeObject.put("duration", 0.8);
//				js.executeScript("mobile: swipe", swipeObject);
//			}
//			if(GetDriverInfo().get("DriverName").toLowerCase().equals("chrome")
//					|GetDriverInfo().get("DriverName").toLowerCase().equals("chrome-mac")){
//				Actions builder=new Actions(driver);
//				element.getSize();
//
//				Action dragAndDrop=builder.clickAndHold(element).moveToElement(element, element.getLocation().x+90, element.getLocation().y).release().build();
//				dragAndDrop.perform();
//				try {Thread.sleep(4000);} catch (InterruptedException e) {}
//			}
//			if(GetDriverInfo().get("DriverName").toLowerCase().equals("safari")){
//				JavascriptExecutor js = (JavascriptExecutor)driver;
//				js.executeScript("$('.owl-wrapper').trigger('owl.next')");
//			}
//		}
//		catch(Exception e){
//			System.out.println(e.getMessage());
//		}
//	}
//
//	public void SwipeBottom(WebElement element){
//		if(!GetDriverInfo().get("DriverName").equals("androidchrome") && !GetDriverInfo().get("DriverType").toLowerCase().equals("desktop")){
//			double browser_top_offset = 0.0;
//			if(GetDriverInfo().get("DriverType").trim().equalsIgnoreCase("mobile")){
//				browser_top_offset = 0;
//			}else if(GetDriverInfo().get("DriverType").trim().equalsIgnoreCase("tablet")){
//				browser_top_offset = 240;
//			}
//			RemoteWebElement remoteelem = ((RemoteWebElement)element);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			Point eloc =  remoteelem.getLocation();
//			double yloc = eloc.getY();
//			double xloc = eloc.getX() + remoteelem.getSize().width/2;
//			double swipe_xratio = xloc;
//			double elemheight = remoteelem.getSize().getHeight();
//			double yStartRatio = (yloc + elemheight + browser_top_offset)/2;
//			double yEndRatio = (eloc.getY()+browser_top_offset);
//			if(swipe_xratio < 10.0){swipe_xratio = 10.0;}
//			if(yEndRatio < 50.0){yEndRatio = 50.0;}
//			HashMap<String, Double> swipeObject = new HashMap<String, Double>();
//			swipeObject.put("startX", swipe_xratio);
//			swipeObject.put("startY", yStartRatio);
//			swipeObject.put("endX", swipe_xratio);
//			swipeObject.put("endY", yEndRatio);
//			swipeObject.put("duration", 1.0);
//			js.executeScript("mobile: swipe", swipeObject);
//		}
//
//	}
//
//	public void SwipeLeft(WebElement element){
//		//Executing swipe on in the case of iOS simulators. Skipping it for Android Chrome as this swipe will not yet implemented on it.
//		if((!GetDriverInfo().get("DriverName").toLowerCase().equals("androidchrome"))
//				&& (!GetDriverInfo().get("DriverName").toLowerCase().equals("chrome"))
//				&& (!GetDriverInfo().get("DriverName").toLowerCase().equals("chrome-mac"))
//				&& (!GetDriverInfo().get("DriverName").toLowerCase().equals("safari"))){
//			double browser_top_offset = 0.0;
//			if(GetDriverInfo().get("DriverType").trim().equalsIgnoreCase("mobile")){
//				browser_top_offset = 0;
//			}else if(GetDriverInfo().get("DriverType").trim().equalsIgnoreCase("tablet")){
//				browser_top_offset = 80;
//			}
//			RemoteWebElement remoteelem = ((RemoteWebElement)element);
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			String script = "return Math.max(document.documentElement.clientHeight, window.innerHeight || 0)";
//			Long pageheight1=(Long)js.executeScript(script);
//			Long pagewidth1=(Long)js.executeScript("return Math.max(document.documentElement.clientWidth, window.innerWidth || 0)");
//			//       		Long pageheight2=(Long)js.executeScript("return window.innerHeight");
//			Point eloc =  remoteelem.getLocation();
//			double yloc = eloc.getY();
//			double xstartloc = eloc.getX();
//			double xendloc = eloc.getX() + remoteelem.getSize().width;
//			double swipe_startxratio = xstartloc/pagewidth1;
//			double swipe_endxratio = xendloc/pagewidth1;
//			double elemheight = remoteelem.getSize().getHeight()/2;
//			double yratio = (yloc + elemheight/2 + browser_top_offset)/pageheight1;
//			if(swipe_startxratio < 0.05){swipe_startxratio = 0.05;}
//			if(swipe_endxratio > .95){swipe_endxratio = 0.95;}
//
//			HashMap<String, Double> swipeObject = new HashMap<String, Double>();
//			swipeObject.put("startX", swipe_startxratio);
//			swipeObject.put("startY", yratio);
//			swipeObject.put("endX", swipe_endxratio);
//			swipeObject.put("endY", yratio);
//			swipeObject.put("duration", 0.8);
//			js.executeScript("mobile: swipe", swipeObject);
//		}
//		if(GetDriverInfo().get("DriverName").toLowerCase().equals("chrome")
//				|GetDriverInfo().get("DriverName").toLowerCase().equals("chrome-mac")){
//			Actions builder=new Actions(driver);
//			Action dragAndDrop=builder.clickAndHold(element).moveToElement(element, element.getLocation().x+800, element.getLocation().y+10).release().build();
//			dragAndDrop.perform();
//			try {Thread.sleep(4000);} catch (InterruptedException e) {}
//		}
//		if(GetDriverInfo().get("DriverName").toLowerCase().equals("safari")){
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("$('.owl-wrapper').trigger('owl.prev')");
//		}
//	}
//
//	public void PinchOpen(){
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
//		swipeObject.put("startX", (double)114);
//		swipeObject.put("startY", (double)198);
//		swipeObject.put("endX", (double)257);
//		swipeObject.put("endY", (double)256);
//		swipeObject.put("duration", 1.8);
//		js.executeScript("mobile: pinchOpen", swipeObject);
//	}
//
//	public void PinchClose(){
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		HashMap<String, Double> swipeObject = new HashMap<String, Double>();
//		swipeObject.put("startX", (double)150);
//		swipeObject.put("startY", (double)230);
//		swipeObject.put("endX", (double)200);
//		swipeObject.put("endY", (double)260);
//		swipeObject.put("duration", 1.8);
//		js.executeScript("mobile: pinchOpen", swipeObject);
//	}
//
//	public void Scroll(String Direction){
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		HashMap<String, String> scrollObject = new HashMap<String, String>();
//		scrollObject.put("direction", Direction);
//		js.executeScript("mobile: scroll", scrollObject);
//	}
//
//	public void ChangeOrientation(String Orientation){   // Valid values are: "LANDSCAPELEFT" , "LANDSCAPERIGHT" , "PORTRAIT"
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		//       String script = "target.setDeviceOrientation(UIA_DEVICE_ORIENTATION_" + Orientation + ");";
//		//        js.executeScript(script);
//		js.executeScript("target.setDeviceOrientation(UIA_DEVICE_ORIENTATION_LANDSCAPERIGHT);");
//	}
//
//	public boolean saveScreenshot(String ImgPath){
//		boolean flag = true;
//		try{
//			File file = null;
//            file = driver.getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(file, new File(ImgPath));
//			//	            org.openqa.selenium.WebDriver augmentedDriver = new Augmenter().augment(driver);
////			WebDriver augmentedDriver = (IOSDriver)(new Augmenter().augment(driver));
////			screenshot = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
////
////			File screenshotfile = new File(ImgPath);
////			FileUtils.copyFile(screenshot, screenshotfile);
//		}catch(Exception e){
//			flag = false;
//			e.printStackTrace();
//		}
//		return flag;
//	}
//
////	public void saveFullScreenShot(String ImgPath){
////		CaptureBrowserScreenShot fullscreenshot = new CaptureBrowserScreenShot();
////		try {
////			fullscreenshot.seleniumCaptureBrowserScreenShot(driver, ImgPath);
////		} catch (InterruptedException | IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
//
//	public boolean IsElementVisible(final By by) {
//		try{
//			wait = new WebDriverWait(driver, 10);
//			wait.until(ExpectedConditions.presenceOfElementLocated(by));
//			if((driver.findElement(by).getSize().height==0) && (driver.findElement(by).getSize().width==0)){
//				return false;
//			}else{
//				return true;
//			}
//		}catch(Exception e1){
//			return false;
//		}
//	}
//	public void WebScrollToTop(){
//		try{
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("window.scrollTo(0,0);");
//		}catch(Exception e){
//
//		}
//
//	}
//
//	public void WebScrollToBottom(){
//		try{
//			JavascriptExecutor js = (JavascriptExecutor)driver;
//			js.executeScript("window.scrollTo(0,document.documentElement.scrollHeight);");
//		}catch(Exception e){
//
//		}
//	}
//
//	public boolean assertLocationAlertPresent(boolean alertShouldBeThereOrNot, Long waitingTime){
//		WebDriverWait wait = new WebDriverWait(driver,waitingTime );
//		Alert alert=null;
//		try {
//			alert=wait.until(ExpectedConditions.alertIsPresent());
//		} catch (TimeoutException e) {
//
//		}
//		Set<String> windowHandles=driver.getWindowHandles();
//		if(windowHandles.size()>1 && alertShouldBeThereOrNot==true){
//			alert = driver.switchTo().alert();
//			/**
//			 * The alert text should be the one that is shown on IPhone/IPad. It should have Allow and Dont Allow buttons.
//			 */
//			String alertText=alert.getText();
//			if(!alertText.equals("\"Safari\" Would Like to Use Your Current Location")){
//				alert=null;
//			}
//		}
//
//		if(alert!=null){
//			System.out.println("alert is present");
//		}
//		else{
//			System.out.println("alert is not present");
//		}
//
//		if(alertShouldBeThereOrNot==true && alert!=null){
//			return true;
//		}
//		else if(alertShouldBeThereOrNot==true && alert==null){
//			return false;
//		}
//		if(alertShouldBeThereOrNot==false && alert!=null){
//			return false;
//		}
//		else if(alertShouldBeThereOrNot==false && alert==null){
//			return true;
//		}
//		else
//			return false;
//	}
//	public Integer CountElement(By by, String description)
//	{
//		String error;
//
//		try
//		{
//			return driver.findElements(by).size();
//		}
//		catch(NoSuchElementException e)
//		{
//			return 0;
//		}
//		catch(Exception e)
//		{
//			return 0;
//		}
//
//	}
//
//	public String getElementText(By by, String description)
//	{
//		String error;
//		try
//		{
//			return driver.findElement(by).getText();
//		}
//		catch(NoSuchElementException e)
//		{
//			return "";
//		}
//		catch(Exception e)
//		{
//			driver.findElement(by).getText();
//			return "";
//		}
//
//	}
//	public String getElementAttribute(By by, String attribute,String description)
//	{
//		String error;
//
//		try
//		{
//			return driver.findElement(by).getAttribute(attribute).toString();
//		}
//		catch(NoSuchElementException e)
//		{
//			return "";
//		}
//		catch(Exception e)
//		{
//			driver.findElement(by).getText();
//			return "";
//		}
//	}
//
//	public void SetDriver(IOSDriver driver){
//		this.driver = driver;
//	}
//
//	public IOSDriver SwitchToWebView(IOSDriver driver){
//        boolean webviewFound = false;
//		try{
//			Set<String> contextNames = driver.getContextHandles();
//            int contexts = contextNames.size();
//            for(int i=1;i<7;i++){
//                if(contexts == 1){
//                    Thread.sleep(2000);
//                    contexts = driver.getContextHandles().size();
//                    if(contexts>1){
//                        System.out.println("Found the WebView in " + i + " Iterations");
//                        webviewFound = true;
//                        break;
//                    }
//                }else{
//                    System.out.println("Found the WebView in " + i + " Iterations");
//                    webviewFound = true;
//                    break;
//                }
//            }
//            if(!webviewFound){
//                System.out.println("WebView not found");
//            }
//			driver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1
//			this.driver = driver;
//			return driver;
//		}catch(Exception e){
//			return null;
//		}
//	}
//
//	public IOSDriver SwichToNativeView(IOSDriver driver){
//		try{
//			driver.context("NATIVE_APP");
//			this.driver = driver;
//			return driver;
//		}catch(Exception e){
//			return null;
//		}
//	}
//
//	public List<WebElement> getchildElements(WebElement parentElement, String childElement)
//	{
//		try{
//			return parentElement.findElements(MobileBy.className(childElement));
//		}catch(Exception e){
//			return null;
//		}
//	}
//
//	public String ConvertStringArrayListToString(List<String> list)
//	{
//		try{
//			String listString = "";
//			for (String s : list)
//			{
//			    listString += s + "\t";
//			}
//			return listString;
//		}catch(Exception e){
//			return "";
//		}
//	}
//
//	public boolean isElementPresent(By locator, Integer timeoutInSeconds)
//	{
//		if (getElement(LocatorType, LocatorString, timeoutInSeconds) != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//    public boolean isElementPresent(By mobBy, Integer timeoutInSeconds)
//    {
//        if(getElement(mobBy, timeoutInSeconds) != null)
//        {
//
//            return true;
//
//        }
//        else
//        {
//
//            return false;
//        }
//    }
//    public String getStackTraceAsString(Object e)
//    {
//        Exception ex=null;
//        try {
//            ex=(Exception)e;
//            StringBuilder sb = new StringBuilder();
//            for (StackTraceElement element : ex.getStackTrace()) {
//                sb.append(element.toString());
//                sb.append("][");
//            }
//            return "<b>"+ex.getClass().toString().toUpperCase()+"</b> Here is the stacktrace: "+sb.toString();
//        }
//        catch (Exception e2) {
//            return "Stack trace not available.";
//        }
//    }
//
//
//    public String AddNoOfDaysInServerTime(String format, int AddNoOfDays)
//    {
//        String Timezone = ConfigManager.getProperties().getProperty("usTimeZone".trim());
//        SimpleDateFormat sdf = new SimpleDateFormat(format);
//        sdf.setTimeZone(TimeZone.getTimeZone(Timezone));
//        try {
//            Calendar c = Calendar.getInstance();
//            c.add(Calendar.DATE, AddNoOfDays);
//            String fdate = sdf.format(c.getTime());
//            return fdate;
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    public Date getDataTimeFromProvidedString(String dateValue, String format)
//    {
//        SimpleDateFormat formatter = new SimpleDateFormat(format);
//        try{
//            Date date = formatter.parse(dateValue);
//            return date;
//        }catch (Exception e)
//        {
//
//            AddToLog("CurrentTestCaseLog", "info","Failed to convert in Date and Time based on provided format ("+format+") and date value ("+dateValue+")");
//            return null;
//
//        }
//    }
//
//
//    public String getDateInExpectedFormat(String format,String dateInString, String returnformat)
//    {
//        SimpleDateFormat formatter = new SimpleDateFormat(format);
//        Date date = null;
//        try
//        {
//            date = formatter.parse(dateInString);
//            formatter = new SimpleDateFormat(returnformat);
//            return formatter.format(date);
//        }
//        catch (ParseException e)
//        {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
//
//
//    public String getTimeStampFromDate(String date)
//    {
//
//        String[] arr = date.substring(11).split(" ");
//        if(arr[0].charAt(0) == '0') {
//            arr[0] = arr[0].substring(1, 5);
//        }else
//        {
//
//            arr[0] = arr[0].substring(0,5);
//        }
//        String timeWithMarker = StringUtils.join(arr, " ");
//        return timeWithMarker;
//
//    }
//
//    public void ScrollToUIATableViewUsingCellIndex(WebElement element, int index)
//    {
//
//        try {
//
//            index = index - 1;
//
//            ((IOSElement)element).findElementByIosUIAutomation(".cells()["+index+"].scrollToVisible();");
//
//        }catch (Exception e)
//        {}
//
//    }
//
//    public Integer getRandomNumberBetween(Integer upper, Integer lower){
//        return (int) ((Math.random() * (upper - lower)) + lower);
//    }
//
//
//    public String getIOSBinary(String iosBuildBranch, String apiEnv )
//    {
//        String appPath = null;
//        System.out.println(System.getProperty("user.dir"));
//		System.out.println(ConfigManager.getProperties().getProperty("deviceType"));
//		String baseBuildPath = System.getProperty("user.dir")+"/Tool/iOSAppBuilds/" + ConfigManager.getProperties().getProperty("deviceType") + "/";
//		if(iosBuildBranch.equalsIgnoreCase("Prod"))
//        {
//            switch (apiEnv.toUpperCase())
//            {
//                case "API-PREPROD":
//                    appPath = baseBuildPath+ iosBuildBranch + "/"+ apiEnv+ "/Equinox.zip";
//                    break;
//                case "API-PROD":
//                    appPath = baseBuildPath + iosBuildBranch + "/"+ apiEnv+"/Equinox.zip";
//                    break;
//                case "API-STAG":
//                    appPath = baseBuildPath + iosBuildBranch + "/"+ apiEnv+ "/Equinox.zip";
//                    break;
//                default:
//                    AddToLog(LogName.CurrentTestCaseLog, "error", "Check parameters passed in method - getIOSBinary"+ apiEnv);
//            }
//        }
//        if(iosBuildBranch.equalsIgnoreCase("Develop"))
//        {
//            switch (apiEnv.toUpperCase())
//            {
//                case "API-PREPROD":
//                    appPath = baseBuildPath + iosBuildBranch + "/"+ apiEnv+ "/Equinox.zip";
//                    break;
//                case "API-PROD":
//                    appPath = baseBuildPath + iosBuildBranch + "/"+ apiEnv + "/Equinox.zip";
//                    break;
//                case "API-STAG":
//                    appPath = baseBuildPath + iosBuildBranch + "/"+ apiEnv + "/Equinox.zip";
//                    break;
//                default:
//                    AddToLog(LogName.CurrentTestCaseLog, "error", "Check parameters passed in method - getIOSBinary"+apiEnv);
//            }
//        }
//		return appPath;
//	}
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//

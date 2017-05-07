//package com.badoo.utils;
//
//import io.appium.java_client.android.AndroidDriver;
//import org.openqa.selenium.WebDriverException;
//
//public class DriverFactory {
//	private ThreadLocal<AndroidDriver> GlobalDriver = new ThreadLocal<AndroidDriver>();
//	private static DriverFactory instance = new DriverFactory();
//    public CommonFunctionLib objCommonFunc = CommonVariables.getCommonFunctionLib();
//	public static DriverFactory getInstance()
//	{
//        return instance;
//	}
//
//	public AndroidDriver getDriver(){
//        if(GlobalDriver.get()==null){
//            boolean resetFlag = Boolean.parseBoolean(ConfigManager.getProperties().getProperty("NoReset"));
//            GlobalDriver.set(CommonVariables.getCommonFunctionLib().StartIOSDriver(resetFlag));
//        }
//        CommonVariables.getCommonFunctionLib().SetDriver(GlobalDriver.get());
//        return GlobalDriver.get();
//    }
//    public AndroidDriver getDriver(boolean NoResetFlag){
//        if(GlobalDriver.get()==null){
//            boolean resetFlag = NoResetFlag;
//            GlobalDriver.set(CommonVariables.getCommonFunctionLib().StartIOSDriver(resetFlag));
//        }
//        CommonVariables.getCommonFunctionLib().SetDriver(GlobalDriver.get());
//        return GlobalDriver.get();
//    }
//    public void CloseApp(){
//        GlobalDriver.get().closeApp();
//    }
//    public void killDriver(){
//        GlobalDriver.get().closeApp();
//        GlobalDriver.get().quit();
//        GlobalDriver.set(null);
//    }
//	public AndroidDriver LaunchApp(){
//        try{
//            GlobalDriver.get().launchApp();
//        }catch(WebDriverException e1){
//        }
//        catch(Exception e){
//            GlobalDriver.set(null);
//            this.getDriver();
//        }
//        return GlobalDriver.get();
//    }
//}

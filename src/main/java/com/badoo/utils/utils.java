package com.badoo.utils;

import com.badoo.pages.BasePageObject;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.velocity.exception.VelocityException;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

public class utils {

	public static WebDriver Driver;
	protected WebDriver driver;
	public static String currentdir = System.getProperty("user.dir");
	private static Properties prop;
	public static final Logger log = Logger.getLogger(Log.class.getName());
	static {
				
		try {
			DOMConfigurator.configure(currentdir+"/src/test/resources/log4j.xml");
			loadProperties("web.properties");
			log.info("Log4j & Property files Initiating.....");

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			log.error("Log4j & Property files Initiated");

		}

	}
	public utils() {
		// TODO Auto-generated constructor stub
	this(Driver);
	}
	
	public utils(WebDriver driver3) {
		// TODO Auto-generated constructor stub
	this.driver = driver3;
	}

	public WebDriver Setup_Browser() {
		log.info("Browser Initiating");
		driver = newWebdriver();
		// driver.get("https://login.salesforce.com/");

		driver.get(getProperty("baseUrl"));
		driver.get(getProperty("baseUrls"));
		driver.manage().window().maximize();
		BasePageObject.Driver = driver;
		Driver=driver;
		return driver;
	}

	private WebDriver newWebdriver() {
		// TODO Auto-generated method stub
		String Browser = getProperty("driverType");
		
		
		
		
		
		if (Browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			log.info("Browser Initiated");
		} else if (Browser.equalsIgnoreCase("chrome")) {
			
			String ChromeDriver_binary_name = "";
			String osname = System.getProperty("os.name");
			if(osname.toLowerCase().contains("windows")){
				ChromeDriver_binary_name = "chromedriver.exe";
			}else{
				ChromeDriver_binary_name = "chromedriver";
			}
					String chromepath = currentdir+"/Drivers/" + ChromeDriver_binary_name;
					System.setProperty("webdriver.chrome.driver", chromepath);
					driver = new ChromeDriver();
					log.info("Browser Initiated");
		} else if (Browser.equalsIgnoreCase("IE")) {
			System.setProperty(
					"webdriver.ie.driver",
					"D:\\Rohit\\Software\\selenium\\Drivers\\Browser\\IE\\IEDriverServer_Win32_2.44.0\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		return driver;
	}

	public void Initiate_Elements() {

	}

	public  void type(By element, String value) {
		try {
			waitForElementPresent(element, 30);
			driver.findElement(element).sendKeys(value);
		} catch (Throwable t) {
			CaptureScreenshot();
		}
	
	}
	
	public  void click(By element)  {
		
		try {
			waitForElementPresent(element, 30);
			driver.findElement(element).click();
		} catch (Throwable t) {
			CaptureScreenshot();
		}
	}
	
	
	public void sleep(long wait){
		try {
			Thread.sleep(wait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public  void  waitForElementPresent(final By by, int timeOutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				// TODO Auto-generated method stub
//				System.out.println("Waiting for element to present" );
				return (d.findElement(by).isDisplayed());
			}
		});
	}
	public  void CaptureScreenshot()  {
		try{
			if (!new File(currentdir + "//screenshot//").isDirectory()) {
			System.out.println("Folder Created screenshot"+ new File(currentdir + "//screenshot//").mkdir());
			}
			
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(currentdir+ "//screenshot//" + GetDateWithTime()+ ".png"),true);
		}catch(Exception e1){
			e1.printStackTrace();
			System.out.println("Not able to take ScreenShot");
		}
		
	}
	public  String GetDate(){
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyy");
		return sdf.format(cal.getTime()) ;
	}
	public  String GetDateforcalendar(){
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MMddyyyyHHmmss");
		return sdf.format(cal.getTime()) ;
	}
	public  String GetDateWithTime(){
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SSS");
		return sdf.format(cal.getTime()) ;
	}
	private static void loadProperties(String filename) {
		if (prop == null) {
			prop = new Properties();
			InputStream istream = null;

			try {

				// first look for file as a resource
				istream = ClassLoader.getSystemResourceAsStream(filename);
				prop.load(istream);

			} catch (IOException ex) {
				throw new RuntimeException(ex);

			} finally {
				IOUtils.closeQuietly(istream);
			}
		}

	}

	public String getProperty(String name) {
		String value = prop.getProperty(name);

		return value;

	}
	public void generateReport(){
		File reportOutputDirectory = new File(currentdir+"/target");
		List<String> list = new ArrayList<String>();
		list.add(currentdir+"/cucumber-report.json");
//		list.add("cucumber-report.json");

		String pluginUrlPath = "";
		String buildNumber = "1";
		String buildProject = "cucumber-jvm";
		boolean skippedFails = true;
		boolean pendingFails = true;
		boolean undefinedFails = true;
		boolean missingFails = true;
		boolean flashCharts = true;
		boolean runWithJenkins = false;
		boolean highCharts = false;
		boolean parallelTesting = false;

		ReportBuilder reportBuilder;
		try {
			reportBuilder = new ReportBuilder(list, reportOutputDirectory, pluginUrlPath, buildNumber,
			    buildProject, skippedFails, pendingFails, undefinedFails, missingFails, flashCharts, runWithJenkins,
			    highCharts, buildProject, parallelTesting, parallelTesting);
			reportBuilder.generateReports();
		} catch (VelocityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

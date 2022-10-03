package base;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utility.PropertyFile_Reader;
import utility.Utility;

public class BaseClass {

	public PropertyFile_Reader pro=new PropertyFile_Reader();
	public ExtentReports reports;
	public ExtentHtmlReporter extent;
	public ExtentTest logger;
	public WebDriver driver;
	
	@BeforeSuite
	public void setupSuite()
	{
		extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/Facebook_"+Utility.getCurrentDateTime()+".html"));
		reports=new ExtentReports();
		reports.attachReporter(extent);
		logger=reports.createTest(" Facebook Application Framework");
	}
	
	public WebDriver openChromeBrowser()
	{
		System.setProperty(pro.getChromeKey(),pro.getChromePath());
		WebDriver driver=new ChromeDriver();	
		driver.get(pro.getUrl());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return driver;
	}
	
	public WebDriver openFirefoxBrowser()
	{
		System.setProperty(pro.getFirefoxKey(), pro.getFirefoxPath());
		WebDriver driver=new FirefoxDriver();
		driver.get(pro.getUrl());
		driver.manage().window().maximize();
		
		return driver;
	}
	
	
	public void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
}

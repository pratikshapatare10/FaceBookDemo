package test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import utility.ExcelFile_Reader;
import utility.Utility;

public class LoginTest extends BaseClass {
 
	WebDriver driver;
	LoginPage lgn;
	HomePage home;
	
	String expectedUrl="https://www.facebook.com/";
	
	@BeforeMethod
	public void openBrowser()
	{
		driver=openChromeBrowser();
		lgn=new LoginPage(driver);
		home=new HomePage(driver);
	}
	
	@Test(priority=1,dataProvider="fetchFromexcel")
	public void loginWithDP(String username, String password) throws InterruptedException
	{
		
		logger.info("Starting login test ..");
		logger.info(username+"  "+password);
		
		lgn.setLoginPageUN(username);
		lgn.setLoginPagePWD(password);
		lgn.clickLoginPageLgnBtn();
		Thread.sleep(10000);
		String currentUrl=driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, expectedUrl);
	}
	
	@DataProvider(name="fetchFromexcel")
	public Object[][] loginData() {
		Object[][] arrayObject = null;
		try {
			arrayObject = ExcelFile_Reader.getData();
		} catch (EncryptedDocumentException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return arrayObject;
	}
	
	@Test(priority=2)
	public void verifyLogo() throws InterruptedException
	{
		
		logger.info("Starting verify logo test..");
	
		lgn.setLoginPageUN("rutujataware62@gmail.com");
		lgn.setLoginPagePWD("sanjana710");
		lgn.clickLoginPageLgnBtn();
		Thread.sleep(10000);
		home.verifyLogo();
		Assert.assertTrue(home.verifyLogo());
	}
	
	@AfterMethod
	public void screenShot(ITestResult result) throws IOException
	{
		if(ITestResult.FAILURE==result.getStatus())
		{
			logger.fail("Test case failed");
			Utility.screenShot(driver);
			
		}
		else if(ITestResult.SUCCESS==result.getStatus())
		{
			logger.pass("Test case pass");
		}
		
		reports.flush();
		closeBrowser(driver);
	}

}

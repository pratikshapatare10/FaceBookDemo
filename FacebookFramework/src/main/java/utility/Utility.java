package utility;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {

	public static String screenShot(WebDriver driver) throws IOException {
		 File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 String screenshotPath=System.getProperty("user.dir")+"/Screenshot/Facebook_"+getCurrentDateTime()+".png";
		 File Destination=new File(screenshotPath);
		 FileHandler.copy(source, Destination);
		 
		 return screenshotPath;
	}
	
	public static String getCurrentDateTime()
	{
		DateFormat customFormat=new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date curDate=new Date();
		return customFormat.format(curDate);
	}
}

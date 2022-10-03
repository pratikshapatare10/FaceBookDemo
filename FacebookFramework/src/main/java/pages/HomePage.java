package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	@FindBy (xpath="//a[@aria-label='Facebook']") private WebElement logo;
	
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyLogo()
	{
		boolean result=logo.isDisplayed();
		
		if(result==true)
		{
			System.out.println("Visible");
		}
		else
			System.out.println("Not visible");
		return result;
	}
}

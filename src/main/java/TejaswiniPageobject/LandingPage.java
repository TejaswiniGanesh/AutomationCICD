package TejaswiniPageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TejaswiniPageobject.Abstract.AbstractComponents;


public class LandingPage extends AbstractComponents {
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
//new commits to check for webhook
	@FindBy(id="userEmail")
	WebElement userid;
	
	@FindBy(id="userPassword")
	WebElement Password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="div[class*='inserted']")
	WebElement ErrorMessage;
	
	public void goToUrl()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public Producttocartpage logintoApplication(String Username,String pwd)
	{
		userid.sendKeys(Username);
		Password.sendKeys(pwd);
		submit.click();	
		Producttocartpage productcart =  new Producttocartpage(driver);
		return productcart;
	}
	
	public String Invalidcredentials()
	{
		waitforelementdriver(ErrorMessage);
		return ErrorMessage.getText();
	}
}

package TejaswiniPageobject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import TejaswiniPageobject.Abstract.AbstractComponents;


public class PlaceOrderPage extends AbstractComponents {
	WebDriver driver;
	public PlaceOrderPage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement selectCountry;
	
	@FindBy(css="section[class*='ta-results'] button")
	List<WebElement> Results;
	
	@FindBy(css=".btnn")
	WebElement ClickPlaceOrder;
	
	@FindBy(css=".hero-primary")
	WebElement OrderIdmessage;
	
	public List<WebElement> getCountryNames(String countryname)
	{
		selectCountry.sendKeys(countryname);
		return Results;
		
	}
	
	public void selectCountry(String countryname) 
	{
		WebElement country = Results.stream().filter(Result->Result.getText().equals(countryname)).findFirst().orElse(null); //if equals india
		country.click(); //click
	}
	 
	public void placeOrder() {
		ClickPlaceOrder.click();	
	}
	
	public String getOrderID()
	{
		return OrderIdmessage.getText();	
		
	}

}

package TejaswiniPageobject.Abstract;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import TejaswiniPageobject.Orderspage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[3]")
	WebElement Clickoncartheader;
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement ClickOrdersheader;

	public void waitforelementdriver(WebElement ele)
	{
		WebDriverWait Wait =  new WebDriverWait(driver,Duration.ofSeconds(10));
		Wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void waitforelement(By findBy)
	{
		WebDriverWait Wait =  new WebDriverWait(driver,Duration.ofSeconds(5));
		Wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void invisiblityOfElement(By findBy)
	{
		WebDriverWait Wait =  new WebDriverWait(driver,Duration.ofSeconds(5));
		Wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}
	
	public void Clickcart()
	{
		Clickoncartheader.click();
	}
	
	public Orderspage clickorders()
	{
		ClickOrdersheader.click();
		Orderspage orderspage =  new Orderspage(driver);
		return orderspage;
	}
	
	

}

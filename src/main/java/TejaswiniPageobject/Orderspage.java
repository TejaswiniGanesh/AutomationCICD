package TejaswiniPageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import TejaswiniPageobject.Abstract.AbstractComponents;

public class Orderspage extends AbstractComponents {
	WebDriver driver;
	public Orderspage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> Orderedproductnames;
	
	public Boolean verifyOrders(String prodname)
	{
		Boolean match = Orderedproductnames.stream().anyMatch(product->product.getText().equalsIgnoreCase(prodname));
		return match;
	}

}

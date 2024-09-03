package TejaswiniPageobject;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import TejaswiniPageobject.Abstract.AbstractComponents;



public class Producttocartpage extends AbstractComponents{
	
	WebDriver driver;
	List<String> cartProductNames;
	public Producttocartpage(WebDriver driver)
	{
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(xpath="//div/h5/b")
	List<WebElement> productstext;
	
	
	@FindBy(xpath="//button[@class='btn w-10 rounded']")
	List<WebElement> multipleitemsaddtocart;
	
	@FindBy(xpath="(//button[@class='btn btn-custom'])[3]")
	WebElement waitforcartvisibility;
	
	@FindBy(xpath="//div[@class='cartSection']/h3")
	List<WebElement> cartproducttitles;
	
	@FindBy(xpath="//button[text()='Buy Now']")
	List<WebElement> clickonbuynow;
	
	By addtocart = By.cssSelector(".card-body button:last-of-type");
	By productsload = By.cssSelector(".mb-3");
	By toast = By.cssSelector("#toast-container");
	By buffer = By.className("ng-animating");
	
	public List<WebElement> productsList()
	{
		waitforelement(productsload);
		return products;
	}
	
	
	public void getMultipleProductsName(List<String> Mylist) throws InterruptedException
	{
	for(int i=0;i<productstext.size();i++) {
			
			String Myproducts =  productstext.get(i).getText();
			if(Mylist.contains(Myproducts))
			{
				invisiblityOfElement(buffer);
				multipleitemsaddtocart.get(i).click();	
			}	
	}
	invisiblityOfElement(buffer);
	waitforelementdriver(waitforcartvisibility);
	Thread.sleep(6000);
	}
	
	public WebElement getProductName(String ProductName)
	{
		WebElement productname = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return productname;
	}
 
	public void addToCart(String ProductName) throws InterruptedException
	{
		WebElement productname =getProductName(ProductName);
		productname.findElement(addtocart).click();
		waitforelement(toast);
		invisiblityOfElement(buffer);
		waitforelementdriver(waitforcartvisibility);
		Thread.sleep(6000);
		
	}
	
	public List<WebElement> getCartProducts()
	{
		return cartproducttitles;
	}
	
	public List<String> validatecartProductNames()
	{
		cartProductNames =  cartproducttitles.stream().map(n->n.getText()).collect(Collectors.toList());
		return cartProductNames;
	
	}
	
	public Boolean validateProductName(String ProductName)
	{
			
		Boolean Match = cartproducttitles.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase(ProductName));
		return Match;	
	}  
	
	public PlaceOrderPage buyNow(String ProductName)
	{
		 for(int i=0;i<cartProductNames.size();i++)
		{
			String productname = cartProductNames.get(i);
			if(productname.equalsIgnoreCase(ProductName))
			{
				clickonbuynow.get(i).click();
			}
		} 
		PlaceOrderPage placeorder =  new PlaceOrderPage(driver);
		return placeorder;
	}
}

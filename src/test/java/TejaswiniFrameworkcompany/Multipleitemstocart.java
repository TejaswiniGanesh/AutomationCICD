package TejaswiniFrameworkcompany;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TejaswiniFrameworkcompany.Basepackage.BaseClass;
import TejaswiniPageobject.LandingPage;
import TejaswiniPageobject.PlaceOrderPage;
import TejaswiniPageobject.Producttocartpage;

public class Multipleitemstocart extends BaseClass {

	//String ProductName = "IPHONE 13 PRO";
	String countryname="India";
	String orderid = "THANKYOU FOR THE ORDER.";
@Test(dataProvider="getData",groups= {"MultiOrderPurchase"})
public void submitOrder(HashMap<String, String> input) throws InterruptedException {
		// TODO Auto-generated method stub
	    List<String> Mylist = Arrays.asList(input.get("ProductName1"),input.get("ProductName2"));
		Producttocartpage productcart = landingpage.logintoApplication(input.get("Username"), input.get("Password"));
		List<WebElement> products = productcart.productsList();
		productcart.getMultipleProductsName(Mylist);
		productcart.Clickcart();
		productcart.getCartProducts();
		List<String> cartproductnames =  productcart.validatecartProductNames();
		Assert.assertEquals(Mylist, cartproductnames);
		System.out.println(cartproductnames);		
		PlaceOrderPage placeorder = productcart.buyNow(input.get("ProductName"));
		placeorder.getCountryNames(countryname);
		placeorder.selectCountry(countryname);
		placeorder.placeOrder();
		String text = placeorder.getOrderID();
		System.out.println(text);
		Assert.assertEquals(orderid, text);	
	}

@DataProvider
public  Object[][] getData() throws IOException
{
	List<HashMap<String, String>> Data = getJsonData(System.getProperty("user.dir")+"\\src\\main\\java\\Utilities\\Multipleorders.json");
	return new Object[][] {{Data.get(0)}};
	
}

}

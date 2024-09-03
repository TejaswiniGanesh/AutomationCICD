package TejaswiniFrameworkcompany;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
import TejaswiniPageobject.Orderspage;
import TejaswiniPageobject.PlaceOrderPage;
import TejaswiniPageobject.Producttocartpage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Submitorder extends BaseClass{

		String countryname="India";
		String orderid = "THANKYOU FOR THE ORDER.";
@Test(dataProvider=("getData"),groups= {"Purchase"})
public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
{
	   // List<String> Mylist = Arrays.asList("ZARA COAT 3");
		//LandingPage landingpage = launchApplication();
		Producttocartpage productcart = landingpage.logintoApplication(input.get("Username"), input.get("Password"));
		List<WebElement> products = productcart.productsList();
		productcart.getProductName(input.get("ProductName"));
		productcart.addToCart(input.get("ProductName"));
		productcart.Clickcart();
		productcart.getCartProducts();
		List<String> cartproductnames =  productcart.validatecartProductNames();
		//Assert.assertEquals(Mylist, cartproductnames);
		System.out.println(cartproductnames);
		PlaceOrderPage placeorder = productcart.buyNow(input.get("ProductName"));
		placeorder.getCountryNames(countryname);
		placeorder.selectCountry(countryname);
		placeorder.placeOrder();
		String text = placeorder.getOrderID();
		System.out.println(text);
		Assert.assertEquals(orderid, text);	
		//driver.close();
	}

@Test(dependsOnMethods= {"submitOrder"},dataProvider="getData",groups= {"Purchase"})
public void verifyOrders(HashMap<String, String> input)
{
	Producttocartpage productcart = landingpage.logintoApplication(input.get("Username"), input.get("Password"));
	Orderspage orderspage = productcart.clickorders();
	Assert.assertTrue(orderspage.verifyOrders(input.get("ProductName")));
}




@DataProvider
public Object[][] getData() throws IOException
{
	List<HashMap<String, String>> Data = getJsonData(System.getProperty("user.dir")+"\\src\\main\\java\\Utilities\\Submitorder.json");
	return new Object[][]  {{Data.get(0)},{Data.get(1)}};
} 


/* @DataProvider
public Object[][] getData()
{
	return new Object[][]  {{"trteja@gmail.com","Teja@selenium1","ZARA COAT 3"},{"tejaganesh@gmail.com","Teja@selenium1","ADIDAS ORIGINAL"}};
} */

/* @DataProvider
public Object[][] getData()
{
	HashMap<String,String> map =  new HashMap<String,String>();
	map.put("Username", "trteja@gmail.com");
	map.put("Password", "Teja@selenium1");
	map.put("ProductName", "ZARA COAT 3");
	
	HashMap<String,String> map1 =  new HashMap<String,String>();
	map1.put("Username", "tejaganesh@gmail.com");
	map1.put("Password", "Teja@selenium1");
	map1.put("ProductName", "ADIDAS ORIGINAL");
	return new Object[][]  {{map},{map1}};
}  */
}

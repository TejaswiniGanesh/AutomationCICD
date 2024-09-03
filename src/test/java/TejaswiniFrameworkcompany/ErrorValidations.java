package TejaswiniFrameworkcompany;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import TejaswiniFrameworkcompany.Basepackage.BaseClass;
import TejaswiniPageobject.PlaceOrderPage;
import TejaswiniPageobject.Producttocartpage;

public class ErrorValidations extends BaseClass {
 
	String prodname = "IPHONE 13 PRO";
	String countryname="India";
	String orderid = "THANKYOU FOR THE ORDER.";
	
	@Test(groups= {"Purchase"},retryAnalyzer=TejaswiniFrameworkcompany.Basepackage.Retry.class)
	public void ErrorMessage(){
		
		 landingpage.logintoApplication("trteja@gmail.com", "Teja@selenium");	
		 landingpage.Invalidcredentials();
		 System.out.println(landingpage.Invalidcredentials());
		 Assert.assertEquals( landingpage.Invalidcredentials(), "Incorrect email or password.");					
	}
	
	
	public void submitOrder() throws InterruptedException {
			// TODO Auto-generated method stub
		     List<String> Mylist = Arrays.asList("ZARA COAT 3","IPHONE 13 PRO");
			Producttocartpage productcart = landingpage.logintoApplication("trteja@gmail.com", "Teja@selenium1");
			List<WebElement> products = productcart.productsList();
			productcart.getMultipleProductsName(Mylist);
			productcart.Clickcart();
			productcart.getCartProducts();
			List<String> cartproductnames =  productcart.validatecartProductNames();
			Assert.assertEquals(Mylist, cartproductnames);
			System.out.println(cartproductnames);		
			PlaceOrderPage placeorder = productcart.buyNow(prodname);
			placeorder.getCountryNames(countryname);
			placeorder.selectCountry(countryname);
			placeorder.placeOrder();
			String text = placeorder.getOrderID();
			System.out.println(text);
			Assert.assertEquals(orderid, text);	
		}

}

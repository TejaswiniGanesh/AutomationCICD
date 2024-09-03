package TejaswiniFrameworkcompany;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import TejaswiniPageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Standalonetest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver =  new ChromeDriver();
		driver.manage().window().maximize();
		List<String> Mylist = Arrays.asList("ZARA COAT 3","ADIDAS ORIGINAL","IPHONE 13 PRO");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait Wait =  new WebDriverWait(driver,Duration.ofSeconds(5));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("trteja@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Teja@selenium1");
		driver.findElement(By.id("login")).click();
		LandingPage landingpage =  new LandingPage(driver);
		landingpage.logintoApplication("trteja@gmail.com", "Teja@selenium1");
		
		
		Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3")); //All products path (this is not product name path)
		WebElement productname = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null); //get text of all products on page
		driver.findElement(By.xpath("//button[@class='btn w-10 rounded']")).click(); //add product to cart
		Wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		Wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
		WebElement text = driver.findElement(By.cssSelector("#toast-container"));
		System.out.println(text.getText());
		Wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]"))));
		driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[3]")).click(); //click on cart
		List<WebElement> cartproducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3")); //get all elements text from cart to validate if product added is added in cart or not
		Boolean Match = cartproducts.stream().anyMatch(cartproduct-> cartproduct.getText().equalsIgnoreCase("ZARA COAT 3"));
		Assert.assertTrue(Match);
		driver.findElement(By.xpath("(//button[@class='btn btn-primary'])[2]")).click(); //click on buy now
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind"); //select country
		List<WebElement> Results = driver.findElements(By.cssSelector("section[class*='ta-results'] button")); //select all items in dropdown
		WebElement country = Results.stream().filter(Result->Result.getText().equals("India")).findFirst().orElse(null); //if equals india
		country.click(); //click
		driver.findElement(By.cssSelector(".btnn")).click();
		String orderid = driver.findElement(By.cssSelector("label[class*='ng-star-inserted']")).getText();
		System.out.println(orderid);
		if (orderid.equals("669e853eae2afd4c0b2e88d0"))
		{
			Assert.assertTrue(true);
		}		

	}

}

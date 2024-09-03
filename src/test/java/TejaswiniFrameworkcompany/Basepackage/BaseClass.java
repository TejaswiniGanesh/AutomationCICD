package TejaswiniFrameworkcompany.Basepackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import TejaswiniPageobject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	public LandingPage landingpage;
public WebDriver initializeDriver() throws IOException
{
	
	Properties prop = new Properties();
	FileInputStream fis =  new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\TejaswiniPageobject\\resources\\GlobalData.properties");
	prop.load(fis);
	String BrowserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
	if(BrowserName.contains("chrome"))
	{
		ChromeOptions options =new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		if(BrowserName.contains("headless"))
		{
			options.addArguments("headless");
		}
		driver =  new ChromeDriver(options);
		driver.manage().window().setSize(new Dimension(1440,900)); //opens in full screen 
	}
	else if(BrowserName.equalsIgnoreCase("firefox"))
	{
		WebDriverManager.firefoxdriver().setup();
		//System.setProperty("webdriver.gecko.driver", "C:\\eclipse\\Browser Drivers\\geckodriver-v0.35.0-win-aarch64\\geckodriver.exe");
	 driver =  new FirefoxDriver();
	 
	}
	else if(BrowserName.equalsIgnoreCase("edge")) 
	{
		WebDriverManager.edgedriver().setup();
		driver =  new EdgeDriver();
	}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}

public List<HashMap<String, String>> getJsonData(String FilePath) throws IOException
{
	//convert json content  to string
	String jsoncontent = FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
	
	//convert string to hashmap using Jackson Databind
	ObjectMapper mapper =  new ObjectMapper();
	List<HashMap<String, String>> Data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>(){
		
	});
	return Data;	
}

public String getScreenShot(String testcasename, WebDriver driver) throws IOException
{
	TakesScreenshot ts = (TakesScreenshot)driver;
	File Source = ts.getScreenshotAs(OutputType.FILE);
	File dest =  new File(System.getProperty("user.dir")+"\\Reports\\" + testcasename + ".png");
	FileUtils.copyFile(Source, dest);
	return System.getProperty("user.dir")+"\\Reports\\" + testcasename + ".png";
}

@BeforeMethod(alwaysRun=true)
public LandingPage launchApplication() throws IOException 
{
	 driver = initializeDriver();
		landingpage =  new LandingPage(driver);
		landingpage.goToUrl();
		return landingpage;
}

@AfterMethod(alwaysRun=true)
public void tearDown()
{
	//driver.close();
}
}

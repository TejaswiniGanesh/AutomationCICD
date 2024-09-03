package TejaswiniPageobject.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsNG {
	
	public static ExtentReports extentReportConfig()
	{
		//ExtentReports ExtentSparkReporter  - create objects for these 2 classes
		String path =  System.getProperty("user.dir")+"\\Reports\\index.html";
		ExtentSparkReporter reporter =  new ExtentSparkReporter(path); //it expects a path where the report has to be stored 
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Web Automation Results");
		
		ExtentReports reports = new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "Tejaswini Reddy");
		return reports;
		
		
		
	}

}

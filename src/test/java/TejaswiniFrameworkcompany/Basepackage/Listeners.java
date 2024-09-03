package TejaswiniFrameworkcompany.Basepackage;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import TejaswiniPageobject.resources.ExtentReportsNG;
import org.openqa.selenium.WebDriver;


public class Listeners extends BaseClass implements ITestListener {
	
	ExtentReports reports = ExtentReportsNG.extentReportConfig();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentest =  new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		 test =  reports.createTest(result.getMethod().getMethodName());
		 extentest.set(test); //it created unique thread id for each test case and maps to a test object
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentest.get().log(Status.PASS, "TestCase Passed");	
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentest.get().fail(result.getThrowable());
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
					.get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String Filepath = null;
		try {
			Filepath = getScreenShot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();  //if filepath doesnot exist in try method it will throw error in catch method
		}
		extentest.get().addScreenCaptureFromPath(Filepath, result.getMethod().getMethodName());  //this filepath which is in local system is now attached to extent reports
	}
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return ITestListener.super.isEnabled();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

}

package TejaswiniFrameworkcompany.Basepackage;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count=0;
	int Maxtry = 1;
	@Override
	public boolean retry(ITestResult result) {
		if(count<Maxtry)
		{
			count++;
			return true;
		}
	
		return false;
	}

}

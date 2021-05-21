package reports;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import driver.DriverScript;


public class ReportUtil extends DriverScript{
	
	public ExtentReports startTest(String FileName, String scenarioName)
	{
		String strPath = null;
		SimpleDateFormat sdf = null;
		File objResLocatoin = null;
		File objScreeenshot = null;
		try
		{
			strPath = System.getProperty("user.dir")+"Results";
			strResultLocation = strPath+"\\"+scenarioName;
			screenShotLocation = strResultLocation+"\\screenShots";
			
			//Create a Result directory
			objResLocatoin = new File(strResultLocation);
			if(!objResLocatoin.exists()) objResLocatoin.mkdir();
			
			objScreeenshot = new File(screenShotLocation);
			if(!objScreeenshot.exists()) objScreeenshot.mkdir();
			
			
	    	// Create an object of Extent Reports
			extent = new ExtentReports(strResultLocation+"\\"+FileName+"_"+appInd.getDateTime("ddMMYYYY_hhmmss")+".html",true);  
			extent.addSystemInfo("Host Name", System.getProperty("os.name"));
			    	extent.addSystemInfo("Environment", System.getProperty("os.version"));
			extent.addSystemInfo("User Name", System.getProperty("user.name"));
			extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
			return extent;
		}catch(Exception e)
		{
			e.printStackTrace();
			return extent;
		}
		finally {
			objResLocatoin = null;
			objScreeenshot = null;
			sdf = null;
		}
	}
	
	
	
	
	/*********************************************
	 * Method Name	: endTest()
	 * Author		: 
	 * Purpose		: 
	 * Date created	: 
	 * 
	 * *******************************************
	 */
	public void endTest(ExtentTest test)
	{
		try
		{
			extent.endTest(test);
			extent.flush();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	/*********************************************
	 * Method Name	: getScreenShot()
	 * Author		: 
	 * Purpose		: 
	 * Date created	: 
	 * 
	 * *******************************************
	 */
	public String getScreenShot(WebDriver driver)
	{
		File source = null;
		String destination = null;
		File finalDestination = null;
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			source = ts.getScreenshotAs(OutputType.FILE);
			// after execution, you could see a folder "FailedTestsScreenshots" under src folder
			//String destination = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName + dateName + ".png";
			destination = screenShotLocation+"\\"+"Screenshot_"+appInd.getDateTime("ddMMYYYY_hhmmss")+".png";
			finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			return destination;
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'getScreenShot()' method. "+e.getMessage());
			return null;
		}
		finally {
			source = null;
			finalDestination = null;
		}
	}
	
	
	
	/*********************************************
	 * Method Name	: writeResult()
	 * Author		: 
	 * Purpose		: 
	 * Date created	: 
	 * captureScreenShot should accept YES Or NO
	 * *******************************************
	 */
	public void writeResult(WebDriver oDriver, String strStatus, String strStepDescription, ExtentTest test, String captureScreenShot)
	{
		try {
			if(captureScreenShot.equalsIgnoreCase("Yes")) {
				if(strStatus.equalsIgnoreCase("Pass")) {
					test.log(LogStatus.PASS, strStepDescription+": "+ test.addScreenCapture(reports.getScreenShot(oDriver)));
				}else if(strStatus.equalsIgnoreCase("Fail")){
					test.log(LogStatus.FAIL, strStepDescription+": "+ test.addScreenCapture(reports.getScreenShot(oDriver)));
				}else if(strStatus.equalsIgnoreCase("Exception")) {
					test.log(LogStatus.FATAL, strStepDescription+": "+ test.addScreenCapture(reports.getScreenShot(oDriver)));
				}else if(strStatus.equalsIgnoreCase("INFO")) {
					test.log(LogStatus.INFO, strStepDescription+": "+ test.addScreenCapture(reports.getScreenShot(oDriver)));
				}
			}else {
				if(strStatus.equalsIgnoreCase("Pass")) {
					test.log(LogStatus.PASS, strStepDescription);
				}else if(strStatus.equalsIgnoreCase("Fail")){
					test.log(LogStatus.FAIL, strStepDescription);
				}else if(strStatus.equalsIgnoreCase("Exception")) {
					test.log(LogStatus.FATAL, strStepDescription);
				}
			}
		}catch(Exception e)
		{
			test.log(LogStatus.FATAL, "Exception while executing 'writeResult()' method. "+e.getMessage());
		}
	}

}

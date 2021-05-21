package driver;

import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import methods.AppDependentMethods;
import methods.AppIndependentMethods;
import methods.Datatable;
import methods.TaskModuleMethods;
import methods.UserModuleMethods;
import reports.ReportUtil;
import testScripts.TaskModuleScripts;
import testScripts.UserModuleScripts;

public class DriverScript {
	public static AppIndependentMethods appInd = null;
	public static AppDependentMethods appDep = null;
	public static Datatable datatable = null;
	public static UserModuleMethods userMethods = null;
	public static TaskModuleMethods taskMethods = null;
	public static ReportUtil reports = null;
	public static UserModuleScripts userScripts = null;
	public static TaskModuleScripts taskScripts = null;
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static String strResultLocation = null;
	public static String screenShotLocation = null;
	
	
	@BeforeSuite
	public void loadClasses() {
		try {
			appInd = new AppIndependentMethods();
			appDep = new AppDependentMethods();
			datatable = new Datatable();
			userMethods = new UserModuleMethods();
			taskMethods = new TaskModuleMethods();
			reports = new ReportUtil();
			userScripts = new UserModuleScripts();
			taskScripts = new TaskModuleScripts();
		}catch(Exception e)
		{
			System.out.println("Exception while executing 'loadClasses()' method. "+e.getMessage());
		}
	}
	
	
	
}

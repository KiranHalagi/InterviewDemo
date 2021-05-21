package methods;

import java.text.SimpleDateFormat;
import java.util.Date;

import driver.DriverScript;

public class AppIndependentMethods extends DriverScript{
	public String getDateTime(String dtFormat)
	{
		Date dt = null;
		SimpleDateFormat sdf = null;
		try{
			dt = new Date();
			sdf = new SimpleDateFormat(dtFormat);
			return sdf.format(dt);
		}catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
}

public static void main branching()
{
}
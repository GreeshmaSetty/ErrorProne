package testCases;
import org.testng.annotations.Test;
import configuration.Global;
import pages.*;

public class TestCase extends Global{

	public CommonMethods common = new CommonMethods();
	
	@Test(priority = 0)
	public void TC_01_AddProfile_ValidData() {
		System.out.println("Inside Test");
		common.Launch("Chrome", "https://www.google.com/");
		logger.logPass("1", "Name", "ABC", "XYZ");
		
	}
}

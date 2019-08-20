package testCases;
import org.testng.annotations.Test;
import configuration.Global;
import pages.*;
public class TestCase extends Global{

	public CommonMethods common = new CommonMethods();
	
	@Test(priority = 0)
	public void TC_01_AddProfile_ValidData() {
		common.Launch("Chrome", "www.google.com");
		logger.logPass("1", "Name", "ABC");
		logger.logFail("Test1");
		logger.logPass("2", "Name2", "ABCD");
		
		
	}
}

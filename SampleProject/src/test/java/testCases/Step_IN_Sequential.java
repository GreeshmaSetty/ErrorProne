package testCases;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import api.APICommon;
import configuration.Global;
import configuration.Keywords;
import configuration.ReadPropertiesFile;
import pages.CommonMethods;
import pages.IndianExpressHome;
import pages.MobileAppPage;
import utilities.Mob_Locators;
import utilities.UI_Locators;

public class Step_IN_Sequential extends Global {
	// public BrowserConfig config = new BrowserConfig();
	public CommonMethods common = new CommonMethods();
	public Keywords actions = new Keywords();
	public UI_Locators ulocator = new UI_Locators();
	public APICommon api = new APICommon();
	public MobileAppPage mobapp = new MobileAppPage();
	public IndianExpressHome iehome = new IndianExpressHome();
	ReadPropertiesFile input = new ReadPropertiesFile();
	LinkedHashMap<String, String> userInput = input.getRunPropertiesFile();
	String brokenLinkValFlag = userInput.get("BrokenLinkValidationFlag");
	String apiResponseValue = userInput.get("APIResponseValue");
	String apiTest = userInput.get("APITest");
	
	
	@Test(priority = 0)
	public void TC_UI_Launch_IndianExpress() {
		String Browser = userInput.get("Browser");
		common.Launch(Browser,"UI", ulocator.UrlData);
		logger.logPass("Launch", "Step In Forum Launched Indian Express link "+ulocator.UrlData);
		actions.waitForPageToLoad(30);
		common.validateBrokenLinks(brokenLinkValFlag);
	}
 
	@Test(priority = 1)
	public void TC_UI_Validation_IndianExpress() {
		iehome.ClosePopup();
		iehome.NavigatetoIndiaPage();
		common.validateBrokenLinks(brokenLinkValFlag);
		iehome.NavigatetoTabs("Politics");
		common.validateBrokenLinks(brokenLinkValFlag);
		iehome.ValidateCarousel_VerifyAPI(apiResponseValue);

	}
	
	@Test(priority = 2)
	public void TC_API_Validation() {
		if(apiTest!=null || !apiTest.contentEquals("")) {
			String[] apiTestCodes = apiTest.split(",");
			for(int i=0;i<apiTestCodes.length;i++) {
				common.validateDifferentStatusCodes(apiTestCodes[i]);
			}
		}
	}
	
			
	@Test(priority = 2)
	public void TC_Mob_Launch() {
		config.webDriver=null;
		common.Launch("Mobile","Mobile","");
		logger.logPass("Launch", "Step In Forum Launched");
	}
	
	@Test(priority = 3)
	public void MobileNavigateAndAPIValidation() {
		mobapp.MobileGoToDetailPage();
		mobapp.MobGetProductsAndValidateAPI();
	}
	
}

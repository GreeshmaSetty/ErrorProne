package testCases;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import api.APICommon;
import configuration.BrowserConfig;
import configuration.Global;
import configuration.Keywords;
import pages.CommonMethods;
import utilities.Mob_Locators;
import utilities.MyThread;
import utilities.UI_Locators;

public class Step_IN_Mob extends Global {
	// public BrowserConfig config = new BrowserConfig();
	public CommonMethods common = new CommonMethods();
	public Keywords actions = new Keywords();
	public Mob_Locators mlocator = new Mob_Locators();
	public APICommon api = new APICommon();

	
	@Test(priority = 0)
	public void TC_Mob_Launch_Youtube() {
		common.Launch("Mobile", "https://www.youtube.com/");
		logger.logPass("Launch", "Step In Forum Launched");
		
	}

	@Test(priority = 1)
	public void TC_Mob_Search_StepInForum() {
		actions.waitExplicit(mlocator.LoginBtn, 60);
		actions.click(mlocator.LoginBtn);
		logger.logPass("Click", "Click on Login button");
		actions.click(mlocator.OkBtn);
		//actions.getWebElement(mlocator.search).sendKeys("step-inforum" + Keys.ENTER);
		logger.logPass("Click", "Click on OK button");
		//actions.waitImplicit(mlocator.step_in_channel, 5);
		//actions.getWebElement(mlocator.step_in_channel).click();
		//actions.waitImplicit(mlocator.Video, 5);
	}

	

}

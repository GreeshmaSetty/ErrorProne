package testCases;

import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import configuration.BrowserConfig;
import configuration.Global;
import configuration.Keywords;
import pages.CommonMethods;
import utilities.Mob_Locators;
import utilities.MyThread;
import utilities.UI_Locators;

public class Step_IN_UI extends Global {
	// public BrowserConfig config = new BrowserConfig();
	public CommonMethods common = new CommonMethods();
	public Keywords actions = new Keywords();
	public UI_Locators ulocator = new UI_Locators();


	@Test(priority = 0)
	public void TC_UI_Fetch_Yuotube() {

	common.Launch("Chrome", "https://www.youtube.com/");

		try {
			actions.getWebElement(ulocator.search).sendKeys("step-inforum" + Keys.ENTER);
			//System.out.println("Print serach");
			actions.getWebElement(ulocator.step_in_channel).click();
			actions.getWebElement(ulocator.Video).click();
			System.out.println("Print video1");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}

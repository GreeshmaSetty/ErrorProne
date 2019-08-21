package testCases;

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

public class Step_IN_UI extends Global {
	// public BrowserConfig config = new BrowserConfig();
	public CommonMethods common = new CommonMethods();
	public Keywords actions = new Keywords();
	public UI_Locators ulocator = new UI_Locators();
	public APICommon api = new APICommon();

	@Test(priority = 0)
	public void TC_UI_Fetch_Yuotube() {

	common.Launch("Chrome", "https://www.youtube.com/");

		try {
			actions.getWebElement(ulocator.search).sendKeys("step-inforum" + Keys.ENTER);
			//System.out.println("Print serach");
			actions.waitImplicit(ulocator.step_in_channel, 5);
			actions.getWebElement(ulocator.step_in_channel).click();
			actions.waitImplicit(ulocator.Video, 5);
			actions.getWebElement(ulocator.Video).click();
			System.out.println("Print video1");
			actions.waitImplicit(null, 2);
			String videoName = api.API_Get("http://54.169.34.162:5252/video");
			System.out.println("VideoName : "+videoName);
			WebElement findVideo = null;
			int flag = 0;
			do{
				try {
					findVideo = actions.getWebElement("//a[@id='video-title'][@title='"+videoName+"']");
					}catch(Exception e) {
						
					}
				actions.scroll(0, 100);
				flag++;
				if(flag>221)
					break;
				//actions.waitForPageToLoad(30);
			}while(findVideo== null);
			if(findVideo==null) {
				System.out.println("Failed Video Name not found");
			}
			actions.jsScrollToElement(findVideo);
			actions.click(findVideo);
			actions.waitForPageToLoad(10);
			actions.mouseHover(ulocator.VideoSettings);
			actions.waitImplicit(ulocator.VideoSettings, 5);
			actions.click(ulocator.VideoSettings);
			actions.click(ulocator.QualityMenu);
			actions.waitImplicit(null, 1);
			//String listQuality = actions.getWebElement(ulocator.QualityList).getText();
			actions.select("360");			
			List<WebElement>videolist=actions.getWebElementList(ulocator.upnext);
			String[] upNextList = new String[videolist.size()];
			for(int j=0; j<videolist.size();j++)
	        {
				upNextList[j]=videolist.get(j+1).getAttribute("title");
	            System.out.println(upNextList[j]);
	        }
			actions.jsonCreate(upNextList);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}

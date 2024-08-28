package testCases;
 
import java.io.File;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import api.APICommon;
import configuration.Global;
import configuration.Keywords;
import pages.CommonMethods;
import utilities.UI_Locators;
 
public class Step_IN_UI extends Global {
	// public BrowserConfig config = new BrowserConfig();
	public CommonMethods common = new CommonMethods();
	public Keywords actions = new Keywords();
	public UI_Locators ulocator = new UI_Locators();
	public APICommon api = new APICommon();
 
	@Test(priority = 0)
	public void TC_UI_Launch_IndianExpress() {
		common.Launch("UI", "https://www.indianexpress.com");
		logger.logPass("Launch", "Step In Forum Launched Indian Express link https://www.indianexpress.com");
		actions.waitForPageToLoad(30);
	}
 
	@Test(priority = 1)
	public void TC_UI_Search_StepInForum() {
		//If Dont Allow Popup appears
		if(actions.getWebElementList(ulocator.dontAllow).size()>0)
		{
		actions.click(ulocator.dontAllow);
		logger.logPass("Clicking on alert button DontAllow", "");
		}
		
		
		actions.javaScriptClick(ulocator.sectionMenu);
		actions.click(ulocator.menuIndia);
		logger.logPass("Navigating to the India Sub Menu", "");
		actions.waitForPageToLoad(30);
		actions.switchToWindow(ulocator.IndiaPageTitleData);
		actions.click(ulocator.tabPolitics);
		logger.logPass("Navigating to the tab Politics", "");
		actions.waitForPageToLoad(10);
		
		actions.click(ulocator.carouselItem1);
		actions.click(ulocator.firstLink);
		logger.logPass("Navigating to the first link ", "");
		String header[] = new String[10];
		String newLink[] = new String[10];
		String datetime[] = new String[10];
		newLink[0] =actions.webDriver.getCurrentUrl().toString();
		header[0]  =actions.webDriver.getTitle();
		String date1 =actions.getWebElement(ulocator.DateTimeContent).getAttribute("content");
		//dateformat = 
		actions.getWebElement(ulocator.search).sendKeys("step-inforum" + Keys.ENTER);
		logger.logPass("Enter", "Step In Forum Entered");
		actions.waitImplicit(ulocator.step_in_channel, 5);
		actions.getWebElement(ulocator.step_in_channel).click();
		actions.waitImplicit(ulocator.Video, 5);
	}
 
//	@Test(priority = 2)
//	public void TC_UI_VideoLink() {
//		actions.getWebElement(ulocator.Video).click();
//		logger.logPass("Select", "Video Link is selected");
//		actions.waitImplicit(null, 3);
//	}
//
//
//	@Test(priority = 3)
//	public void TC_UI_GetVideoNameViaApi() {
//		String videoName = api.API_Get("http://54.169.34.162:5252/video");
//		logger.logPass("Get Video name via API", "VideoName : "+videoName);
//		common.SelectVideoFromList(ulocator.SelectVideo, videoName);
//	}
//
//	@Test(priority = 4)
//	public void TC_UI_SelectQuality() {
//		actions.mouseHover(ulocator.VideoSettings);
//		actions.waitImplicit(ulocator.VideoSettings, 5);
//		actions.click(ulocator.VideoSettings);
//		actions.click(ulocator.QualityMenu);
//		actions.waitImplicit(null, 1);
//		actions.select("360");			
//	}
//
//	@Test(priority = 5)
//	public void TC_UI_GetUpNextListAndPost() {
//			List<WebElement>videolist=actions.getWebElementList(ulocator.upnext);
//			String[] upNextList = common.GetUpNextVideo(videolist);
//			String jsonValue = actions.jsonCreate(upNextList);
//			File file = common.WriteToJsonFile(jsonValue,"UI");
//			String response = api.API_Post(file, "http://54.169.34.162:5252/upload");
//			logger.logPass("API uploaded response",response);
//			String getResponse = api.API_Get("http://54.169.34.162:5252/result/"+response);
//			logger.logPass("API Get response",getResponse);
//	}
 
}
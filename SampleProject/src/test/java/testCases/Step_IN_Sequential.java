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
import utilities.Mob_Locators;
import utilities.UI_Locators;

public class Step_IN_Sequential extends Global {
	// public BrowserConfig config = new BrowserConfig();
	public CommonMethods common = new CommonMethods();
	public Keywords actions = new Keywords();
	public UI_Locators ulocator = new UI_Locators();
	public Mob_Locators mlocator = new Mob_Locators();
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

		try {
			String carousel[] = {ulocator.carouselItem1,ulocator.carouselItem2,ulocator.carouselItem3};
			String links[] = {ulocator.firstLink,ulocator.secondLink,ulocator.thirdLink};
			for (int i = 0; i < links.length; i++) {
				actions.click(carousel[i]);
				actions.click(links[i]);
				logger.logPass("Click on the link : "+i, "");
				String newLink =actions.webDriver.getCurrentUrl().toString();
				String header  =actions.webDriver.getTitle();
				String date1 =actions.getWebElement(ulocator.DateTimeContent).getAttribute("content");
				String datetime =actions.dateformat(date1);
				logger.logPass("Navigating to the link "+ newLink+" "+ header +" "+ datetime,"" ); 
				actions.webDriver.navigate().back();
				actions.waitForPageToLoad(10);

				String jsonBody = actions.jsonCreateUI(newLink,header,datetime);
				logger.logInfo("Send Request : "+""+" : "+jsonBody);
				String Url = "http://ec2-54-254-162-245.ap-southeast-1.compute.amazonaws.com:9000/items/";
				String response = api.API_Post(jsonBody, Url);
				logger.logInfo("Send Response : "+""+" : "+response);
				String obtained_id = actions.getResponseValue(response, "id");
				
				String get_resposne = api.API_Get(Url+obtained_id);
				String obtained_name = actions.getResponseValueStr(get_resposne, "name");
				logger.logInfo("Received Response : "+""+" : "+get_resposne);

				//Validation 
				if(obtained_name.contains(newLink)) {
					logger.logPass("Validated Name : ", obtained_name);
				}else {
					logger.logFail("Name validation Failed : "+newLink);
				}
			}
			logger.logPass("All 3 Items in Carousel passed", "");
		}catch(Exception e) {
			logger.logFail("All 3 Items in Carousel failed" + e.getMessage());
		}
	}
	
	@Test(priority = 2)
	public void TC_Mob_Launch() {
		config.webDriver=null;
		common.Launch("Mobile", "");
		logger.logPass("Launch", "Step In Forum Launched");
	}
	
	@Test(priority = 3)
	public void TC_Mob_GoToDetailPage() {
		actions.waitExplicit(mlocator.getProd_Btn, 60);
		actions.click(mlocator.getProd_Btn);
		logger.logPass("Click", "Click on Get Products button");
		actions.waitExplicit(mlocator.ProductsList, 10);
		logger.logPass("Navigation", "Navigated to Details Page");
	}

	@Test(priority = 4)
	public void TC_Mob_GetProducts() {
		//User changed value
		//int prodCount = actions.getWebElementListWithoutExcel(mlocator.productCount).size();
		try {
		int prodCount = 2;
		for(int i=1; i<=prodCount; i++) {
			String ProductValue = mlocator.ProductsList.replace("##", String.valueOf(i));
			List<WebElement> prodDetails = actions.getWebElementListWithoutExcel(ProductValue);
			String[] productList =  common.GetAllProductDetails(prodDetails);
			String jsonBody = actions.jsonCreateMob(productList);
			logger.logInfo("Send Request : "+prodCount+" : "+jsonBody);
			String Url = "http://ec2-54-254-162-245.ap-southeast-1.compute.amazonaws.com:9000/items/";
			String response = api.API_Post(jsonBody, Url);
			String sent_name = actions.getResponseValueStr(response, "name");
			logger.logInfo("Send Response : "+prodCount+" : "+response);
			String obtained_id = actions.getResponseValue(response, "id");
			String get_resposne = api.API_Get(Url+obtained_id);
			logger.logInfo("Received Response : "+prodCount+" : "+get_resposne);
			String obtained_name = actions.getResponseValueStr(get_resposne, "name");
			
			//Validation
			if(obtained_name.contains(sent_name)) {
				logger.logPass("Validated Name : ", obtained_name);
			}else {
				logger.logFail("Name validation Failed : "+sent_name);
			}
		}
		logger.logPass("Products are fetched " , "Post and Get are working as expecetd");
		}catch(Exception e) {
			logger.logFail("Products fetched failed" + e.getMessage());
		}
	}
}

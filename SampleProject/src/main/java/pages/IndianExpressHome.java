package pages;

import api.APICommon;
import configuration.Keywords;
import utilities.Locators;
import utilities.Reporting;
import utilities.TestData;

public class IndianExpressHome {
	
	Locators locator = new Locators();
	Keywords actions = new Keywords();
	Reporting logger = new Reporting();
	TestData td = new TestData();
	APICommon api = new APICommon();
	
	//Locators : 
	
	 String dontAllow = "//button[contains(text(),\"Don't Allow\")]";
	 String sectionMenu = "//button[@class='hamburger-menu hamburger_btn toggle-menu']";
	 String menuIndia = "//ul[@class='topics-submenu']//a[@data-ie-event-label='India' and @href='https://indianexpress.com/section/india/']";
	 String generalTabs= "//a[@data-ie-event-label='##']";	
	 String carouselItem1 = "//ul[@class='i-sports-slides slick-initialized slick-slider slick-dotted']//ul/li/button[@aria-label='1 of 5']";
	 String carouselItem2 = "//ul[@class='i-sports-slides slick-initialized slick-slider slick-dotted']//ul/li/button[@aria-label='2 of 5']";
	 String carouselItem3 = "//ul[@class='i-sports-slides slick-initialized slick-slider slick-dotted']//ul/li/button[@aria-label='3 of 5']";
	 String  firstLink= "//li[@class='swiper-slide slick-slide slick-current slick-active' and @data-slick-index = '0']";
	 String  secondLink= "//li[@class='swiper-slide slick-slide slick-current slick-active' and @data-slick-index = '1']";
	 String  thirdLink= "//li[@class='swiper-slide slick-slide slick-current slick-active' and @data-slick-index = '2']";
	 String  DateTimeContent= "//span[@itemprop='dateModified']";
	
	public void ClosePopup() {
		try {
			//If Dont Allow Popup appears
			if(actions.getWebElementList(dontAllow).size()>0)
			{
			actions.click(dontAllow);
			logger.logPass("Clicking on alert button DontAllow", "");
			}
		}catch(Exception e) {
			logger.logFail("Failed to close popup due to exception "+e.getMessage());
		}
	}
	
	public void NavigatetoIndiaPage() {
		try {
			actions.javaScriptClick(sectionMenu);
			actions.click(menuIndia);
			logger.logPass("Navigating to the India Sub Menu", "");
			actions.waitForPageToLoad(30);
			actions.switchToWindow(td.IndiaPageTitleData);
		}catch(Exception e) {
			logger.logFail("Failed to Navigate due to exception "+e.getMessage());
		}
	}

	public void NavigatetoTabs(String TabName) {
		try {
			String clickOnTab = generalTabs.replace("##", TabName);
			actions.click(clickOnTab);
			logger.logPass("Navigating to the tab", TabName);
			actions.waitForPageToLoad(30);
		}catch(Exception e) {
			logger.logFail("Failed to Naviagte to tabs due to exception "+e.getMessage());
		}
	
	}
	
	public void ValidateCarousel_VerifyAPI(String validationFlag) {
		try {
			String carousel[] = {carouselItem1,carouselItem2,carouselItem3};
			String links[] = {firstLink,secondLink,thirdLink};
			for (int i = 0; i < links.length; i++) {
				actions.click(carousel[i]);
				actions.click(links[i]);
				logger.logPass("Click on the link : "+i, "");
				String newLink = actions.webDriver.getCurrentUrl().toString();
				String header  = actions.webDriver.getTitle();
				String date1 = actions.getWebElement(DateTimeContent).getAttribute("content");
				String datetime = actions.dateformat(date1);
				logger.logPass("Navigating to the first link "+ newLink+" "+ header +" "+ datetime,"" ); 
				actions.webDriver.navigate().back();
				actions.waitForPageToLoad(10);
				
				String jsonBody = actions.jsonCreateUI(newLink,header,datetime);
				logger.logInfo("Send Request : "+""+" : "+jsonBody);
				String Url = td.ApiUrlData;
				String response = api.API_Post(jsonBody, Url);
				logger.logInfo("Send Response : "+""+" : "+response);
				String obtained_id = actions.getResponseValue(response, "id");
				String get_resposne = api.API_Get(Url+obtained_id);
				String obtained_name = actions.getResponseValueStr(get_resposne, "name");
				String obtained_description = actions.getResponseValueStr(get_resposne, "description");
				String obtained_date = actions.getResponseValue(get_resposne, "price");

				logger.logInfo("Received Response : "+""+" : "+get_resposne);
				
				//Validation : 
				if(validationFlag.contains("Y")) {
					validatevalues(newLink,obtained_name);
					validatevalues(header,obtained_description);
					validatevalues(datetime,obtained_date);
				}
			}
		}catch(Exception e) {
			logger.logFail("Failed to Naviagte to tabs due to exception "+e.getMessage());
		}
	}
	
	public void validatevalues (String value1, String value2) {
		if(value1.equals(value2)) {
			logger.logPass("Value Matched ", "Actual : "+value1+" Expected : "+value2 );
		}else {
			logger.logFail("Value Not Matched. Actual : "+value1+" Expected : "+value2 );
		}
	}
}

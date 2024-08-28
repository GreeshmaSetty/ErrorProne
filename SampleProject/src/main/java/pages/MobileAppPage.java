package pages;

import java.util.List;

import org.openqa.selenium.WebElement;

import api.APICommon;
import configuration.Keywords;
import utilities.Locators;
import utilities.Reporting;
import utilities.TestData;

public class MobileAppPage {
	
	Locators locator = new Locators();
	Keywords actions = new Keywords();
	Reporting logger = new Reporting();
	CommonMethods common = new CommonMethods();
	TestData td = new TestData();
	APICommon api = new APICommon();
	
	//Locators : 
	 String getProd_Btn = "//android.widget.Button";
	 String ProductsList = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View[##]//android.widget.TextView[@text]";
	 String productCount = "//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View/android.view.View";
	 String CompanyName = "Epsilon1";
	
	public void MobileGoToDetailPage() {
		actions.waitExplicit(getProd_Btn, 60);
		actions.click(getProd_Btn);
		logger.logPass("Click", "Click on Get Products button");
		actions.waitExplicit(ProductsList, 10);
		logger.logPass("Navigation", "Navigated to Details Page");
	}

	public void MobGetProductsAndValidateAPI() {
		//User changed value
		//int prodCount = actions.getWebElementListWithoutExcel(mlocator.productCount).size();
		try {
		int prodCount = 2;
		for(int i=1; i<=prodCount; i++) {
			String ProductValue = ProductsList.replace("##", String.valueOf(i));
			List<WebElement> prodDetails = actions.getWebElementListWithoutExcel(ProductValue);
			String[] productList =  common.GetAllProductDetails(prodDetails);
			String jsonBody = actions.jsonCreateMob(productList);
			logger.logInfo("Send Request : "+prodCount+" : "+jsonBody);
			String Url = td.ApiUrlData;
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

package testCases;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;
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
	public void TC_Mob_GoToDetailPage() {
		actions.waitExplicit(mlocator.getProd_Btn, 60);
		actions.click(mlocator.getProd_Btn);
		logger.logPass("Click", "Click on Get Products button");
		actions.waitExplicit(mlocator.ProductsList, 10);
		logger.logPass("Navigation", "Navigated to Details Page");
	}

	@Test(priority = 1)
	public void TC_Mob_GetProducts() {
		//User changed value
		//int prodCount = actions.getWebElementListWithoutExcel(mlocator.productCount).size();
		int prodCount = 2;
		for(int i=1; i<=prodCount; i++) {
			String ProductValue = mlocator.ProductsList.replace("##", String.valueOf(prodCount));
			List<WebElement> prodDetails = actions.getWebElementListWithoutExcel(ProductValue);
			String[] productList =  common.GetAllProductDetails(prodDetails);
			String jsonBody = actions.jsonCreateMob(productList);
			logger.logInfo("Send Request : "+prodCount+" : "+jsonBody);
			String Url = "http://ec2-54-254-162-245.ap-southeast-1.compute.amazonaws.com:9000/items/";
			String response = api.API_Post(jsonBody, Url);
			logger.logInfo("Send Response : "+prodCount+" : "+response);
			String obtained_id = actions.getResponseValue(response, "id");
			String get_resposne = api.API_Get(Url+obtained_id);
			logger.logInfo("Received Response : "+prodCount+" : "+get_resposne);
		}
	}
}

package testCases;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.RequestSpecification;

import api.APICommon;
import configuration.BrowserConfig;
import configuration.Global;
import configuration.Keywords;
import io.restassured.response.Response;
import pages.CommonMethods;
import utilities.Mob_Locators;
import utilities.MyThread;
import utilities.UI_Locators;

public class ApiTest extends Global {
	public BrowserConfig config = new BrowserConfig();
	public CommonMethods common = new CommonMethods();
	public Keywords actions = new Keywords();
	public UI_Locators ulocator = new UI_Locators();
	public Mob_Locators mlocator = new Mob_Locators();
	public static HashMap<String, String> hashMap = new HashMap();
	APICommon api = new APICommon();

	@Test(priority = 1)
	public void RegistrationSuccessful()
	{ 
		String jsonBody = "{\"name\": \"{{RandomName}}\", \"job\": \"Programmer\"}";
		api.API_Post("https://reqres.in/api/users", jsonBody);
	}
}

package testCases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import configuration.BrowserConfig;
import configuration.Global;
import configuration.Keywords;
import pages.CommonMethods;
import utilities.UI_Locators;

public class TestCase extends Global {
	public BrowserConfig config = new BrowserConfig();
	public CommonMethods common = new CommonMethods();
	public Keywords actions = new Keywords();
	public UI_Locators locator = new UI_Locators();

	public static HashMap<String, String> hashMap = new HashMap();

	@Test(priority = 0)
	public void TC_01_AddProfile_ValidData() {

		common.Launch("Chrome", "https://www.google.co.in/");
		String[] movie = common
				.csv_FileRead("C:/Users/epsilon/Documents/GitHub/ErrorProne/SampleProject/Testdata/Movielist.csv");
		for (int i = 1; i < movie.length; i += 2) {
			try {
				actions.getWebElement(locator.search).sendKeys(movie[i] + Keys.ENTER);
				List<WebElement> links = actions.getWebElementList(locator.wikipedia_Link);
				hashMap.put(movie[i], links.get(0).getAttribute("href"));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			actions.waitExplicit(locator.search, 10);
			actions.clear(locator.search);

		}
	}

	@Test(priority = 1)
	public void TC_02_AddProfile_ValidData() {
		try {
			for (String movie : hashMap.keySet()) {
				Thread t = new Thread(movie);
				t.start();
				t.join(10);
				common.get_DirectorandCompare(movie, hashMap.get(movie).toString());
				System.out.println(t.currentThread().getId());
			}
		} catch (Exception e) {

		}

	}

}

package pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import api.APICommon;
import configuration.BrowserConfig;
import configuration.Keywords;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import utilities.DataGeneration;
import utilities.Mob_Locators;
import utilities.Reporting;
import utilities.TestData;
import utilities.UI_Locators;

public class CommonMethods {

	public Keywords actions = new Keywords();
	public DataGeneration dataGenerate = new DataGeneration();
	public Reporting logger = new Reporting();
	public BrowserConfig config = new BrowserConfig();
	public UI_Locators locator = new UI_Locators();
	public static HashMap<String, String> imdbmap = new HashMap();
	TestData td = new TestData();
	APICommon api = new APICommon();

	public void Launch(String browserName,String Mode, String Url) {
		try {
			if (config.webDriver == null)
				config.Launch(browserName,Mode,Url);
			else
				actions.getURL(Url);
			//logger.logPass("Launched", "N");
		} catch (Exception e) {
			System.out.println("Failed to Launch due to exception " + e.getMessage());
		}
	}

	public String generateRandomNumber() {
		Random rn = new Random();
		long range = 1000000000L + (long) (rn.nextDouble() * 999999999L);
		String randNumb = String.valueOf(range);
		return randNumb;
	}

	public String getDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
		LocalDateTime now = LocalDateTime.now();
		return (dtf.format(now));
	}

	public String[] csv_FileRead(String Fileloc) {
		BufferedReader br = null;
		String line = " ";
		String[] data = null;
		try {
			br = new BufferedReader(new FileReader(Fileloc));
			while ((line = br.readLine()) != null) {
				data = line.split("\\|");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return data;

	}

	public void SelectVideoFromList(String Locator,String videoName) {
		try {
			WebElement findVideo = null;
			int flag = 0;
			do{
				try {
					findVideo = actions.getWebElement("//a[@id='video-title'][@title='"+videoName+"']");
				}catch(Exception e) {

				}
				actions.scroll(0, 100);
				Thread.sleep(300);
				flag++;
				if(flag>250)
					break;
				//actions.waitForPageToLoad(30);
			}while(findVideo== null);
			if(findVideo==null) {
				logger.logFail("Video Not Found in list");
			}
			actions.jsScrollToElement(findVideo);
			logger.logPass("Screenshot Of Video", "Located video");
			actions.click(findVideo);
			actions.waitForPageToLoad(10);
		}catch(Exception e) {
			logger.logFail("Failed to select video from list due to exception "+e.getMessage());
		}
	}
	
	

	public String[] GetAllProductDetails(List<WebElement> prodlist) {
	    String[] prodDetails = new String[prodlist.size()]; // Initialize the array with the correct size
	    try {
	        for (int j = 0; j < prodlist.size(); j++) {
	            prodDetails[j] = prodlist.get(j).getText(); // Assign the text to the array
	            System.out.println(prodDetails[j]); // Print the details
	        }
	    } catch (Exception e) {
	        logger.logFail("Failed to get product details from list due to exception " + e.getMessage());
	    }
	    return prodDetails; // Return the array with product details
	}
	public File WriteToJsonFile(String jsonValue,String Mode) {
		File file = new File(System.getProperty("user.dir") + "//src//main//resources//jsonBody"+Mode+".json");
		try {
			Writer writer;
			writer = new FileWriter(file);
			writer.write(jsonValue);
			writer.close();
		} catch (IOException e) {
			logger.logFail("Failed to write to json file due to exception "+e.getMessage());
		}
		return file;
	}

	public void validateBrokenLinks(String Flag) {
		try {
			boolean failedflag = false;
			// Get all the links on the page
			if(Flag.toUpperCase().contains("Y")) {
				List<WebElement> links = config.webDriver.findElements(By.tagName("a"));

				logger.logInfo("Total links found: " + links.size());

				// Iterate over each link and validate it
				for (WebElement link : links) {
					String linkURL = link.getAttribute("href");
					if (linkURL != null && !linkURL.isEmpty()) {
						try {
							System.out.println("Validating broken links for : "+linkURL);
							// Create a URL object and open a connection
							URL urlLink = new URL(linkURL);
							HttpURLConnection httpURLConnection = (HttpURLConnection) urlLink.openConnection();
							httpURLConnection.setConnectTimeout(5000);
							httpURLConnection.connect();

							// Check the response code
							if (httpURLConnection.getResponseCode() >= 400) {
								logger.logFail(linkURL + " is a broken link. Response Code: " + httpURLConnection.getResponseCode());
								failedflag = true;
							}

							// Disconnect the connection
							httpURLConnection.disconnect();

						} catch (IOException e) {
							System.out.println(linkURL + " is a broken link. Exception: " + e.getMessage());
						}
					} else {
						System.out.println("Link is either not configured or is empty.");
					}
				}
				if(!failedflag) {
					logger.logPass("Broken Link Validation : ", "No Broken Link Found on the Page");
				}
			}

		}catch(Exception e) {

		}
	}
	
	public void validatevalues (String value1, String value2) {
		if(value1.contentEquals(value2)) {
			logger.logPass("Value Matched. Actual : "+value1+" Expected : "+value2 );
		}else {
			logger.logFail("Value Not Matched. Actual : "+value1+" Expected : "+value2 );
		}
	}
	
	
	public void validateDifferentStatusCodes(String statusCode) {
		if(statusCode.contains("422")) {
				JSONArray NewsDataArray = new JSONArray();
				String json = "{\r\n" + 
						" \r\n" + 
						"  \"name\": \"This is the news\",\r\n" + 
						"  \"description\": \"String data in place of Int\",\r\n" + 
						"  \"price\": \"string format\",\r\n" + 
						"  \"item_type\": \"Epsilon1\"\r\n" + 
						"}";
				
			logger.logInfo("Send Request : "+""+" : "+json);
			String Url = td.ApiUrlData;
			String statusCodeObtained = api.API_PostStatusCode(json, Url);
			
			validatevalues(statusCode, statusCodeObtained);
			
		}if(statusCode.contains("405")) {
			JSONArray NewsDataArray = new JSONArray();
			String json = "{\r\n" + 
					" \r\n" + 
					"  \"name\": \"This is the news\",\r\n" + 
					"  \"description\": \"String data \",\r\n" + 
					"  \"price\": 1234,\r\n" + 
					"  \"item_type\": \"Epsilon1\"\r\n" + 
					"}";
			
		logger.logInfo("Send Request : "+""+" : "+json);
		String Url = td.ApiUrlData;
		String statusCodeObtained = api.API_PutStatusCode(json, Url);
		
		validatevalues(statusCode, statusCodeObtained);
		
	}if(statusCode.contains("500")) {
		JSONArray NewsDataArray = new JSONArray();
		String json = "{\r\n" + 
				" \r\n" + 
				"  \"LineHeader\": \"This is the news\",\r\n" + 
				"  \"LineDescription\": \"String data in place of Int\",\r\n" + 
				"  \"price\": 1234,\r\n" + 
				"  \"item_type\": \"Epsilon1\"\r\n" + 
				"}";
		
	logger.logInfo("Send Request : "+""+" : "+json);
	String Url = td.ApiUrlData;
	String statusCodeObtained = api.API_PostStatusCode(json, Url);
	
	validatevalues(statusCode, statusCodeObtained);
	
}
		
	}
}

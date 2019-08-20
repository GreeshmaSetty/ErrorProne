package pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.testng.Assert;

import configuration.BrowserConfig;
import configuration.Keywords;
import utilities.DataGeneration;
import utilities.Reporting;
import utilities.UI_Locators;

public class CommonMethods {

	public Keywords actions = new Keywords();
	public DataGeneration dataGenerate = new DataGeneration();
	public Reporting logger = new Reporting();
	public BrowserConfig config = new BrowserConfig();
	public UI_Locators locator = new UI_Locators();
	public static HashMap<String, String> imdbmap = new HashMap();

	public void Launch(String browserName, String Url) {
		try {
			if (config.webDriver == null)
				config.Launch(browserName, Url);
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

	public void get_DirectorandCompare(String moviename, String Wikipedialink) {
		try {
			Launch("Chrome", Wikipedialink);
			String Wiki_Directorname = actions.getWebElement(locator.directed_By).getText();
			String Imdb_link = actions.getWebElement(locator.wiki_imdblink).getAttribute("href");
			Launch("Chrome", Imdb_link);
			actions.waitExplicit(locator.imdblink_directed_By, 30);
			String Imdb_Directorname = actions.getWebElement(locator.imdblink_directed_By).getText();
			System.out.println(moviename+"Dir1:" + Wiki_Directorname + "Dir2:" + Imdb_Directorname);
			if(Wiki_Directorname.equals(Imdb_Directorname))
			{
				System.out.println(moviename+"Pass");
				//logger.logPass(moviename, Wiki_Directorname, Imdb_link, Imdb_Directorname);
			}
			//Assert.assertEquals(Wiki_Directorname, Imdb_Directorname);
		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}

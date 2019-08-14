package pages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import configuration.BrowserConfig;
import configuration.Keywords;
import utilities.DataGeneration;
import utilities.Reporting;

public class CommonMethods {

	public Keywords actions = new Keywords();
	public DataGeneration dataGenerate = new DataGeneration();
	public Reporting logger = new Reporting();
	public BrowserConfig config = new BrowserConfig();

	public void Launch(String browserName, String Url) {
		try {
			config.Launch(browserName, Url);
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
	
}

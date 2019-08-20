package utilities;

public class Mob_Locators {
	
	public  String search = "//input[@aria-label='Search']";
	
	//public static String wiki = "//a[@href='https://en.wikipedia.org/wiki/";
	
	public  String wikipedia_Link = "//a[starts-with(@href,'https://en.m.wikipedia.org/wiki')]";
	
	public  String directed_By="//th[contains(text(),'Directed by')]/../td/a";
	
	public  String wiki_imdblink="//span[@id='External_links']/../following-sibling::ul/li/a[@title='IMDb']//preceding-sibling::a[@href]";
	
	public  String imdblink_directed_By="//div[@class='credit_summary_item']//h4[contains(text(),'Director')]//following-sibling::a";
	

}

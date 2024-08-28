package utilities;
 
public class UI_Locators {
 
	public  String search = "//input[@id='search']";
 
	public  String step_in_channel = "//div[@id='avatar']/yt-img-shadow//img[@id='img']";
 
	public String Video="//div[@id='tabsContainer']/div/paper-tab[2]/div";
 
	public String VideoSettings = "//div[@id='movie_player']//button[@aria-label='Settings']";
 
	public String QualityMenu = "//div[@role='menu']//div[@class='ytp-menuitem-label'][text()='Quality']";
	public String QualityList = "//div[@class='ytp-panel ytp-quality-menu']";
	public String upnext="//div[@id='items']//span[@id='video-title'][@title]";
	public String SelectVideo = "//a[@id='video-title'][@title='##']";
	//-----------------------------------------------//
	public String dontAllow = "//button[contains(text(),\"Don't Allow\")]";
	public String sectionMenu = "//button[@class='hamburger-menu hamburger_btn toggle-menu']";
	public String menuIndia = "//ul[@class='topics-submenu']//a[@data-ie-event-label='India' and @href='https://indianexpress.com/section/india/']";
	public String  tabPolitics= "//a[@data-ie-event-label='Politics']";
 
	public String  carouselItem1 = "//ul[@class='i-sports-slides slick-initialized slick-slider slick-dotted']//ul/li/button[@aria-label='1 of 5']";
 
	public String carouselItem2 = "//ul[@class='i-sports-slides slick-initialized slick-slider slick-dotted']//ul/li/button[@aria-label='2 of 5']";
 
	public String carouselItem3 = "//ul[@class='i-sports-slides slick-initialized slick-slider slick-dotted']//ul/li/button[@aria-label='3 of 5']";
 
	public String  firstLink= "//li[@class='swiper-slide slick-slide slick-current slick-active' and @data-slick-index = '0']";
 
	public String  secondLink= "//li[@class='swiper-slide slick-slide slick-current slick-active' and @data-slick-index = '1']";
 
	public String  thirdLink= "//li[@class='swiper-slide slick-slide slick-current slick-active' and @data-slick-index = '2']";
 
	public String  DateTimeContent= "//span[@itemprop='dateModified']";
	///content get for date and time 
	//content="2024-08-27T20:45:30+05:30" to DDMMYYYY
 
//	public String  = "";
	public String IndiaPageTitleData = "India News: Latest News India, Today Breaking News Headlines from India and Updates in Bharat | The Indian Express";
	public String UrlData = "https://www.indianexpress.com";
	
	public String ApiUrlData = "http://ec2-54-254-162-245.ap-southeast-1.compute.amazonaws.com:9000/items/";
}
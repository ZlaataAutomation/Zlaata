package objectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public abstract class landingPageObjRepo extends  BasePage {
	
	@FindBy(xpath = "//div[@class='toggle_icon']")
	protected WebElement hambager;

	@FindBy(xpath = "//h2[@class='prod_listing_topic']")
	protected WebElement categoryName;
	
	@FindBy(xpath = "//img[@alt='Empty image']")
	protected WebElement listingPageEmptyImage;
	
	@FindBy(xpath = "//a[@href='zlaata-india']//div[@class='landing_page_content']//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW']")
	   protected WebElement zlaataIndiaShopButton;
	
	 @FindBy(xpath = "//a[@href='/boss-lady/']//div[@class='landing_page_content']//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW']")
	   protected WebElement bossladyShopButton;
	
	
}

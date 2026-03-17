package objectRepo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public abstract class AllBrokenLinkObjRepo extends BasePage{
	
	@FindBy(xpath = "//a[@href='zlaata-india']//div[@class='landing_page_content']//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW']")
	   protected WebElement zlaataIndiaShopButton;
	
	

}

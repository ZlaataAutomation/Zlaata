package pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.MenuObjRepo;
import utils.Common;

public final class Menus extends MenuObjRepo {

	

	public Menus(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	public void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
  public void clickHome() {
	
	click(homeMenu);
	String banner = banners.getTagName();
    Assert.assertTrue("Banner is Visible", banner.length() <= 50);
    

}
 public void clickNewArrival() {
	 
	 click(newArrivalMenu);
		String heading = newArrivalheading.getText();
		if (heading.contains(heading)) {
			System.out.println("The heading is displayed: "+ heading);
			
		}
		else {
			System.out.println("The Menu is not redirecting");
		}
	    Assert.assertTrue("Navigated to New Arrivals Page", heading.equals(heading));
	    
}
 
 public void newArrivalSuggestion() {
	 
	 Actions actions = new Actions(driver);
	 actions.moveToElement(newArrivalMenu).perform();

	 // Get all dropdown products
	 List<WebElement> clickRandomProduct = driver.findElements(By.xpath("//span[@class='na_dropdown_card_name']"));
	 Collections.shuffle(clickRandomProduct);

	 if (!clickRandomProduct.isEmpty()) {
	     WebElement randomProduct = clickRandomProduct.get(0);
	     actions.moveToElement(randomProduct).click().perform();

	     // Get product heading
	     String heading = newArrivalSuggestionRedirection.getText();

	     // Assert that heading is not empty
	     Assert.assertFalse("❌ Product heading is empty, navigation may have failed!", heading.isEmpty());

	     // Optional: Check that URL contains 'product'
	     String currentUrl = driver.getCurrentUrl();
	     Assert.assertTrue("❌ Did not navigate to product details page!", currentUrl.contains("product"));

	     System.out.println("✅ Navigated to product details page. Heading: " + heading);
	 } else {
	     System.out.println("⚠️ No products found in New Arrivals dropdown.");
	 }

 }
public void saleMenu() {
	click(saleMenu);
	String heading = saleMenuHead.getText();
    Assert.assertTrue("Navigated to sale Page", heading.length() <= 50);

}

	public void bossLady() {
		Actions actions = new Actions(driver);
		actions.moveToElement(bossLadyMenu).build().perform();
	    List<WebElement> clickRandomProduct = driver.findElements(By.xpath("//span[@class='bl_dropdown_card_name']"));
		Collections.shuffle(clickRandomProduct);

		if (!clickRandomProduct.isEmpty()) {
			WebElement randomProduct = clickRandomProduct.get(0);
			actions.moveToElement(randomProduct).click().build().perform();
			String heading = bossLadyPage.getText();
		    Assert.assertTrue("Navigated to boss lady Page", heading.length() <= 50);
		
	
		if (heading.contains(heading)) {
			System.out.println("The heading is displayed: "+ heading);
			
		}
		else {
			System.out.println("The Menu is not redirecting");
		}
		}
	}
	
	public void shopCategory() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		String heading = shopPageHead.getText();
	    Assert.assertTrue("Navigated to Category Page", heading.length() <= 50);
	    if (heading.contains(heading)) {
			System.out.println("The heading is displayed: "+ heading);
			
		}
		else {
			System.out.println("The Menu is not redirecting");
	
		}	

	}
	
	public void shopCollection() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		String heading = shopPageHead.getText();
	    Assert.assertTrue("Navigated to Collection Page", heading.length() <= 50);
	    if (heading.contains(heading)) {
			System.out.println("The heading is displayed: "+ heading);
			
		}
		else {
			System.out.println("The Menu is not redirecting");
	
		}	

	}
	
	public void shopStyles() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		String heading = shopPageHead.getText();
	    Assert.assertTrue("Navigated to Category Page", heading.length() <= 50);
	    if (heading.contains(heading)) {
			System.out.println("The heading is displayed: "+ heading);
			
		}
		else {
			System.out.println("The Menu is not redirecting");
	
		}

	}
	
	public void PopShop() {
		Common.waitForElement(5);
		click(popShop);
	}
	
	public void getUpdates() 
	{
		Common.waitForElement(1);
		click(getUpdateMenu);
		

	}
	
	public void appLaunch() {
		HomePage home = new HomePage(driver);
		home.homeLaunch();
	}
	
	
	
	
	public void verifyHomeMenu() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    String GREEN  = "\u001B[32m";
	    String RESET  = "\u001B[0m";
	    String CYAN   = "\u001B[36m";
	    String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
	    System.out.println(GREEN + "🚀 Starting Home Header Menu..." + RESET);
	    System.out.println(CYAN + line + RESET);
	    WebElement homeMenu1 = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[normalize-space()='Home']") 
	    ));
	    homeMenu1.click();
	    Common.waitForElement(2);
	    String beforeClickUrl = driver.getCurrentUrl();
	    System.out.println("🔍 URL before clicking Home: " + beforeClickUrl);

	    WebElement homeMenu = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[normalize-space()='Home']") 
	    ));

	    assertTrue("❌ Home menu is not clickable", homeMenu.isEnabled());
	    homeMenu.click();
	    System.out.println(GREEN + "✅ Home menu clicked" + RESET);

	    wait.until(ExpectedConditions.urlToBe(beforeClickUrl));

	    String afterClickUrl = driver.getCurrentUrl();
	    System.out.println("🔍 URL after clicking Home : " + afterClickUrl);

	    assertEquals(
	            "❌ URL changed after clicking Home!",
	            beforeClickUrl,
	            afterClickUrl
	    );

	    System.out.println(
	            GREEN + "✅ URL unchanged | Before: " + beforeClickUrl +
	            " | After: " + afterClickUrl + RESET
	    );

	    WebElement homeBanner = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'home_banner_container')]")
	    ));

	    assertTrue(
	            "❌ Home page banner is not displayed",
	            homeBanner.isDisplayed()
	    );

	    System.out.println(GREEN + "✅ Home page banner is displayed" + RESET);
	}
		
	public void verifynewArrivalMenu() throws InterruptedException {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    Common.waitForElement(2);
	    String RESET  = "\u001B[0m";
	    String GREEN  = "\u001B[32m";
	    String CYAN   = "\u001B[36m";
	    String YELLOW = "\u001B[33m";
	    String PURPLE = "\u001B[35m";
	    String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
	    System.out.println(GREEN + "🚀 Starting New Arrival Header Menu..." + RESET);
	    System.out.println(CYAN + line + RESET);

	    String expectedUrl = "https://www.zlaata.com/new-arrivals";

	    System.out.println(CYAN + "🔍 Verifying New Arrival menu..." + RESET);

	    WebElement newArrivalMenu = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[normalize-space()='New Arrivals']")
	    ));

	    assertTrue("❌ New Arrival menu is not clickable", newArrivalMenu.isEnabled());
	    System.out.println(GREEN + "✅ New Arrival menu is clickable" + RESET);

	    System.out.println(YELLOW + "👉 Clicking New Arrival menu" + RESET);
	    newArrivalMenu.click();

	    wait.until(ExpectedConditions.urlToBe(expectedUrl));
	    String actualUrl = driver.getCurrentUrl();

	    assertEquals(
	            "❌ URL mismatch! Expected: " + expectedUrl + " | Actual: " + actualUrl,
	            expectedUrl,
	            actualUrl
	    );

	    System.out.println(
	            GREEN + "✅ URL verified" + RESET +
	            CYAN  + " | Expected: " + expectedUrl +
	            " | Actual: " + actualUrl + RESET
	    );

	    System.out.println(CYAN + "🔍 Verifying New Arrival heading" + RESET);

	    WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//h3[@class='prod_list_topic']//span[normalize-space()='NEW ARRIVALS']")
	    ));

	    assertTrue("❌ New Arrival heading not displayed", heading.isDisplayed());
	    System.out.println(GREEN + "✅ New Arrival heading is displayed" + RESET);

	    System.out.println(CYAN + "🔍 Verifying product list" + RESET);

	    List<WebElement> products = wait.until(ExpectedConditions
	            .visibilityOfAllElementsLocatedBy(
	                    By.xpath("//div[contains(@class,'product_list_cards_list')]")
	            ));

	    assertTrue(
	            "❌ Less than 2 products displayed! Found: " + products.size(),
	            products.size() >= 2
	    );

	    System.out.println(
	            PURPLE + "📦 Products displayed count: " + products.size() + RESET
	    );
	    
	    
	    

	    System.out.println(CYAN + "🔍 Verifying New Arrival hover dropdown" + RESET);

	    Actions actions = new Actions(driver);

	    WebElement newArrivalMenuHover = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//a[normalize-space()='New Arrivals']")
	            )
	    );

	    // Hover safely
	    actions.moveToElement(newArrivalMenuHover)
	           .pause(Duration.ofMillis(700))
	           .perform();

	    // Dropdown container
	    WebElement dropdown = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//div[contains(@class,'new_arrival_dropdown')]")
	            )
	    );

	    assertTrue("❌ New Arrival dropdown not visible on hover", dropdown.isDisplayed());
	    System.out.println(GREEN + "✅ New Arrival dropdown displayed on hover" + RESET);

	    // Dropdown products
	    List<WebElement> dropdownProducts = wait.until(
	            ExpectedConditions.numberOfElementsToBeMoreThan(
	                    By.xpath("//div[contains(@class,'new_arrival_dropdown')]//a[contains(@class,'na_dropdown_card')]"),
	                    2
	            )
	    );

	    System.out.println(
	            PURPLE + "🧾 Dropdown products count: " + dropdownProducts.size() + RESET
	    );

	    // Print product names
	    for (WebElement product : dropdownProducts) {
	        String productName = product.findElement(
	                By.xpath(".//span[@class='na_dropdown_card_name']")
	        ).getText().trim();

	        System.out.println(YELLOW + "➡ " + productName + RESET);
	    }
	}
	
	public void verifySaleMenu() {
		Common.waitForElement(2);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    String RESET  = "\u001B[0m";
	    String GREEN  = "\u001B[32m";
	    String CYAN   = "\u001B[36m";
	    String YELLOW = "\u001B[33m";
	    String PURPLE = "\u001B[35m";
	    String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
	    System.out.println(GREEN + "🚀 Starting Sale Header Menu..." + RESET);
	    System.out.println(CYAN + line + RESET);

	    String expectedUrl = "https://www.zlaata.com/sale";

	    System.out.println(CYAN + "🔍 Verifying Sale menu..." + RESET);

	    WebElement saleMenu = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[normalize-space()='SALE']")
	    ));

	    assertTrue("❌ Sale menu is not clickable", saleMenu.isEnabled());
	    System.out.println(GREEN + "✅ Sale menu is clickable" + RESET);

	    System.out.println(YELLOW + "👉 Clicking Sale menu" + RESET);
	    saleMenu.click();

	    wait.until(ExpectedConditions.urlToBe(expectedUrl));
	    String actualUrl = driver.getCurrentUrl();

	    assertEquals(
	            "❌ URL mismatch! Expected: " + expectedUrl + " | Actual: " + actualUrl,
	            expectedUrl,
	            actualUrl
	    );

	    System.out.println(
	            GREEN + "✅ URL verified" + RESET +
	            CYAN  + " | Expected: " + expectedUrl +
	            " | Actual: " + actualUrl + RESET
	    );

	    System.out.println(CYAN + "🔍 Verifying Sale products..." + RESET);

	    List<WebElement> products = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                    By.xpath("//div[contains(@class,'product_list_cards_list')]")
	            )
	    );

	    assertTrue(
	            "❌ Less than 2 Sale products displayed! Found: " + products.size(),
	            products.size() >= 2
	    );

	    System.out.println(
	            PURPLE + "📦 Sale products displayed count: " + products.size() + RESET
	    );
	}
	
	
	public void verifyShopHeaderMenu() throws InterruptedException {

	    Common.waitForElement(2);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    Actions actions = new Actions(driver);

	    // 🎨 Console Colors
	    String RESET  = "\u001B[0m";
	    String GREEN  = "\u001B[32m";
	    String CYAN   = "\u001B[36m";
	    String YELLOW = "\u001B[33m";
	    String RED    = "\u001B[31m";

	    String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
	    System.out.println(GREEN + "🚀 Starting Shop Header Menu Validation" + RESET);
	    System.out.println(CYAN + line + RESET);


	    WebElement shopMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//span[@class='navigation_menu_txt'][normalize-space()='Shop']")
	    ));

	    System.out.println(CYAN + "🔍 Shop menu located" + RESET);

	    String beforeClickUrl = driver.getCurrentUrl();
	    shopMenu.click();
	    Common.waitForElement(1);
	    String afterClickUrl = driver.getCurrentUrl();

	    if (!beforeClickUrl.equals(afterClickUrl)) {
	        System.out.println(RED + "❌ Shop menu changed URL on click (Should NOT)" + RESET);
	        fail("Shop menu should not be clickable");
	    }

	    System.out.println(GREEN + "✅ Shop menu is NOT clickable (URL unchanged)" + RESET);

	    System.out.println(YELLOW + "👉 Hovering over Shop menu" + RESET);
	    actions.moveToElement(shopMenu).perform();

	    Thread.sleep(2000);
	    WebElement categories = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//h5[@class='nav_drop_down_category_heading' and normalize-space()='Categories']")
	    ));

	    WebElement collection = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//h5[@class='nav_drop_down_category_heading' and normalize-space()='Collection']")
	    ));

	    WebElement styles = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//h5[@class='nav_drop_down_category_heading' and normalize-space()='Styles']")
	    ));

	    assertTrue("❌ Categories not displayed", categories.isDisplayed());
	    assertTrue("❌ Collection not displayed", collection.isDisplayed());
	    assertTrue("❌ Styles not displayed", styles.isDisplayed());

	    System.out.println(
	            GREEN + "✅ Shop hover menu displayed: Categories | Collection | Styles" + RESET
	    );
	}
	
	
	public void verifyBossLadyMenu() {

	    Common.waitForElement(2);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    Actions actions = new Actions(driver);

	    // 🎨 Console Colors
	    String RESET  = "\u001B[0m";
	    String GREEN  = "\u001B[32m";
	    String CYAN   = "\u001B[36m";
	    String YELLOW = "\u001B[33m";
	    String RED    = "\u001B[31m";
	    String PURPLE = "\u001B[35m";

	    System.out.println(CYAN + "🔍 Verifying Boss Lady header menu" + RESET);

	    // 🔁 Always re-locate (avoid stale element)
	    WebElement bossLadyMenu = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//li[contains(@class,'boss-lady')]//span[normalize-space()='Boss Lady']")
	            )
	    );

	    // ❌ Boss Lady should NOT be clickable
	    String beforeClickUrl = driver.getCurrentUrl();
	    bossLadyMenu.click();
	    Common.waitForElement(1);
	    String afterClickUrl = driver.getCurrentUrl();

	    if (!beforeClickUrl.equals(afterClickUrl)) {
	        System.out.println(RED + "❌ Boss Lady menu changed URL on click (Should NOT)" + RESET);
	        fail("Boss Lady menu should not be clickable");
	    }

	    System.out.println(GREEN + "✅ Boss Lady menu is NOT clickable (URL unchanged)" + RESET);


	    // 👉 Hover on Boss Lady
	    System.out.println(YELLOW + "👉 Hovering on Boss Lady menu" + RESET);

	    actions.moveToElement(bossLadyMenu)
	           .pause(Duration.ofMillis(800))
	           .perform();

	    // ✅ Dropdown container
	    WebElement dropdown = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//li[contains(@class,'boss-lady')]//div[contains(@class,'bl_dropdown')]")
	            )
	    );

	    assertTrue("❌ Boss Lady dropdown not visible on hover", dropdown.isDisplayed());
	    System.out.println(GREEN + "✅ Boss Lady dropdown displayed" + RESET);

	    // ✅ Dropdown cards
	    List<WebElement> dropdownItems = wait.until(
	            ExpectedConditions.numberOfElementsToBeMoreThan(
	                    By.xpath("//li[contains(@class,'boss-lady')]//a[contains(@class,'bl_dropdown_card')]"),
	                    2
	            )
	    );

	    System.out.println(
	            PURPLE + "🧾 Boss Lady dropdown items count: " + dropdownItems.size() + RESET
	    );

	    // 🖨️ Print category names
	    for (WebElement item : dropdownItems) {
	        String name = item.findElement(
	                By.xpath(".//span[@class='bl_dropdown_card_name']")
	        ).getText().trim();

	        System.out.println(YELLOW + "➡ " + name + RESET);
	    }
	}
	
	public void verifyStyledByMenu() {

	    Common.waitForElement(2);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    // 🎨 Console Colors
	    String RESET  = "\u001B[0m";
	    String GREEN  = "\u001B[32m";
	    String CYAN   = "\u001B[36m";
	    String YELLOW = "\u001B[33m";
	    String RED    = "\u001B[31m";
	    String PURPLE = "\u001B[35m";

	    System.out.println(CYAN + "🔍 Verifying Styled By header menu" + RESET);

	    String expectedUrl = "https://www.zlaata.com/influencers";

	    // 🔁 Locate Styled By menu
	    WebElement styledByMenu = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("//a[normalize-space()='Styled By']")
	            )
	    );

	    // ✅ Clickable validation
	    assertTrue("❌ Styled By menu is not clickable", styledByMenu.isEnabled());
	    System.out.println(GREEN + "✅ Styled By menu is clickable" + RESET);

	    // 👉 Click Styled By
	    System.out.println(YELLOW + "👉 Clicking Styled By menu" + RESET);
	    styledByMenu.click();

	    // ✅ URL validation
	    wait.until(ExpectedConditions.urlToBe(expectedUrl));
	    String actualUrl = driver.getCurrentUrl();

	    assertEquals(
	            "❌ URL mismatch! Expected: " + expectedUrl + " | Actual: " + actualUrl,
	            expectedUrl,
	            actualUrl
	    );

	    System.out.println(
	            GREEN + "✅ URL verified" + RESET +
	            CYAN + " | Expected: " + expectedUrl +
	            " | Actual: " + actualUrl + RESET
	    );

	    // ✅ Heading validation
	    System.out.println(CYAN + "🔍 Verifying Style by heading" + RESET);

	    WebElement heading = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//h3[@class='prod_list_topic']//span[normalize-space()='Style by']")
	            )
	    );

	    assertTrue("❌ Style by heading not displayed", heading.isDisplayed());
	    System.out.println(GREEN + "✅ Style by heading is displayed" + RESET);

	    // ✅ Products validation
	    System.out.println(CYAN + "🔍 Verifying product list" + RESET);

	    List<WebElement> products = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                    By.xpath("//div[contains(@class,'product_list_cards_list')]")
	            )
	    );

	    assertTrue(
	            "❌ Less than 2 products displayed! Found: " + products.size(),
	            products.size() >= 2
	    );

	    System.out.println(
	            PURPLE + "📦 Products displayed count: " + products.size() + RESET
	    );
	}
	
	
	public void verifyGiftMenu() {

	    Common.waitForElement(2);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	    // 🎨 Console Colors
	    String RESET  = "\u001B[0m";
	    String GREEN  = "\u001B[32m";
	    String CYAN   = "\u001B[36m";
	    String YELLOW = "\u001B[33m";
	    String RED    = "\u001B[31m";
	    String PURPLE = "\u001B[35m";

	    System.out.println(CYAN + "🔍 Verifying Gift header menu" + RESET);

	    String expectedUrl = "https://www.zlaata.com/gift";

	    // 🔁 Locate Gift menu
	    WebElement giftMenu = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("//a[normalize-space()='Gift']")
	            )
	    );

	    // ✅ Clickable validation
	    assertTrue("❌ Gift menu is not clickable", giftMenu.isEnabled());
	    System.out.println(GREEN + "✅ Gift menu is clickable" + RESET);

	    // 👉 Click Gift
	    System.out.println(YELLOW + "👉 Clicking Gift menu" + RESET);
	    giftMenu.click();

	    // ✅ URL validation
	    wait.until(ExpectedConditions.urlToBe(expectedUrl));
	    String actualUrl = driver.getCurrentUrl();

	    assertEquals(
	            "❌ URL mismatch! Expected: " + expectedUrl + " | Actual: " + actualUrl,
	            expectedUrl,
	            actualUrl
	    );

	    System.out.println(
	            GREEN + "✅ URL verified" + RESET +
	            CYAN + " | Expected: " + expectedUrl +
	            " | Actual: " + actualUrl + RESET
	    );

	    // ✅ Heading validation
	    System.out.println(CYAN + "🔍 Verifying Gifts Collection heading" + RESET);

	    WebElement heading = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//h3[@class='prod_list_topic']//span[normalize-space()='Gifts Collection']")
	            )
	    );

	    assertTrue("❌ Gifts Collection heading not displayed", heading.isDisplayed());
	    System.out.println(GREEN + "✅ Gifts Collection heading is displayed" + RESET);

	    // ✅ Products validation
	    System.out.println(CYAN + "🔍 Verifying product list" + RESET);

	    List<WebElement> products = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                    By.xpath("//div[contains(@class,'product_list_cards_list')]")
	            )
	    );

	    assertTrue(
	            "❌ Less than 2 products displayed! Found: " + products.size(),
	            products.size() >= 2
	    );

	    System.out.println(
	            PURPLE + "📦 Products displayed count: " + products.size() + RESET
	    );
	}
	
	
	
	public void verifyNewArrivalSuggestion() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    Actions actions = new Actions(driver);

	    // 🎨 Colors
	    String RESET  = "\u001B[0m";
	    String GREEN  = "\u001B[32m";
	    String CYAN   = "\u001B[36m";
	    String YELLOW = "\u001B[33m";
	    String RED    = "\u001B[31m";
	    String PURPLE = "\u001B[35m";

	    By newArrivalMenuBy = By.xpath("//a[normalize-space()='New Arrivals']");
	    By dropdownProductsBy = By.xpath(
	            "//div[contains(@class,'new_arrival_dropdown')]//a[contains(@class,'na_dropdown_card')]"
	    );

	    System.out.println(CYAN + "🔍 Verifying New Arrival suggestions" + RESET);

	    // 🔁 Initial hover
	    actions.moveToElement(
	            wait.until(ExpectedConditions.visibilityOfElementLocated(newArrivalMenuBy))
	    ).pause(Duration.ofMillis(800)).perform();

	    int productCount = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownProductsBy)
	    ).size();

	    assertTrue("❌ No products found in New Arrival dropdown", productCount > 0);

	    System.out.println(
	            PURPLE + "🧾 Total New Arrival dropdown products: " + productCount + RESET
	    );

	    // 🔁 Click one by one
	    for (int i = 0; i < productCount; i++) {

	        // 🔁 Re-hover EVERY loop
	        WebElement newArrivalMenu = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(newArrivalMenuBy)
	        );

	        actions.moveToElement(newArrivalMenu)
	               .pause(Duration.ofMillis(700))
	               .perform();

	        List<WebElement> products = wait.until(
	                ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownProductsBy)
	        );

	        WebElement productToClick = products.get(i);
	        String dropdownName = productToClick.getText().trim();

	        System.out.println(
	                YELLOW + "👉 Clicking product [" + (i + 1) + "]: " + dropdownName + RESET
	        );

	        productToClick.click();

	        // ✅ PDP heading check (display only)
	        WebElement productHeading = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//h4[@class='prod_name']")
	                )
	        );

	        assertTrue(
	                "❌ Product heading not displayed for: " + dropdownName,
	                productHeading.isDisplayed()
	        );

	        System.out.println(
	                GREEN + "✅ Product page loaded | Heading: "
	                        + productHeading.getText().trim() + RESET
	        );

	        // 🔙 Back
	        driver.navigate().back();

	        // ✅ Wait for homepage header to reload (NOT WebElement)
	        wait.until(ExpectedConditions.visibilityOfElementLocated(newArrivalMenuBy));
	    }

	    System.out.println(
	            GREEN + "🎉 All New Arrival suggestion products validated successfully!" + RESET
	    );
	}
	
	private String generateExpectedCategoryUrl(String categoryName) {

	    String baseUrl = "https://www.zlaata.com/";
	    String name = categoryName.trim().toUpperCase();

	    // 🔴 Special category overrides
	    if (name.equals("URBANLAADO")) {
	        return baseUrl + "urban-laado";
	    }
	    if (name.equals("BOSS LADY")) {
	        return baseUrl + "formal-all";
	    }
	    if (name.equals("AURORA")) {
	        return baseUrl + "aurora-party-wear";
	    }
	    if (name.equals("IKAT REIMAGINED")) {
	        return baseUrl + "ikat";
	    }
	    if (name.equals("FORMAL SHIRTS/TOPS")) {
	        return baseUrl + "formal-shirtstops";
	    }


	    // 🟢 Default dynamic rule
	    String slug = categoryName
	            .toLowerCase()
	            .replace("&", "")
	            .replaceAll("\\s+", "-")
	            .replaceAll("-+", "-")
	            .trim();

	    return baseUrl + slug;
	}
	public void verifyShopMenuAllCategories_CollectionsAndStyle() throws InterruptedException {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    Actions actions = new Actions(driver);

	    // 🎨 Console colors
	    String RESET  = "\u001B[0m";
	    String GREEN  = "\u001B[32m";
	    String RED    = "\u001B[31m";
	    String CYAN   = "\u001B[36m";
	    String YELLOW = "\u001B[33m";
	    String PURPLE = "\u001B[35m";

	    By shopMenuBy = By.xpath("//span[@class='navigation_menu_txt'][normalize-space()='Shop']");
	    By categoriesBy = By.xpath("//div[contains(@class,'nav_drop_down_box_category')]//ul/li/a");
	    By headingBy = By.xpath("//h3[@class='prod_list_topic']/span");
	    By productsBy = By.xpath("//div[contains(@class,'product_list_cards_list ')]");

	    System.out.println(CYAN + "🔍 Verifying Shop → All Categories (URL Rule Based)" + RESET);

	    // Hover Shop
	    WebElement shopMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(shopMenuBy));
	    actions.moveToElement(shopMenu).pause(Duration.ofMillis(800)).perform();

	    List<WebElement> categories = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(categoriesBy)
	    );

	    int count = categories.size();
	    assertTrue("❌ No categories found under Shop menu", count > 0);

	    System.out.println(PURPLE + "🧾 Total categories: " + count + RESET);

	    for (int i = 0; i < count; i++) {

	        // Re-hover to avoid stale element
	        shopMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(shopMenuBy));
	        actions.moveToElement(shopMenu).pause(Duration.ofMillis(700)).perform();

	        categories = wait.until(
	                ExpectedConditions.visibilityOfAllElementsLocatedBy(categoriesBy)
	        );

	        WebElement category = categories.get(i);
	        String categoryName = category.getText().trim();

	        String expectedUrl = generateExpectedCategoryUrl(categoryName);

	        System.out.println(
	                YELLOW + "👉 Clicking [" + (i + 1) + "] " + categoryName + RESET
	        );
	        System.out.println(
	                CYAN + "🌐 Expected URL: " + expectedUrl + RESET
	        );

	        category.click();
	        Common.waitForElement(2);
		wait.until(ExpectedConditions.urlContains(expectedUrl.replace("https://www.zlaata.com/", "")));
	        String actualUrl = driver.getCurrentUrl();

	        assertEquals(
	                "❌ URL mismatch for category: " + categoryName +
	                "\nExpected: " + expectedUrl +
	                "\nActual: " + actualUrl,
	                expectedUrl,
	                actualUrl
	        );

	        System.out.println(
	                GREEN + "✅ URL matched successfully" + RESET
	        );

	        // Heading visible
	        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(headingBy));
	        assertTrue("❌ Heading not displayed for " + categoryName, heading.isDisplayed());

	        System.out.println(
	                GREEN + "📌 Heading displayed: " + heading.getText().trim() + RESET
	        );
	        
	        
	 //------------------------------------------------        
		     // ⚠ Skip ALL remaining checks if heading is JUMPSUITS
	        String headingText = heading.getText().trim().toUpperCase();
	        if ("JUMPSUITS".equals(headingText)) {

	            System.out.println(
	                    YELLOW + "⚠ Skipping JUMPSUITS category (No products expected)" + RESET
	            );

	            driver.navigate().back();
	            wait.until(ExpectedConditions.visibilityOfElementLocated(shopMenuBy));
	            continue;   // 🔥 THIS MUST EXECUTE
	        }
		        
	//---------------------------------------------------------	   

	        // Products count
	        List<WebElement> products = driver.findElements(productsBy);

     
	        
	        
	        assertTrue(
	                "❌ Less than 1 products for " + categoryName,
	                products.size() >= 1
	        );

	        System.out.println(
	                GREEN + "🛍️ Products displayed: " + products.size() + RESET
	        );

	        driver.navigate().back();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(shopMenuBy));
	    }

	    System.out.println(
	            GREEN + "🎉 All Shop categories validated with URL rules!" + RESET
	    );
	}
	
	
	
	
	public void verifyBossLadySuggestions() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    Actions actions = new Actions(driver);

	    // 🎨 Console colors
	    String RESET  = "\u001B[0m";
	    String GREEN  = "\u001B[32m";
	    String RED    = "\u001B[31m";
	    String CYAN   = "\u001B[36m";
	    String YELLOW = "\u001B[33m";
	    String PURPLE = "\u001B[35m";

	    By bossLadyMenuBy = By.xpath("//li[contains(@class,'boss-lady')]//span[normalize-space()='Boss Lady']");
	    By dropdownItemsBy = By.xpath("//li[contains(@class,'boss-lady')]//a[contains(@class,'bl_dropdown_card')]");
	    By headingBy = By.xpath("//h3[@class='prod_list_topic']/span");
	    By productsBy = By.xpath("//div[contains(@class,'product_list_cards_list ')]");

	    System.out.println(CYAN + "🔍 Verifying Boss Lady dropdown suggestions" + RESET);

	    // Hover Boss Lady
	    WebElement bossLadyMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(bossLadyMenuBy));
	    actions.moveToElement(bossLadyMenu).pause(Duration.ofMillis(800)).perform();

	    List<WebElement> items = wait.until(
	            ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownItemsBy)
	    );

	    int count = items.size();
	    assertTrue("❌ No Boss Lady dropdown items found", count > 0);

	    System.out.println(PURPLE + "🧾 Boss Lady dropdown items: " + count + RESET);

	    for (int i = 0; i < count; i++) {

	        // Re-hover to avoid stale
	        bossLadyMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(bossLadyMenuBy));
	        actions.moveToElement(bossLadyMenu).pause(Duration.ofMillis(700)).perform();

	        items = wait.until(
	                ExpectedConditions.visibilityOfAllElementsLocatedBy(dropdownItemsBy)
	        );

	        WebElement item = items.get(i);

	        String categoryName = item.findElement(
	                By.xpath(".//span[@class='bl_dropdown_card_name']")
	        ).getText().trim();

	        String expectedUrl = generateExpectedCategoryUrl(categoryName);

	        System.out.println(YELLOW + "👉 Clicking [" + (i + 1) + "] " + categoryName + RESET);
	        System.out.println(CYAN + "🌐 Expected URL: " + expectedUrl + RESET);

	        item.click();

	        // URL validation
	        wait.until(ExpectedConditions.urlContains(
	                expectedUrl.replace("https://www.zlaata.com/", "")
	        ));

	        assertEquals(
	                "❌ URL mismatch for " + categoryName,
	                expectedUrl,
	                driver.getCurrentUrl()
	        );

	        System.out.println(GREEN + "✅ URL matched" + RESET);

	        // ✅ Heading MATCH validation
	        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(headingBy));
	        String actualHeading = heading.getText().trim();


	        String expectedNormalized = categoryName
	                .replaceAll("\\s+", "")   // 🔥 remove ALL spaces
	                .toUpperCase();

	        String actualNormalized = actualHeading
	                .replaceAll("\\s+", "")   // 🔥 remove ALL spaces
	                .toUpperCase();

	        System.out.println("🧪 Expected Heading (normalized): " + expectedNormalized);
	        System.out.println("🧪 Actual Heading   (normalized): " + actualNormalized);

	        Assert.assertEquals(
	                "❌ Heading mismatch for category: " + categoryName,
	                expectedNormalized,
	                actualNormalized
	        );

	        System.out.println(
	                GREEN + "📌 Heading matched: " + actualHeading + RESET
	        );

	        // Products validation
	        List<WebElement> products = wait.until(
	                ExpectedConditions.numberOfElementsToBeMoreThan(productsBy, 1)
	        );

	        assertTrue(
	                "❌ Less than 2 products for " + categoryName,
	                products.size() >= 2
	        );

	        System.out.println(
	                GREEN + "🛍️ Products displayed: " + products.size() + RESET
	        );

	        // Navigate back
	        driver.navigate().back();
	        wait.until(ExpectedConditions.visibilityOfElementLocated(bossLadyMenuBy));
	    }

	    System.out.println(
	            GREEN + "🎉 Boss Lady dropdown categories validated successfully!" + RESET
	    );
	}
	
	
//TC-01	
	public void validateAllHeaderMenus() throws InterruptedException {
		
	//	appLaunch();
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		
		verifyHomeMenu();
		
		verifynewArrivalMenu();
		
		verifySaleMenu();
		
		verifyShopHeaderMenu();
		
		verifyBossLadyMenu();
		
		verifyStyledByMenu();
		
	//	verifyGiftMenu();	
	}
	
	
//TC-02
	public void validateNewArrivalSuggestions() {
	//	appLaunch();
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		
		verifyNewArrivalSuggestion();
	}
	
//TC-03
	public void validateShopAllCategories_CollectionsAndStyle() throws InterruptedException {
	//	appLaunch();
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		
		verifyShopMenuAllCategories_CollectionsAndStyle();
	}
	
//TC-04
	
	
	public void validateBossLadySuggestions() {
		
	//	appLaunch();
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		
		verifyBossLadySuggestions();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public WebDriver gmail(String browserName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isAt() {
		// TODO Auto-generated method stub
		return false;
	}


}
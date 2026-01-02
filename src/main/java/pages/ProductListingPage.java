package pages;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.ProductListObjRepo;
import utils.Common;

public final class ProductListingPage extends ProductListObjRepo {



	public ProductListingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
//TC-01
	public void homeCrumbLink() {
		   String GREEN  = "\u001B[32m";
		    String RESET  = "\u001B[0m";
		    String CYAN   = "\u001B[36m";
		    String YELLOW = "\u001B[33m";
		    String PURPLE = "\u001B[35m";
		    String line = "──────────────────────────────────────────────────────────────";
		    System.out.println(CYAN + line + RESET);
	    // Launch home
	    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // Hover and open category
	    Actions actions = new Actions(driver);
	    actions.moveToElement(shopMenu).perform();
	    actions.moveToElement(category).click().perform();

	    System.out.println(CYAN + "🔍 Navigated to category page" + RESET);

	    // Click Home breadcrumb
	    wait.until(ExpectedConditions.elementToBeClickable(homeCrumbLink)).click();
	    System.out.println(YELLOW + "👉 Clicked Home breadcrumb" + RESET);

	    // ✅ Verify URL
	    String expectedUrl = "https://www.zlaata.com/";
	    wait.until(ExpectedConditions.urlToBe(expectedUrl));

	    String actualUrl = driver.getCurrentUrl();
	    Assert.assertEquals(
	            "❌ URL mismatch after clicking Home breadcrumb",
	            expectedUrl,
	            actualUrl
	    );

	    System.out.println(GREEN + "✅ URL verified: " + actualUrl + RESET);

	    // ✅ Verify Home Banner
	    WebElement homeBanner = wait.until(
	            ExpectedConditions.visibilityOf(banners)
	    );

	    Assert.assertTrue(
	            "❌ Home banner is NOT visible",
	            homeBanner.isDisplayed()
	    );

	    System.out.println(GREEN + "✅ Home banner is displayed" + RESET);
	}
	
	//TC-02
	
	public void pLpHeading() throws InterruptedException {

	    String GREEN = "\u001B[32m";
	    String RED   = "\u001B[31m";
	    String CYAN  = "\u001B[36m";
	    String RESET = "\u001B[0m";
	    String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
    // Launch home
    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    System.out.println(CYAN + "🔍 Navigating to Product Listing Page..." + RESET);

	    Actions actions = new Actions(driver);
	    actions.moveToElement(shopMenu).perform();
	    actions.moveToElement(category).click().perform();

	    WebElement headingElement = wait.until(
	            ExpectedConditions.visibilityOf(shopPageHead)
	    );

	    String headingText = headingElement.getText().trim();
Thread.sleep(2000);
	    // ✅ Validation
	    if (headingElement.isDisplayed() && !headingText.isEmpty()) {
	        System.out.println(GREEN + "✅ PLP Heading Displayed Successfully" + RESET);
	        System.out.println(CYAN + "📌 Heading Text: " + headingText + RESET);
	    } else {
	        System.out.println(RED + "❌ PLP Heading NOT displayed or empty" + RESET);
	        Assert.fail("PLP Heading validation failed");
	    }
	}
//TC-03
	public void pagination() throws InterruptedException {

	    String GREEN = "\u001B[32m";
	    String RED   = "\u001B[31m";
	    String CYAN  = "\u001B[36m";
	    String RESET = "\u001B[0m";
	    String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
    // Launch home
    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    System.out.println(CYAN + "🔍 Navigating to Product Listing Page..." + RESET);

	    Actions actions = new Actions(driver);
	    actions.moveToElement(shopMenu).perform();
	    actions.moveToElement(category).click().perform();

	    try {
	        // Wait for pagination
	        WebElement paginationElement = wait.until(
	                ExpectedConditions.visibilityOf(pagination)
	        );

	        // Scroll to pagination
	        ((JavascriptExecutor) driver)
	                .executeScript("arguments[0].scrollIntoView(true);", paginationElement);

	        if (paginationElement.isDisplayed()) {
	            System.out.println(GREEN + "✅ Pagination is visible on PLP page" + RESET);
	            Assert.assertTrue(true);
	        }

	    } catch (TimeoutException e) {
	        System.out.println(RED + "❌ Pagination not found on PLP page" + RESET);
	        Assert.fail("Pagination not displayed on Product Listing Page");

	    } catch (Exception e) {
	        System.out.println(RED + "❌ Unexpected error while verifying pagination: " 
	                           + e.getMessage() + RESET);
	        throw e;
	    }
	    Thread.sleep(2000);
	}
	
//TC-04	
	public void pagiNationArrows() {

	    String GREEN = "\u001B[32m";
	    String RED   = "\u001B[31m";
	    String CYAN  = "\u001B[36m";
	    String RESET = "\u001B[0m";
	    String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
	 // Launch home
	    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    System.out.println(CYAN + "🔍 Navigating to Product Listing Page..." + RESET);

		    Actions actions = new Actions(driver);
		    actions.moveToElement(shopMenu).perform();
		    actions.moveToElement(category).click().perform();
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    try {
	        // Wait for pagination
	        WebElement paginationBlock = wait.until(
	                ExpectedConditions.visibilityOf(pagination)
	        );

	        System.out.println(GREEN + "✅ Pagination visible" + RESET);

	        // Scroll pagination to CENTER of screen (important)
	        js.executeScript(
	                "arguments[0].scrollIntoView({block:'center'});",
	                paginationBlock
	        );

	        // Wait a moment for sticky header to settle
	        Thread.sleep(2000);

	        WebElement nextBtn = wait.until(
	                ExpectedConditions.elementToBeClickable(paginationNext)
	        );

	        System.out.println(CYAN + "➡ Clicking Next page" + RESET);

	        // ✅ JS click avoids interception
	        js.executeScript("arguments[0].click();", nextBtn);

	        // Validate navigation
	        wait.until(ExpectedConditions.urlContains("page=2"));
	        String currentUrl = driver.getCurrentUrl();

	        Assert.assertTrue(
	                "❌ Pagination failed. URL: " + currentUrl,
	                currentUrl.contains("page=2")
	        );

	        System.out.println(GREEN + "✅ Pagination success → Page 2 loaded" + RESET);

	    } catch (Exception e) {
	        System.out.println(RED + "❌ Pagination failed: " + e.getMessage() + RESET);
	        Assert.fail("Pagination arrow not clickable");
	    }
	}
//TC-05
	
	public void paginationNumber() {

	    String GREEN = "\u001B[32m";
	    String RED   = "\u001B[31m";
	    String CYAN  = "\u001B[36m";
	    String RESET = "\u001B[0m";

	    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    Actions actions = new Actions(driver);

	    System.out.println(CYAN + "🔍 Navigating to PLP..." + RESET);

	    actions.moveToElement(shopMenu).perform();
	    actions.moveToElement(category).click().perform();

	    try {
	        // Get all pagination numbers
	        List<WebElement> pages = wait.until(
	                ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                        By.xpath("//div[@class='pagi_count_wrap']//a[normalize-space()]")
	                )
	        );

	        Assert.assertTrue("❌ Pagination not found", pages.size() > 0);

	        Collections.shuffle(pages);
	        WebElement page = pages.get(0);

	        String pageNo = page.getText().trim();
	        System.out.println(CYAN + "➡ Clicking page: " + pageNo + RESET);

	        // Scroll safely
	        js.executeScript("arguments[0].scrollIntoView({block:'center'});", page);
	        Thread.sleep(800);

	        // Click via JS (avoids header issue)
	        js.executeScript("arguments[0].click();", page);

	        // Validate URL
	        wait.until(ExpectedConditions.urlContains("/all"));
	        String currentUrl = driver.getCurrentUrl();

	        if (pageNo.equals("1")) {

	            Assert.assertTrue(
	                    "❌ Page 1 URL incorrect: " + currentUrl,
	                    currentUrl.equals("https://www.zlaata.com/all")
	                    || currentUrl.equals("https://www.zlaata.com/all#")
	            );

	            System.out.println(GREEN + "✅ Page 1 loaded correctly → " + currentUrl + RESET);

	        } else {
	            Assert.assertTrue(
	                    "❌ Page URL mismatch: " + currentUrl,
	                    currentUrl.contains("page=" + pageNo)
	            );
	            System.out.println(GREEN + "✅ Page " + pageNo + " loaded correctly" + RESET);
	        }

	    } catch (Exception e) {
	        System.out.println(RED + "❌ Pagination validation failed: " + e.getMessage() + RESET);
	        Assert.fail("Pagination failed");
	    }
	}

//TC-06
	public void showFilter() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		Common.waitForElement(5);
		actions.moveToElement(showFilter).click().build().perform();
		try {

			if (showFilterMenu.isDisplayed()) {
				Assert.assertTrue((verifyDisplayed(showFilterMenu)));
			}

		} catch (Exception e) {
			System.out.println("Caught an exception: " + e.getMessage());
			NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException exception occurred");
			e1.initCause(e);
			throw e1;
		}
	}

	public void sortByFilter() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		Common.waitForElement(5);
		actions.moveToElement(sortBy).click().build().perform();

	}
//	public void basicFilterFunction() {
//		Common.waitForElement(5);
//		Actions actions = new Actions(driver);
//		actions.moveToElement(shopMenu);
//		actions.moveToElement(category).click().build().perform();
//		Common.waitForElement(2);
//		actions.moveToElement(showFilter).click().build().perform();
//		List<WebElement> clickShowFilterMenu = driver.findElements(By.xpath("//span[@class='prod_filter_heading']"));
//		Collections.shuffle(clickShowFilterMenu);
//
//		if (!clickShowFilterMenu.isEmpty()) {
//			WebElement randomFilter = clickShowFilterMenu.get(0);
//			actions.moveToElement(randomFilter).click().build().perform();
//		}
//		List<WebElement> clickShowFilterSubMenu = driver.findElements(By.xpath("//div[@class='prod_filter_value']"));
//		Collections.shuffle(clickShowFilterSubMenu);
//
//		if (priceRangeFilter.isDisplayed()) {
//			click(maxPriceFilter);
//			maxPriceFilter.clear();
//			Common.waitForElement(2);
//			type(maxPriceFilter,Common.getValueFromTestDataMap("Mobile Number"));
//			Common.waitForElement(2);
//			click(filterApply);
//			click(showFilter);
//			click(filterClearAll);
//			Common.waitForElement(2);
//			click(closeShowFilter);
//		}
//
//
//		click(filterApply);
//		Common.waitForElement(2);
//		click(showFilter);
//		click(filterClearAll);
//	}

	public void basicFilterFunction() {
	    Common.waitForElement(5);
	    Actions actions = new Actions(driver);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    // Hover over shopMenu and click category
	    actions.moveToElement(shopMenu).perform();
	    wait.until(ExpectedConditions.elementToBeClickable(category));
	    actions.moveToElement(category).click().build().perform();
	    Common.waitForElement(2);
	    // Click showFilter
	    wait.until(ExpectedConditions.elementToBeClickable(showFilter));
	    actions.moveToElement(showFilter).click().build().perform();
	    // Click a random main filter
	    List<WebElement> mainFilters = driver.findElements(By.xpath("//span[@class='prod_filter_heading']"));
	    List<WebElement> visibleMainFilters = mainFilters.stream()
	            .filter(WebElement::isDisplayed)
	            .filter(WebElement::isEnabled)
	            .collect(Collectors.toList());
	    Collections.shuffle(visibleMainFilters);
	    String selectedMainFilterName = "";
	    if (!visibleMainFilters.isEmpty()) {
	        WebElement randomMainFilter = visibleMainFilters.get(0);
	        selectedMainFilterName = randomMainFilter.getText().trim();
	        js.executeScript("arguments[0].scrollIntoView(true);", randomMainFilter);
	        wait.until(ExpectedConditions.elementToBeClickable(randomMainFilter));
	        try {
	            actions.moveToElement(randomMainFilter).click().build().perform();
	        } catch (ElementNotInteractableException e) {
	            js.executeScript("arguments[0].click();", randomMainFilter);
	        }
	    }
	    // Wait for sub-filters to appear and get only the visible sub-filters
	    List<WebElement> filterOptions = driver.findElements(By.xpath("//p[@class='prod_filter_value_name']"));
	    List<WebElement> visibleOptions = filterOptions.stream()
	            .filter(WebElement::isDisplayed)
	            .filter(WebElement::isEnabled)
	            .collect(Collectors.toList());
	    Collections.shuffle(visibleOptions);
	    int expectedProductCount = -1;
	    String selectedSubFilterName = "";
	    if (!visibleOptions.isEmpty()) {
	        WebElement selectedFilter = visibleOptions.get(0);
	        String filterText = selectedFilter.getText().trim();  // Example: Green (25)
	        selectedSubFilterName = filterText.replaceAll("\\s*\\(\\d+\\)$", ""); // Get "Green"
	        System.out.println("Sub filter selected: " + filterText);
	        try {
	            expectedProductCount = Integer.parseInt(filterText.replaceAll(".*\\((\\d+)\\)", "$1"));
	        } catch (Exception e) {
	            System.out.println(":x: Could not extract count from: " + filterText);
	        }
	        js.executeScript("arguments[0].scrollIntoView(true);", selectedFilter);
	        wait.until(ExpectedConditions.elementToBeClickable(selectedFilter));
	        try {
	            selectedFilter.click();
	        } catch (ElementNotInteractableException e) {
	            js.executeScript("arguments[0].click();", selectedFilter);
	        }
	    }
	    // Apply filter
	    click(filterApply);
	    Common.waitForElement(3);
	    // Count products across all pages
	    int totalProductsFound = 0;
	    boolean hasNextPage = true;
	    while (hasNextPage) {
	        List<WebElement> productItems = driver.findElements(By.xpath("//div[@class='product_list_card_img']"));
	        totalProductsFound += productItems.size();
	        List<WebElement> nextButtons = driver.findElements(By.xpath("//a[contains(@class, 'next')]"));
	        if (!nextButtons.isEmpty() && nextButtons.get(0).isDisplayed() && nextButtons.get(0).isEnabled()) {
	            WebElement nextBtn = nextButtons.get(0);
	            js.executeScript("arguments[0].scrollIntoView(true);", nextBtn);
	            try {
	                nextBtn.click();
	            } catch (Exception e) {
	                js.executeScript("arguments[0].click();", nextBtn);
	            }
	            Common.waitForElement(3);
	        } else {
	            hasNextPage = false;
	        }
	    }
	    // Final verification logs
	    System.out.println("Main filter selected: " + selectedMainFilterName);
	    System.out.println("Sub filter selected: " + selectedSubFilterName + " with count of " + expectedProductCount);
	    System.out.println("Product listing page matched count: " + totalProductsFound);
	    if (expectedProductCount != -1 && expectedProductCount == totalProductsFound) {
	        System.out.println(":white_tick: Product count matches across pages.");
	    } else {
	        System.out.println(":x: Product count mismatch.");
	    }
	    // Cleanup filters
	    click(showFilter);
	    click(filterClearAll);
	    Common.waitForElement(2);
	    click(closeShowFilter);
	}
	public void allsortBy() {
		Common.waitForElement(5);
		Actions actions = new Actions(driver);
		actions.moveToElement(shopMenu);
		actions.moveToElement(category).click().build().perform();
		Common.waitForElement(2);
		click(sortBy);
		actions.moveToElement(sortByWhatsNew).click().build().perform();;


		String sortOptionXpath ="//li[contains(@class,'filter_sort_list_item ') or contains(@class,'filter_sort_')]";

		 List<WebElement> options = driver.findElements(By.xpath(sortOptionXpath));
		    int totalOptions = options.size();

		    for (int i = 0; i < totalOptions; i++) {
		        // Re-click the Sort By dropdown before each selection
		        sortBy.click();
		        Common.waitForElement(1);

		        // Re-fetch the sort options to avoid stale elements
		        List<WebElement> currentOptions = driver.findElements(By.xpath(sortOptionXpath));

		        if (i < currentOptions.size()) {
		            WebElement option = currentOptions.get(i);
		            String optionText = option.getText().trim();
		            System.out.println("🟢 Clicking Sort Option [" + (i + 1) + "]: " + optionText);

		            option.click();
			        Common.waitForElement(1);
		        } else {
		            System.out.println("❌ Index " + i + " is out of range!");
		        }
		    }
		}
//		try {

			


//			List<Double> allDiscounts = new ArrayList<>();
//
//			// Loop through pagination if exists
//			while (true) {
//				List<WebElement> discountElements = driver.findElements(By.xpath("//span[@class='product_list_cards_discount_percent']"));
//
//				for (WebElement element : discountElements) {
//					String discountText = element.getText(); // Example: "25% Off"
//					double discount = extractDiscount(discountText);
//					allDiscounts.add(discount);
//				}
//
//				// Try to click next if exists, else break
//				List<WebElement> nextButtons = driver.findElements(By.xpath("//div[@class='pagi_next_btn']"));
//				if (nextButtons.size() > 0 && nextButtons.get(0).isDisplayed()) {
//					nextButtons.get(0).click();
//					Common.waitForElement(5);// wait for next page load
//				} else {
//					break;
//				}
//			}
//
//			// Check if the list is sorted in ascending order
//			List<Double> sortedDiscounts = new ArrayList<>(allDiscounts);
//			Collections.sort(sortedDiscounts);
//
//			if (allDiscounts.equals(sortedDiscounts)) {
//				System.out.println("✅ Discount values are correctly sorted from Low to High.");
//			} else {
//				System.out.println("❌ Discount sorting is incorrect!");
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
		

	



	private double extractPrice(String priceText) {
		// TODO Auto-generated method stub
		return 0;

	}

	// Helper function to extract numeric discount
	public static double extractDiscount(String text) {
		return Double.parseDouble(text.replaceAll("[^0-9]", ""));
	}
















	public void wishListIcon() throws InterruptedException {

	    
	    String GREEN = "\u001B[32m";
	    String RED   = "\u001B[31m";
	    String CYAN  = "\u001B[36m";
	    String RESET = "\u001B[0m";
	    String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
    // Launch home
    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
	   
    // Hover and open category
    Actions actions = new Actions(driver);
    actions.moveToElement(shopMenu).perform();
    actions.moveToElement(category).click().perform();

    System.out.println(CYAN + "🔍 Navigated to category page" + RESET);
    Common.waitForElement(3);
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    // ✅ Pick first product
	    WebElement productCard = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//div[@class='product_list_cards_list ']")
	            )
	    );

	    // ✅ Capture product details
	    String productName = productCard.findElement(
	            By.xpath(".//h2[@class='product_list_cards_heading']")
	    ).getText();

	    String productPrice = productCard.findElement(
	            By.xpath(".//span[@class='prod_current_price']")
	    ).getText();

	    String productDiscount = productCard.findElement(
	            By.xpath(".//span[@class='prod_discount_percentage']")
	    ).getText();

	    System.out.println(CYAN + "📌 Selected Product:" + RESET);
	    System.out.println("   Name: " + productName);
	    System.out.println("   Price: " + productPrice);
	    System.out.println("   Discount: " + productDiscount);

	    // ✅ Click wishlist icon
	    WebElement wishBtn = productCard.findElement(
	            By.xpath(".//div[contains(@class,'product_list_wishlist_icon')]")
	    );

	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", wishBtn);
	    System.out.println(GREEN + "❤️ Product added to wishlist" + RESET);

	    // ✅ Open Wishlist page
	    WebElement wishlistIcon = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("//a[contains(@class,'wishlist-icon')]")
	            )
	    );
	    wishlistIcon.click();
Thread.sleep(2000);
	    // ✅ Verify product in wishlist
	    WebElement wishProduct = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//h2[text()='" + productName + "']")
	            )
	    );

	    String wishPrice = driver.findElement(
	            By.xpath("//h2[text()='" + productName + "']/following::span[@class='prod_current_price'][1]")
	    ).getText();

	    String wishDiscount = driver.findElement(
	            By.xpath("//h2[text()='" + productName + "']/following::span[@class='prod_discount_percentage'][1]")
	    ).getText();

	    // ✅ Assertions
	    Assert.assertEquals(productPrice, wishPrice);
	    Assert.assertEquals(productDiscount, wishDiscount);

	    System.out.println(GREEN + "✅ Wishlist validation successful!" + RESET);
	}


	public void addToCart() throws InterruptedException {

	    
	    String GREEN = "\u001B[32m";
	    String RED   = "\u001B[31m";
	    String CYAN  = "\u001B[36m";
	    String RESET = "\u001B[0m";
	    String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
    // Launch home
    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	 // Hover and open category
	    Actions actions = new Actions(driver);
	    actions.moveToElement(shopMenu).perform();
	    actions.moveToElement(category).click().perform();

	    System.out.println(CYAN + "🔍 Navigated to category page" + RESET);

	    // 🔹 Wait for product card
	    WebElement productCard = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("(//div[contains(@class,'product_list_cards_list')])[1]")
	            )
	    );

	    // 🔹 Capture product details
	    String productName = productCard.findElement(
	            By.xpath(".//h2[@class='product_list_cards_heading']")
	    ).getText();

	    String productPrice = productCard.findElement(
	            By.xpath(".//span[@class='prod_current_price']")
	    ).getText();

	    String productDiscount = productCard.findElement(
	            By.xpath(".//span[@class='prod_discount_percentage']")
	    ).getText();

	    System.out.println(CYAN + "🛍 Selected Product" + RESET);
	    System.out.println("   Name     : " + productName);
	    System.out.println("   Price    : " + productPrice);
	    System.out.println("   Discount : " + productDiscount);

	    // 🔹 Click Add to Bag (PLP)
	    WebElement addToBagBtn = productCard.findElement(
	            By.xpath(".//div[contains(@class,'product_list_add_to_cart')]")
	    );

	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].scrollIntoView({block:'center'});", addToBagBtn);
	    ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].click();", addToBagBtn);
	    Common.waitForElement(2);
	    System.out.println(GREEN + "✅ PLP Add to Bag clicked" + RESET);

	    // 🔹 Capture popup product name
	    WebElement popupName = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//h4[@class='prod_name']")
	            )
	    );

	    String popupProductName = popupName.getText().trim();
	    System.out.println(GREEN + "🧾 Popup Product: " + popupProductName + RESET);
	    Common.waitForElement(2);
	    // 🔹 Click Add to Bag in popup
	    WebElement popupAddBtn = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("//button[contains(@class,'add_bag_prod_buy_now_btn')]")
	            )
	    );
	    popupAddBtn.click();

	    System.out.println(GREEN + "✅ Product added to cart" + RESET);
	    Common.waitForElement(2);
	    // 🔹 Open cart
	    WebElement cartIcon = wait.until(
	            ExpectedConditions.elementToBeClickable(
	                    By.xpath("//a[contains(@class,'Cls_cart_btn')]")
	            )
	    );
	    cartIcon.click();

	    // 🔹 Verify product in cart
	    WebElement cartProduct = wait.until(
	            ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//a[contains(@class,'cp_name') and contains(text(),'" + popupProductName + "')]")
	            )
	    );
	    Common.waitForElement(2);
	    String cartPrice = cartProduct.findElement(
	            By.xpath("./ancestor::div[contains(@class,'cart_prod_card')]//div[@class='cp_current_price']")
	    ).getText();

	    String cartDiscount = cartProduct.findElement(
	            By.xpath("./ancestor::div[contains(@class,'cart_prod_card')]//div[@class='cp_discount_percentage']")
	    ).getText();

	    // ✅ Assertions
	    Assert.assertEquals(cartPrice, productPrice);
	    Assert.assertEquals(cartDiscount, productDiscount);

	    System.out.println(GREEN + "✅ Cart validation successful!" + RESET);
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

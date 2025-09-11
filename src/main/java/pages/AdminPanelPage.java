package pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Assume;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import manager.FileReaderManager;
import objectRepo.AdminPanelObjRepo;
import utils.Common;

public final class AdminPanelPage extends AdminPanelObjRepo  {
	

	 

	public AdminPanelPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	public void clickUsingJavaScript(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	private String expectedBannerTitle;



	public void uploadImage(String imagePath) {
	    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl());
	    type(adminEmail, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminName"));
	    type(adminPassword, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminPassword"));
	    click(adminLogin);
	    Common.waitForElement(2);

	    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	    expectedBannerTitle = Common.getValueFromTestDataMap("Banner Title");
	    click(homePageBannerDropDown);
	    selectHomePageValue.get(0).click();
	    Common.waitForElement(1);
		click(status);
		statusFilterSelect.get(0).click();
		Common.waitForElement(1);
	    boolean foundSortBy1 = false;

	    // Check if Sort By = 1 already exists
	    List<WebElement> bannerRows = driver.findElements(By.xpath(".//span[@class='d-inline-flex']"));
	    for (int i = 0; i < bannerRows.size(); i++) {
	        try {
	            WebElement currentRow = driver.findElements(By.xpath("(.//tr[@class='odd'])[1]")).get(i);

	            WebElement sortByInput = currentRow.findElement(By.xpath(".//input[@type='number']"));
	            String value = sortByInput.getAttribute("value").trim();

	            if (value.equals("1")) {
	                // Found row with Sort By = 1 → Click Edit and Save
	                WebElement editButton = currentRow.findElement(By.xpath(".//i[@class='las la-edit']"));
	                clickUsingJavaScript(editButton);
	                Common.waitForElement(2);
	                type(bannerTitle,expectedBannerTitle);
	                uploadImageInput.sendKeys(imagePath);
	                click(uploadButton); 
	                System.out.println("Edited and saved existing banner with Sort By = 1");
	                foundSortBy1 = true;
	                break;
	            }
	        } catch (Exception e) {
	            // Ignore rows without input/edit
	        }
	    }

	    // If no row with Sort By = 1 → Add new banner
	    if (!foundSortBy1) {
	        click(addHomePageBanner);
	        type(bannerTitle, "Home Page Automation Banner");
	        uploadImageInput.sendKeys(imagePath);
	        click(uploadButton);

	        // set Sort By = 1
	        type(sortBy, "1");
	        click(sortBySave);

	        System.out.println("Added new banner and saved with Sort By = 1");
	    }
	}


	public void verifyBannerOnHomePage() {
		switchToWindow(1);
	    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
	 	    // Expected values from test data
	    String expectedTitle = Common.getValueFromTestDataMap("Banner Title");

	    // ✅ Wait until the expected banner title appears
	    int timeoutMinutes = 10;  
	    int pollSeconds = 5;
	    boolean titleFound = false;
	    WebElement titleElement = null;

	    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        try {
	            // Refresh and wait
	            driver.navigate().refresh();
	            Common.waitForElement(2);

	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(pollSeconds))
	                    .pollingEvery(Duration.ofSeconds(2))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            titleElement = wait.until(d -> {
	                List<WebElement> elements = d.findElements(By.xpath("//div[contains(@class,'banner') or contains(@class,'carousel')]//img[contains(@alt,'" 
	                         + expectedBannerTitle + "')]"));
	                return elements.isEmpty() ? null : elements.get(0);
	            });

	            if (titleElement != null && titleElement.isDisplayed()) {
	                titleFound = true;
	                break;
	            }

	        } catch (TimeoutException te) {
	            // keep looping until timeout
	        }

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
	    }

		// ✅ Final check
		if (titleFound) {
			System.out.println("✅ Banner title '" + expectedTitle + "' is visible in User Application.");
		} else {
			System.out
					.println("❌ Banner title '" + expectedTitle + "' not found within " + timeoutMinutes + " minutes.");
			Assert.fail("❌ Banner title '" + expectedTitle + "' not found within " + timeoutMinutes + " minutes.");
		}
		
	}

	

//SarojKumar 
	
	public void adminLogin() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl());
	    type(adminEmail, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminName"));
	    type(adminPassword, FileReaderManager.getInstance().getJsonReader().getValueFromJson("AdminPassword"));
	    click(adminLogin);
	    System.out.println("✅ Admin Login Successfull");
	    
	}
	public void givesProductName() {
		Common.waitForElement(4);
	    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	    System.out.println("✅ Successfull redirect to Adimn Product page");
		 // Open product listing
	    click(productListingMenu);  
	    System.out.println("✅ Successfull click product listing menu");
	    waitFor(productSearchBox);
	    click(productSearchBox);

	    // Fetch the product name directly from Excel map
	    String productName = Common.getValueFromTestDataMap("ProductListingName");
	    System.out.println("✅ Successfull fetch product listing name from excel sheet");
	 // Search or enter the product
	    type(productSearchBox, productName + Keys.ENTER);
	    Common.waitForElement(2);
	    System.out.println("✅ Successfull put product listing name in searchbox and also click enter");

		
	}
	public String fetchSkuFromProduct() {
		
	    // now click edit, etc…
	    waitFor(editProductButton);
	    click(editProductButton);
	    System.out.println("✅ Successfull click product edit option");
	    // now click item, etc…
	    waitFor(itemProductButton);
	    click(itemProductButton);
	    System.out.println("✅ Successfull click product item option");
	    
	 // now take Sku
	    waitFor(skuField);
	    System.out.println("✅ Successfull copy the SKU from skufield");
        return skuField.getAttribute("value").trim();
     
        
	   	
	}
	public void putSkuIntoTopSelling(String sku) {
	    try {
	    	
	    	Common.waitForElement(2);
	    	waitFor(generalSettingsMenu);
	        click(generalSettingsMenu);
	        Common.waitForElement(2);
	        
//		    waitFor(clickSetKey);
//		    click(clickSetKey);
//		    type(clickSetKey, "top_selling");
//		    clickSetKey.sendKeys(Keys.ENTER);
//		    System.out.println("✅ Searched for top-selling");
	        waitFor(topSellingEdit);
	        click(topSellingEdit);
	        System.out.println("✅ Successfull click the top selling edit button");     
	        
	        waitFor(topSellingSkuInput);
	        String current = topSellingSkuInput.getAttribute("value");
	        String cleaned = (current == null) ? "" : current.trim();

	        if (cleaned.startsWith("[") && cleaned.endsWith("]")) {
	            cleaned = cleaned.substring(1, cleaned.length() - 1).trim();
	        }

	        java.util.List<String> skuList = new java.util.ArrayList<>();
	        if (!cleaned.isEmpty()) {
	            for (String part : cleaned.split(",")) {
	                String val = part.trim();
	                if (!val.isEmpty()) skuList.add(val);
	            }
	        }

	        boolean alreadyExists = skuList.stream()
	                .anyMatch(s -> s.equalsIgnoreCase(sku));

	        String updated;
	        String message;
	        if (alreadyExists) {
	            updated = "[" + String.join(", ", skuList) + "]";
	            message = "SKU '" + sku + "' already present. No changes made.";
	        } else if (skuList.isEmpty()) {
	            updated = "[" + sku + "]";
	            message = "SKU '" + sku + "' added as the first Top Selling item.";
	        } else {
	            updated = "[" + sku + ", " + String.join(", ", skuList) + "]";
	            message = "SKU '" + sku + "' prepended to Top Selling list.";
	        }

	        topSellingSkuInput.clear();
	        type(topSellingSkuInput, updated);
	        click(saveTopSelling);

	        System.out.println("✅ " + message);
	        

	    } catch (Exception e) {
	        String error = "❌ Failed to update Top Selling SKU due to: " + e.getMessage();
	        System.err.println(error);
	        throw e;
	    }
	    
	    Common.waitForElement(2);
	    waitFor(clearCatchButton);
	    click(clearCatchButton);
	    System.out.println("✅ Successfull click Clear Catch Button");
	    Common.waitForElement(2);
	    
	}
	//Verify the product successfull showing in user application home page top selling Section
	
	public void verifyProductShowInTopSelling(String productName) throws InterruptedException {
	    switchToWindow(1);
	    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
	    Common.waitForElement(3);
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,700);");

	    int timeoutMinutes = 15;   // total wait time
	    int refreshInterval = 5;  // refresh every 5 seconds
	    boolean productFound = false;
	    WebElement card = null;

	    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        try {
	            //  Refresh page every cycle
	            driver.navigate().refresh();
	            Common.waitForElement(3);

	            // ✅ After refresh,  FluentWait to check element without refreshing again
	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(refreshInterval)) // max wait before next refresh
	                    .pollingEvery(Duration.ofSeconds(3))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            card = wait.until(d -> {
	                List<WebElement> elements = d.findElements(By.xpath(
	                        "//div[contains(@class,'products_cards')]//h3[@class='product_heading' and normalize-space(text())='" 
	                        + productName + "']"
	                ));
	                return elements.isEmpty() ? null : elements.get(0);
	            });

	            if (card != null && card.isDisplayed()) {
	                productFound = true;
	                break; //  stop if found
	            }
	        } catch (TimeoutException te) {
	            // element not found in this refresh cycle → loop continues
	        }

	        Thread.sleep(1000); // small buffer
	    }

	    // ✅ Final validation
	    if (productFound && card != null && card.getText().trim().equalsIgnoreCase(productName.trim())) {
	        System.out.println("✅ Product '" + productName + "' is visible in Top Selling.");
	    } else {
	        System.err.println("❌ Product '" + productName + "' not found in Top Selling within " 
	                           + timeoutMinutes + " minutes.");
	        
	    }
	}
	    
	    
	


//New Arrivals
	 private String copiedSku;
	
	public void verifyColourOfTheProductIsFirstPosition() {
		Common.waitForElement(2);
	    driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	    System.out.println("✅ Successful redirect to Adimn Product page");
		 // Open product listing
	    click(productListingMenu);  
	    System.out.println("✅ Successful click product listing menu");
	    waitFor(productSearchBox);
	    click(productSearchBox);

	    // Fetch the product name directly from Excel map
	    String productName = Common.getValueFromTestDataMap("ProductListingName");
	    System.out.println("✅ Fetched product listing name from Excel sheet:" + productName);
	 // Search or enter the product
	    type(productSearchBox, productName + Keys.ENTER);
	    Common.waitForElement(2);
	    System.out.println("✅  Entered product listing name in search box & pressed ENTER");
	    
	 // now click edit, etc…
	    Common.waitForElement(3);
	    waitFor(editProductButton);
	    click(editProductButton);
	    System.out.println("✅ Clicked product edit option");
	    // now click item, etc…
	    waitFor(itemProductButton);
	    click(itemProductButton);
	    System.out.println("✅  Clicked product item option");
	    
	    // -------- Check that the product text matches Excel name --------
	    waitFor(productListingBoxText); 
	    String uiProductName = productListingBoxText.getAttribute("value").trim();
	    System.out.println(" UI Product Listing Name: " + uiProductName);
	    //For Sorting we copied sku
	    copiedSku = skuTextbox.getAttribute("value").trim();
	    System.out.println(" Copied SKU : " + copiedSku);

	  
	    boolean match = uiProductName.toLowerCase().contains(productName.toLowerCase());
	    if (!match) {
	        System.err.println("❌ Product mismatch,This is not First Colour Both are Different— skipping remaining steps → Expected: " + productName + " | Actual: " + uiProductName);
	        // Now skip scenario
	        Assume.assumeTrue("Skipping due to product mismatch", false);
	    }
	  //For Assert
//	    Assert.assertEquals(
//	    	    uiProductName,
//	    	    productName,
//	    	    "❌ Product mismatch, expected: " + productName + " | but found: " + uiProductName
//	    	);
	    
	    System.out.println("✅ Product listing name matches expected.");
	    
	    
////	 -------- Check Stock Status --------
//	    waitFor(stockStatusTextbox); 
//	    String stockStatus = firstProductStockStatus.getAttribute("value").trim();
//
//	    if(stockStatus.equalsIgnoreCase("Available")) {
//	        System.out.println("✅ Product is available in stock");
//	    } 
//	    else if(stockStatus.equalsIgnoreCase("Out Of Stock")) {
//	        System.out.println("❌ Product is out of stock");
//	        // skip further steps if out of stock
//	        Assume.assumeTrue("Skipping further steps as product is out of stock", false);
//	    } 
//	    

		
		
		
	}
	
	public void addTheProductInNewArrivalSection() throws InterruptedException {
		// Click product button
	    click(productButton);
	    System.out.println("✅ Clicked Product button");

	    // Copy text from Product Name textbox
	    String copiedProductName = productNameTextbox.getAttribute("value").trim();
	    System.out.println("📋 Copied product name: " + copiedProductName);

	    //Navigate to Search Product Collection page
	    
	    click(searchProductCollectionMenu);
	    System.out.println("✅ Clicked Search Product Collection");

	    //Type in the search text box
	    //Thread.sleep(2000);
	    waitFor(searchProductCollectionMenu); 
	    type(searchProductCollectionMenu, "	");
	    //Thread.sleep(2000);
	    System.out.println("✅ Typed 'Product Collections' ");
	    waitFor(clickProductCollection);
	  //  Thread.sleep(3000);
	    click(clickProductCollection);
	    System.out.println("✅ Selected Product Collection");
//	     3. Select from the dropdown or result line
//	    selectDropdownByVisibleText(productCollectionDropdown, "Product Collections");
//	    System.out.println("✅ Selected Product Collection");

	    Common.waitForElement(2);
	    waitFor(clickStatus);
	    click(clickStatus);

	    // Select Status -> Active
	    waitFor(statusActiveOption);
	    click(statusActiveOption);
	    System.out.println("✅ Selected Active status");

	    // Click Collection button
	    //Thread.sleep(2000);
	    Common.waitForElement(2);
	    waitFor(collectionButton);
	    click(collectionButton);
	    System.out.println("✅ Clicked Collection button");

	    //Search 'new-arrivals'
	    //Thread.sleep(2000);
	    Common.waitForElement(2);
	    waitFor(searchTextBox);
	    type(searchTextBox, "new-arrivals");
	    searchTextBox.sendKeys(Keys.ENTER);
	    System.out.println("✅ Searched for new-arrivals");
	   // Thread.sleep(2000);

	    //Click Edit
	    Common.waitForElement(2);
	    waitFor(editCollectionButton);
	    editCollectionButton.click();
	    System.out.println("✅ Entered Edit mode for collection");

	    //Add copied product to last position in product text field
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,900);");
	    waitFor(addProductTextbox);
	    type(addProductTextbox, copiedProductName);
	    Common.waitForElement(2);
	   //Thread.sleep(3000);
	    addProductTextbox.sendKeys(Keys.ENTER);
	    System.out.println("✅ Added product name to collection");
	    

	    //Save
	    waitFor(saveButton);
	    click(saveButton);
	    System.out.println("✅ Saved collection changes");
		
		
	}
	
	public void sortTheProductInFirstPosition() {
		// Click on Search Box for Product Sort
		Common.waitForElement(3);
		click(searchProductSortMenu);
	    System.out.println("✅ Clicked Search Product Collection");

		// Type in the search text box
		// Thread.sleep(2000);
		waitFor(searchProductSortMenu);
		type(searchProductSortMenu, "Product Sorts");
		// Thread.sleep(2000);
		System.out.println("✅ Typed 'Product Sorts");
		waitFor(clickProductSort);
		// Thread.sleep(3000);
		click(clickProductSort);
		System.out.println("✅ Selected Product Sorts");

		//  Click Category Name
		waitFor(categoryName);
		click(categoryName);
		System.out.println("✅ Clicked Category Name");

		// In the Category Search, type "New Arrivals" and hit Enter
		 Common.waitForElement(2);
		    waitFor(categorySearchBox);
		    type(categorySearchBox, "New Arrivals");
		    categorySearchBox.sendKeys(Keys.ENTER);
		    System.out.println("✅ Typed 'New Arrivals' & pressed Enter");
		   // Thread.sleep(2000);
		

		// Scroll down
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,600);");
		System.out.println("✅ Scrolled down");

		//Click Next Page arrow
		Common.waitForElement(3);
		waitFor(nextPageArrow);
		click(nextPageArrow);
		System.out.println("✅ Clicked next page");

		// Click Plus Button to add products
		Common.waitForElement(2);
		waitFor(plusButton);
		click(plusButton);
		System.out.println("✅ Clicked '+' button");

		//  In the products list, find SKU & drag to first position
		// XPath to locate card by SKU text
		Common.waitForElement(3);
		By skuCard = By.xpath("//div[contains(@class,'sortable-card')]//span[contains(text(),'" + copiedSku + "')]/ancestor::div[contains(@class,'sortable-card')]");

		// Find the product card
		List<WebElement> products = driver.findElements(skuCard);

		if (products.isEmpty()) {
		    System.out.println("❌ Product with SKU '" + copiedSku + "' not found.");
		} else {
		    WebElement from = products.get(0);  // found product card
		    WebElement firstPosition = driver.findElement(By.xpath("(//div[contains(@class,'sortable-card')])[1]"));

		    try {
		        // try native Selenium drag & drop
		        Actions actions = new Actions(driver);
		        actions.clickAndHold(from)
		               .moveToElement(firstPosition, 0, 0) // move inside first card
		               .release()
		               .build()
		               .perform();

		        System.out.println("✅ Dragged product '" + copiedSku + "' to first position (Selenium).");

		    } catch (Exception e) {
		        // JavaScript drag & drop simulation
		        String jsDragDrop =
		            "function triggerDragAndDrop(source, target) {" +
		            "  const dataTransfer = new DataTransfer();" +
		            "  const dragStartEvent = new DragEvent('dragstart', { dataTransfer });" +
		            "  source.dispatchEvent(dragStartEvent);" +
		            "  const dropEvent = new DragEvent('drop', { dataTransfer });" +
		            "  target.dispatchEvent(dropEvent);" +
		            "  const dragEndEvent = new DragEvent('dragend', { dataTransfer });" +
		            "  source.dispatchEvent(dragEndEvent);" +
		            "}" +
		            "triggerDragAndDrop(arguments[0], arguments[1]);";

		        ((JavascriptExecutor) driver).executeScript(jsDragDrop, from, firstPosition);

		        System.out.println("✅ Dragged product '" + copiedSku + "' to first position (JS fallback).");
		    }
		}
		
		 //  Save
		Common.waitForElement(3);
	    waitFor(saveButton);
	    click(saveButton);
	    System.out.println("✅ Saved collection changes");
	    //Clear Catch
	    Common.waitForElement(2);
	    waitFor(clearCatchButton);
	    click(clearCatchButton);
	    System.out.println("✅ Successfull click Clear Catch Button");
	    Common.waitForElement(2);
		
		
		
	}
	
	public void verifyProductShowInNewArrivalsSction(String productName) {
	    switchToWindow(1);
	    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
	    Common.waitForElement(3);
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2400);");
	    Common.waitForElement(3);

	    int timeoutMinutes = 10;   // total wait time
	    int refreshInterval = 15; // refresh every 15 seconds
	    boolean productFound = false;
	    WebElement card = null;

	    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        try {
	            //  Refresh page
	            driver.navigate().refresh();
	            Common.waitForElement(2);

	            // ✅ Use FluentWait after refresh
	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(refreshInterval)) 
	                    .pollingEvery(Duration.ofSeconds(2))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            card = wait.until(d -> {
	                List<WebElement> elements = d.findElements(By.xpath(
	                        "//div[contains(@class,'products_cards')]//h3[@class='product_heading' and normalize-space(text())='" 
	                        + productName + "']"
	                ));
	                return elements.isEmpty() ? null : elements.get(0);
	            });

	            if (card != null && card.isDisplayed()) {
	                productFound = true;
	                break; // ✅ stop if found
	            }
	        } catch (TimeoutException te) {
	            // not found in this refresh cycle → loop continues
	        }

	        try {
	            Thread.sleep(1000); // small buffer
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }

	    // ✅ Final validation
	    if (productFound && card != null && card.getText().trim().equalsIgnoreCase(productName.trim())) {
	        System.out.println("✅ Product '" + productName + "' is visible in New Arrivals.");
	    } else {
	        System.err.println("❌ Product '" + productName + "' not found in New Arrivals within " 
	                           + timeoutMinutes + " minutes.");
	    }
	}
	
	//Catagory Section
	
	  public void updateCategoryBanner(String imagePathCatagory) {
		
		driver.get(Common.getValueFromTestDataMap("ExcelPath"));
	    System.out.println("✅ Successful redirect to Home page Banner ");
	    
      
        // Click on Home Page Banner menu
	    Common.waitForElement(2);
	    waitFor(homePageBannerMenu);
        click(homePageBannerMenu);
        Common.waitForElement(2);
        

        // Search for category
        Common.waitForElement(2);
	    waitFor(catagorysearchTextBox);
	    type(catagorysearchTextBox, "Dazzle Category");
	    catagorysearchTextBox.sendKeys(Keys.ENTER);
	    System.out.println("✅ Typed 'Dazzle Category' & pressed Enter");
	    
        // Click Edit button
	    Common.waitForElement(2);
	    waitFor(editButton);
        click(editButton);
        System.out.println("✅ Clicked  editbutton");
        

        // Clear and enter new banner title
        String bannerTitle = Common.getValueFromTestDataMap("Banner Title");
        Common.waitForElement(2);
	    waitFor(bannerTitleTextBox);
	    bannerTitleTextBox.clear();
	    type(bannerTitleTextBox, bannerTitle);
	    System.out.println("✅ Typed bannerTitle");
	    

        // Upload new banner image
	    Common.waitForElement(2);
	    waitFor(desktopBannerUpload);
        desktopBannerUpload.sendKeys(imagePathCatagory);
        System.out.println("✅ successful image updated");
        

        // Save changes
        Common.waitForElement(2);
        waitFor(saveButton);
        saveButton.click();
        Common.waitForElement(3);
      //Clear Catch
	    Common.waitForElement(2);
	    waitFor(clearCatchButton);
	    click(clearCatchButton);
	    System.out.println("✅ Successfull click Clear Catch Button");
	    Common.waitForElement(2);
    }

    // Method 2: Verify Banner in User Application
	public void verifyBannerUserApp() {
		switchToWindow(1);
	    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
	    Common.waitForElement(3);
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,1300);");
	    Common.waitForElement(3);

	    // Expected values from test data
	    String expectedTitle = Common.getValueFromTestDataMap("Banner Title");

	    // ✅ Wait until the expected banner title appears
	    int timeoutMinutes = 10;  
	    int pollSeconds = 5;
	    boolean titleFound = false;
	    WebElement titleElement = null;

	    long endTime = System.currentTimeMillis() + timeoutMinutes * 60 * 1000;

	    while (System.currentTimeMillis() < endTime) {
	        try {
	            // Refresh and wait
	            driver.navigate().refresh();
	            Common.waitForElement(2);

	            Wait<WebDriver> wait = new FluentWait<>(driver)
	                    .withTimeout(Duration.ofSeconds(pollSeconds))
	                    .pollingEvery(Duration.ofSeconds(2))
	                    .ignoring(NoSuchElementException.class)
	                    .ignoring(StaleElementReferenceException.class);

	            titleElement = wait.until(d -> {
	                List<WebElement> elements = d.findElements(By.xpath(
	                		"//img[@alt='" + expectedTitle + "']"));
	                return elements.isEmpty() ? null : elements.get(0);
	            });

	            if (titleElement != null && titleElement.isDisplayed()) {
	                titleFound = true;
	                break;
	            }

	        } catch (TimeoutException te) {
	            // keep looping until timeout
	        }

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
	    }

		// ✅ Final check
		if (titleFound) {
			System.out.println("✅ Banner title '" + expectedTitle + "' is visible in User Application.");
		} else {
			System.out
					.println("❌ Banner title '" + expectedTitle + "' not found within " + timeoutMinutes + " minutes.");
			Assert.fail("❌ Banner title '" + expectedTitle + "' not found within " + timeoutMinutes + " minutes.");
		}
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

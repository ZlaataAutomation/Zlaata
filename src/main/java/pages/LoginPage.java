package pages;


import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ibm.disthub2.impl.matching.DisthubValueAccessor.Action;

import manager.FileReaderManager;
import objectRepo.LoginObjRepository;
import utils.Common;
import utils.DateUtils;



public final class LoginPage extends LoginObjRepository {

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
    
    public void homeLaunch() {
		driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
//				type(accessCode, FileReaderManager.getInstance().getJsonReader().getValueFromJson("Access"));
//				click(submit);
//		handleAccessCodeIfPresentFast();
//		popup();
    }
    
    public void homeLaunchAfterSaved() {

        try {
            
            profile.click();

            WebElement userName = driver.findElement(
                    By.xpath("//div[contains(@class,'account_tabs_user_content')]//h2")
            );

            if (userName.isDisplayed()) {
                System.out.println("\u001B[32m✅ User already logged in: " 
                    + userName.getText() + "\u001B[0m");
                driver.navigate().refresh();
                return; 
            }

        } catch (Exception e) {

           
            System.out.println("\u001B[33m⚠ User not logged in. Proceeding with login flow...\u001B[0m");

            driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());

            handleAccessCodeIfPresentFast();
            popup();
            userLogin();
        }
    }

    public void handleAccessCodeIfPresentFast() {

        try {
            List<WebElement> accessCodeInput = driver.findElements(
                    By.xpath("//input[@id='security_code']")
            );

            // 🔹 Instant check – if not present, skip
            if (accessCodeInput.isEmpty()) {
                return;
            }

            WebElement input = accessCodeInput.get(0);

            if (input.isDisplayed()) {

                String accessCode = FileReaderManager.getInstance()
                        .getJsonReader()
                        .getValueFromJson("Access");

                // Type access code
                input.clear();
                input.sendKeys(accessCode);

                // Click submit
                WebElement submitBtn = driver.findElement(
                        By.xpath("//form[contains(@action,'accessCheckProcess')]//button")
                );

                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", submitBtn);
                
                System.out.println("⚡ Access code entered (fast path)");
            }

        } catch (Exception e) {
            // swallow – fast skip mode
        }
    }
    public void userLogin() {
    	
    	homeLaunch();    
    	Common.waitForElement(1);
    	click(profile);
        type(loginNumber, FileReaderManager.getInstance().getJsonReader().getValueFromJson("Number"));
        Common.waitForElement(1);
        click(sendotp);
        Common.waitForElement(40);
 //     type(enterotp, FileReaderManager.getInstance().getJsonReader().getValueFromJson("OTP"));
        click(verifyotp);
        Common.waitForElement(3); // small buffer

       
            System.out.println("\u001B[32m✅ Login successful\u001B[0m");
       
         // ❌ Red color
//           System.out.println("\u001B[31m❌ Login failed: OTP verification or redirection failed\u001B[0m");
        }
    public void popup() {
	    List<WebElement> popUps = driver.findElements(
	            By.xpath("//div[contains(@class,'chrismas_closebtn')]")
	    );

	    if (!popUps.isEmpty()) {
	    	 ((JavascriptExecutor) driver)
             .executeScript("arguments[0].click();", popUps.get(0));
	    }
	}
//    public void userLogin() {
//        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
//        Common.waitForElement(1);
//        click(profile);
//        Common.waitForElement(1);
//        type(loginNumber, FileReaderManager.getInstance().getJsonReader().getValueFromJson("Number"));
//        Common.waitForElement(1);
//        click(sendotp);
//
//        List<WebElement> otpenterBox = driver.findElements(By.xpath("//form[@class='digit-group login_otp_input_form']//input"));
//        Scanner scanner = new Scanner(System.in);
//        boolean otpVerified = false;
//
//        while (!otpVerified) {
//            System.out.print("🔐 Enter OTP: ");
//            String enteredOtp = scanner.nextLine();
//
//            // Clear previous input
//            for (WebElement box : otpenterBox) {
//                box.clear();
//            }
//
//            // Enter new OTP
//            for (int i = 0; i < enteredOtp.length(); i++) {
//                if (i < otpenterBox.size()) {
//                    otpenterBox.get(i).sendKeys(Character.toString(enteredOtp.charAt(i)));
//                }
//            }
//
////            Common.waitForElement(2);
//            click(verifyotp);
//            Common.waitForElement(3);
//
//
//            try {
//                // Case 1: OTP expired
//                if (otpEnterAfterTimefinish.isDisplayed() &&
//                    otpEnterAfterTimefinish.getText().toLowerCase().contains("expired")) {
//                    System.out.println("\u001B[31m⏰ OTP expired. Resending OTP...\u001B[0m");
//                    click(resendOtpButton);
//                    Common.waitForElement(3);
//                    continue; // re-prompt for new OTP
//                }
//
//                // Case 2: Wrong OTP
//                if (validationForWrongOTP.isDisplayed()) {
//                    String error = validationForWrongOTP.getText();
//                    System.out.println("\u001B[31m❌ Invalid OTP: " + error + "\u001B[0m");
//                    continue; // re-prompt
//                }
//
//                // Case 3: No error — assume success
//                otpVerified = true;
//
//            } catch (Exception e) {
//                // If elements not found, assume OTP was correct and login succeeded
//                otpVerified = true;
//            }
//        }
//
//        System.out.println("\u001B[32m✅ OTP verified successfully. Logged in!\u001B[0m");
//    }

    public void verifyFirstBuy() {
    	homeLaunch();
        click(profile);
        Common.waitForElement(1);
        try {
            String firstBuy = getText(firstBuyCode);
            System.out.println("INFO: First Buy Coupon Code displayed: " + firstBuy);
        } catch (NoSuchElementException e) {
            System.out.println("ERROR: Exception occurred - " + e.getMessage());
            NoSuchElementException e1 = new NoSuchElementException("A NoSuchElementException exception occurred");
            e1.initCause(e);
            throw e1;
        }
    }

    public void numberFieldEmpty() {
    	homeLaunch();

        click(profile);
        click(sendotp);

        String actualMessage = validationMessage.getText();
        Assert.assertTrue("Validation failed for empty number field", actualMessage.equals(actualMessage));

        String excelData = Common.getValueFromTestDataMap("Mobile Number");
        type(loginNumber, excelData);
        String uiData = loginNumber.getAttribute("value");

        System.out.println("📥 Excel Data: " + excelData + " | Length: " + excelData.length());
        System.out.println("📤 Application UI Data: " + (uiData != null ? uiData : "null") + " | Length: " + (uiData != null ? uiData.length() : 0));
        System.out.println("\u001B[32m✅ SUCCESS: Validation Message = " + actualMessage + "\u001B[0m");
    }


    public void numberLessthan10Digit() {
    	homeLaunch();
        click(profile);

        String excelData = Common.getValueFromTestDataMap("Mobile Number");
        type(loginNumber, excelData);
        click(sendotp);

        String actualMessage = validationMessage.getText();
        Assert.assertTrue("Validation failed for <10 digit number", actualMessage.equals(actualMessage));

        String uiData = loginNumber.getAttribute("value");

        System.out.println("📥 Excel Data: " + excelData + " | Length: " + excelData.length());
        System.out.println("📤 Application UI Data: " + (uiData != null ? uiData : "null") + " | Length: " + (uiData != null ? uiData.length() : 0));
        System.out.println("\u001B[32m✅ SUCCESS: Validation Message = " + actualMessage + "\u001B[0m");
    }


    public void numberMOrethan10() {
    	homeLaunch();
        click(profile);

        String excelData = Common.getValueFromTestDataMap("Mobile Number");
        type(loginNumber, excelData);

        String uiData = loginNumber.getAttribute("value");
        Assert.assertTrue("Validation failed for >10 digit number", uiData.length() >= 10);

        System.out.println("📥 Excel Data: " + excelData + " | Length: " + excelData.length());
        System.out.println("📤 Application UI Data: " + (uiData != null ? uiData : "null") + " | Length: " + (uiData != null ? uiData.length() : 0));
    }


    public void numberWithNonNumeric() {
    	homeLaunch();

        click(profile);

        String excelData = Common.getValueFromTestDataMap("Mobile Number");
        type(loginNumber, excelData);

        String uiData = loginNumber.getAttribute("value");

        if (uiData != null) {
            System.out.println("📥 Excel Data: " + excelData + " | Length: " + excelData.length());
            System.out.println("📤 Application UI Data: " + uiData + " | Length: " + uiData.length());
//            System.out.println("\u001B[32m✅ SUCCESS: Non-numeric input handled\u001B[0m");
        } else {
            System.out.println("\u001B[31m❌ ERROR: Application UI Data is null or unreadable.\u001B[0m");
            Assert.fail("Phone number field is empty or unreadable.");
        }
    }


    public void numberWith123() {
    	homeLaunch();
        click(profile);
        String excelData = Common.getValueFromTestDataMap("Mobile Number");
        type(loginNumber, excelData);

        String uiData = loginNumber.getAttribute("value");

        System.out.println("📥 Excel Data: " + excelData + " | Length: " + excelData.length());
        System.out.println("📤 Application UI Data: " + uiData + " | Length: " + uiData.length());
//        System.out.println("\u001B[32m✅ SUCCESS: Entered number displayed correctly\u001B[0m");
    }


    public void newNumber() {
    	homeLaunch();

        click(profile);
//        Common.waitForElement(5);

        Random rnd = new Random();
        int n = 66666 + rnd.nextInt(99999);
        String excelData = n + DateUtils.getCurrentLocalDateTimeStamp("YYYYMMdd");

        type(loginNumber, excelData);
//        Common.waitForElement(10);
        click(sendotp);
//        Common.waitForElement(10);

        String actualMessage = userNotRegisterValidationMessage.getText();
        Assert.assertTrue("Expected message for unregistered user", actualMessage.equals(actualMessage));

        String uiData = loginNumber.getAttribute("value");

        System.out.println("📥 Excel Data: " + excelData + " | Length: " + excelData.length());
        System.out.println("📤 Application UI Data: " + uiData + " | Length: " + uiData.length());
        System.out.println("\u001B[32m✅ SUCCESS: Validation Message = " + actualMessage + "\u001B[0m");
    }


    public void numberSpecialSymbol() {
    	homeLaunch();

        click(profile);

        String excelData = Common.getValueFromTestDataMap("Mobile Number");
        type(loginNumber, excelData);
//        Common.waitForElement(1);

        String uiData = loginNumber.getAttribute("value");
        String expectedSanitized = excelData.replaceAll("[^0-9]", "");

        System.out.println("📥 Excel Data: " + excelData + " | Length: " + excelData.length());
        System.out.println("📤 Application UI Data: " + uiData + " | Length: " + uiData.length());

        Assert.assertEquals("❌ Sanitized number mismatch", expectedSanitized, uiData);
        System.out.println("\u001B[32m✅ SUCCESS: Special symbols removed as expected\u001B[0m");
    }

    public void numberwithSpaces() {
    	homeLaunch();

        click(profile);

        String excelData = Common.getValueFromTestDataMap("Mobile Number");
        type(loginNumber, excelData);

        String uiData = loginNumber.getAttribute("value");
        String expected = excelData.replaceAll("\\s+", "");

        System.out.println("📥 Excel Data: " + excelData + " | Length: " + excelData.length());
        System.out.println("📤 UI Data: " + uiData + " | Length: " + (uiData != null ? uiData.length() : 0));

        if (!expected.equals(uiData)) {
            System.out.println("❌ ERROR: Input mismatch. Expected: " + expected + " | Actual: " + uiData);
        } else {
            System.out.println("\u001B[32m✅ SUCCESS: Number with spaces handled correctly\u001B[0m");
        }

        // Optional assert if needed
        // Assert.assertEquals("❌ Number with spaces not handled correctly", expected, uiData);
    }



    public void mailLogin() {
    	homeLaunch();

        click(profile);
//        Common.waitForElement(5);
        click(mailIcon);
        System.out.println("successfully open the gmail page");
    }

    public void faceBookLink() {
        try {
            // Step 1: Navigate to Application and Open Facebook Login
        	homeLaunch();

            Common.waitForElement(5);
            click(profile);
            Common.waitForElement(2);
            click(faceBookIcon);
            System.out.println("🔗 Navigated to Facebook login window.");

            // Step 2: Read credentials from JSON
            String fbNumber = FileReaderManager.getInstance().getJsonReader().getValueFromJson("FaceBook");
            String fbPassword = FileReaderManager.getInstance().getJsonReader().getValueFromJson("FaceBookPassword");

            // Step 3: Input credentials
            type(enterFaceBookNumber, fbNumber);
            type(enterFaceBookPasswrod, fbPassword);

            System.out.println("📥 Facebook Number Input: " + fbNumber + " | Length: " + fbNumber.length());
            System.out.println("📥 Facebook Password Input: " + fbPassword.replaceAll(".", "*") + " | Length: " + fbPassword.length());

            // Step 4: Click Login and proceed
            click(clickOnFaceBookLoginButton);
            Common.waitForElement(20);

            // Step 5: Handle captcha or continue prompts
            click(captchaContinueButton);
            click(clickOnContinueButton);

            // Step 6: Validate login success message
            String actualMessage = facebookLoginValidationMessage.getText();
            System.out.println("📤 UI Message After Login: " + actualMessage + " | Length: " + actualMessage.length());

            Assert.assertNotNull("Validation message should not be null after Facebook login.", actualMessage);
            Assert.assertTrue("Validation message should not be empty.", !actualMessage.trim().isEmpty());

            System.out.println("\u001B[32m✅ SUCCESS: Facebook login message = " + actualMessage + "\u001B[0m");

        } catch (Exception e) {
            System.out.println("\u001B[31m❌ ERROR: Facebook login automation failed - " + e.getMessage() + "\u001B[0m");
            throw e;
        }
    }
    
    
    public void verifyEmptyField() {

     

        String GREEN = "\u001B[32m";
        String RED   = "\u001B[31m";
        String CYAN  = "\u001B[36m";
        String RESET = "\u001B[0m";
        String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
        String expectedError = "Please enter a valid 10-digit phone number.";
        Common.waitForElement(2);
        System.out.println(CYAN + "🔍 Verifying empty mobile number validation" + RESET);

     
        click(profile);
        click(sendotp);

        String actualMessage = validationMessage.getText().trim();

        Assert.assertEquals(
                "❌ Validation message mismatch for EMPTY mobile number",
                expectedError,
                actualMessage
        );

        System.out.println(GREEN + "✅ Empty field validation passed" + RESET);


        // 2️⃣ Enter less than 10 digits
        System.out.println(CYAN + "🔍 Verifying invalid (less than 10 digits) number" + RESET);

        type(loginNumber, "859604");   // less than 10 digits
        click(sendotp);

        String actualMessage2 = validationMessage.getText().trim();

        Assert.assertEquals(
                "❌ Validation message mismatch for invalid mobile number",
                expectedError,
                actualMessage2
        );

        System.out.println(GREEN + "✅ Invalid number validation passed" + RESET);
    }
    
    public void verifyNumberMorethan10() {

        String GREEN = "\u001B[32m";
        String RED   = "\u001B[31m";
        String CYAN  = "\u001B[36m";
        String RESET = "\u001B[0m";
        String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
        Common.waitForElement(2);
     //   String excelData = Common.getValueFromTestDataMap("Mobile Number"); 
        String excelData = "987654321012"; 
        // Example: 987654321012 (12 digits)
        loginNumber.clear();

        type(loginNumber, excelData);

        String uiData = loginNumber.getAttribute("value");

        System.out.println(CYAN + "📥 Excel Data: " + excelData + " | Length: " + excelData.length() + RESET);
        System.out.println(CYAN + "📤 UI Data: " + uiData + " | Length: " + uiData.length() + RESET);

       
        Assert.assertEquals(
                "❌ Application accepted more than 10 digits!",
                10,
                uiData.length()
        );

        System.out.println(GREEN + "✅ Mobile number restricted to 10 digits as expected" + RESET);
    }
    
    public void verifyNumberWithNonNumeric() {

        String RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String CYAN = "\u001B[36m";
        String RESET = "\u001B[0m";
        String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);

        Common.waitForElement(2);
        loginNumber.clear();

        // Non-numeric input
        String input = "98ab@#123";
        type(loginNumber, input);

        String uiData = loginNumber.getAttribute("value");

        System.out.println(CYAN + "📥 Entered Data: " + input + RESET);
        System.out.println(CYAN + "📤 UI Value: " + uiData + RESET);

        // ✅ Correct validation
        boolean isOnlyDigits = uiData.matches("\\d+");
        boolean isFiltered = !uiData.equals(input);

        if (isOnlyDigits && isFiltered) {
            System.out.println(GREEN + "✅ Non-numeric characters are correctly blocked" + RESET);
        } else {
            Assert.fail(
                RED + "❌ Invalid behavior! UI accepted non-numeric input: " + uiData + RESET
            );
        }
    }
    
    public void verifyNumberWith123() {

        String RESET  = "\u001B[0m";
        String GREEN  = "\u001B[32m";
        String RED    = "\u001B[31m";
        String YELLOW = "\u001B[33m";
        String CYAN   = "\u001B[36m";
        String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);
        Common.waitForElement(2);
        loginNumber.clear();

        String input = "123";
        type(loginNumber, input);

        String uiData = loginNumber.getAttribute("value");

        System.out.println(CYAN + "📥 Tried Input : " + input + RESET);
        System.out.println(CYAN + "📤 UI Value   : " + (uiData == null ? "null" : "'" + uiData + "'") + RESET);

        // ✅ Expected: field should remain EMPTY
        if (uiData == null || uiData.isEmpty()) {
            System.out.println(GREEN + "✅ PASS: Field blocked invalid number (123)" + RESET);
        } else {
            System.out.println(RED + "❌ FAIL: Field accepted invalid number → " + uiData + RESET);
            Assert.fail("Field should not accept less than 10 digits");
        }
    }
    
    public void verifyNewNumber() {

        
        String RESET  = "\u001B[0m";
        String GREEN  = "\u001B[32m";
        String RED    = "\u001B[31m";
        String CYAN   = "\u001B[36m";
        String YELLOW = "\u001B[33m";
        String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);

        Common.waitForElement(2);
        loginNumber.clear();

     // Generate 10-digit unregistered number starting with 66666
        Common.waitForElement(1);
        Random rnd = new Random();
        int lastFive = 10000 + rnd.nextInt(90000); 

        String newNumber = "66666" + lastFive; 

        type(loginNumber, newNumber);
        Common.waitForElement(1);
        click(sendotp);

        // Expected validation message
        String expectedMessage = "User is not registered. Please sign up first.";

        String actualMessage = userNotRegisterValidationMessage.getText().trim();

        System.out.println(CYAN + "📥 Entered Number : " + newNumber + RESET);
        System.out.println(CYAN + "📤 Actual Message : " + actualMessage + RESET);

        // ✅ Assertion
        if (actualMessage.equalsIgnoreCase(expectedMessage)) {
            System.out.println(GREEN + "✅ PASS: Correct validation message displayed" + RESET);
        } else {
            System.out.println(RED + "❌ FAIL: Wrong validation message" + RESET);
            System.out.println(YELLOW + "Expected: " + expectedMessage + RESET);
            System.out.println(YELLOW + "Actual  : " + actualMessage + RESET);
            Assert.fail("Validation message mismatch");
        }
    }
    
    public void verifyNumberwithSpaces() {

        String GREEN = "\u001B[32m";
        String RED   = "\u001B[31m";
        String CYAN  = "\u001B[36m";
        String RESET = "\u001B[0m";
        String line = "──────────────────────────────────────────────────────────────";
	    System.out.println(CYAN + line + RESET);

        Common.waitForElement(2);
        loginNumber.clear();

        // Input with spaces (from Excel)
      //  String excelData = Common.getValueFromTestDataMap("Mobile Number");
        String excelData="                 ";
        type(loginNumber, excelData);

        // Get value from UI
        String uiData = loginNumber.getAttribute("value");


        String expected = excelData.replaceAll("\\s+", "");

        System.out.println(CYAN + "📥 Excel Data : " + excelData + " | Length: " + excelData.length() + RESET);
        System.out.println(CYAN + "📤 UI Data    : " + uiData + " | Length: " +
                (uiData != null ? uiData.length() : 0) + RESET);

        // ✅ Assertion
        if (expected.equals(uiData)) {
            System.out.println(GREEN + "✅ PASS: Spaces are removed correctly" + RESET);
        } else {
            System.out.println(RED + "❌ FAIL: Space handling incorrect" + RESET);
            System.out.println("Expected: " + expected);
            System.out.println("Actual  : " + uiData);
            Assert.fail("Phone number field did not handle spaces correctly");
        }
    }
    public void verifyUserStillLoggedInAfterRefresh() {

        String GREEN = "\u001B[32m";
        String RED   = "\u001B[31m";
        String RESET = "\u001B[0m";

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

       
        driver.navigate().refresh();

        // Click Profile icon
        WebElement profileIcon = wait.until(
                ExpectedConditions.elementToBeClickable(profile)
        );
        profileIcon.click();

        // ✅ Verify account section is visible
        WebElement accountSection = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'account_tabs_user_content')]")
                )
        );

        // ✅ Verify user name
        WebElement userName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[contains(@class,'account_tabs_user_name')]")
                )
        );

//        // ✅ Verify My Profile link
//        WebElement myProfileLink = wait.until(
//                ExpectedConditions.visibilityOfElementLocated(
//                        By.xpath("//a[contains(@class,'account_sidebar_user_profile_link_acc_settings')]")
//                )
//        );

        // Assertions
        Assert.assertTrue("❌ Account section not visible", accountSection.isDisplayed());
        Assert.assertTrue("❌ Username not visible", userName.isDisplayed());
 //       Assert.assertTrue("❌ My Profile link not visible", myProfileLink.isDisplayed());

        System.out.println(GREEN + "✅ User still logged in after refresh" + RESET);
        System.out.println(GREEN + "👤 Username: " + userName.getText() + RESET);
    }

    public void verifyLogOut() {

    	
        try {
        	  profile.click();
              WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // 🔹 Case 1: Logout visible
            WebElement logoutBtn = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(
                            By.xpath("//a[contains(@class,'logout-btn')]")
                    ));
         // ✅ Scroll to Logout
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", logoutBtn);

            Common.waitForElement(1);
            if (logoutBtn.isDisplayed()) {
                System.out.println("✅ Logout option visible. Logging out...");
                Common.waitForElement(2);
                ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", logoutBtn);
                  return;
            }

        } catch (Exception e) {
        	driver.navigate().refresh();
        	   System.out.println("ℹ Logout option not found. Skipping logout.");
        }

       
    }
    
    //TC_01
    public void validateLoginNegativeCredantial() {
    	homeLaunch();
    	
    	handleAccessCodeIfPresentFast();
    	
    	verifyLogOut();
    	
    	verifyEmptyField();
    	
    	verifyNumberMorethan10();
    	
    	verifyNumberWithNonNumeric();
    	
    	verifyNumberWith123();
    	
    	verifyNewNumber();
    	
    	verifyNumberwithSpaces();
    }
    
 //TC-02   
    public void validateUserLoginWithValidCredantial() {
    	
    	    	
    	userLogin();
    	
    	verifyUserStillLoggedInAfterRefresh();
    }
    
    
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    


    @Override
    public WebDriver gmail(String browserName) {
        return null;
    }

    @Override
    public boolean verifyExactText(WebElement ele, String expectedText) {
        return false;
    }

    @Override
    protected boolean isAt() {
        return this.wait.until((d) -> this.accessCode.isDisplayed());
    }
}

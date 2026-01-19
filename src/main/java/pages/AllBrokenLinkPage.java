package pages;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.AllBrokenLinkObjRepo;
import utils.Common;

public class AllBrokenLinkPage extends AllBrokenLinkObjRepo{
	
	
	
	public AllBrokenLinkPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	
	
	public void verifyAllLinksAndUrls() {

	    String GREEN = "\u001B[32m";
	    String RED   = "\u001B[31m";
	    String CYAN  = "\u001B[36m";
	    String RESET = "\u001B[0m";

	    driver.get(FileReaderManager.getInstance()
	            .getConfigReader()
	            .getApplicationUrl());

	    System.out.println(CYAN + "🔍 Starting Broken Link Verification..." + RESET);
	    Common.waitForElement(3);
	    List<WebElement> allLinks = driver.findElements(By.tagName("a"));
	    System.out.println("🔗 Total links found: " + allLinks.size());

	    List<String> brokenLinks = new ArrayList<>();

	    for (WebElement link : allLinks) {

	        String url = link.getAttribute("href");

	        if (url == null || url.isEmpty()
	                || url.startsWith("javascript")
	                || url.startsWith("mailto")
	                || url.startsWith("tel")) {
	            continue;
	        }

	        try {
	            HttpURLConnection connection =
	                    (HttpURLConnection) new URL(url).openConnection();

	            connection.setConnectTimeout(5000);
	            connection.setRequestMethod("HEAD");
	            connection.connect();

	            int responseCode = connection.getResponseCode();

	            if (responseCode >= 400) {
	                brokenLinks.add(url + " --> " + responseCode);
	                System.out.println(RED + "❌ Broken: " + url + " | Code: " + responseCode + RESET);
	            } else {
	                System.out.println(GREEN + "✅ Valid: " + url + " | Code: " + responseCode + RESET);
	            }

	        } catch (Exception e) {
	            brokenLinks.add(url + " --> Exception");
	            System.out.println(RED + "❌ Error: " + url + " | " + e.getMessage() + RESET);
	        }
	    }

	    System.out.println(CYAN + "──────────────────────────────────────" + RESET);
	    System.out.println("🔎 Broken Links Count: " + brokenLinks.size());

	    for (String broken : brokenLinks) {
	        System.out.println(RED + broken + RESET);
	    }

	    if (!brokenLinks.isEmpty()) {
	        Assert.fail("❌ Broken / Invalid links found: " + brokenLinks.size());
	    } else {
	        System.out.println(GREEN + "🎉 All links are valid!" + RESET);
	    }
	}

	
	
	public void verifyAllLinksAndPrintStatus() {

 	    String GREEN  = "\u001B[32m";
	    String RED    = "\u001B[31m";
	    String YELLOW = "\u001B[33m";
	    String CYAN   = "\u001B[36m";
	    String BLUE   = "\u001B[34m";
	    String RESET  = "\u001B[0m";

	    driver.get(FileReaderManager.getInstance()
	            .getConfigReader()
	            .getApplicationUrl());

	    List<WebElement> links = driver.findElements(By.tagName("a"));

	    int okCount = 0;
	    int brokenCount = 0;
	    int nullCount = 0;

	    List<String> brokenLinks = new ArrayList<>();

	    long startTime = System.currentTimeMillis();

	    System.out.println(BLUE + "──────────────────────────────────────────────" + RESET);
	    System.out.println(CYAN + "🔍 Starting Broken Link Verification..." + RESET);
	    System.out.println(BLUE + "──────────────────────────────────────────────" + RESET);
	    System.out.println("🔗 Total links found: " + links.size());


	    for (WebElement link : links) {

	        String url = link.getAttribute("href");

 	        if (url == null || url.trim().isEmpty()) {
	            System.out.println(YELLOW + "[NO HREF] ---> null" + RESET);
	            nullCount++;
	            continue;
	        }

	        if (url.equals("#")) {
	            System.out.println(YELLOW + "[# LINK] ---> null" + RESET);
	            nullCount++;
	            continue;
	        }

	        if (url.startsWith("javascript")
	                || url.startsWith("mailto")
	                || url.startsWith("tel")) {

	            System.out.println(YELLOW + "[SKIPPED] " + url + RESET);
	            nullCount++;
	            continue;
	        }


	        try {
	            HttpURLConnection connection =
	                    (HttpURLConnection) new URL(url).openConnection();

	            connection.setRequestMethod("HEAD");
	            connection.setConnectTimeout(5000);
	            connection.connect();

	            int responseCode = connection.getResponseCode();

	            if (responseCode >= 400) {
	                System.out.println(RED + url + " ---> "
	                        + getMessage(responseCode)
	                        + " is a broken link" + RESET);

	                brokenLinks.add(url + " (" + responseCode + ")");
	                brokenCount++;
	            } else {
	                System.out.println(GREEN + url + " ---> OK" + RESET);
	                okCount++;
	            }

	        } catch (Exception e) {
	            System.out.println(YELLOW + url + " ---> null" + RESET);
	            nullCount++;
	            brokenLinks.add(url + " (Exception)");
	        }
	    }

	    long totalTime = System.currentTimeMillis() - startTime;

 	    System.out.println(BLUE + "──────────────────────────────────────────────" + RESET);
	    System.out.println(CYAN + "📊 Link Verification Summary" + RESET);
	    System.out.println(GREEN + "✅ Valid Links   : " + okCount + RESET);
	    System.out.println(RED   + "❌ Broken Links : " + brokenCount + RESET);
	    System.out.println(YELLOW+ "⚠️ Null / Skipped: " + nullCount + RESET);
	    System.out.println("⏱️ Total Time   : " + totalTime + " ms");
	    System.out.println(BLUE + "──────────────────────────────────────────────" + RESET);

 	    if (!brokenLinks.isEmpty()) {
	        Assert.fail("❌ Broken links found: " + brokenLinks.size());
	    }
	}

	
	
	
	private String getMessage(int statusCode) {

	    switch (statusCode) {
	        case 400:
	            return "Bad Request";
	        case 401:
	            return "Unauthorized";
	        case 403:
	            return "Forbidden";
	        case 404:
	            return "Not Found";
	        case 500:
	            return "Internal Server Error";
	        case 502:
	            return "Bad Gateway";
	        case 503:
	            return "Service Unavailable";
	        case 504:
	            return "Gateway Timeout";
	        default:
	            return "HTTP Error " + statusCode;
	    }
	}

	
	
	public void verifyLinksByClick_No404() {

	    String GREEN = "\u001B[32m";
	    String RED = "\u001B[31m";
	    String YELLOW = "\u001B[33m";
	    String CYAN = "\u001B[36m";
	    String RESET = "\u001B[0m";

	    driver.get(FileReaderManager.getInstance()
	            .getConfigReader().getApplicationUrl());

	    List<WebElement> links = driver.findElements(By.tagName("a"));

	    int ok = 0, broken = 0, skipped = 0;
	    List<String> brokenLinks = new ArrayList<>();

	    System.out.println(CYAN + "🔍 Starting SAFE 404 validation..." + RESET);

	    for (WebElement link : links) {

	        String url = link.getAttribute("href");
	        String text = link.getText().trim();

	        // 🔹 STRICT FILTER
	        String baseUrl = driver.getCurrentUrl();

	        if (url == null || url.isEmpty()
	                || url.startsWith("javascript")
	                || url.startsWith("mailto")
	                || url.startsWith("tel")
	                || url.equalsIgnoreCase(baseUrl)
	                || !url.startsWith("http")) {

	            skipped++;
	            System.out.println(YELLOW + "⚠ Skipped invalid / same-page link" + RESET);
	            continue;
	        }


	        try {
 	            HttpURLConnection conn =
	                    (HttpURLConnection) new URL(url).openConnection();
	            conn.setRequestMethod("HEAD");
	            conn.setConnectTimeout(5000);
	            conn.connect();

	            int code = conn.getResponseCode();

	            if (code >= 400) {
	                broken++;
	                brokenLinks.add(url);
	                System.out.println(RED + "❌ BROKEN LINK: " + text + " ---> " + url + RESET);
	                continue;
	            }

	            ok++;
	            System.out.println(GREEN + "✅ OK: " + text + " ---> " + url + RESET);

	        } catch (Exception e) {
	            skipped++;
	            System.out.println(YELLOW + "⚠ Exception, skipped: " + url + RESET);
	        }
	    }

 	    System.out.println("\n================ SUMMARY ================");
	    System.out.println(GREEN + "✅ OK Links     : " + ok + RESET);
	    System.out.println(RED + "❌ Broken Links : " + broken + RESET);
	    System.out.println(YELLOW + "⚠ Skipped Links : " + skipped + RESET);
	    System.out.println("========================================");

 	    if (!brokenLinks.isEmpty()) {
	        Assert.fail("Broken links found: " + brokenLinks.size());
	    }
	}


	public void verifyAllApplicationLinksForReal404Fast() {
	    String GREEN  = "\u001B[32m";
	    String RED    = "\u001B[31m";
	    String YELLOW = "\u001B[33m";
	    String CYAN   = "\u001B[36m";
	    String RESET  = "\u001B[0m";

	    System.out.println(CYAN + "🔍 Starting REAL 404 validation (Fast Mode)..." + RESET);

	    driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());

	    List<WebElement> linkElements = driver.findElements(By.tagName("a"));
	    List<String> allUrls = new ArrayList<>();
	    List<String> brokenUrls = new ArrayList<>();

	    int skippedCount = 0;
	    int okCount = 0;

	    for (WebElement link : linkElements) {
	        String href = link.getAttribute("href");
	        if (href != null && !href.isEmpty()) {
	            allUrls.add(href.trim());
	        }
	    }

	    long startTime = System.currentTimeMillis();

	    for (String url : allUrls) {
 	        if (url.startsWith("javascript") || url.startsWith("mailto") || url.startsWith("tel") || url.contains("#")) {
	            System.out.println(YELLOW + "⚠ Skipped: " + url + RESET);
	            skippedCount++;
	            continue;
	        }

	        try {
	            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
	            connection.setRequestMethod("GET");
	            connection.setConnectTimeout(5000); // 5 seconds
	            connection.setReadTimeout(5000);
	            connection.connect();

	            int code = connection.getResponseCode();
	            if (code >= 400) {
	                System.out.println(RED + "❌ BROKEN PAGE (HTTP " + code + "): " + url + RESET);
	                brokenUrls.add(url);
	            } else {
	                System.out.println(GREEN + "✅ OK (HTTP " + code + "): " + url + RESET);
	                okCount++;
	            }

	        } catch (Exception e) {
	            System.out.println(RED + "❌ ERROR opening: " + url + RESET);
	            brokenUrls.add(url);
	        }
	    }

	    long totalTime = System.currentTimeMillis() - startTime;

 	    System.out.println(CYAN + "\n──────────── SUMMARY ────────────" + RESET);
	    System.out.println(GREEN + "✅ OK Links     : " + okCount + RESET);
	    System.out.println(RED   + "❌ Broken Links : " + brokenUrls.size() + RESET);
	    System.out.println(YELLOW+ "⚠ Skipped      : " + skippedCount + RESET);
	    System.out.println(CYAN  + "⏱ Time Taken   : " + totalTime + " ms" + RESET);
	    System.out.println(CYAN + "────────────────────────────────" + RESET);

	    if (!brokenUrls.isEmpty()) {
	        Assert.fail("Broken pages found: " + brokenUrls.size());
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
	
	
	public void ValidateAllLinkMethods() {
		
		handleAccessCodeIfPresentFast();
		
		verifyAllLinksAndUrls();
		
		verifyAllLinksAndPrintStatus();
		
		verifyLinksByClick_No404();
		
	//	verifyAllApplicationLinksForReal404Fast();
		
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

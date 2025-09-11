package stepDef;

import context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AdminPanelPage;
import utils.Common;


public class AdminPanelStepDef {
	
	TestContext testContext;
	AdminPanelPage admin;
	private String capturedSku;


	public AdminPanelStepDef(TestContext context) {
		testContext = context;
		admin = testContext.getPageObjectManager().getAdminPanelPage();

	}

	@Given("I upload an image {string} in admin panel")
	public void i_upload_an_image_in_admin_panel(String imageName) {
		String imagePath = System.getProperty("user.dir") + "/src/test/resources/images/" + imageName;
		admin.uploadImage(imagePath);

	}

	@When("I verify that the homepage first banner is {string}")
	public void i_verify_that_the_homepage_first_banner_is(String expectedBannerTitle) {
		admin.verifyBannerOnHomePage();
	}

	
	// Verify Top Selling
	@Given("admin is logged in")
	public void admin_is_logged_in() {
		admin.adminLogin();

	}

	@When("amdin copies the SKU for Product")
	public void amdin_copies_the_sku_for_product() {
		admin.givesProductName();
		capturedSku = admin.fetchSkuFromProduct();

	}

	@When("admin puts this SKU at first position in Top Selling")
	public void admin_puts_this_sku_at_first_position_in_top_selling() {
		admin.putSkuIntoTopSelling(capturedSku);

	}

	@Then("the product should appear in Top Selling on the user panel")
	public void the_product_should_appear_in_top_selling_on_the_user_panel() throws InterruptedException {
		// pull product name from Excel map
		String productName = Common.getValueFromTestDataMap("ProductListingName");
		admin.verifyProductShowInTopSelling(productName);
	}

	
	// Verify New Arrivals

	@When("the admin verifies the colour of the product at the first position")
	public void the_admin_verifies_the_colour_of_the_product_at_the_first_position() {
		admin.verifyColourOfTheProductIsFirstPosition();

	}

	@When("the admin adds this product to the New Arrivals section")
	public void the_admin_adds_this_product_to_the_new_arrivals_section() throws InterruptedException {
		admin.addTheProductInNewArrivalSection();

	}

	@When("the admin sorts this product to the first position in New Arrivals")
	public void the_admin_sorts_this_product_to_the_first_position_in_new_arrivals() {
		admin.sortTheProductInFirstPosition();

	}

	@Then("the product should appear in the New Arrivals section on the user application")
	public void the_product_should_appear_in_the_new_arrivals_section_on_the_user_application() {
		String productName = Common.getValueFromTestDataMap("ProductListingName");
		admin.verifyProductShowInNewArrivalsSction(productName);

	}
	

	// Catagory

	@When("I update the category banner with image {string}")
	public void iUpdateTheCategoryBanner(String imageName) {
		String imagePathCatagory = System.getProperty("user.dir") + "/src/test/resources/images/" + imageName;
		admin.updateCategoryBanner(imagePathCatagory);
	}

	// Step definition for verifying banner in User Application
	@Then("I should see the updated banner in the user application")
	public void iShouldSeeTheUpdatedBannerInUserApp() {
		admin.verifyBannerUserApp();
	}






			
				





	
				
	
	
	
	
	


}

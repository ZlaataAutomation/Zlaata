package stepDef;

import context.TestContext;
import io.cucumber.java.en.Given;
import pages.landingPage;

public class landingStepDef {




	TestContext testContext;
	landingPage land;

	public landingStepDef(TestContext context) {
		testContext = context;
		land = testContext.getPageObjectManager().getLandingPage();
	}

	@Given("User clicks on zlaata logo")
	public void user_clicks_on_zlaata_logo() {
		land.verifythatLogoRedirectionInLandingPage();

	}


	@Given("User clicks all  menu in hamburger drop down")
	public void user_clicks_all_menu_in_hamburger_drop_down() throws InterruptedException {

		land.verifyhamburgerdropdownAllMenu();

	}


	@Given("User clicks all shop now buttons on lading page")
	public void user_clicks_all_shop_now_buttons_on_lading_page() {

		land.verifyUserCanabletoClikShopButtonforZlaata_IndiaandBoss_lady();
	}



		@Given("User is on landing page, if cart button is redirected")
	public void user_is_on_landing_page_if_cart_button_is_redirected() {
		land.verifycartButtonRedirection();
	}








	@Given("User is on landing page, if  Profile  button is redirected")
	public void user_is_on_landing_page_if_profile_button_is_redirected() {
		land.verifyProfileiconRedirection();
	}




	@Given("User is on the landing page and checks for Flash Sale notification")
	public void user_is_on_the_landing_page_and_checks_for_flash_sale_notification() {
		land.verifyFlashSaleSection();
	}



	@Given("User is on the landing page and verifies the close button is displayed in the dropdown and categories are displayed")
	public void user_is_on_the_landing_page_and_verifies_the_close_button_is_displayed_in_the_dropdown_and_categories_are_displayed() {
		land.verifyhamburgeiconCloseButtonandPrintThedropdowncategoryname();
	}







}

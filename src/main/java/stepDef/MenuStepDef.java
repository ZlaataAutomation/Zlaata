package stepDef;

import context.TestContext;
import pages.HomePage;
import pages.Menus;

import io.cucumber.java.en.Given;

public class MenuStepDef {

	TestContext testContext;
	Menus menu;
	HomePage home;

	public MenuStepDef(TestContext context) {
		testContext = context;
		menu = testContext.getPageObjectManager().getMenus();
		testContext = context;
		home=testContext.getPageObjectManager().getHomePage();
	}




@Given("User clicks on All  Header menu")
		public void user_clicks_on_all_header_menu() throws InterruptedException {
	menu.validateAllHeaderMenus();
		}



@Given("User clicks on newAriivals header suggestion all products.")
public void user_clicks_on_new_ariivals_header_suggestion_all_products() {
	menu.validateNewArrivalSuggestions();
	
	
}


@Given("User clicks on shop all category,Collections and Styles.")
public void user_clicks_on_shop_all_category_collections_and_styles() throws InterruptedException {
	menu.validateShopAllCategories_CollectionsAndStyle();
}


@Given("User clicks on Boss lady menu  All Suggestions")
public void user_clicks_on_boss_lady_menu_all_suggestions() {
	menu.validateBossLadySuggestions();
}





































































	@Given("User clicks on newAriivals header")
	public void user_clicks_on_new_ariivals_header() {
		home.homeLaunch();
		menu.clickNewArrival();
	}

	@Given("User clicks on new arrivals suggestion")
	public void user_clicks_on_new_arrivals_suggestion() {
		home.homeLaunch();
		menu.newArrivalSuggestion();
	}


	@Given("User clicks on sale menu")
	public void user_clicks_on_sale_menu() {
		home.homeLaunch();
		menu.saleMenu();
	}



	@Given("User clicks on boss lady suggestions")
	public void user_clicks_on_boss_lady_suggestions() {
		home.homeLaunch();
		menu.bossLady();
	}



	@Given("User clicks on get update")
	public void user_clicks_on_get_update() {
		home.homeLaunch();
		menu.getUpdates();
		
	}



	@Given("User clicks on shop category")
	public void user_clicks_on_shop_category() {
		home.homeLaunch();
		menu.shopCategory();
	}


	@Given("User clicks on shop collection")
	public void user_clicks_on_shop_collection() {
		home.homeLaunch();
		menu.shopCollection();
	}



	@Given("User clicks on shop styles")
	public void user_clicks_on_shop_styles() {
		home.homeLaunch();
		menu.shopStyles();
	}

	@Given("User clicks on Pop shop")
	public void user_clicks_on_pop_shop() {
		home.homeLaunch();
		menu.PopShop();
	}
















}

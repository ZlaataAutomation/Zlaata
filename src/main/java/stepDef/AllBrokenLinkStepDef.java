package stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import pages.AllBrokenLinkPage;

public class AllBrokenLinkStepDef {
	TestContext testContext;
	AllBrokenLinkPage link;
	

	public AllBrokenLinkStepDef(TestContext context) {
		testContext = context;
		link = testContext.getPageObjectManager().getAllBrokenLinkPage();
	}
	


		@Given("all application links and URLs are validated for broken links")
		public void all_application_links_and_ur_ls_are_validated_for_broken_links() {
			link.ValidateAllLinkMethods();
		}




}

Feature: All Broken Links

  #===========================================================================
  # Test Case ID :: TC_UI_Zlaata_Link_01
  #===========================================================================
  # Scenario Description: Complete All Broken Link page
  # Expected:All Broken Link
  #===========================================================================
  @Sanity
  @TC_UI_Zlaata_Link_01  
Scenario Outline: TC_UI_Zlaata_Link_01 |Verify that all application links and URLs are not broken.| "<TD_ID>"  
  Given all application links and URLs are validated for broken links

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Link_01   |
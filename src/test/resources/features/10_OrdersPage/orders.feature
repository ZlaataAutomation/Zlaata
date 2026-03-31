Feature: This is My orders feature

  #===========================================================================
  #Test case ID :: TC_UI_Zlaata_Orders_01
  #===========================================================================
  #ScenarioDescription : Complete Orders
  #Expected: Orders sanity 
  #============================================================================
  @Sanity
  @Order
  @TC_UI_Zlaata_Orders_01
  Scenario Outline: TC_UI_Zlaata_Orders_01 |Verify user placing order and verifies all calculations|"<TD_ID>"
     
     Given User placing an order and verifing calculations including check out page
    

    Examples: 
      | TD_ID                  |
      | TD_UI_Zlaata_Orders_01 |
       
         @Sanity
         @Order
    @TC_UI_Zlaata_Orders_02
  Scenario Outline: TC_UI_Zlaata_Orders_02 |Verify user Cancelling order and all labels.|"<TD_ID>"
     
     Given User Cancelling an order and labels
    

    Examples: 
      | TD_ID                  |
      | TD_UI_Zlaata_Orders_02 | 
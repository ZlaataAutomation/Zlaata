Feature: This is Landing Page  feature

  #===========================================================================
  #Test case ID :: TC_UI_Zlaata_Landing_01
  #===========================================================================
  #ScenarioDescription : Complete  Landing Page
  #Expected: Menu sanity 
  #============================================================================
  
  @Sanity
@land
  @TC_UI_Zlaata_Landing_01
  Scenario Outline: TC_UI_Zlaata_Landing_01 |Verify that the user is able to click Zlaata log in landing page .|"<TD_ID>"
     
     Given User clicks on zlaata logo
    

    Examples: 
      | TD_ID                  |
      | TD_UI_Zlaata_Landing_01 |
      
      @Sanity
      @land
        @TC_UI_Zlaata_Landing_02
  Scenario Outline: TC_UI_Zlaata_Landing_02 |Verify that the user is able to click all zlaata India and Boss lady  menu in hamburger drop down .|"<TD_ID>"
     
     Given User clicks all  menu in hamburger drop down
    

    Examples: 
      | TD_ID                  |
      | TD_UI_Zlaata_Landing_02 |
      @Sanity
            @land
      
        @TC_UI_Zlaata_Landing_03
  Scenario Outline: TC_UI_Zlaata_Landing_03 |Verify that the user is able to click all zlaata India and Boss lady shop Now buttons .|"<TD_ID>"
     
     Given User clicks all shop now buttons on lading page
    

    Examples: 
      | TD_ID                  |
      | TD_UI_Zlaata_Landing_03 |
   @land    
 @Sanity     
@TC_UI_Zlaata_Landing_04
Scenario Outline: TC_UI_Zlaata_Landing_04 |Verify user can click cart icon on landing page | "<TD_ID>"

  Given User is on landing page, if cart button is redirected

Examples:
  | TD_ID                   |
  | TD_UI_Zlaata_Landing_04 |
  @land 
@Sanity  
@TC_UI_Zlaata_Landing_05
Scenario Outline: TC_UI_Zlaata_Landing_05 |Verify user can click Profile  icon on landing page | "<TD_ID>"

  Given User is on landing page, if  Profile  button is redirected

Examples:
  | TD_ID                   |
  | TD_UI_Zlaata_Landing_05 |
  @land 
 @Sanity 
@TC_UI_Zlaata_Landing_06
Scenario Outline: TC_UI_Zlaata_Landing_06 | Verify whether Flash Sale notification is available on the landing page | "<TD_ID>"

  Given User is on the landing page and checks for Flash Sale notification

Examples:
  | TD_ID                   |
  | TD_UI_Zlaata_Landing_06 |   
  
 @land 
@Sanity 
@TC_UI_Zlaata_Landing_07
Scenario Outline: TC_UI_Zlaata_Landing_07 | Verify Close button and categories menu on landing page | "<TD_ID>"

  Given User is on the landing page and verifies the close button is displayed in the dropdown and categories are displayed

Examples:
  | TD_ID                   |
  | TD_UI_Zlaata_Landing_07 |
  
  
  
          
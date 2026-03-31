Feature: This is Footer Section feature

  #===========================================================================
  #Test case ID :: TC_UI_Zlaata_FS_01
  #===========================================================================
  #ScenarioDescription : Complete Footer Section
  #Expected: footer section sanity 
  #============================================================================
  
  @Sanity
  @Footer
  @TC_UI_Zlaata_FS_01
  Scenario Outline: TC_UI_Zlaata_FS_01 |Verify that About Us Content details and URL are displayed in the footer.| "<TD_ID>"
    Given User verifies About Us details are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_01  |
      
 @Sanity
  @Footer
@TC_UI_Zlaata_FS_02
  Scenario Outline: TC_UI_Zlaata_FS_02 |Verify that Blogs Content details and URL are displayed in the footer.| "<TD_ID>"
    Given User verifies Blogs details are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_02  |
      
   @Sanity
  @Footer
@TC_UI_Zlaata_FS_03
  Scenario Outline: TC_UI_Zlaata_FS_03 |Verify that Pop Shop Content details and URL are displayed in the footer.| "<TD_ID>"
    Given User verifies Pop Shop details are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_03  |    
      
      @Sanity
  @Footer
@TC_UI_Zlaata_FS_04
  Scenario Outline: TC_UI_Zlaata_FS_04 |Verify that Terms & Conditions Content details and URL are displayed in the footer.| "<TD_ID>"
    Given User verifies Terms & Conditions details are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_04  |    
      
    @Sanity
  @Footer
@TC_UI_Zlaata_FS_05
  Scenario Outline: TC_UI_Zlaata_FS_05 |Verify that Privacy Policy Content details and URL are displayed in the footer.| "<TD_ID>"
    Given User verifies Privacy Policy details are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_05  |       
      
      
      @Sanity
  @Footer
@TC_UI_Zlaata_FS_06
  Scenario Outline: TC_UI_Zlaata_FS_06 |Verify that Raise a query Content details and URL are displayed in the footer.| "<TD_ID>"
    Given User verifies Raise a query  details are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_06  |         
      
   @Sanity
     @Footer
@TC_UI_Zlaata_FS_07
  Scenario Outline: TC_UI_Zlaata_FS_07 |Verify that Faq Content details and URL are displayed in the footer.| "<TD_ID>"
    Given User verifies Faq  details are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_07  |       
      

@TC_UI_Zlaata_FS_08
  Scenario Outline: TC_UI_Zlaata_FS_08 |Verify that Shipping & Cancellation Policy Content details and URL are displayed in the footer.| "<TD_ID>"
    Given User verifies Shipping & Cancellation Policy  details are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_08  |       
      
     
   
@TC_UI_Zlaata_FS_09
  Scenario Outline: TC_UI_Zlaata_FS_09 |Verify that Return, Exchange & Replacement Policy Content details and URL are displayed in the footer.| "<TD_ID>"
    Given User verifies Return, Exchange & Replacement Policy  details are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_09  |        
      

@TC_UI_Zlaata_FS_10
  Scenario Outline: TC_UI_Zlaata_FS_10 |Verify that Contact Us Content details Time, Mobile, Email and Address are displayed in the footer.| "<TD_ID>"
    Given User verifies Contact Us  details are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_10  |      
      
   @Sanity
  @Footer
@TC_UI_Zlaata_FS_11
  Scenario Outline: TC_UI_Zlaata_FS_11 |Verify that Track Order are displayed in the footer.| "<TD_ID>"
    Given User verifies Track Order   are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_11  |      
      
         @Sanity
  @Footer
   @TC_UI_Zlaata_FS_12
  Scenario Outline: TC_UI_Zlaata_FS_12 | Verify that social media icons are displayed in the footer. | "<TD_ID>"
    Given User verifies social media icons are visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_12  |    
      
         @Sanity
  @Footer
   @TC_UI_Zlaata_FS_13
  Scenario Outline: TC_UI_Zlaata_FS_13 | Verify that the payment logo is displayed in the footer. | "<TD_ID>"
    Given User verifies the payment logo is visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_13  |    
      
        @Sanity
  @Footer
   @TC_UI_Zlaata_FS_14
  Scenario Outline: TC_UI_Zlaata_FS_14 | Verify that Copy Rights is displayed in the footer.| "<TD_ID>"
    Given User verifies Copy Rights is visible in footer

    Examples:
      | TD_ID                |
      | TD_UI_Zlaata_FS_14  |        
   
    @Sanity
  @Footer   
  @TC_UI_Zlaata_FS_15 
      Scenario Outline: TC_UI_Zlaata_FS_15 |Verify that user can subscribe to the newsletter field.| "<TD_ID>"
      Given User the newsletter subscription field

Examples:
  | TD_ID               |
  | TD_UI_Zlaata_FS_15  |    
      
 @Sanity     
@Footer
@TC_UI_Zlaata_FS_16
Scenario Outline: TC_UI_Zlaata_FS_16 | Verify that the Loyalty Points link and URL are displayed correctly in the footer. | "<TD_ID>"
  Given User verifies that the Loyalty Points link in the footer navigates to the correct page

Examples:
  | TD_ID              |
  | TD_UI_Zlaata_FS_16 |
@Sanity
@Footer
@TC_UI_Zlaata_FS_17
Scenario Outline: TC_UI_Zlaata_FS_17 |Verify that the Gift Card link and URL are displayed correctly in the footer.| "<TD_ID>"
  Given User verifies that the Gift Card link in the footer redirects to the correct page

Examples:
  | TD_ID              |
  | TD_UI_Zlaata_FS_17 |

      

  
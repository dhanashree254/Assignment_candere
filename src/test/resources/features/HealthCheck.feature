@ui @healthcheck

Feature: E-commerce Project Web Site Health Check

@Requirement1
Scenario: User visit the application and validate the landing page
Given User navigated to the landing page of the applicaton
Then Validating title "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"

@Requirement2
Scenario: User visit the application and Search for the product
Given User navigated to the landing page of the applicaton
And Validating title "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
When User search for product "majestic solitaire diamond ring"
Then Search Result page is displayed as "Majestic Solitaire Diamond Ring"


@Requirement3
Scenario: User clicked on search product and select the size
Given User navigated to the landing page of the applicaton
And Validating title "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
When User search for product "majestic solitaire diamond ring"
And Search Result page is displayed as "Majestic Solitaire Diamond Ring"
Then Click on displayed product and validate title of page "Majestic Solitaire Diamond Ring"
And User select size of product and validate "18"
Then Displayed with text as "Price updated"

@Requirement4
Scenario: User scroll down the page and see the about us section
Given User navigated to the landing page of the applicaton
And Validating title "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
When Scroll down the page
And Validating "ABOUT US" Section
Then Validating List of link below about us
|     About Our Company    |
|     Terms and Conditions |
|     Privacy Policy       |
|     FAQ                  |
|     Offers T&Cs          |
|     Customer Reviews     |

@Requirement5
Scenario Outline: User scroll down the page and validate the all social media handles

Given User navigated to the landing page of the applicaton
And Validating title "Online Jewellery Shopping India | Candere By Kalyan Jewellers | Most Trusted Online Jewellery Store"
When Scroll down the page
And Validating footer "FOLLOW US" Section
And Clicking on social medialink "<MediaLink>"
Then Validating the All social media links With Expected "<Expected>" and "<Pagetext>"

Examples:
|     MediaLink     |     Expected      |          Pagetext             |
|     facebook      | canderejewellery  |  Candere by Kalyan Jewellers  |
|     instagram     | canderejewellery  |       canderejewellery        |
|     twitter       |  canderebyKalyan  |  Candere By Kalyan jewellers  |
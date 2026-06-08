Feature: Complete Checkout Process

  @Checkout @E2E
  Scenario: TC-005 - Complete End-to-End Checkout Process
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have added a product to my cart
    When I navigate to the shopping cart
    And I click the checkout button
    And I enter first name "John"
    And I enter last name "Doe"
    And I enter postal code "12345"
    And I click the continue button
    Then I should see the order summary
    When I click the finish button
    Then I should see the order confirmation message "Thank you for your order!"

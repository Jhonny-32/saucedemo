Feature: Filter and Select Products

  @Products
  Scenario: TC-003 - Filter Products by Price and Select an Item
    Given I am logged in as "standard_user" with password "secret_sauce"
    When I select "Price (low to high)" from the sort dropdown
    And I click on the first product
    Then I should see the product detail page

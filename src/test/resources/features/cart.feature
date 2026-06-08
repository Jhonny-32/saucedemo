Feature: Shopping Cart Management

  @Cart
  Scenario: TC-004 - Add Product to Shopping Cart
    Given I am logged in as "standard_user" with password "secret_sauce"
    When I click "Add to cart" on the first product
    Then I should see the cart badge showing "1"
    And the product should be in my cart

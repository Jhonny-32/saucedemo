Feature: Login to SauceDemo Application

  @Login @Positive
  Scenario: TC-001 - Successful Login with Valid Credentials
    Given I navigate to SauceDemo
    When I enter username "standard_user"
    And I enter password "secret_sauce"
    And I click the login button
    Then I should be logged in successfully
    And I should see the products page

  @Login @Negative
  Scenario: TC-002 - Login Failure with Invalid Credentials
    Given I navigate to SauceDemo
    When I enter username "invalid_user"
    And I enter password "wrong_password"
    And I click the login button
    Then I should see an error message
    And I should remain on the login page
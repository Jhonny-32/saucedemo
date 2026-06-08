package com.com.saucedemo.steps;

import com.com.saucedemo.function.TextContext;
import com.com.saucedemo.page.LoginPage;
import com.com.saucedemo.page.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/** Step definitions for login-related scenarios (TC-001, TC-002). */
public class LoginSteps {

    /** Returns a fresh LoginPage bound to the current driver. */
    private LoginPage loginPage() {
        return new LoginPage(TextContext.driver);
    }

    /** Verifies the login page is visible (browser is already opened by Hooks). */
    @Given("I navigate to SauceDemo")
    public void iNavigateToSauceDemo() {
        loginPage().verifyLoginPageLoaded();
    }

    /** Performs a full login with the supplied credentials; used as a shared precondition. */
    @Given("I am logged in as {string} with password {string}")
    public void iAmLoggedInAs(String username, String password) {
        loginPage().enterUsername(username);
        loginPage().enterPassword(password);
        loginPage().clickLoginButton();
    }

    /** Types the given username into the login form. */
    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage().enterUsername(username);
    }

    /** Types the given password into the login form. */
    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage().enterPassword(password);
    }

    /** Clicks the Login button to submit the credentials. */
    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage().clickLoginButton();
    }

    /** Asserts that login was successful by verifying the Products page is shown. */
    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        loginPage().verifySuccessfulLogin();
    }

    /** Verifies the Products page title is visible. */
    @And("I should see the products page")
    public void iShouldSeeTheProductsPage() {
        new ProductsPage(TextContext.driver).verifyProductsPageLoaded();
    }

    /** Verifies the error banner is displayed after a failed login attempt. */
    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        loginPage().verifyErrorMessage();
    }

    /** Asserts the login button is still present, confirming the user was not redirected. */
    @And("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        loginPage().verifyRemainsOnLoginPage();
    }
}

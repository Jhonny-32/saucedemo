package com.com.saucedemo.steps;

import com.com.saucedemo.page.LoginPage;
import com.com.saucedemo.page.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    private LoginPage loginPage() {
        return new LoginPage();
    }

    @Given("I navigate to SauceDemo")
    public void iNavigateToSauceDemo() {
        loginPage().verifyLoginPageLoaded();
    }

    @Given("I am logged in as {string} with password {string}")
    public void iAmLoggedInAs(String username, String password) {
        loginPage().enterUsername(username);
        loginPage().enterPassword(password);
        loginPage().clickLoginButton();
    }

    @When("I enter username {string}")
    public void iEnterUsername(String username) {
        loginPage().enterUsername(username);
    }

    @And("I enter password {string}")
    public void iEnterPassword(String password) {
        loginPage().enterPassword(password);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage().clickLoginButton();
    }

    @Then("I should be logged in successfully")
    public void iShouldBeLoggedInSuccessfully() {
        loginPage().verifySuccessfulLogin();
    }

    @And("I should see the products page")
    public void iShouldSeeTheProductsPage() {
        new ProductsPage().verifyProductsPageLoaded();
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        loginPage().verifyErrorMessage();
    }

    @And("I should remain on the login page")
    public void iShouldRemainOnTheLoginPage() {
        loginPage().verifyRemainsOnLoginPage();
    }
}

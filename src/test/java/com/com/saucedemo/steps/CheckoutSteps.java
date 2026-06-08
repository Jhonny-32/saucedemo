package com.com.saucedemo.steps;

import com.com.saucedemo.function.TextContext;
import com.com.saucedemo.page.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps {

    private CheckoutPage checkoutPage() {
        return new CheckoutPage(TextContext.driver);
    }

    @And("I enter first name {string}")
    public void iEnterFirstName(String firstName) {
        checkoutPage().enterFirstName(firstName);
    }

    @And("I enter last name {string}")
    public void iEnterLastName(String lastName) {
        checkoutPage().enterLastName(lastName);
    }

    @And("I enter postal code {string}")
    public void iEnterPostalCode(String postalCode) {
        checkoutPage().enterPostalCode(postalCode);
    }

    @And("I click the continue button")
    public void iClickTheContinueButton() {
        checkoutPage().clickContinue();
    }

    @Then("I should see the order summary")
    public void iShouldSeeTheOrderSummary() {
        checkoutPage().verifyOrderSummary();
    }

    @When("I click the finish button")
    public void iClickTheFinishButton() {
        checkoutPage().clickFinish();
    }

    @Then("I should see the order confirmation message {string}")
    public void iShouldSeeTheOrderConfirmationMessage(String message) {
        checkoutPage().verifyOrderConfirmation(message);
    }
}

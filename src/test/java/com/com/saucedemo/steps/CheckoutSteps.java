package com.com.saucedemo.steps;

import com.com.saucedemo.function.TextContext;
import com.com.saucedemo.page.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/** Step definitions for the checkout information form and order confirmation (TC-005). */
public class CheckoutSteps {

    /** Returns a fresh CheckoutPage bound to the current driver. */
    private CheckoutPage checkoutPage() {
        return new CheckoutPage(TextContext.driver);
    }

    /** Enters the given first name in the checkout form. */
    @And("I enter first name {string}")
    public void iEnterFirstName(String firstName) {
        checkoutPage().enterFirstName(firstName);
    }

    /** Enters the given last name in the checkout form. */
    @And("I enter last name {string}")
    public void iEnterLastName(String lastName) {
        checkoutPage().enterLastName(lastName);
    }

    /** Enters the given postal code in the checkout form. */
    @And("I enter postal code {string}")
    public void iEnterPostalCode(String postalCode) {
        checkoutPage().enterPostalCode(postalCode);
    }

    /** Submits the checkout form by clicking Continue. */
    @And("I click the continue button")
    public void iClickTheContinueButton() {
        checkoutPage().clickContinue();
    }

    /** Verifies the order summary block and total are visible on the review screen. */
    @Then("I should see the order summary")
    public void iShouldSeeTheOrderSummary() {
        checkoutPage().verifyOrderSummary();
    }

    /** Clicks the Finish button to complete the purchase. */
    @When("I click the finish button")
    public void iClickTheFinishButton() {
        checkoutPage().clickFinish();
    }

    /** Asserts the confirmation header matches the expected success message. */
    @Then("I should see the order confirmation message {string}")
    public void iShouldSeeTheOrderConfirmationMessage(String message) {
        checkoutPage().verifyOrderConfirmation(message);
    }
}

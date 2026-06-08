package com.com.saucedemo.steps;

import com.com.saucedemo.function.TextContext;
import com.com.saucedemo.page.CartPage;
import com.com.saucedemo.page.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

/** Step definitions for shopping cart and checkout navigation scenarios (TC-005). */
public class CartSteps {

    /** Returns a fresh ProductsPage bound to the current driver. */
    private ProductsPage productsPage() {
        return new ProductsPage(TextContext.driver);
    }

    /** Returns a fresh CartPage bound to the current driver. */
    private CartPage cartPage() {
        return new CartPage(TextContext.driver);
    }

    /** Clicks "Add to cart" on the first product as a background precondition. */
    @Given("I have added a product to my cart")
    public void iHaveAddedAProductToMyCart() {
        productsPage().clickAddToCartOnFirstProduct();
    }

    /** Clicks the cart icon and verifies the cart page is loaded. */
    @When("I navigate to the shopping cart")
    public void iNavigateToTheShoppingCart() {
        productsPage().navigateToCart();
    }

    /** Clicks the Checkout button on the cart page to begin the checkout flow. */
    @And("I click the checkout button")
    public void iClickTheCheckoutButton() {
        cartPage().clickCheckout();
    }
}

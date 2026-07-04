package com.com.saucedemo.steps;

import com.com.saucedemo.page.CartPage;
import com.com.saucedemo.page.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CartSteps {

    private ProductsPage productsPage() {
        return new ProductsPage();
    }

    private CartPage cartPage() {
        return new CartPage();
    }

    @Given("I have added a product to my cart")
    public void iHaveAddedAProductToMyCart() {
        productsPage().clickAddToCartOnFirstProduct();
    }

    @When("I navigate to the shopping cart")
    public void iNavigateToTheShoppingCart() {
        productsPage().navigateToCart();
    }

    @And("I click the checkout button")
    public void iClickTheCheckoutButton() {
        cartPage().clickCheckout();
    }
}

package com.com.saucedemo.steps;

import com.com.saucedemo.page.ProductDetailPage;
import com.com.saucedemo.page.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductSteps {

    private ProductsPage productsPage() {
        return new ProductsPage();
    }

    @When("I select {string} from the sort dropdown")
    public void iSelectFromTheSortDropdown(String sortOption) {
        productsPage().selectSortOption(sortOption);
    }

    @And("I click on the first product")
    public void iClickOnTheFirstProduct() {
        productsPage().clickFirstProduct();
    }

    @Then("I should see the product detail page")
    public void iShouldSeeTheProductDetailPage() {
        new ProductDetailPage().verifyProductDetailPageLoaded();
    }

    @When("I click {string} on the first product")
    public void iClickOnTheFirstProductAction(String action) {
        productsPage().clickAddToCartOnFirstProduct();
    }

    @Then("I should see the cart badge showing {string}")
    public void iShouldSeeTheCartBadgeShowing(String count) {
        productsPage().verifyCartBadge(count);
    }

    @And("the product should be in my cart")
    public void theProductShouldBeInMyCart() {
        productsPage().navigateToCart();
    }
}

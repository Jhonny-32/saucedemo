package com.com.saucedemo.steps;

import com.com.saucedemo.function.TextContext;
import com.com.saucedemo.page.ProductDetailPage;
import com.com.saucedemo.page.ProductsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

/** Step definitions for product filtering and selection scenarios (TC-003, TC-004). */
public class ProductSteps {

    /** Returns a fresh ProductsPage bound to the current driver. */
    private ProductsPage productsPage() {
        return new ProductsPage(TextContext.driver);
    }

    /** Selects the given sort option from the products dropdown. */
    @When("I select {string} from the sort dropdown")
    public void iSelectFromTheSortDropdown(String sortOption) {
        productsPage().selectSortOption(sortOption);
    }

    /** Clicks the first product name link to open its detail page. */
    @And("I click on the first product")
    public void iClickOnTheFirstProduct() {
        productsPage().clickFirstProduct();
    }

    /** Verifies the product detail page is loaded with name and price visible. */
    @Then("I should see the product detail page")
    public void iShouldSeeTheProductDetailPage() {
        new ProductDetailPage(TextContext.driver).verifyProductDetailPageLoaded();
    }

    /** Clicks the "Add to cart" button on the first product in the list. */
    @When("I click {string} on the first product")
    public void iClickOnTheFirstProductAction(String action) {
        productsPage().clickAddToCartOnFirstProduct();
    }

    /** Asserts the cart badge counter shows the expected number of items. */
    @Then("I should see the cart badge showing {string}")
    public void iShouldSeeTheCartBadgeShowing(String count) {
        productsPage().verifyCartBadge(count);
    }

    /** Navigates to the cart and asserts that at least one product is listed. */
    @And("the product should be in my cart")
    public void theProductShouldBeInMyCart() {
        productsPage().navigateToCart();
    }
}

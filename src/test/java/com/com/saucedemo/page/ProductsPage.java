package com.com.saucedemo.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement lbl_pageTitle;

    @FindBy(css = "[data-test='product-sort-container']")
    private WebElement ddl_sortContainer;

    @FindBy(className = "inventory_item")
    private List<WebElement> tbl_inventoryItems;

    @FindBy(className = "inventory_item_name")
    private List<WebElement> lbl_productNames;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement lbl_cartBadge;

    @FindBy(css = ".shopping_cart_link")
    private WebElement link_cart;

    /** Asserts that the page title equals "Products", confirming successful navigation. */
    public void verifyProductsPageLoaded() {
        try {
            waitForVisible(lbl_pageTitle);
            assertEquals("Products", lbl_pageTitle.getText(), "Products page title mismatch");
            captureScreenshot("products_page_loaded");
        } catch (Exception e) {
            throw new RuntimeException("Products page not loaded: " + e.getMessage());
        }
    }

    /** Selects the given option from the sort dropdown and asserts it is the active selection. */
    public void selectSortOption(String sortText) {
        try {
            highlightElement(ddl_sortContainer);
            Select select = new Select(ddl_sortContainer);
            select.selectByVisibleText(sortText);
            assertEquals(sortText, select.getFirstSelectedOption().getText(), "Sort option not selected correctly");
            captureScreenshot("sort_selected");
        } catch (Exception e) {
            throw new RuntimeException("Error selecting sort option: " + e.getMessage());
        }
    }

    /** Scrolls to and clicks the first product name link, navigating to its detail page. */
    public void clickFirstProduct() {
        try {
            assertFalse(lbl_productNames.isEmpty(), "No products found on page");
            WebElement firstProduct = lbl_productNames.get(0);
            scrollToElement(firstProduct);
            highlightElement(firstProduct);
            firstProduct.click();
            captureScreenshot("product_clicked");
        } catch (Exception e) {
            throw new RuntimeException("Error clicking product: " + e.getMessage());
        }
    }

    /** Clicks the "Add to cart" button on the first product in the inventory list. */
    public void clickAddToCartOnFirstProduct() {
        try {
            assertFalse(tbl_inventoryItems.isEmpty(), "No products found on page");
            WebElement firstItem = tbl_inventoryItems.get(0);
            WebElement addToCartBtn = firstItem.findElement(
                    org.openqa.selenium.By.cssSelector(".btn_primary.btn_inventory"));
            scrollToElement(addToCartBtn);
            highlightElement(addToCartBtn);
            addToCartBtn.click();
            captureScreenshot("add_to_cart_clicked");
        } catch (Exception e) {
            throw new RuntimeException("Error adding product to cart: " + e.getMessage());
        }
    }

    /** Asserts that the cart badge counter matches the expected count. */
    public void verifyCartBadge(String expectedCount) {
        try {
            waitForVisible(lbl_cartBadge);
            highlightElement(lbl_cartBadge);
            assertEquals(expectedCount, lbl_cartBadge.getText(), "Cart badge count mismatch");
            captureScreenshot("cart_badge_verified");
        } catch (Exception e) {
            throw new RuntimeException("Error verifying cart badge: " + e.getMessage());
        }
    }

    /** Clicks the shopping cart icon and returns the CartPage. */
    public void navigateToCart() {
        try {
            highlightElement(link_cart);
            link_cart.click();
            captureScreenshot("navigated_to_cart");
        } catch (Exception e) {
            throw new RuntimeException("Error navigating to cart: " + e.getMessage());
        }
    }
}

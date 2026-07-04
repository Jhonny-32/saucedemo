package com.com.saucedemo.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CartPage extends BasePage {

    @FindBy(css = ".title")
    private WebElement lbl_cartTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> tbl_cartItems;

    @FindBy(id = "checkout")
    private WebElement btn_checkout;

    @FindBy(id = "continue-shopping")
    private WebElement btn_continueShopping;

    /** Asserts that the page title equals "Your Cart", confirming navigation to the cart. */
    public void verifyCartPageLoaded() {
        try {
            waitForVisible(lbl_cartTitle);
            assertEquals("Your Cart", lbl_cartTitle.getText(), "Cart page title mismatch");
            captureScreenshot("cart_page_loaded");
        } catch (Exception e) {
            throw new RuntimeException("Cart page not loaded: " + e.getMessage());
        }
    }

    /** Asserts that the cart contains at least one item and that it is visible. */
    public void verifyProductInCart() {
        try {
            assertFalse(tbl_cartItems.isEmpty(), "Cart is empty, no products found");
            WebElement firstItem = tbl_cartItems.get(0);
            highlightElement(firstItem);
            assertTrue(firstItem.isDisplayed(), "Cart item not visible");
            captureScreenshot("product_in_cart_verified");
        } catch (Exception e) {
            throw new RuntimeException("Error verifying product in cart: " + e.getMessage());
        }
    }

    /** Clicks the Checkout button and returns the CheckoutPage. */
    public void clickCheckout() {
        try {
            highlightElement(btn_checkout);
            btn_checkout.click();
            captureScreenshot("checkout_clicked");
        } catch (Exception e) {
            throw new RuntimeException("Error clicking checkout: " + e.getMessage());
        }
    }
}

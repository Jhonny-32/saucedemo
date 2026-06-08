package com.com.saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDetailPage extends BasePage {

    @FindBy(css = ".inventory_details_name")
    private WebElement lbl_productName;

    @FindBy(css = ".inventory_details_price")
    private WebElement lbl_productPrice;

    @FindBy(css = ".btn_primary.btn_inventory")
    private WebElement btn_addToCart;

    @FindBy(css = ".inventory_details_img")
    private WebElement img_product;

    public ProductDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Asserts that the product name and price are visible, confirming the detail page loaded. */
    public void verifyProductDetailPageLoaded() {
        try {
            waitForVisible(lbl_productName);
            highlightElement(lbl_productName);
            assertTrue(lbl_productName.isDisplayed(), "Product name not visible on detail page");
            assertTrue(lbl_productPrice.isDisplayed(), "Product price not visible on detail page");
            captureScreenshot("product_detail_page_loaded");
        } catch (Exception e) {
            throw new RuntimeException("Product detail page not loaded: " + e.getMessage());
        }
    }
}

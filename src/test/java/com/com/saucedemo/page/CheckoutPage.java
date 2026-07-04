package com.com.saucedemo.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    private WebElement ipt_firstName;

    @FindBy(id = "last-name")
    private WebElement ipt_lastName;

    @FindBy(id = "postal-code")
    private WebElement ipt_postalCode;

    @FindBy(id = "continue")
    private WebElement btn_continue;

    @FindBy(css = ".summary_info")
    private WebElement lbl_summaryInfo;

    @FindBy(css = ".summary_total_label")
    private WebElement lbl_totalLabel;

    @FindBy(id = "finish")
    private WebElement btn_finish;

    @FindBy(css = ".complete-header")
    private WebElement lbl_completeHeader;

    @FindBy(css = ".complete-text")
    private WebElement lbl_completeText;

    /** Types the given first name into the checkout form and asserts the field value. */
    public void enterFirstName(String firstName) {
        try {
            highlightElement(ipt_firstName);
            ipt_firstName.clear();
            ipt_firstName.sendKeys(firstName);
            assertEquals(firstName, ipt_firstName.getAttribute("value"), "First name not entered correctly");
            captureScreenshot("first_name_entered");
        } catch (Exception e) {
            throw new RuntimeException("Error entering first name: " + e.getMessage());
        }
    }

    /** Types the given last name into the checkout form and asserts the field value. */
    public void enterLastName(String lastName) {
        try {
            highlightElement(ipt_lastName);
            ipt_lastName.clear();
            ipt_lastName.sendKeys(lastName);
            assertEquals(lastName, ipt_lastName.getAttribute("value"), "Last name not entered correctly");
            captureScreenshot("last_name_entered");
        } catch (Exception e) {
            throw new RuntimeException("Error entering last name: " + e.getMessage());
        }
    }

    /** Types the given postal code into the checkout form and asserts the field value. */
    public void enterPostalCode(String postalCode) {
        try {
            highlightElement(ipt_postalCode);
            ipt_postalCode.clear();
            ipt_postalCode.sendKeys(postalCode);
            assertEquals(postalCode, ipt_postalCode.getAttribute("value"), "Postal code not entered correctly");
            captureScreenshot("postal_code_entered");
        } catch (Exception e) {
            throw new RuntimeException("Error entering postal code: " + e.getMessage());
        }
    }

    /** Clicks the Continue button to advance from the info form to the order summary. */
    public void clickContinue() {
        try {
            highlightElement(btn_continue);
            btn_continue.click();
            captureScreenshot("continue_clicked");
        } catch (Exception e) {
            throw new RuntimeException("Error clicking continue: " + e.getMessage());
        }
    }

    /** Asserts that the order summary block and total label are visible on the review screen. */
    public void verifyOrderSummary() {
        try {
            waitForVisible(lbl_summaryInfo);
            highlightElement(lbl_summaryInfo);
            assertTrue(lbl_summaryInfo.isDisplayed(), "Order summary not visible");
            assertNotNull(lbl_totalLabel.getText(), "Total label is empty");
            captureScreenshot("order_summary_verified");
        } catch (Exception e) {
            throw new RuntimeException("Error verifying order summary: " + e.getMessage());
        }
    }

    /** Clicks the Finish button to complete the purchase and navigate to the confirmation screen. */
    public void clickFinish() {
        try {
            highlightElement(btn_finish);
            btn_finish.click();
            captureScreenshot("finish_clicked");
        } catch (Exception e) {
            captureScreenshot("finish_click_error");
            throw new RuntimeException("Error clicking finish: " + e.getMessage());
        }
    }

    /** Asserts that the confirmation header matches the expected success message. */
    public void verifyOrderConfirmation(String expectedMessage) {
        try {
            waitForVisible(lbl_completeHeader);
            highlightElement(lbl_completeHeader);
            assertEquals(expectedMessage, lbl_completeHeader.getText(), "Order confirmation message mismatch");
            captureScreenshot("order_confirmed");
        } catch (Exception e) {
            throw new RuntimeException("Error verifying order confirmation: " + e.getMessage());
        }
    }
}

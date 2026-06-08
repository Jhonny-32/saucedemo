package com.com.saucedemo.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.junit.jupiter.api.Assertions.*;

public class LoginPage extends BasePage {

    @FindBy(className = "login_logo")
    private WebElement lbl_title;

    @FindBy(id = "user-name")
    private WebElement ipt_username;

    @FindBy(id = "password")
    private WebElement ipt_password;

    @FindBy(id = "login-button")
    private WebElement btn_login;

    @FindBy(css = ".error-message-container h3")
    private WebElement lbl_errorMessage;

    /** Initialises PageFactory elements for this page. */
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /** Asserts that the login button is visible, confirming the page is fully loaded. */
    public void verifyLoginPageLoaded() {
        try {
            waitForVisible(btn_login);
            assertTrue(btn_login.isDisplayed(), "Login button not visible");
            captureScreenshot("login_page_loaded");
        } catch (Exception e) {
            throw new RuntimeException("Login page not loaded: " + e.getMessage());
        }
    }

    /** Clears the username field, types the given value, and asserts it was entered correctly. */
    public void enterUsername(String username) {
        try {
            ipt_username.clear();
            ipt_username.sendKeys(username);
            highlightElement(ipt_username);
            assertEquals(username, ipt_username.getAttribute("value"), "Username not entered correctly");
            captureScreenshot("username_entered");
        } catch (Exception e) {
            throw new RuntimeException("Error entering username: " + e.getMessage());
        }
    }

    /** Clears the password field, types the given value, and asserts it is not empty. */
    public void enterPassword(String password) {
        try {
            ipt_password.clear();
            ipt_password.sendKeys(password);
            highlightElement(ipt_password);
            assertNotNull(ipt_password.getAttribute("value"), "Password field is empty");
            captureScreenshot("password_entered");
        } catch (Exception e) {
            throw new RuntimeException("Error entering password: " + e.getMessage());
        }
    }

    /** Clicks the login button and returns the ProductsPage (valid for successful logins). */
    public void clickLoginButton() {
        try {
            btn_login.click();
            highlightElement(btn_login);
            captureScreenshot("login_clicked");
        } catch (Exception e) {
            throw new RuntimeException("Error clicking login: " + e.getMessage());
        }
    }

    /** Confirms a successful login by asserting that the Products page is displayed. */
    public void verifySuccessfulLogin() {
        try {
            new ProductsPage(driver).verifyProductsPageLoaded();
            captureScreenshot("login_successful");
        } catch (Exception e) {
            throw new RuntimeException("Login verification failed: " + e.getMessage());
        }
    }

    /** Waits for the error banner to appear and asserts it is visible (negative login scenario). */
    public void verifyErrorMessage() {
        try {
            waitForVisible(lbl_errorMessage);
            highlightElement(lbl_errorMessage);
            assertTrue(lbl_errorMessage.isDisplayed(), "Error message not displayed");
            captureScreenshot("error_message_displayed");
        } catch (Exception e) {
            throw new RuntimeException("Error verifying error message: " + e.getMessage());
        }
    }

    /** Asserts that the login button is still present, confirming the user stayed on the login page. */
    public void verifyRemainsOnLoginPage() {
        try {
            assertTrue(btn_login.isDisplayed(), "Not on login page");
            captureScreenshot("remains_on_login_page");
        } catch (Exception e) {
            throw new RuntimeException("Error checking login page: " + e.getMessage());
        }
    }
}

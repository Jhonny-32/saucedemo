package com.com.saucedemo.page;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.com.saucedemo.utilities.HighlightElement;
import com.com.saucedemo.utilities.ReportUtils;
import com.com.saucedemo.utilities.ScreenshotUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /** Highlights the element with a red border, captures a screenshot while it is visible, then removes the border. */
    protected void highlightElement(WebElement element) {
        HighlightElement.highlight(driver, element);
    }

    /** Saves a screenshot to disk and attaches it to the current ExtentReports step with the given label. */
    protected void captureScreenshot(String stepName) {
        try {
            String base64 = ScreenshotUtils.captureAndSave(driver, stepName);
            if (ReportUtils.test != null && !base64.isEmpty()) {
                ReportUtils.test.log(
                        Status.INFO,
                        stepName,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build()
                );
            }
        } catch (Exception e) {
            System.out.println("Error capturing screenshot for report: " + e.getMessage());
        }
    }

    /** Waits up to 15 s for the element to be visible and returns it. */
    protected WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /** Scrolls the page until the element is inside the visible viewport. */
    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}

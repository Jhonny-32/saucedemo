package com.com.saucedemo.utilities;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/** Utility that visually highlights a WebElement during test execution. */
public class HighlightElement {

    /**
     * Adds a red border to the element, waits 500 ms so the highlight is visible in the screenshot,
     * logs the screenshot to ExtentReports, then removes the border.
     */
    public static void highlight(WebDriver driver, WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='3px solid red'; arguments[0].style.backgroundColor='rgba(255,0,0,0.1)';", element);
            Thread.sleep(500);

            // Capture screenshot WITH the red highlight still visible
            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            if (ReportUtils.test != null) {
                ReportUtils.test.log(
                        Status.INFO,
                        "Elemento seleccionado: " + getElementDescription(element),
                        MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build()
                );
            }

            js.executeScript("arguments[0].style.border=''; arguments[0].style.backgroundColor='';", element);
        } catch (Exception e) {
            System.out.println("Error al hacer highlight: " + e.getMessage());
        }
    }

    /** Builds a short CSS-like descriptor (tag#id, tag[name=…], or tag "text") for the log message. */
    private static String getElementDescription(WebElement element) {
        try {
            String tag = element.getTagName();
            String id = element.getAttribute("id");
            String name = element.getAttribute("name");
            String text = element.getText();
            if (id != null && !id.isEmpty()) return tag + "#" + id;
            if (name != null && !name.isEmpty()) return tag + "[name=" + name + "]";
            if (text != null && !text.isEmpty()) return tag + " \"" + text + "\"";
            return tag;
        } catch (Exception e) {
            return "element";
        }
    }
}

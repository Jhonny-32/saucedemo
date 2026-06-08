package com.com.saucedemo.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/** Utility for capturing and persisting screenshots during test execution. */
public class ScreenshotUtils {

    private static final String SCREENSHOT_DIR = "reports/screenshots/";

    /**
     * Takes a screenshot via BASE64, writes the PNG to {@code reports/screenshots/},
     * and returns the BASE64 string so ExtentReports can embed it inline in the HTML.
     */
    public static String captureAndSave(WebDriver driver, String name) {
        try {
            Files.createDirectories(Paths.get(SCREENSHOT_DIR));
            String base64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            byte[] bytes = Base64.getDecoder().decode(base64);
            String path = SCREENSHOT_DIR + name + "_" + System.currentTimeMillis() + ".png";
            Files.write(Paths.get(path), bytes);
            return base64;
        } catch (IOException e) {
            System.out.println("Error capturando screenshot: " + e.getMessage());
            return "";
        }
    }

    /** Alias for {@link #captureAndSave} kept for backward compatibility. */
    public static String capture(WebDriver driver, String name) {
        return captureAndSave(driver, name);
    }
}

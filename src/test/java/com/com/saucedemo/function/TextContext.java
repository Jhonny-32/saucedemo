package com.com.saucedemo.function;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.openqa.selenium.WebDriver;


public class TextContext {

    public enum Engine { SELENIUM, PLAYWRIGHT }

    public static Engine engine = resolveEngine();

    public static WebDriver driver;

    public static Playwright playwright;
    public static Browser browser;
    public static Page page;


    public static boolean isPlaywright() {
        return engine == Engine.PLAYWRIGHT;
    }

    private static Engine resolveEngine() {
        String value = System.getProperty("engine",
                System.getenv().getOrDefault("ENGINE", "selenium"));
        return "playwright".equalsIgnoreCase(value) ? Engine.PLAYWRIGHT : Engine.SELENIUM;
    }
}

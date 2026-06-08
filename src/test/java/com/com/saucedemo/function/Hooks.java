package com.com.saucedemo.function;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks extends TextContext{

    @Before
    public void setUp(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/configuration/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @After
    public void getDown(){
        driver.quit();
    }

}

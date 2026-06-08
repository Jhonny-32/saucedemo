package com.com.saucedemo.page;

import com.com.saucedemo.function.TextContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TextContext {

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CLASS_NAME, using = "login_logo")
    WebElement lbl_title;

    @FindBy(how = How.ID, using = "user-name")
    WebElement ipt_username;

    @FindBy(how = How.ID, using = "password")
    WebElement ipt_password;

    @FindBy(how = How.ID, using = "login-button")
    WebElement btn_login;


}

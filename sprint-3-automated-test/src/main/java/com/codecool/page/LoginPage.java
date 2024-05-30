package com.codecool.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    String userName = System.getenv("USER_NAME");
    String password = System.getenv("PASSWORD");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "login-form-username")
    WebElement userNameField;
    @FindBy(id = "login-form-password")
    WebElement passwordField;
    @FindBy(id = "login-form-submit")
    WebElement loginButton;

    public void handleLogin(){
        userNameField.click();
        userNameField.clear();
        userNameField.sendKeys(userName);
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }
}

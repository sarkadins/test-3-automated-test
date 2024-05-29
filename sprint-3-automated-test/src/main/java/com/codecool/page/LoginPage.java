package com.codecool.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    private final String userName = System.getenv("USER_NAME");
    private final String password = System.getenv("PASSWORD");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "login-form-username")
    private WebElement userNameField;

    @FindBy(id = "login-form-password")
    private WebElement passwordField;

    @FindBy(id = "login-form-submit")
    private WebElement loginButton;

    public void handleLogin(){
        sendDataForUsernameField();
        sendDataForPasswordField();
        clickOnLoginButton();
    }

    private void sendDataForUsernameField() {
        userNameField.click();
        userNameField.clear();
        userNameField.sendKeys(userName);
    }

    private void sendDataForPasswordField() {
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    private void clickOnLoginButton() {
        loginButton.click();
    }

}

package com.codecool.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage extends BasePage{

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "strong")
    private WebElement logoutLongMessage;

    public String getLogoutLongMessage() {
        return logoutLongMessage.getText();
    }
}

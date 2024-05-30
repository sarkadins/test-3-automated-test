package com.codecool.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".gadget-inline h3")
    private WebElement loginMessage;

    @FindBy(id = "user-options")
    private WebElement userOptions;

    @FindBy(linkText = "Log Out")
    private WebElement logoutButton;

    public String getLoginMessage() {
        return loginMessage.getText();
    }

    public void handleLogout() {
        userOptions.click();
        logoutButton.click();
    }
}

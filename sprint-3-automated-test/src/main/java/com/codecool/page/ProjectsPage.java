package com.codecool.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProjectsPage extends BasePage {

    private WebDriverWait wait;

    public ProjectsPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @FindBy(id = "project-filter-text")
    private WebElement filterField;

    @FindBy(className = "cell-type-key")
    private WebElement keyTextField;

    public void setFilterText(String filterText) {
        filterField.click();
        filterField.clear();
        filterField.sendKeys(filterText);
    }

    public String getTypeKey(String input) {
        wait.until(ExpectedConditions.textToBePresentInElement(keyTextField, input.toUpperCase()));
        return keyTextField.getText();
    }

}

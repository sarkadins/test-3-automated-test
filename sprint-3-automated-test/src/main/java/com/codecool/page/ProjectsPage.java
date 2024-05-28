package com.codecool.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectsPage extends BasePage {

    public ProjectsPage(WebDriver driver) {
        super(driver);
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

    public String getTypeKey() {
        return keyTextField.getText();
    }

}

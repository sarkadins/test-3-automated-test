package com.codecool.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IssuesPage extends BasePage {

    Wait<WebDriver> wait;
    Select select;

    public IssuesPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(css = "li:nth-of-type(1) > .aui-button.aui-button-subtle.criteria-selector.jira-aui-dropdown2-trigger")
    WebElement projectDropdown;
    @FindBy(css = "li:nth-of-type(1) > label[title='COALA project']")
    WebElement coalaProjectCheckBox;
    @FindBy(css = "li:nth-of-type(3) > label[title='JETI project']")
    WebElement jetiProjectCheckBox;
    @FindBy(css = "li:nth-of-type(8) > label[title='TOUCAN project']")
    WebElement toucanProjectCheckBox;
    @FindBy(css = "li:nth-of-type(1) > .splitview-issue-link .issue-link-key")
    WebElement confirmProject;
    @FindBy(xpath = "//a[@id='edit-issue']/span[@class='trigger-label']")
    WebElement editButton;
    @FindBy(xpath = "//div[@id='priority-single-select']/span[@role='button']")
    WebElement priorityDropdownButton;
    @FindBy(css = "li:nth-of-type(2) > a[role='presentation']")
    WebElement mediumButton;
    @FindBy(css = "li#high-2 > a[role='presentation']")
    WebElement highButton;
    @FindBy(id = "edit-issue-submit")
    WebElement submitButton;
    @FindBy(css = ".aui-message.aui-message-success.aui-will-close.closeable")
    WebElement confirmUpdateMessage;
    @FindBy(id = "duedate")
    WebElement selectedDueDate;
    @FindBy(id = "jira")
    WebElement jira;
    @FindBy(id = "priority-field")
    WebElement priorityField;


    public void clickCoalaProject() {
        projectDropdown.click();
        coalaProjectCheckBox.click();
    }

    public void clickJetiProject() {
        projectDropdown.click();
        jetiProjectCheckBox.click();
    }

    public void clickToucanProject() {
        projectDropdown.click();
        toucanProjectCheckBox.click();
    }

    public String getConfirmProject() {
        wait.until(ExpectedConditions.visibilityOf(confirmProject));
        return confirmProject.getText();
    }

    public void selectProject(String project) {
        switch (project.toLowerCase()) {
            case "coala":
                clickCoalaProject();
                break;
            case "jeti":
                clickJetiProject();
                break;
            case "toucan":
                clickToucanProject();
                break;
        }
    }

    public void editIssue(String project) {
        if (project.equals("coala")) {
            clickCoalaProject();
            jira.sendKeys("e");
            wait.until(ExpectedConditions.visibilityOf(priorityDropdownButton));
            priorityDropdownButton.click();
            if (priorityField.getText().equals("Medium")){
                highButton.click();
            } else if (priorityField.getText().equals("High")) {
                mediumButton.click();
            }
            submitButton.click();
        }
    }

    public String getConfirmUpdateMessage() {
        wait.until(ExpectedConditions.visibilityOf(confirmUpdateMessage));
        return confirmUpdateMessage.getText();
    }

}

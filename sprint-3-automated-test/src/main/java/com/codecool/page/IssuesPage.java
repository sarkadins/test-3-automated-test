package com.codecool.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IssuesPage extends BasePage {

    Wait<WebDriver> wait;
    Actions action;

    public IssuesPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        action = new Actions(driver);
    }

    @FindBy(css = "li:nth-of-type(1) > .aui-button.aui-button-subtle.criteria-selector.jira-aui-dropdown2-trigger")
    private WebElement projectDropdown;

    @FindBy(css = "li:nth-of-type(1) > label[title='COALA project']")
    private WebElement coalaProjectCheckBox;

    @FindBy(css = "li:nth-of-type(3) > label[title='JETI project']")
    private WebElement jetiProjectCheckBox;

    @FindBy(css = "li:nth-of-type(8) > label[title='TOUCAN project']")
    private WebElement toucanProjectCheckBox;

    @FindBy(css = "li:nth-of-type(1) > .splitview-issue-link .issue-link-key")
    private WebElement confirmProject;

    @FindBy(xpath = "//a[@id='edit-issue']/span[@class='trigger-label']")
    private WebElement editButton;

    @FindBy(xpath = "//div[@id='priority-single-select']/span[@role='button']")
    private WebElement priorityDropdownButton;

    @FindBy(css = "li:nth-of-type(2) > a[role='presentation']")
    private WebElement mediumButton;

    @FindBy(css = "li#high-2 > a[role='presentation']")
    private WebElement highButton;

    @FindBy(id = "edit-issue-submit")
    private WebElement submitButton;

    @FindBy(css = ".aui-message.aui-message-success.aui-will-close.closeable")
    private WebElement confirmUpdateMessage;

    @FindBy(id = "duedate")
    private WebElement selectedDueDate;

    @FindBy(id = "jira")
    private WebElement jira;

    @FindBy(id = "priority-field")
    private WebElement priorityField;

    @FindBy(id = "components-val")
    private WebElement currentComponentsValueIfAny;

    @FindBy(css = "span#components-val > .aui-icon.aui-icon-small.aui-iconfont-edit")
    private WebElement currentComponentsValueIfNone;

    @FindBy(css = "[aria-label=' ']")
    private WebElement deleteCurrentComponentButton;

    @FindBy(xpath = "//form[@id='components-form']//button[@title='Press Alt+s to submit this form']/span[@class='aui-icon aui-icon-small aui-iconfont-success']")
    private WebElement saveComponentButton;

    @FindBy(css = "div#components-multi-select > span[role='button']")
    private WebElement componentsDropDownButton;

    @FindBy(linkText = "TestComponent")
    private WebElement testComponentButton;

    @FindBy(css = "span#fixfor-val > .aui-icon.aui-icon-small.aui-iconfont-edit")
    private WebElement editVersionButton;

    @FindBy(id = "fixfor-val")
    private WebElement currentVersionValueIfNone;

    @FindBy(linkText = "999.999")
    private WebElement currentVersionValueIfAny;

    @FindBy(css = "[aria-label=' ']")
    private WebElement deleteCurrentVersionButton;

    @FindBy(xpath = "//form[@id='fixVersions-form']//button[@title='Press Alt+s to submit this form']/span[@class='aui-icon aui-icon-small aui-iconfont-success']")
    private WebElement saveVersionButton;

    @FindBy(css = "div#fixVersions-multi-select > span[role='button']")
    private WebElement versionDropDownButton;

    @FindBy(linkText = "999.999")
    private WebElement versionButton;

    private void clickCoalaProject() {
        projectDropdown.click();
        coalaProjectCheckBox.click();
    }

    private void clickJetiProject() {
        projectDropdown.click();
        jetiProjectCheckBox.click();
    }

    private void clickToucanProject() {
        projectDropdown.click();
        toucanProjectCheckBox.click();
    }

    public String getConfirmProject(String input){
        wait.until(ExpectedConditions.textToBePresentInElement(confirmProject, input));
        return confirmProject.getText();
    }

    public void selectProject(String project) {
        switch (project) {
            case "COALA-1057":
                clickCoalaProject();
                break;
            case "JETI-1064":
                clickJetiProject();
                break;
            case "TOUCAN-100":
                clickToucanProject();
                break;
        }
    }

    public void editIssue(String project) {
        if (project.equals("coala")) {
            clickCoalaProject();
            wait.until(ExpectedConditions.visibilityOf(jira));
            jira.sendKeys("e");
            wait.until(ExpectedConditions.visibilityOf(priorityDropdownButton));
            priorityDropdownButton.click();
            if (priorityField.getAttribute("value").equals("Medium")){
                highButton.click();
            } else if (priorityField.getAttribute("value").equals("High")) {
                mediumButton.click();
            }
            submitButton.click();
        }
    }

    public String getConfirmUpdateMessage() {
        wait.until(ExpectedConditions.visibilityOf(confirmUpdateMessage));
        return confirmUpdateMessage.getText();
    }

    private void editIssueComponents(String input){
        var currentComponentsValueText = wait.until(ExpectedConditions.visibilityOf(currentComponentsValueIfAny)).getText();

        if (!currentComponentsValueText.equals(input))
        {
            if (input.equals("None"))
            {
                action.moveToElement(currentComponentsValueIfNone).perform();
                wait.until(ExpectedConditions.visibilityOf(currentComponentsValueIfNone)).click();
                wait.until(ExpectedConditions.visibilityOf(deleteCurrentComponentButton)).click();
                wait.until(ExpectedConditions.visibilityOf(saveComponentButton)).click();
            }
            else
            {
                action.moveToElement(currentComponentsValueIfAny).perform();
                wait.until(ExpectedConditions.visibilityOf(currentComponentsValueIfAny)).click();
                wait.until(ExpectedConditions.visibilityOf(componentsDropDownButton)).click();
                wait.until(ExpectedConditions.visibilityOf(testComponentButton)).click();
                wait.until(ExpectedConditions.visibilityOf(saveComponentButton)).click();
            }
        }
    }

    public String getSelectedComponentText(String input) {
        editIssueComponents(input);
        wait.until(ExpectedConditions.textToBePresentInElement(currentComponentsValueIfAny, input));
        return currentComponentsValueIfAny.getText();
    }

    private void editIssueVersion(String input) throws InterruptedException {
        Thread.sleep(3000);
        var currentVersionValueText = wait.until(ExpectedConditions.visibilityOf(currentVersionValueIfNone)).getText();

        if (!currentVersionValueText.equals(input))
        {
            if (input.equals("None"))
            {
                action.moveToElement(currentVersionValueIfAny).perform();
                wait.until(ExpectedConditions.visibilityOf(currentVersionValueIfAny)).click();
                Thread.sleep(3000);
                System.out.println("clickmégjó");
                wait.until(ExpectedConditions.visibilityOf(deleteCurrentVersionButton)).click();
                Thread.sleep(3000);
                wait.until(ExpectedConditions.visibilityOf(saveVersionButton)).click();
                Thread.sleep(3000);
            }
            else
            {
                action.moveToElement(currentVersionValueIfNone).perform();
                wait.until(ExpectedConditions.visibilityOf(currentVersionValueIfNone)).click();
                Thread.sleep(3000);
                wait.until(ExpectedConditions.visibilityOf(versionDropDownButton)).click();
                Thread.sleep(3000);
                wait.until(ExpectedConditions.visibilityOf(versionButton)).click();
                Thread.sleep(3000);
                wait.until(ExpectedConditions.visibilityOf(saveVersionButton)).click();
                Thread.sleep(3000);
            }
        }
    }

    public String getSelectedVersionText(String input) throws InterruptedException {
        editIssueVersion(input);
        wait.until(ExpectedConditions.textToBePresentInElement(currentVersionValueIfAny, input));
        return currentVersionValueIfAny.getText();
    }

}

package com.codecool.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateIssuesPage extends BasePage {
    Wait<WebDriver> wait;

    @FindBy(css = "h2[title='Create Issue']")
    WebElement h2ByText;

    @FindBy(id = "create-issue-submit")
    WebElement submitIssueButton;

    @FindBy(css = "a#create_link")
    WebElement createButton;

    @FindBy(id = "summary")
    WebElement summaryField;

    @FindBy(css = ".aui-message-success")
    WebElement successPopupMessage;


    public CreateIssuesPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    public String getPopupMessageText(){
        wait.until(ExpectedConditions.visibilityOf(successPopupMessage));
        return successPopupMessage.getText();
    }

    public void createNewIssue(String input){
        createButton.click();
        if (input.equals("test1")){
            wait.until(ExpectedConditions.visibilityOf(summaryField));
            summaryField.sendKeys("test1");
            submitIssueButton.click();
        } else if (input.equals("test2")){
            wait.until(ExpectedConditions.visibilityOf(summaryField));
            summaryField.sendKeys("test2");
            submitIssueButton.click();
        }
    }



}

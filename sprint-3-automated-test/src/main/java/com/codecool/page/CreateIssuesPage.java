package com.codecool.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateIssuesPage extends BasePage {
    //https://jira-auto.codecool.metastage.net/secure/CreateIssue!default.jspa

    @FindBy(xpath = "//h1[text()='Create Issue']")
    WebElement h1ByText;

    @FindBy(id = "issue-create-submit")
    WebElement nextBtn;

    @FindBy(id = "issue-create-cancel")
    WebElement cancelBtn;

    @FindBy(id = "project-single-select")
    WebElement projectDropdown;

    @FindBy(id = "issuetype-single-select")
    WebElement issueDropdown;

    public CreateIssuesPage(WebDriver driver) {
        super(driver);
    }

    public boolean isCreateIssueH1Displayed(){
        return h1ByText.isDisplayed();
    }

}

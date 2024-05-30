package com.codecool.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class UITPPProjectPage extends BasePage {

    public UITPPProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "administer_project_versions")
    private WebElement versions;

    @FindBy(id = "administer_project_components")
    private WebElement components;

    @FindBy(id = "view_project_permissions")
    private WebElement permissions;

    @FindBy(css = "div:nth-of-type(1) > .aui.jira-admin-table > tbody > tr:nth-of-type(2) > .grants  dd")
    private WebElement browseProjectPermissionCheckField;

    @FindBy(css = "div:nth-of-type(2) > .aui.jira-admin-table > tbody > tr:nth-of-type(4) > .grants  dd")
    private WebElement createIssuePermissionCheckField;

    @FindBy(css = "div:nth-of-type(2) > .aui.jira-admin-table > tbody > tr:nth-of-type(6) > .grants  dd")
    private WebElement editIssuePermissionCheckField;

    @FindBy(id = "view_project_issuetypes")
    private WebElement issuetypes;

    @FindBy(className = "project-config-issuetype-name")
    private List<WebElement> issueTypeElements;

    public void clickOnVersions() {
        versions.click();
    }

    public void clickOnComponents() {
        components.click();
    }

    private void clickOnPermissions() {
        permissions.click();
    }

    public String getBrowseProjectPermissionCheckFieldText() {
        clickOnPermissions();
        return browseProjectPermissionCheckField.getText();
    }

    public String getCreateIssuePermissionCheckFieldText() {
        clickOnPermissions();
        return createIssuePermissionCheckField.getText();
    }

    public String getEditIssuePermissionCheckFieldText() {
        clickOnPermissions();
        return editIssuePermissionCheckField.getText();
    }

    private void clickOnIssueTypes()
    {
        issuetypes.click();
    }

    public List<String> getIssueTypesNames() {
        return issueTypeElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

}

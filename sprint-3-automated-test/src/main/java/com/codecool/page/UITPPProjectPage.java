package com.codecool.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void clickOnVersions() {
        versions.click();
    }

    public void clickOnComponents() {
        components.click();
    }

    public void clickOnPermissions() {
        permissions.click();
    }

    public String getBrowseProjectPermissionCheckFieldText() {
        return browseProjectPermissionCheckField.getText();
    }

    public String getCreateIssuePermissionCheckFieldText() {
        return createIssuePermissionCheckField.getText();
    }

    public String getEditIssuePermissionCheckFieldText() {
        return editIssuePermissionCheckField.getText();
    }

}

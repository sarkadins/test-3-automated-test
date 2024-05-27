package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PermissionWithGlass {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://jira-auto.codecool.metastage.net/");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("#login-form-username")).sendKeys("automation13");
        driver.findElement(By.cssSelector("#login-form-password")).sendKeys("CCAutoTest19.");
        driver.findElement(By.cssSelector("#login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create_link")));
    }

    @Test
    void verifyProjectPermission() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//a[@id='browse_link']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Use It To Practice Project (PP)"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//section[@id='sidebar']//a[@href='/plugins/servlet/project-config/PP']")))
                .click();

        Thread.sleep(1500);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)", "");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//a[@id='view_project_permissions']")))
                .click();

        String actualPermissionText = driver.findElement(By.cssSelector("#project-config-panel-permissions > jira-permissions-table > div:nth-child(1) > table > tbody > tr:nth-child(2) > td.grants > dl > dd"))
                .getText();

        Assertions.assertEquals("Any logged in user", actualPermissionText);
    }

    @Test
    void verifyCreateIssuePermission() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//a[@id='browse_link']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Use It To Practice Project (PP)"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//section[@id='sidebar']//a[@href='/plugins/servlet/project-config/PP']")))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//a[@id='view_project_permissions']")))
                .click();
        WebElement permission = driver.findElement(By.cssSelector("div:nth-of-type(2) > .aui.jira-admin-table > tbody > tr:nth-of-type(4) > .grants  dd"));
        String actual = permission.getText();
        String expected = "Any logged in user";
        Assertions.assertEquals(expected, actual);

    }

    @Test
    void verifyEditIssuePermission() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//a[@id='browse_link']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Use It To Practice Project (PP)"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//section[@id='sidebar']//a[@href='/plugins/servlet/project-config/PP']")))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//a[@id='view_project_permissions']")))
                .click();
        WebElement permission = driver.findElement(By.cssSelector(".permissions-group:nth-child(2) tr:nth-child(6) dd"));
        String actual = permission.getText();
        String expected = "Any logged in user";
        Assertions.assertEquals(expected, actual);

    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
}

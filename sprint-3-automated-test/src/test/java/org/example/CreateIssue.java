package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CreateIssue {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach

    void setUp() {

        driver = new ChromeDriver();
        driver.get("https://jira-auto.codecool.metastage.net/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.cssSelector("#login-form-username")).sendKeys("automation13");
        driver.findElement(By.cssSelector("#login-form-password")).sendKeys("CCAutoTest19.");
        driver.findElement(By.cssSelector("#login")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("create_link")));
    }

    @Test
    void testCreateIssueAsToucan() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"create_link\"]")))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#project-field")))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//input[@id='project-field']")))
                .sendKeys("TOUCAN project (TOUCAN)", Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"summary\"]")))
                .sendKeys("test summary");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"create-issue-submit\"]")))
                .click();

        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"aui-flag-container\"]/div/div/a")));
        Assertions.assertTrue(confirmation.isDisplayed());
    }

    @Test
    void testCreateIssueAsJeti() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"create_link\"]")).click();
        Thread.sleep(700);
        driver.findElement(By.cssSelector("#project-field")).click();
        assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.linkText("JETI project (JETI)")).click();
        });
    }

    @Test
    void testCreateIssueAsCoala() {
        //COALA project (COALA)
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"create_link\"]")))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#project-field")))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//input[@id='project-field']")))
                .sendKeys("COALA project (COALA)", Keys.ENTER);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"summary\"]")))
                .sendKeys("test summary");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"create-issue-submit\"]")))
                .click();

        WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"aui-flag-container\"]/div/div/a")));
        Assertions.assertTrue(confirmation.isDisplayed());
    }

    @AfterEach
    void tearDown() {

        driver.quit();
    }
}

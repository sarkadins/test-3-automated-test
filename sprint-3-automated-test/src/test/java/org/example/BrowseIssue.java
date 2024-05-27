package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BrowseIssue {

    WebDriver driver;

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
        driver.findElement(By.id("login-form-username")).sendKeys("automation11");
        driver.findElement(By.id("login-form-password")).sendKeys("CCAutoTest19.");
        driver.findElement(By.id("login-form-submit")).click();
    }
    @Test
    void testBrowseToucanIssue() throws InterruptedException {
        driver.findElement(By.id("find_link")).click();
        Thread.sleep(500);

        driver.findElement(By.id("issues_new_search_link_lnk")).click();
        Thread.sleep(500);
        driver.findElement(By.id("searcher-query")).sendKeys("toucan");
        driver.findElement(By.className("search-button")).click();
        Thread.sleep(500);
        WebElement toucanMessage = driver.findElement(By.className("issue-link-summary"));
        String actual = toucanMessage.getText();
        String expected = "TOUCAN project issue";
        assertEquals(expected, actual);
    }
    @Test
    void testBrowseJetiIssue() throws InterruptedException {
        driver.findElement(By.id("find_link")).click();
        Thread.sleep(500);
        driver.findElement(By.id("issues_new_search_link_lnk")).click();
        Thread.sleep(500);
        driver.findElement(By.id("searcher-query")).sendKeys("jeti");
        Thread.sleep(500);
        driver.findElement(By.className("search-button")).click();
        Thread.sleep(500);
        WebElement jetiKey = driver.findElement(By.className("issue-link-key"));
        String actual = jetiKey.getText();
        String expected = "JETI-731";
        assertEquals(expected, actual);
    }
    @Test
    void testBrowseCoalaIssue() throws InterruptedException {
        driver.findElement(By.id("find_link")).click();
        Thread.sleep(500);
        driver.findElement(By.id("issues_new_search_link_lnk")).click();
        Thread.sleep(500);
        driver.findElement(By.id("searcher-query")).sendKeys("coala");
        Thread.sleep(500);
        driver.findElement(By.className("search-button")).click();
        Thread.sleep(500);
        WebElement coalaKey = driver.findElement(By.className("issue-link-key"));
        String actual = coalaKey.getText();
        String expected = "COALA-695";
        assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

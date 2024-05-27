package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyIssueTypeScheme {
    WebDriver driver;

    @BeforeEach
    void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
        Thread.sleep(500);
        driver.findElement(By.id("login-form-username")).sendKeys("automation11");
        driver.findElement(By.id("login-form-password")).sendKeys("CCAutoTest19.");
        driver.findElement(By.id("login-form-submit")).click();
        Thread.sleep(500);
        driver.findElement(By.className("aui-iconfont-configure")).click();
        Thread.sleep(500);
        driver.findElement(By.id("admin_project_menu")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"projects\"]/div/table/tbody/tr/td[1]/a")).click();
        Thread.sleep(500);
    }

    @Test
    void testVerifyBugType() {
        WebElement bug = driver.findElement(By.xpath("//*[@id=\"project-config-webpanel-summary-issuetypes\"]/div[2]/div/ul/li[1]/span/span/a"));
        String actual = bug.getText();
        String expected = "Bug";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testVerifyEpicType() {
        WebElement epic = driver.findElement(By.xpath("//*[@id=\"project-config-webpanel-summary-issuetypes\"]/div[2]/div/ul/li[2]/span/span/a"));
        String actual = epic.getText();
        String expected = "Epic";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testVerifyStoryType() {
        WebElement story = driver.findElement(By.xpath("//*[@id=\"project-config-webpanel-summary-issuetypes\"]/div[2]/div/ul/li[3]/span/span/a"));
        String actual = story.getText();
        String expected = "Story";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testVerifySubTaskType() {
        WebElement subTask = driver.findElement(By.xpath("//*[@id=\"project-config-webpanel-summary-issuetypes\"]/div[2]/div/ul/li[4]/span/span[1]/a"));
        String actual = subTask.getText();
        String expected = "Sub-task";
        Assertions.assertEquals(expected, actual);
    }
    @Test
    void testVerifyTaskType(){
        WebElement task = driver.findElement(By.xpath("//*[@id=\"project-config-webpanel-summary-issuetypes\"]/div[2]/div/ul/li[5]/span/span/a"));
        String actual = task.getText();
        String expected = "Task";
        Assertions.assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

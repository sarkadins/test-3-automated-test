package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Logout {
    WebDriver driver;

    @BeforeEach
    void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("https://jira-auto.codecool.metastage.net/");
        driver.findElement(By.cssSelector("#login-form-username")).sendKeys("automation13");
        driver.findElement(By.cssSelector("#login-form-password")).sendKeys("CCAutoTest19.");
        driver.findElement(By.cssSelector("#login")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("#header-details-user-fullname > span > span > img")).click();
        driver.findElement(By.xpath("//*[@id=\"log_out\"]")).click();
    }

    @Test
    void testLogout() throws InterruptedException {
        String expected = "You are now logged out. Any automatic login has also been stopped.";
        String actual = driver.findElement(By.xpath("//*[@id=\"main\"]/div/div/p[1]/strong")).getText();
        Assertions.assertEquals(expected, actual);
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        Thread.sleep(1500);
        driver.quit();
    }
}

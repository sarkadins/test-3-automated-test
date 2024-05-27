package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowseProject {

    WebDriver driver;

    @BeforeEach
    void setUp() throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
        //login
        driver.findElement(By.id("login-form-username")).sendKeys("automation12");
        driver.findElement(By.id("login-form-password")).sendKeys("CCAutoTest19.");
        driver.findElement(By.id("login-form-submit")).click();
        Thread.sleep(2000);

    }

    @AfterEach
    void tearDown() {

        //logout
        driver.findElement(By.className("aui-avatar-inner")).click();
        driver.findElement(By.id("log_out")).click();
        driver.quit();

    }

    @Test
    void testBrowseAllProject() throws InterruptedException {

        //arrange
        driver.findElement(By.id("browse_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("project_view_all_link_lnk")).click();
        Thread.sleep(2000);

        //act
        var actualText = driver.findElement(By.className("aui-page-header-main")).getText();


        //assert
        Assertions.assertEquals("Browse projects", actualText);

    }

    @Test
    void testBrowseToucanProject() throws InterruptedException {

        //arrange
        driver.findElement(By.id("browse_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("project_view_all_link_lnk")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("project-filter-text")).sendKeys("Toucan");
        Thread.sleep(2000);

        //assert
        var elementsList = driver.findElements(By.className("cell-type-key"));
        var isAllNamedToucan = false;

        for (var element : elementsList) {
            if (element.getText().equals("TOUCAN")) {
                isAllNamedToucan = true;
            }
        }

        //assert
        assertTrue(isAllNamedToucan, "TOUCAN project not found!");

    }

    @Test
    void testBrowseJetiProject() throws InterruptedException {

        //arrange
        driver.findElement(By.id("browse_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("project_view_all_link_lnk")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("project-filter-text")).sendKeys("Jeti");
        Thread.sleep(2000);

        //act
        var elementsList = driver.findElements(By.className("cell-type-key"));
        var isAllNamedJeti = false;

        for (var element : elementsList) {
            if (element.getText().equals("JETI")) {
                isAllNamedJeti = true;
            }
        }

        //assert
        assertTrue(isAllNamedJeti, "JETI project not found!");

    }

    @Test
    void testBrowseCoalaProject() throws InterruptedException {

        //arrange
        driver.findElement(By.id("browse_link")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("project_view_all_link_lnk")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("project-filter-text")).sendKeys("Coala");
        Thread.sleep(2000);

        //act
        var elementsList = driver.findElements(By.className("cell-type-key"));
        var isAllNamedCoala = false;

        for (var element : elementsList) {
            if (element.getText().equals("COALA")) {
                isAllNamedCoala = true;
            }
        }

        //assert
        assertTrue(isAllNamedCoala, "COALA project not found!");

    }

}

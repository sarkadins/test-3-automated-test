package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ComponentsWithGlass {

    private WebDriver driver;

    @BeforeEach
    public void setUp() throws InterruptedException {

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
    public void tearDown() {

        //logout
        driver.findElement(By.className("aui-avatar-inner")).click();
        driver.findElement(By.id("log_out")).click();
        driver.quit();

    }

    @Test
    public void CategorizeIssueModify() throws InterruptedException {

        Actions action = new Actions(driver);

        //arrange
            //find UITPP issues
        driver.findElement(By.id("find_link")).click(); Thread.sleep(1000);
        driver.findElement(By.id("issues_new_search_link_lnk")).click(); Thread.sleep(1000);
        driver.findElement(By.className("criteria-wrap")).click(); Thread.sleep(1000);
        driver.findElement(By.xpath("//li[9]/label")).click(); Thread.sleep(1000);

            //modify the PP-464 issue's component
        driver.findElement(By.className("criteria-wrap")).click(); Thread.sleep(1000);
        driver.findElement(By.linkText("PP-464")).click(); Thread.sleep(1000);
        var actualComponentElement = driver.findElement(By.id("components-val"));
        var actualText = actualComponentElement.getText();

        if (actualText.equals("None"))
        {
            action.moveToElement(actualComponentElement).perform(); Thread.sleep(1000);
            driver.findElement(By.xpath("//span[@id='components-val']/span")).click(); Thread.sleep(1000);
            driver.findElement(By.xpath("//div[@id='components-multi-select']/span")).click(); Thread.sleep(1000);
            driver.findElement(By.linkText("TestComponent")).click();
            driver.findElement(By.xpath("//form[@id='components-form']/div[2]/button/span")).click(); Thread.sleep(1000);
        }
        else
        {
            action.moveToElement(actualComponentElement).perform(); Thread.sleep(1000);
            driver.findElement(By.xpath("//span[@id='components-val']/span[2]")).click(); Thread.sleep(1000);
            driver.findElement(By.cssSelector(".item-delete")).click();
            driver.findElement(By.xpath("//form[@id='components-form']/div[2]/button/span")).click(); Thread.sleep(1000);
        }

        //act
        var actualComponentText = driver.findElement(By.id("components-val")).getText();
        var expectedComponentText = actualText.equals("None") ? "TestComponent" : "None";

        //assert
        Assertions.assertEquals(expectedComponentText, actualComponentText);

    }

}

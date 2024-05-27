package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class EditIssue {

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
    void editIssueToucan871Summary() throws InterruptedException {

        //arrange
            //find Toucan issues
        driver.findElement(By.id("find_link")).click(); Thread.sleep(1000);
        driver.findElement(By.id("issues_new_search_link_lnk")).click(); Thread.sleep(1000);
        driver.findElement(By.className("criteria-wrap")).click(); Thread.sleep(1000);
        driver.findElement(By.xpath("//li[8]/label")).click(); Thread.sleep(1000);

            //modify the first issue's summary
        driver.findElement(By.className("criteria-wrap")).click(); Thread.sleep(1000);
        driver.findElement(By.linkText("TOUCAN-871")).click(); Thread.sleep(1000);
        driver.findElement(By.id("edit-issue")).click(); Thread.sleep(1000);
        var actualText = driver.findElement(By.id("summary")).getAttribute("value");
        driver.findElement(By.id("summary")).clear(); Thread.sleep(1000);
        if (actualText.equals("ModifiedSummaryVersion1")) {
            driver.findElement(By.id("summary")).sendKeys("ModifiedSummaryVersion2");
        } else {
            driver.findElement(By.id("summary")).sendKeys("ModifiedSummaryVersion1");
        }
        Thread.sleep(1000);
        driver.findElement(By.id("edit-issue-submit")).click(); Thread.sleep(1000);

        //act
        var actualSummaryText = driver.findElement(By.id("summary-val")).getText();
        var expectedSummaryText = actualText.equals("ModifiedSummaryVersion1") ? "ModifiedSummaryVersion2" : "ModifiedSummaryVersion1";

        //assert
        Assertions.assertEquals(expectedSummaryText, actualSummaryText);

    }

    @Test
    void editIssueJetiType() throws InterruptedException {

        //arrange
            //find Jeti issues
        driver.findElement(By.id("find_link")).click(); Thread.sleep(1000);
        driver.findElement(By.id("issues_new_search_link_lnk")).click(); Thread.sleep(1000);
        driver.findElement(By.className("criteria-wrap")).click(); Thread.sleep(1000);
        driver.findElement(By.xpath("//li[3]/label")).click(); Thread.sleep(1000);

            //modify the first issue's summary
        driver.findElement(By.className("criteria-wrap")).click(); Thread.sleep(1000);
        driver.findElement(By.linkText("JETI-1063")).click(); Thread.sleep(1000);
        var actualText = driver.findElement(By.id("type-val")).getText();
        driver.findElement(By.id("type-val")).click(); Thread.sleep(1000);
        driver.findElement(By.cssSelector(".aui-ss-icon")).click(); Thread.sleep(1000);
        if (actualText.equals("Bug")) {
            driver.findElement(By.linkText("Story")).click();
        } else {
            driver.findElement(By.linkText("Bug")).click();
        }
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".aui-iconfont-success")).click(); Thread.sleep(1000);

        //act
        var actualSummaryText = driver.findElement(By.id("type-val")).getText();
        var expectedSummaryText = actualText.equals("Bug") ? "Story" : "Bug";

        //assert
        Assertions.assertEquals(expectedSummaryText, actualSummaryText);

    }

    @Test
    void editIssueCoalaDueDate() throws InterruptedException {

        //arrange
            //find Coala issues
        driver.findElement(By.id("find_link")).click(); Thread.sleep(1000);
        driver.findElement(By.id("issues_new_search_link_lnk")).click(); Thread.sleep(1000);
        driver.findElement(By.className("criteria-wrap")).click(); Thread.sleep(1000);

                //working Jeti project
        //driver.findElement(By.xpath("//li[3]/label")).click(); Thread.sleep(1000);
                //not working with Coala project
        driver.findElement(By.xpath("//li/label")).click(); Thread.sleep(1000);

            //modify the first issue's summary
        driver.findElement(By.className("criteria-wrap")).click(); Thread.sleep(1000);

        //driver.findElement(By.linkText("JETI-1063")).click(); Thread.sleep(1000);
        driver.findElement(By.linkText("COALA-977")).click(); Thread.sleep(1000);

        var actualDate = driver.findElement(By.id("due-date")).getText();
        driver.findElement(By.id("edit-issue")).click(); Thread.sleep(1000);
        driver.findElement(By.id("duedate")).click(); Thread.sleep(1000);
        driver.findElement(By.id("duedate")).clear(); Thread.sleep(1000);
        if (actualDate.equals("05/May/24")) {
            driver.findElement(By.id("duedate")).sendKeys("04/May/24");
        } else {
            driver.findElement(By.id("duedate")).sendKeys("05/May/24");
        }
        Thread.sleep(1000);
        driver.findElement(By.id("edit-issue-submit")).click(); Thread.sleep(1000);

        //act
        var actualDueDate = driver.findElement(By.id("due-date")).getText();
        var expectedDueDate = actualDate.equals("05/May/24") ? "04/May/24" : "05/May/24";

        //assert
        Assertions.assertEquals(expectedDueDate, actualDueDate);

    }
}

package test;

import com.codecool.page.HomePage;
import com.codecool.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.SQLOutput;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private String LOGIN_URL = System.getenv("BASE_URL") + "login.jsp";

    @BeforeEach
    public void before() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get(LOGIN_URL);
        loginPage.handleLogin();
        homePage = new HomePage(driver);
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    @Test
    public void handleLoginTest() {
        String actual = homePage.getLoginMessage();
        String expected = "Welcome to Jira Auto";
        Assertions.assertEquals(expected, actual);
    }
}

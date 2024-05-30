package test;

import com.codecool.page.HomePage;
import com.codecool.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    
    String LOGIN_URL = System.getenv("BASE_URL") + "login.jsp";

    @BeforeEach
    public void before() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
    }
    @AfterEach
    public void after() {
       driver.quit();
    }
    @Test
    public void testLogin() {
        driver.get(LOGIN_URL);
        loginPage.handleLogin();
        String actual = homePage.getLoginMessage();
        String expected = "Welcome to Jira Auto";
        Assertions.assertEquals(expected, actual);
    }
}

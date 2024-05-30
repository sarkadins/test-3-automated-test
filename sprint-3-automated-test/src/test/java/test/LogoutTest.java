package test;

import com.codecool.page.HomePage;
import com.codecool.page.LoginPage;
import com.codecool.page.LogoutPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LogoutTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private LogoutPage logoutPage;
    private String LOGIN_URL = System.getenv("BASE_URL") + "login.jsp";
    private String HOME_URL = System.getenv("BASE_URL") + "secure/Dashboard.jspa";

    @BeforeEach
    public void before() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get(LOGIN_URL);
        loginPage.handleLogin();
        homePage = new HomePage(driver);
        driver.get(HOME_URL);
        logoutPage = new LogoutPage(driver);
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    @Test
    public void checkLogoutPageLongMessageText() {
        clickOnLogout();
        String expected = "You are now logged out. Any automatic login has also been stopped.";
        String actual = getLogoutLongText();
        Assertions.assertEquals(expected, actual);
    }

    private void clickOnLogout() {
        homePage.handleLogout();
    }

    private String getLogoutLongText()
    {
        return logoutPage.getLogoutLongMessage();
    }

}

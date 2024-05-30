package test;

import com.codecool.page.CreateIssuesPage;
import com.codecool.page.HomePage;
import com.codecool.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateIssuesPageTest {

    WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    CreateIssuesPage createIssuesPage;
    private String LOGIN_URL = System.getenv("BASE_URL") + "login.jsp";
    private String CREATE_ISSUES_URL = System.getenv("BASE_URL") + "secure/CreateIssue!default.jspa";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get(LOGIN_URL);
        loginPage.handleLogin();
        createIssuesPage = new CreateIssuesPage(driver);
        driver.manage().window().maximize();
//        driver.get(CREATE_ISSUES_URL);
    }

    @AfterEach
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/createNewIssueData.csv", numLinesToSkip = 1)
    public void testCreateNewIssue(String input, String expected){
        createIssuesPage.createNewIssue(input);
        String actual = createIssuesPage.getPopupMessageText();
        Assertions.assertTrue(actual.contains(expected));
    }




}

package test;

import com.codecool.page.HomePage;
import com.codecool.page.IssuesPage;
import com.codecool.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class IssuesPageTest {
    private WebDriver driver;
    IssuesPage issuesPage;
    HomePage homePage;
    String BROWSE_ISSUES_URL = System.getenv("BASE_URL") + "browse/TOUCAN-100?jql=";
    String LOGIN_URL = System.getenv("BASE_URL") + "/login.jsp";
    String EDITCOALA_URL = System.getenv("BASE_URL") + "secure/EditIssue!default.jspa?id=18571";
    LoginPage loginPage;


    @BeforeEach
    public void before() {
        driver = new ChromeDriver();
        driver.get(LOGIN_URL);
        loginPage = new LoginPage(driver);
        loginPage.handleLogin();
        issuesPage = new IssuesPage(driver);
        homePage = new HomePage(driver);
        driver.manage().window().maximize();
    }
    @AfterEach
    public void after() {
      //driver.quit();
    }
   //@ParameterizedTest
   //@CsvFileSource(resources = "/browseissue.csv", numLinesToSkip = 1)
    public void testBrowseIssue(String input, String expected) {
        driver.get(BROWSE_ISSUES_URL);
        issuesPage.selectProject(input);
        String actual = issuesPage.getConfirmProject();
        Assertions.assertEquals(expected, actual);
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/editissue.csv", numLinesToSkip = 1)
    public void testEditIssue(String input, String expected) {
        driver.get(BROWSE_ISSUES_URL);
        issuesPage.editIssue(input);
        String actual = issuesPage.getConfirmUpdateMessage();
        Assertions.assertEquals(expected, actual);
    }
}
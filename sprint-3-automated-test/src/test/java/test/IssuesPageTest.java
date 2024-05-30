package test;

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
    private IssuesPage issuesPage;
    private String BROWSE_ISSUES_URL = System.getenv("BASE_URL") + "browse/TOUCAN-100?jql=";
    private String LOGIN_URL = System.getenv("BASE_URL") + "/login.jsp";
    private String PP_464__ISSUE_URL = System.getenv("BASE_URL") + "browse/PP-464?jql=project%20%3D%20PP";
    private LoginPage loginPage;


    @BeforeEach
    public void before() {
        driver = new ChromeDriver();
        driver.get(LOGIN_URL);
        loginPage = new LoginPage(driver);
        loginPage.handleLogin();
        issuesPage = new IssuesPage(driver);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void after() {
      driver.quit();
    }

   //@ParameterizedTest
   @CsvFileSource(resources = "/browseissue.csv", numLinesToSkip = 1)
    public void testBrowseIssue(String input, String expected) {
        driver.get(BROWSE_ISSUES_URL);
        issuesPage.selectProject(input);
        String actual = issuesPage.getConfirmProject(input);
        Assertions.assertEquals(expected, actual);
    }

    //@ParameterizedTest
    @CsvFileSource(resources = "/editissue.csv", numLinesToSkip = 1)
    public void testEditIssue(String input, String expected) {
        driver.get(BROWSE_ISSUES_URL);
        issuesPage.editIssue(input);
        String actual = issuesPage.getConfirmUpdateMessage();
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/componentTypeData.csv", numLinesToSkip = 1)
    public void testGetSelectedComponentText(String input, String expected) throws InterruptedException {
        driver.get(PP_464__ISSUE_URL);
        var actual = issuesPage.getSelectedComponentText(input);
        Assertions.assertEquals(expected, actual);
    }

}
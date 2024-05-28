package test;

import com.codecool.page.LoginPage;
import com.codecool.page.UITPPProjectPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class UITPPProjectPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private UITPPProjectPage UITPPProjectPage;
    private String LOGIN_URL = System.getenv("BASE_URL") + "login.jsp";
    private String UITPPPROJECT_URL = System.getenv("BASE_URL") + "plugins/servlet/project-config/PP/summary";

    @BeforeEach
    public void before() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get(LOGIN_URL);
        loginPage.handleLogin();
        UITPPProjectPage = new UITPPProjectPage(driver);
        driver.get(UITPPPROJECT_URL);
        UITPPProjectPage.clickOnPermissions();
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/permissionTypeData.csv", numLinesToSkip = 1)
    public void checkAccessTextByPermissionType(String input, String expected) {
        String actual = "";
        switch (input) {
            case "BrowseProjectsPermission" : actual = UITPPProjectPage.getBrowseProjectPermissionCheckFieldText(); break;
            case "CreateIssuesPermission" : actual =  UITPPProjectPage.getCreateIssuePermissionCheckFieldText(); break;
            case "EditIssuesPermission" : actual = UITPPProjectPage.getEditIssuePermissionCheckFieldText(); break;
        }

        Assertions.assertEquals(expected, actual);
    }

}

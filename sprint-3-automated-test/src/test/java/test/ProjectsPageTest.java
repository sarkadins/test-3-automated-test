package test;

import com.codecool.page.LoginPage;
import com.codecool.page.ProjectsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ProjectsPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProjectsPage projectsPage;
    private String LOGIN_URL = System.getenv("BASE_URL") + "login.jsp";
    private String PROJECTS_URL = System.getenv("BASE_URL") + "secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=all";

    @BeforeEach
    public void before() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        driver.get(LOGIN_URL);
        loginPage.handleLogin();
        projectsPage = new ProjectsPage(driver);
        driver.get(PROJECTS_URL);
    }

    @AfterEach
    public void after() {
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/browseProjectData.csv", numLinesToSkip = 1)
    public void checkBrowseProjectsResult(String input, String expected) {
        projectsPage.setFilterText(input);
        String actual = projectsPage.getTypeKey(input).toLowerCase();
        Assertions.assertEquals(expected, actual);
    }
}
package org.example;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

class Login {

WebDriver driver;
@BeforeEach
    void setUp() {
    driver = new ChromeDriver();
}
@Test
void testSuccessfulLogin() {
   driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
   driver.findElement(By.id("login-form-username")).sendKeys("automation11");
   driver.findElement(By.id("login-form-password")).sendKeys("CCAutoTest19.");
   driver.findElement(By.id("login-form-submit")).click();
   WebElement loginMessage = driver.findElement(By.xpath("//*[@id=\"gadget-10000\"]/div/div/h3"));
   String actual = loginMessage.getText();
   String expected = "Welcome to Jira Auto";
   assertEquals(expected, actual);
}
@Test
void testWrongPassword(){
    driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
    driver.findElement(By.id("login-form-username")).sendKeys("automation11");
    driver.findElement(By.id("login-form-password")).sendKeys("wrongPass");
    driver.findElement(By.id("login-form-submit")).click();
    WebElement errorMessage = driver.findElement(By.className("aui-message-error"));
    String actual = errorMessage.getText();
    String expected = "Sorry, your username and password are incorrect - please try again.";
    assertEquals(expected, actual);
}
@Test
void testGetCaptchaAfterThreeWrongPassword(){
    driver.get("https://jira-auto.codecool.metastage.net/login.jsp");
    driver.findElement(By.id("login-form-username")).sendKeys("automation10");
    driver.findElement(By.id("login-form-password")).sendKeys("wrongPass1");
    driver.findElement(By.id("login-form-submit")).click();
    driver.findElement(By.id("login-form-username")).sendKeys("automation10");
    driver.findElement(By.id("login-form-password")).sendKeys("wrongPass2");
    driver.findElement(By.id("login-form-submit")).click();
    driver.findElement(By.id("login-form-username")).sendKeys("automation10");
    driver.findElement(By.id("login-form-password")).sendKeys("wrongPass3");
    driver.findElement(By.id("login-form-submit")).click();
    WebElement captchaMessage = driver.findElement(By.className("aui-message-error"));
    String actual = captchaMessage.getText();
    String expected = "Sorry, your userid is required to answer a CAPTCHA question correctly.";
    assertEquals(expected, actual);
}
 @AfterEach
    void tearDown() {
    driver.quit();
 }

}
package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;


public class MainPageTest {

    public String baseUrl = "https://opensource-demo.orangehrmlive.com/";
    String driverPath = "C:\\Users\\Gargameth\\Desktop\\chromedriver.exe";
    public WebDriver driver;

    public MainPageTest() {
    }

    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
    }

    @Test
    public void logInWithValidCredentials() {
        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement userNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement loginInButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("orangehrm-login-button")));
        userNameField.sendKeys("Admin");
        passwordField.sendKeys("admin123");
        loginInButton.click();
    }

    @AfterTest
    public void terminateBrowser(){
        driver.close();
    }
}
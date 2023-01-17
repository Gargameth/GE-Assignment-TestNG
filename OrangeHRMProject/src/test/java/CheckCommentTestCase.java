import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;


public class CheckCommentTestCase {

    public String baseUrl = "https://opensource-demo.orangehrmlive.com/";
    String driverPath = "C:\\Users\\Gargameth\\Desktop\\chromedriver.exe";
    public WebDriver driver;

    public CheckCommentTestCase() {
    }

    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
    }

    @Test
    public void checkCommentText(){
        driver.get(baseUrl);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement userNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("username")));
        WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));
        WebElement loginInButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("orangehrm-login-button")));
        userNameField.sendKeys(System.getenv("username"));
        passwordField.sendKeys(System.getenv("password"));
        loginInButton.click();
        WebElement timeButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[(@href='/web/index.php/time/viewTimeModule')]")));
        timeButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[(@class='oxd-button oxd-button--medium oxd-button--text oxd-table-cell-action-space')]")));
        List<WebElement> viewButtons = driver.findElements(By.xpath("//button[(@class='oxd-button oxd-button--medium oxd-button--text oxd-table-cell-action-space')]"));
        WebElement timesheetPendingActionViewButton = viewButtons.get(1);
        timesheetPendingActionViewButton.click();
        WebElement commentButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[(@class='oxd-icon-button oxd-icon-button--secondary orangehrm-timesheet-icon-comment')]")));
        commentButton.click();

        WebElement commentTextElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#app > div.oxd-layout > div.oxd-layout-container > div.oxd-layout-context > div > form > div.oxd-overlay.oxd-overlay--flex.oxd-overlay--flex-centered > div > div > div > form > div:nth-child(2) > div > div:nth-child(2) > textarea")));
        String commentText = commentTextElement.getAttribute("value");
        System.out.println(commentText);
        Assert.assertEquals(commentText, "Leadership Development");
    }

    @AfterTest
    public void terminateBrowser(){
        driver.quit();
    }
}
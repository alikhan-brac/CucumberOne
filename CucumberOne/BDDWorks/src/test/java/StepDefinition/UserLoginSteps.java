package StepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class UserLoginSteps {
    private static WebDriver driver;
    private final int MAX_WAIT = 10;
    private WebDriverWait webDriverWait;
    private static final String baseURL = "http://uitestingplayground.com/";
    private static final String userId = "userId";
    private static final String password = "pwd";

    @Given("User navigates to login page")
    public void navigateToLogin() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(MAX_WAIT));
        driver.get(baseURL);
        driver.findElement(By.xpath("//*[@id=\"overview\"]/div/div[4]/div[2]/h3/a")).click();
    }

    @When("User provides valid user id")
    public void provideUserId() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("/html/body/section/div/h3"))));
        driver.findElement(By.xpath("//input[contains(@placeholder,'User Name')]")).sendKeys(userId);
    }

    @And("User provides valid password")
    public void providePassword() {
        driver.findElement(By.xpath("//input[contains(@name,'Password')]")).sendKeys(password);
    }

    @And("Clicks on login button")
    public void clickLogin() {
        driver.findElement(By.xpath("//*[@id=\"login\"]")).click();
    }

    @Then("User should see login success message")
    public void loginSuccess() {
        String expectedText = String.format("Welcome, %s!", userId);
        String actualText = driver.findElement(By.xpath("//*[@id=\"loginstatus\"]")).getText();
        Assert.assertEquals(actualText, expectedText, "mismatch in welcome message");
    }

    @And("Close the browser")
    public void closeBrowser() {
        driver.quit();
    }
}

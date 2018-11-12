import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ListenerExample {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        Reporter.setEscapeHtml(false);
    }

    @Test
    public void validateSearchPageTitle() {
        log("Open main page");
        driver.navigate().to("http://www.google.com");
        log("Check page title");
        log("Page title is: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "Google", "Unexpected page title!");
        log("Type search query");
        Assert.assertTrue(driver.findElements(By.name("q")).size() > 0, "No query input found!");
        WebElement element = driver.findElement(By.name("q"));
        element.sendKeys("Selenium");
        element.submit();
        log("Wait for search results");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.getTitle().toLowerCase().startsWith("selenium");
            }
        });
        log("Check page title after typing search query");
        log("Page title is: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("Google"), "Page title doesn't contain 'Google' string.");
    }

    @Test(dependsOnMethods = "validateSearchPageTitle")
    public void failedTest() {
        log("Starting failed test");
        Assert.assertTrue(false, "Condition failed");
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    private void log(String message) {
        Reporter.log(message + "<br>");
    }

}

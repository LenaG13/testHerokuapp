import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ContextMenuTest {

    static final String BASE_URL = "http://the-internet.herokuapp.com/context_menu";
    static final String DRIVER_PATH = "src/test/resources/chromedriver.exe";
    static final String WEB_DRIVER = "webdriver.chrome.driver";

    ChromeDriver driver;
    ChromeOptions chromeOptions;
    Actions action;
    Alert alert;
    WebElement element;

    static final By HOT_SPOT = By.id("hot-spot");
    public String context = "You selected a context menu";

    @BeforeClass
    public void config() {
        System.setProperty(WEB_DRIVER, DRIVER_PATH);
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--ignore-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(chromeOptions);
        driver.get(BASE_URL);
        action = new Actions(driver);
    }

    @Test
    public void ContextMenuTest() {
        element = driver.findElement(HOT_SPOT);

        action.contextClick(element).build().perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(), context, "Alert message is not correct");
        driver.switchTo().alert().accept();
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.close();
        driver.quit();
    }

}

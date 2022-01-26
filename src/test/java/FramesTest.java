import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FramesTest {

    static final String BASE_URL = "http://the-internet.herokuapp.com/frames";
    static final String DRIVER_PATH = "src/test/resources/chromedriver.exe";
    static final String WEB_DRIVER = "webdriver.chrome.driver";

    ChromeDriver driver;
    ChromeOptions chromeOptions;
    WebDriverWait wait;
    WebElement iFrame, iFrameLocator;

    static final By IFRAME_BUTTON = By.linkText("iFrame");
    static final By IFRAME_TEXT_FIELD = By.id("tinymce");
    static final By IFRAME_BODY = By.id("mce_0_ifr");
    static final String IFRAME_TEXT = "Your content goes here.";

    @BeforeClass
    public void config() {
        System.setProperty(WEB_DRIVER, DRIVER_PATH);
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--ignore-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(chromeOptions);
        driver.get(BASE_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void iFrameTest() {
        iFrame = driver.findElement(IFRAME_BUTTON);
        boolean actual = iFrame.isDisplayed();
        Assert.assertEquals(actual, true, "Link is not on the webPage");
        iFrame.click();

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(IFRAME_BODY));

        iFrameLocator = driver.findElement(IFRAME_TEXT_FIELD);
        Assert.assertEquals(iFrameLocator.getText(), IFRAME_TEXT, "IFrame text is not correct");
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.close();
        driver.quit();
    }

}

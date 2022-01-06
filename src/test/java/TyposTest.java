import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TyposTest {

    static final String BASE_URL = "http://the-internet.herokuapp.com/typos";
    static final String DRIVER_PATH = "src/test/resources/chromedriver.exe";
    static final String WEB_DRIVER = "webdriver.chrome.driver";
    ChromeDriver driver;
    ChromeOptions chromeOptions;

    @BeforeClass
    public void config() {
        System.setProperty(WEB_DRIVER, DRIVER_PATH);
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get(BASE_URL);
    }

    @Test
    public void typosTest() {
        List<WebElement> typos = driver.findElements(By.tagName("p"));
        String expectedResult = "Sometimes you'll see a typo, other times you won't.";
        Assert.assertEquals(typos.get(1).getText(), expectedResult, "incorrect writing");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}

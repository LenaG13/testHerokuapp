import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class CheckboxesTest {

    static final String BASE_URL = "http://the-internet.herokuapp.com/checkboxes";
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
    public void checkBoxesTest() {
        List<WebElement> elements = driver.findElements(By.cssSelector("[type=checkbox]"));

        Assert.assertFalse(elements.get(0).isSelected(), "unchecked 1");
        elements.get(0).click();
        Assert.assertTrue(elements.get(0).isSelected(), "checked 1");

        Assert.assertTrue(elements.get(1).isSelected(), "checked 2");
        elements.get(1).click();
        Assert.assertFalse(elements.get(1).isSelected(), "unchecked 2");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}

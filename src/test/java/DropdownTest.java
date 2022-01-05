import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DropdownTest {

    static final String BASE_URL = "http://the-internet.herokuapp.com/dropdown";
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

    // variant 1 By.id
    @Test
    public void dropdownTest() {
        WebElement dropdown = driver.findElement(By.id("dropdown"));
        Select dropdownSelect = new Select(dropdown);
        Assert.assertFalse(dropdownSelect.getAllSelectedOptions().isEmpty());

        dropdownSelect.selectByVisibleText("Option 1");
        Assert.assertEquals(dropdownSelect.getFirstSelectedOption().getText(), "Option 1 is invalid");

        dropdownSelect.selectByVisibleText("Option 2");
        Assert.assertEquals(dropdownSelect.getFirstSelectedOption().getText(), "Option 2 is invalid");
    }

    // variant 2 By.xpath
    @Test
    public void dropDownTest() {
        WebElement element1 = driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[2]"));
        element1.click();
        Assert.assertEquals(element1.isSelected(), true, "Option 1 is invalid");

        WebElement element2 = driver.findElement(By.xpath("//*[@id=\"dropdown\"]/option[3]"));
        element2.click();
        Assert.assertEquals(element2.isSelected(), true, "Option 2 is invalid");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}

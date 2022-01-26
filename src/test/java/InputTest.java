import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class InputTest {

    static final String BASE_URL = "http://the-internet.herokuapp.com/inputs";
    static final String DRIVER_PATH = "src/test/resources/chromedriver.exe";
    static final String WEB_DRIVER = "webdriver.chrome.driver";
    ChromeDriver driver;
    ChromeOptions chromeOptions;
    int inputValue = 1;

    @BeforeClass
    public void config() {
        System.setProperty(WEB_DRIVER, DRIVER_PATH);
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(chromeOptions);
        driver.get(BASE_URL);
    }

    @Test
    public void inputTest() {
        validateInputIncrease(inputValue);
        validateInputDecrease(inputValue);
    }


    private void validateInputIncrease(int inputValue) { // проверить увеличение значения
        validateInputChange(inputValue, Keys.ARROW_UP, 1, "Неверное значение элемента input после увеличения");
    }

    private void validateInputDecrease(int inputValue) { // проверить уменьшение значения
        validateInputChange(inputValue, Keys.ARROW_DOWN, -1, "Неверное значение элемента input после уменьшения");
    }

    private void validateInputChange(int inputValue, Keys key, int delta, String message) {
        WebElement input = driver.findElement(By.tagName("input")); // выделить элемент
        input.clear(); // очистить значение
        input.sendKeys(String.valueOf(inputValue));
        input.sendKeys(key); // нажать увеличить значение
        Assert.assertEquals(input.getAttribute("value"), String.valueOf(inputValue + delta), message); // проверить значение
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}

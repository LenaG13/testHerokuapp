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

public class DynamicControlsTest {

    static final String BASE_URL = "http://the-internet.herokuapp.com/dynamic_controls";
    static final String DRIVER_PATH = "src/test/resources/chromedriver.exe";
    static final String WEB_DRIVER = "webdriver.chrome.driver";

    ChromeDriver driver;
    ChromeOptions chromeOptions;
    WebDriverWait wait;
    WebElement checkbox, removeButton, message, inputField, enableButton;

    static final By CHECKBOX_LOCATOR = By.cssSelector("input[type='checkbox']");
    static final By REMOVE_BUTTON = By.cssSelector("[onclick='swapCheckbox()']");
    static final By ENABLE_BUTTON = By.cssSelector("[onclick='swapInput()']");
    static final By INPUT_FIELD = By.cssSelector("input[type='text']");
    static final By MESSAGE = By.cssSelector("#message");
    //static final By MESSAGE = By.id("message");

    public String message_checkbox = "It's gone!";
    public String message_input = "It's enabled!";

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
    public void DynamicControlTest() {
        checkoutCheckBox();
        checkoutInput();
    }

    public void checkoutCheckBox() {
        checkbox = driver.findElement(CHECKBOX_LOCATOR);
        Assert.assertTrue(checkbox.isDisplayed(), "Checkbox is not displayed");

        removeButton = driver.findElement(REMOVE_BUTTON);
        Assert.assertEquals(removeButton.getText(), "Remove", "RemoveButton text is not correct");
        removeButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(MESSAGE));

        message = driver.findElement(MESSAGE);
        Assert.assertEquals(message.getText(), message_checkbox, "Message Checkbox is not correct");
    }

    public void checkoutInput() {
        inputField = driver.findElement(INPUT_FIELD);
        Assert.assertTrue(inputField.isDisplayed(), "Input is not displayed");

        enableButton = driver.findElement(ENABLE_BUTTON);
        Assert.assertEquals(enableButton.getText(), "Enable", "EnableButton text is not correct");
        enableButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(MESSAGE));

        message = driver.findElement(MESSAGE);
        Assert.assertEquals(message.getText(), message_input, "Message Input is not correct");
        Assert.assertEquals(enableButton.getText(), "Disable", "DisableButton text is not correct");
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.close();
        driver.quit();
    }

}

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class NotificationMessagesTest {

    static final String BASE_URL = "http://the-internet.herokuapp.com/notification_message_rendered";
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @Test
    public void notificationMessages() {
        String message1 = "Action successful\n" + "×";
        String message2 = "Action unsuccesful, please try again\n" + "×";
        WebElement clickHere = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p/a"));
        clickHere.click();
        String message = driver.findElement(By.xpath("//*[@id=\"flash\"]")).getText();
        Assert.assertEquals(message, message1, "Action successful - invalid");
        Assert.assertEquals(message, message2, "Action unsuccesful, please try again - invalid");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}

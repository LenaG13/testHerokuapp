import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddRemoveElements {
    static final String BASE_URL = "http://the-internet.herokuapp.com/add_remove_elements/";
    static final String DRIVER_PATH = "src/test/resources/chromedriver.exe";
    static final String WEBDRIVER = "webdriver.chrome.driver";
    public WebDriver driver;

    @BeforeTest
    public void launchBrowser() {
        System.setProperty(WEBDRIVER, DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(BASE_URL);
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
    }
}

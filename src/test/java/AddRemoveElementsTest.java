import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class AddRemoveElementsTest {

    static final String BASE_URL = "http://the-internet.herokuapp.com/add_remove_elements/";
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
    public void addRemoveElementsTest() {
        WebElement buttonAddElement = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
         for (int i = 0; i < 2; i++) {
             buttonAddElement.click();
         }
        WebElement buttonDelete = driver.findElement(By.className("added-manually"));
        buttonDelete.click();
        int countButtonDelete = driver.findElements(By.className("added-manually")).size();
        Assert.assertEquals(countButtonDelete, 1, "Delete - отсутствует");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}

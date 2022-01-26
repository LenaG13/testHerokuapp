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

import java.io.File;
import java.time.Duration;

public class FileUploadTest {

    static final String BASE_URL = "http://the-internet.herokuapp.com/upload";
    static final String DRIVER_PATH = "src/test/resources/chromedriver.exe";
    static final String WEB_DRIVER = "webdriver.chrome.driver";

    ChromeDriver driver;
    ChromeOptions chromeOptions;
    WebDriverWait wait;
    WebElement file, uploadButton, uploadedFile;

    static final By BROWSE_BUTTON = By.id("file-upload");
    static final By UPLOAD_BUTTON = By.id("file-submit");
    static final By UPLOADED_FILES = By.id("uploaded-files");
    private final String FILE_UPLOAD = "src/test/resources/database.txt";

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
        File filepath = new File(FILE_UPLOAD);
        String absolutePath = filepath.getAbsolutePath();
        String fileName = filepath.getName();

        file = driver.findElement(BROWSE_BUTTON);
        file.sendKeys(absolutePath);

        uploadButton = driver.findElement(UPLOAD_BUTTON);
        uploadButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(UPLOADED_FILES));

        uploadedFile = driver.findElement(UPLOADED_FILES);
        Assert.assertEquals(uploadedFile.getText(), fileName, "Uploaded file title is not correct");
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.close();
        driver.quit();
    }

}

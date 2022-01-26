import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;

public class FileDownloadTest {

    static final String BASE_URL = "http://the-internet.herokuapp.com/download";
    static final String DRIVER_PATH = "src/test/resources/chromedriver.exe";
    static final String WEB_DRIVER = "webdriver.chrome.driver";

    ChromeDriver driver;
    ChromeOptions chromeOptions;
    WebDriverWait wait;

    public static final String DOWNLOAD_FILE_NAME = "some-file.txt";
    public static final String FILE_PATH = "src/test/resources";
    public static final File RESOURCE_PATH_FILE = new File(FILE_PATH);
    public static final String ABSOLUTE_RESOURCE_PATH = RESOURCE_PATH_FILE.getAbsolutePath();

    @BeforeClass
    public void config() {
        System.setProperty(WEB_DRIVER, DRIVER_PATH);
        chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--ignore-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", ABSOLUTE_RESOURCE_PATH);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(chromeOptions);
        driver.get(BASE_URL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void fileDownloadTest() throws InterruptedException {
        downloadFile(DOWNLOAD_FILE_NAME);
        boolean result = checkDownloadedFile(DOWNLOAD_FILE_NAME);
        Assert.assertTrue(result, "Downloaded document is not found");
        removeDownloadFile(DOWNLOAD_FILE_NAME);
    }

    public void downloadFile(String fileName) {
        driver.findElement(By.partialLinkText(fileName)).click();
    }

    public void removeDownloadFile(String fileName) {
        File pathToFile = new File("src/test/resources/" + fileName);
        pathToFile.delete();
    }

    private boolean checkDownloadedFile(String testFileName) throws InterruptedException {
        boolean found = false;
        for (int i = 0; i < 10; i++) {
            File folder = new File(ABSOLUTE_RESOURCE_PATH);
            File[] listOfFiles = folder.listFiles();
            File f = null;
            for (File listOfFile : listOfFiles) {
                if (listOfFile.isFile()) {
                    String fileName = listOfFile.getName();
                    System.out.println("File " + listOfFile.getName());
                    if (fileName.matches(testFileName)) {
                        f = new File(fileName);
                        found = true;
                    }
                }
            }
            if (found) {
                break;
            }
            Thread.sleep(1000);
        }
        return found;
    }

    @AfterClass(alwaysRun = true)
    public void teardown() {
        driver.close();
        driver.quit();
    }

}

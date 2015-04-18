package testTask.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import testTask.controls.Pages;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;

    @BeforeClass
    public void setUp() throws IOException{
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+ File.separator+"lib\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.DEFAULT_WAIT_TIMEOUT, TimeUnit.SECONDS);

        Pages.setupPages();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}

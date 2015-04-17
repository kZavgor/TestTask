package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTest {

    public static WebDriver driver;


    @BeforeClass
    public void setUp() throws IOException{
        ChromeOptions options = new ChromeOptions();
        options.setBinary(System.getProperty("user.bin")+"\\lib\\driver");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}

package testTask.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTest {

    public static WebDriver driver;


    @BeforeClass
    public void setUp() throws IOException{
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary(System.getProperty("user.bin")+"\\lib\\driver\\chromedriver.exe");
//        driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
//        driver.quit();
    }

}

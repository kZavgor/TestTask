package testTask.tests;


import org.junit.Assert;
import org.testng.annotations.Test;
import testTask.base.BaseTest;
import testTask.controls.Pages;

public class Test_test extends BaseTest{

    @Test
    public void loginTest(){

        Pages.loginPage().openPage();
        Pages.loginPage().typeEmail("k.zavgor@gmail.com");
        Pages.loginPage().typePassword("4271990naruto");
        Pages.loginPage().clickSignInButton();
        Pages.receivedMailsPage().waitForPageToLoad();

        Pages.receivedMailsPage().clickWriteLetterButton();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Test(dependsOnMethods = "test1")
    public void test2(){
        Assert.assertTrue(true);
    }
}

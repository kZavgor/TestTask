package testTask.tests;


import org.junit.Assert;
import org.testng.annotations.Test;
import testTask.base.BaseTest;
import testTask.controls.Pages;

public class Test_test extends BaseTest{

    @Test
    public void test1(){

        Pages.loginPage().openPage();
    }

    @Test(dependsOnMethods = "test1")
    public void test2(){
        Assert.assertTrue(true);
    }
}

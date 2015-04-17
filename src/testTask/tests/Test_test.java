package testTask.tests;


import testTask.base.BaseTest;
import org.junit.Assert;
import org.testng.annotations.Test;

public class Test_test extends BaseTest{

    @Test
    public void test1(){
        Assert.assertFalse(false);
    }

    @Test(dependsOnMethods = "test1")
    public void test2(){
        Assert.assertTrue(true);
    }
}

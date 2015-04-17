package testTask.tests;


import org.junit.Assert;
import org.testng.annotations.Test;
import testTask.util.WebElementsParcer;

public class Test_test{

    @Test
    public void test1(){

        Assert.assertFalse(false);
    }

    @Test(dependsOnMethods = "test1")
    public void test2(){
        Assert.assertTrue(false);
    }
}

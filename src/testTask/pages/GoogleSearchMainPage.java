package testTask.pages;

import org.testng.Assert;
import testTask.base.BasePage;

public class GoogleSearchMainPage extends BasePage{
    public void waitForPageToLoad(){
        waitForElementVisibility("logo");
        Assert.assertTrue(isElementVisible("logo"),
                "Page loaded incorrectly");
    }

}

package testTask.pages;


import testTask.base.BasePage;
import testTask.base.Constants;

public class LoginPage extends BasePage{

    public void openPage() {
        driver.get(Constants.BASE_URL);
        waitForElementVisibility("logo");
    }

}

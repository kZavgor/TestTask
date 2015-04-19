package testTask.pages;


import testTask.base.BasePage;
import testTask.base.Constants;

public class LoginPage extends BasePage{

    public void openPage() {
        driver.get(Constants.BASE_URL);
        waitForElementVisibility("logo");
    }

    public void typeEmail(String email){
        type(email, "emailInput");
    }

    public void typePassword(String password){
        type(password, "passwordInput");
    }

    public void clickSignInButton() {
        click("signInButton");
        if(isElementPresent("accountRecoveryLabel"))click("declineRecoveryPassword");
    }



}

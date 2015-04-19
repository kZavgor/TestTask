package testTask.pages;


import testTask.base.BasePage;

public class ReceivedMailsPage extends BasePage{
    public void waitForPageToLoad(){
        waitForElementVisibility("userNavDropdownHeader");
    }

    public void clickWriteLetterButton(){
        click("writeLetterButton");
        waitForElementVisibility("writeLetterFrame");
    }
}

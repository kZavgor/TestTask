package testTask.pages;


import org.testng.Assert;
import testTask.base.BasePage;

public class ReceivedMailsPage extends BasePage{
    public void waitForPageToLoad(){
        waitForElementVisibility("userNavDropdownHeader");
    }

    public void clickWriteLetterButton(){
        click("writeLetterButton");
    }

    public void waitAttachmentOversizeErrorPopup(){
        waitForElementVisibility("fileOverSizeErrorPopup");
    }

    public void assertAttachmentOversizeErrorPopup(){
        Assert.assertTrue(isElementPresent("declineProposeButton"),
                "Decline button is missed!");
        Assert.assertTrue(isElementPresent("useGoogleDriveButton"),
                "Use google drive button is missed!");
    }

    public void refreshPage() {
        super.refreshPage();
        waitForPageToLoad();
    }

    public void clickEmailBySubject(String subject){
        getElement("receivedEmailTableRow", subject).click();
    }


}

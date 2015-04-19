package testTask.tests;


import org.junit.Assert;
import org.testng.annotations.Test;
import testTask.base.BaseTest;
import testTask.base.Constants;
import testTask.controls.Actions;
import testTask.controls.Pages;

public class OversizeAttachmentCannotSentTest extends BaseTest{

    @Test
    public void loginTest(){
        Actions.googleActions().doLogin(Constants.DEFAULT_ACCOUNT_EMAIL,
                Constants.DEFAULT_ACCOUNT_PASSWORD);
    }

    @Test(dependsOnMethods = "loginTest")
    public void openSendMailFrameTest(){
        Pages.receivedMailsPage().clickWriteLetterButton();
        Pages.sendMailFrame().waitFrameToLoad();
        Pages.sendMailFrame().assertFrame();
    }

    @Test(dependsOnMethods = "openSendMailFrameTest")
    public void checkSendOversizeAttachmentTest(){
        Pages.sendMailFrame().attachFileToLetter(System.getProperty("user.dir") +
                Constants.OVERSIZE_ATTACHMENT_PATH);
        Pages.receivedMailsPage().waitAttachmentOversizeErrorPopup();
        Pages.receivedMailsPage().assertAttachmentOversizeErrorPopup();
    }

}

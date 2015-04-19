package testTask.tests;


import org.testng.annotations.Test;
import testTask.base.BaseTest;
import testTask.base.Constants;
import testTask.controls.Actions;
import testTask.controls.Pages;
import testTask.data.MailData;

public class CheckIncludedLInkToMailTest extends BaseTest{

    MailData mailData;

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
    public void sendEmailCheck(){
        mailData = MailData.generateDefaultMail();
        Actions.googleActions().sendEmail(mailData);
    }

    @Test(dependsOnMethods = "sendEmailCheck")
    public void includedLinkCheck(){
        Pages.receivedMailsPage().refreshPage();
        Pages.receivedMailsPage().clickEmailBySubject(mailData.getMailSubject());
        Pages.receivedMail().waitForPageLoad();
        Pages.receivedMail().clickIncludedLinkByLabel(mailData.getLinkData().getLinkLabel());
        Pages.googleSearchMainPage().waitForPageToLoad();
    }
}

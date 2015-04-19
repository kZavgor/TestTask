package testTask.actions;

import testTask.controls.Pages;
import testTask.data.MailData;

public class GoogleActions {

    public void doLogin(String email, String password){
        Pages.loginPage().openPage();
        Pages.loginPage().typeEmail(email);
        Pages.loginPage().typePassword(password);
        Pages.loginPage().clickSignInButton();
        Pages.receivedMailsPage().waitForPageToLoad();
    }

    public void sendEmail(MailData mailData){
        Pages.sendMailFrame().typeReceiverEmail(mailData.getReceiverMail());
        Pages.sendMailFrame().typeEmailSubject(mailData.getMailSubject());

        if(mailData.getLinkData() != null){
            Pages.sendMailFrame().clickAddLinkButton();
            Pages.sendMailFrame().typeLinktext(mailData.getLinkData().getLinkLabel());
            Pages.sendMailFrame().setLinkToURLRadioState(mailData.getLinkData().isWebUrl());
            Pages.sendMailFrame().typeLinkURL(mailData.getLinkData().getLinkUrl());
            Pages.sendMailFrame().clickSubitAddingLink();
        }

        Pages.sendMailFrame().clickSendMailButton();
    }
}

package testTask.pages;


import org.testng.Assert;
import testTask.base.BasePage;


public class SendMailFrame extends BasePage {

    public void waitFrameToLoad(){
        waitForElementVisibility("writeLetterFrame");
    }

    public void assertFrame(){
        Assert.assertTrue(isElementVisible("receiverEmailInput"),
                "Receiver email header is absent");
        Assert.assertTrue(isElementVisible("subjectInput"),
                "Subject input is absent");
        Assert.assertTrue(isElementVisible("emailTextInput"),
                "Email body input is absent");
        Assert.assertTrue(isElementVisible("sendMailButton"),
                "Send mail button is absent");
    }

    public void attachFileToLetter(String filePath){
        waitForElementVisibility("encloseFileButton");
        uploadFile(filePath, "encloseFileButton");
    }

    public void typeReceiverEmail(String email){
        type(email, "receiverEmailInput");
    }

    public void typeEmailSubject(String subject){
        type(subject, "subjectInput");
    }

    public void typeEmailBody(String text){
        type(text, "emailTextInput");
    }

    public void clickAddLinkButton(){
        click("addLinkButton");
        waitForElementVisibility("linkTextInput");
    }

    public void typeLinktext(String text){
        type(text, "linkTextInput");
    }

    public void typeLinkURL(String url){
        type(url, "linkURLInput");
    }

    public void setLinkToURLRadioState(Boolean state){
        setCheckboxState(state, "linkURLInput");
    }

    public void clickSubitAddingLink(){
        click("submitAddLinkButton");
    }

    public void clickSendMailButton(){
        click("sendMailButton");
        waitForElementVisibility("mailSentMessage");
    }

}

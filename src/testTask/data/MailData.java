package testTask.data;


import testTask.base.Constants;

public class MailData {
    private String receiverMail;
    private String mailSubject;
    private String mailBody;
    private LinkData linkData;

    public MailData() {

    }

    public  MailData(String receiverMail, String mailSubject, String mailBody, LinkData linkData){
        setReceiverMail(receiverMail);
        setMailSubject(mailBody);
        setMailBody(mailBody);
        setLinkData(linkData);
    }

    public static MailData generateDefaultMail(){
        MailData mailData = new MailData();
        mailData.setReceiverMail(Constants.DEFAULT_ACCOUNT_EMAIL);
        mailData.setMailSubject("Subject" + System.currentTimeMillis());

        LinkData linkData = new LinkData();
        linkData.setLinkLabel("GOOGLE");
        linkData.setLinkUrl("http://google.com");
        linkData.setWebUrl(true);

        mailData.setLinkData(linkData);

        return mailData;
    }

    public String getReceiverMail() {
        return receiverMail;
    }

    public String getMailBody() {
        return mailBody;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public LinkData getLinkData() {
        return linkData;
    }

    public void setLinkData(LinkData linkData) {
        this.linkData = linkData;
    }

    public void setMailBody(String mailBody) {
        this.mailBody = mailBody;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public void setReceiverMail(String receiverMail) {
        this.receiverMail = receiverMail;
    }
}

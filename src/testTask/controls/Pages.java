package testTask.controls;


import testTask.pages.*;

import java.lang.management.GarbageCollectorMXBean;

public class Pages {


    private LoginPage loginPage;
    private ReceivedMailsPage receivedMailsPage;
    private SendMailFrame sendMailFrame;
    private ReceivedMail reseivedMail;
    private GoogleSearchMainPage googleSearchMainPage;

    private static Pages pages;

    private Pages() {
        this.loginPage = new LoginPage();
        this.receivedMailsPage = new ReceivedMailsPage();
        this.sendMailFrame = new SendMailFrame();
        this.reseivedMail = new ReceivedMail();
        this.googleSearchMainPage = new GoogleSearchMainPage();

    }

    public static void setupPages() {
        pages = new Pages();
    }

    public static LoginPage loginPage() {return pages.loginPage;}

    public static ReceivedMailsPage receivedMailsPage() {return pages.receivedMailsPage;}

    public static SendMailFrame sendMailFrame() {return pages.sendMailFrame;}

    public static ReceivedMail receivedMail() {return pages.reseivedMail;}

    public static GoogleSearchMainPage googleSearchMainPage() {return pages.googleSearchMainPage;}

}

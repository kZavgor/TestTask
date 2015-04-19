package testTask.controls;


import testTask.pages.LoginPage;
import testTask.pages.ReceivedMailsPage;

public class Pages {


    private LoginPage loginPage;
    private ReceivedMailsPage receivedMailsPage;
    private static Pages pages;

    private Pages() {
        this.loginPage = new LoginPage();
        this.receivedMailsPage = new ReceivedMailsPage();

    }

    public static void setupPages() {
        pages = new Pages();
    }

    public static LoginPage loginPage() {return pages.loginPage;}

    public static ReceivedMailsPage receivedMailsPage() {return pages.receivedMailsPage;}
}

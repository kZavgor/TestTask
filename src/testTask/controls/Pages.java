package testTask.controls;


import testTask.pages.LoginPage;

public class Pages {


    private LoginPage loginPage;
    private static Pages pages;

    private Pages() {
        this.loginPage = new LoginPage();

    }

    public static void setupPages() {
        pages = new Pages();
    }

    public static LoginPage loginPage() {return pages.loginPage;}
}

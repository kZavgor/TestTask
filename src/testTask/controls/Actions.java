package testTask.controls;


import testTask.actions.GoogleActions;

public class Actions {

    private GoogleActions googleActions;
    private static Actions actions;

    private Actions(){this.googleActions =new GoogleActions();}

    public static void setupActions() {
        actions = new Actions();
    }

    public static GoogleActions googleActions() {return actions.googleActions;}
}

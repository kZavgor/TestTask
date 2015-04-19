package testTask.pages;

import org.openqa.selenium.WebElement;
import testTask.base.BasePage;

public class ReceivedMail extends BasePage {
    public void waitForPageLoad(){
        waitForElementVisibility("pageLabel");
    }

    public void clickIncludedLinkByLabel(String label) {
        getElement("mailLink", label).click();
        switchToNewTab();
    }
}

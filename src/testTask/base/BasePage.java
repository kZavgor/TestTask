package testTask.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import testTask.util.WebElementsParser;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BasePage {

    public static WebDriver driver;
    private Map<String, String[]> webElements;


    public BasePage(){
        String className = getClass().getName();
        String pageElementsXMLFilePath = System.getProperty("user.dir") + "\\config\\elements\\" +
                className.substring(className.lastIndexOf(".") + 1, className.length()) + ".xml";

        Assert.assertTrue(new File(pageElementsXMLFilePath).exists(), "XML page locators file does not exists.");
        this.webElements = WebElementsParser.parse(pageElementsXMLFilePath);
        driver = BaseTest.driver;
    }

    protected WebElement getElement(String elementName){
        return driver.findElement(getFindElementOption(this.webElements.get(elementName)));
    }

    protected WebElement getElement(String elementName, String pattern){
        return driver.findElement(getFindElementOption(this.webElements.get(elementName), pattern));
    }

    protected List<WebElement> getElements(String elementName){
        return driver.findElements(getFindElementOption(this.webElements.get(elementName)));
    }

    private By getFindElementOption(String[] locatorsData, String pattern){
        locatorsData[1] = String.format(locatorsData[1], pattern);
        return this.getFindElementOption(locatorsData);
    }

    private By getFindElementOption(String[] locatorData){

        By by = null;
        String type = locatorData[0];
        String value = locatorData[1];

        switch (type) {
            case "xpath": by = By.xpath(value);
                break;

            case "css": by = By.cssSelector(value);
                break;

            case "id": by = By.id(value);
                break;

            case "class": by = By.className(value);
                break;

            case "name": by = By.name(value);
                break;
        }
        return by;
    }

    protected String getElementAttributeValue(String attributeName, String elementName) {
        WebElement element = this.getElement(elementName);
        return element.getAttribute(attributeName);
    }

    protected String getElementText(String elementName) {
        WebElement element = this.getElement(elementName);
        return element.getText();
    }

    protected void type(String value, String elementName) {
        WebElement inputElement = this.getElement(elementName);
        inputElement.clear();
        inputElement.sendKeys(value);
    }

    protected boolean isCheckboxChecked(String elementName) {
        return this.getElement(elementName).isSelected();
    }

    protected void setCheckboxState(boolean checked, String elementName) {
        if (checked ^ this.isCheckboxChecked(elementName)) {
            this.click(elementName);
        }
    }

    protected void click(String elementName) {
        WebElement element = this.getElement(elementName);
        element.click();
    }

    protected int getElementsCount(String elementName) {
        return this.getElementsCountWithWait(0, elementName);
    }

    protected int getElementsCountWithWait(int waitInSeconds, String elementName) {
        driver.manage().timeouts().implicitlyWait(waitInSeconds, TimeUnit.SECONDS);
        int size =  this.getElements(elementName).size();
        driver.manage().timeouts().implicitlyWait(Constants.DEFAULT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        return size;
    }

    protected boolean isElementPresent(String elementName) {
        return (this.getElementsCount(elementName) > 0);
    }

    protected boolean isElementPresentWithWait(int waitInSeconds, String elementName) {
        return (this.getElementsCountWithWait(waitInSeconds, elementName) > 0);
    }

    protected boolean isElementVisible(String elementName) {
        return this.isElementVisibleWithWait(1, elementName);
    }

    protected boolean isElementVisibleWithWait(int waitInSeconds, String elementName) {
        By by = this.getFindElementOption(webElements.get(elementName));
        WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable th) {
            return false;
        }
        return true;
    }

    protected void waitForElementVisibility(String elementName) {
        By by = this.getFindElementOption(this.webElements.get(elementName));
        WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void waitForElementVisibility(int timeout, String elementName, Object... args) {
        By by = this.getFindElementOption(this.webElements.get(elementName));
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void switchToNewTab() {
        Set<String> handles = driver.getWindowHandles();
        String current = getCurrentPageHandler();
        handles.remove(current);
        for (String handle : handles) {
            driver.switchTo().window(handle);
        }
    }

    public String getCurrentPageHandler() {
        return driver.getWindowHandle();
    }

    public void uploadFile(String filePath, String uploadButtonElementName){

        getElement(uploadButtonElementName).click();

        System.out.println(filePath);
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        } catch(AWTException exception){
            exception.printStackTrace();
        }
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }






}

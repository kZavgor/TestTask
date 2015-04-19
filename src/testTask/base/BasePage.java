package testTask.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import testTask.util.WebElementsParser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        for (String[] value : webElements.values()) {
            System.out.println("Value: " + value[0]+" "+value[1] );
        }
        this.driver = BaseTest.driver;
    }

    protected WebElement getElement(String elementName){
        return driver.findElement(getFindElementOption(this.webElements.get(elementName)));
    }

    protected List<WebElement> getElements(String elementName){
        return driver.findElements(getFindElementOption(this.webElements.get(elementName)));
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

//    protected void selectDropDownListOption(String selectValue,  String locatorName, Object... args){
//        Reporter.log(message);
//        Select dropDownList = new Select(this.getElement(locatorName, args));
//        dropDownList.selectByValue(selectValue);
//    }
//
//    protected void selectDropDownListOptionByIndex(String message, int itemIndex,  String locatorName, Object... args){
//        Reporter.log(message);
//        if (itemIndex > 0) {
//            Select dropDownList = new Select(this.getElement(locatorName, args));
//            dropDownList.selectByIndex(itemIndex - 1);
//        }
//    }
//
//    protected void selectDropDownListOptionByText(String message, String selectItemText, String locatorName, Object... args) {
//        Reporter.log(message);
//
//        Select dropDownList = new Select(this.getElement(locatorName, args));
//        WebElement select = this.getElement(locatorName, args);
//        WebElement option = select.findElement(By.xpath(".//option[contains(text(),'" + selectItemText + "')]"));
//        String optionValue = option.getAttribute("value");
//        // if element has wrong value we can try select item only by text
//        try {
//            dropDownList.selectByValue(optionValue);
//        } catch (NoSuchElementException e) {
//            dropDownList.selectByVisibleText(selectItemText);
//        }
//    }
//
//    protected int selectDropDownListRandomOption(String message, String locatorName, Object... args) {
//        Reporter.log(message);
//        Select dropDownList = new Select(this.getElement(locatorName, args));
//        int size = dropDownList.getOptions().size();
//        int selectedIndex = Random.genInt(0, size - 1);
//        dropDownList.selectByIndex(selectedIndex);
//        return selectedIndex;
//    }
//
//    /**
//     * Select option from drop down
//     * @param message Reporter log message
//     * @param firstIndex Index of the first of drop down items from which select
//     * @param lastIndex Index of the last of drop down items from which select. If 'lastIndex' equals to -1
//     *                  select from items starting from 'firstIndex' to the last item in the drop down
//     * @param locatorName locator name in UIXML file
//     * @param args locator arguments
//     * @return index of selected drop down item
//     */
//    protected int selectDropDownListRandomOption(String message, int firstIndex, int lastIndex, String locatorName, Object... args) {
//        Reporter.log(message);
//        Select dropDownList = new Select(this.getElement(locatorName, args));
//        int size = dropDownList.getOptions().size();
//        int selectedIndex = (lastIndex > 0)
//                ? Random.genInt(firstIndex - 1, lastIndex - 1)
//                : Random.genInt(firstIndex - 1, size - 1);
//        dropDownList.selectByIndex(selectedIndex);
//        return selectedIndex;
//    }
//
//    protected String getDropDownListSelectedValueText(String message, String locatorName, Object... args) {
//        Reporter.log(message);
//        return new Select(this.getElement(locatorName, args)).getFirstSelectedOption().getText();
//    }
//
//    protected List<String> getDropDownListItemsValueTexts(String message, String locatorName, Object... args) {
//        Reporter.log(message);
//        List<WebElement> options =  new Select(this.getElement(locatorName, args)).getOptions();
//        List<String> items = new ArrayList<>();
//        for(WebElement option: options)
//            items.add(option.getText());
//        return items;
//    }
//
//    protected int getDropDownListItemsCount(String message, String locatorName, Object... args) {
//        Reporter.log(message);
//        return new Select(this.getElement(locatorName, args)).getOptions().size();
//    }
//
//    /*
//     * Input fields and textareas
//     */
    protected void type(String value, String elementName) {
        WebElement inputElement = this.getElement(elementName);
        inputElement.clear();
        inputElement.sendKeys(value);
    }
//
//    protected void typeWithJS(String message, String value, String xpathElementName, Object... args) {
//        Reporter.log(message);
//        ElementData elementData = locators.get(xpathElementName);
//        String locatorXpath = elementData.getLocatorValue(args);
//        this.executeJS("document.evaluate(\"" + locatorXpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.value=\"" + value + "\";", locatorXpath);
//    }
//
//    protected String getTextFromInput(String message, String elementName, Object... args) {
//        return this.getElementAttributeValue(message, "value", elementName, args);
//    }
//
//    protected void typeWithWipe(String message, String value, String elementName, Object... args) {
//        Reporter.log(message);
//        WebElement inputElement = this.getElement(elementName, args);
//        String oldValue = inputElement.getAttribute("value");
//        // simulate user interaction during clearing the input
//        for (int i = 1; i <= oldValue.length(); i++)
//            inputElement.sendKeys(Keys.BACK_SPACE);
//        // type new value
//        inputElement.sendKeys(value);
//    }
//
//    /*
//     * Checkboxes
//     */
//    protected boolean isCheckboxChecked(String elementName, Object... args) {
//        return this.getElement(elementName, args).isSelected();
//    }
//
//    protected void setCheckboxState(String message, boolean checked, String elementName, Object... args) {
//        if (checked ^ this.isCheckboxChecked(elementName, args)) {
//            this.click(message, elementName, args);
//        }
//    }
//
//    /*
//     * Clicks
//     */
    protected void click(String elementName) {
        WebElement element = this.getElement(elementName);
        element.click();
    }
//
//    protected void clickByXpathWithJS(String message, String elementName, Object... args) {
//        Reporter.log(message);
//        ElementData elementData = locators.get(elementName);
//        String locatorXpath = elementData.getLocatorValue(args);
//        this.executeJS("document.evaluate(\"" + locatorXpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();", locatorXpath);
//    }
//
//    protected void clickAndWaitElementVisibility(String message, String elementName, String elementNameWaitFor, Object... elemArgs) {
//        this.click(message, elementName, elemArgs);
//        this.waitForElementVisibility(elementNameWaitFor);
//
//    }
//
//    protected void clickAndWaitElementInvisibility(String message, String elementName, String elementNameWaitFor, Object... elemArgs) {
//        this.click(message, elementName, elemArgs);
//        this.waitForElementInvisibility(elementNameWaitFor);
//    }
//
//    /*
//     * Count elements
//     */
    protected int getElementsCount(String elementName) {
        return this.getElementsCountWithWait(0, elementName);
    }

    protected int getElementsCountWithWait(int waitInSeconds, String elementName) {
        driver.manage().timeouts().implicitlyWait(waitInSeconds, TimeUnit.SECONDS);
        int size =  this.getElements(elementName).size();
        driver.manage().timeouts().implicitlyWait(Constants.DEFAULT_WAIT_TIMEOUT, TimeUnit.SECONDS);
        return size;
    }

    protected boolean isElementPresent(String elementName, Object... args) {
        return (this.getElementsCount(elementName) > 0);
    }
//
//    protected boolean isElementPresentWithWait(int waitInSeconds, String elementName, Object... args) {
//        return (this.getElementsCountWithWait(waitInSeconds, elementName, args) > 0);
//    }
//
//    protected boolean isElementVisible(String elementName, Object... args) {
//        return this.isElementVisibleWithWait(0, elementName, args);
//    }
//
//    protected boolean isElementVisibleWithWait(int waitInSeconds, String elementName, Object... args) {
//        By by = this.getElementLocator(elementName, args);
//        WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
//        } catch (Throwable th) {
//            return false;
//        }
//        return true;
//    }
//
//
//    /*
//     * Element waits
//     */
//    protected void waitForElementToBeClickable(String elementName, Object... args) {
//        By by = this.getElementLocator(elementName, args);
//        WebDriverWait wait = new WebDriverWait(driver, Constants.ELEMENT_TIMEOUT_SECONDS);
//        wait.until(ExpectedConditions.elementToBeClickable(by));
//
//        //wait until element will be at the same place (for moving elements: for Chrome and IE)
//        Point currLocation, newLocation;
//        long startTime = System.currentTimeMillis();
//        long delta;
//
//        newLocation = new Point(-1,-1);
//        do {
//            try { Thread.sleep(100); } catch (InterruptedException ie) {}   //the element may move veeery slowly. It's better to wait some time
//            currLocation = newLocation;
//            newLocation = this.getElement(elementName, args).getLocation();
//            delta = System.currentTimeMillis() - startTime;
//        } while ((currLocation.getX() - newLocation.getX() != 0 && currLocation.getY() - newLocation.getY() != 0)
//                && (delta <= Constants.ELEMENT_TIMEOUT_SECONDS * 1000));
//
//        if (delta > Constants.ELEMENT_TIMEOUT_SECONDS * 1000) {
//            throw new InvalidElementStateException("Element did not stand at the same place for " + Constants.ELEMENT_TIMEOUT_SECONDS + " seconds");
//        }
//        if (System.getProperty("browser", "firefox").equalsIgnoreCase("firefox")) {
//            //wait(Constants.ELEMENT_MICRO_TIMEOUT_SECONDS);
//        }
//    }
//
//    protected void waitForElementPresent(String elementName, Object... args) {
//        By by = this.getElementLocator(elementName, args);
//        WebDriverWait wait = new WebDriverWait(driver, Constants.ELEMENT_TIMEOUT_SECONDS);
//        wait.until(ExpectedConditions.presenceOfElementLocated(by));
//    }
//
//    protected void waitForElementPresent(int timeout, String elementName, Object... args) {
//        By by = this.getElementLocator(elementName, args);
//        WebDriverWait wait = new WebDriverWait(driver, timeout);
//        wait.until(ExpectedConditions.presenceOfElementLocated(by));
//    }
//
    protected void waitForElementVisibility(String elementName) {
        System.out.println(elementName);
        System.out.println(webElements.get(elementName)[0]);
        System.out.println(webElements.get(elementName)[1]);
        By by = this.getFindElementOption(this.webElements.get(elementName));
        WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected void waitForElementVisibility(int timeout, String elementName, Object... args) {
        By by = this.getFindElementOption(this.webElements.get(elementName));
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
//
//    protected void waitForElementInvisibility(String elementName, Object... args) {
//        this.waitForElementInvisibilityWithWait(0, elementName, args);
//    }
//
//    protected void waitForElementInvisibilityWithWait(int waitInSecondsBefore, String elementName, Object... args) {
//        By by = this.getElementLocator(elementName, args);
//        driver.manage().timeouts().implicitlyWait(waitInSecondsBefore, TimeUnit.SECONDS);
//        WebDriverWait wait = new WebDriverWait(driver, Constants.ELEMENT_TIMEOUT_SECONDS);
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
//        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
//    }
//
//    protected void waitForElementInvisibilityWithWait(int waitInSecondsBefore, int waitInSeconds, String elementName, Object... args) {
//        By by = this.getElementLocator(elementName, args);
//        driver.manage().timeouts().implicitlyWait(waitInSecondsBefore, TimeUnit.SECONDS);
//        WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
//        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
//    }
//
//    public void wait(int waitInSeconds) {
//        try {
//            Thread.sleep(waitInSeconds * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*
//     * Actions with mouse
//     */
//
//    /**
//     * Move real mouse cursor to (0; 0)
//     */
//    protected void moveMouseOut() {
//        try {
//            Robot robot = new Robot();
//            robot.mouseMove(0, 0);
//        } catch (AWTException e) {
//            Reporter.log("# A problem occurred during trying to move cursor out of the browser window!");
//            e.printStackTrace();
//        }
//    }
//
//    protected void mouseMove(String message, String elementName, Object... args) {
//        Reporter.log(message);
//        moveMouseOut();
//        new Actions(driver).moveToElement(getElement(elementName, args)).build().perform();
//    }
//
////    protected void mouseMove(String message, int offsetX, int offsetY, String elementName, Object... args) {
////        Reporter.log(message);
////        new Actions(driver).moveToElement(getElement(elementName, args), offsetX, offsetY).build().perform();
////    }
//
//    protected void mouseOverByXpathUsingJS(String message, String elementName, Object... args) {
//        Reporter.log(message);
//        ElementData elementData = locators.get(elementName);
//        String locatorXpath = elementData.getLocatorValue(args);
//        String elem = "document.evaluate(\"" + locatorXpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue";
//        String javaScript = "if(document.createEvent) {" +
//                "    var evObj = document.createEvent('MouseEvents');" +
//                "    evObj.initEvent('mouseover', true, false);" +
//                "    " + elem + ".dispatchEvent(evObj);" +
//                "} else if(document.createEventObject) {" +
//                "    " + elem + ".fireEvent('onmouseover');" +
//                "}";
//        executeJS(javaScript, locatorXpath);
//    }
//
//    protected void mouseOutByXpathUsingJS(String message, String elementName, Object... args) {
//        Reporter.log(message);
//        ElementData elementData = locators.get(elementName);
//        String locatorXpath = elementData.getLocatorValue(args);
//        String elem = "document.evaluate(\"" + locatorXpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue";
//        String javaScript = "if(document.createEvent) {" +
//                "    var evObj = document.createEvent('MouseEvents');" +
//                "    evObj.initEvent('mouseout', true, false);" +
//                "    " + elem + ".dispatchEvent(evObj);" +
//                "} else if(document.createEventObject) {" +
//                "    " + elem + ".fireEvent('onmouseout');" +
//                "}";
//        executeJS(javaScript, locatorXpath);
//    }
//
////    protected void mouseDown(String message, String elementName, Object... args) {
////        Reporter.log(message);
////        Locatable mouseDownItem = (Locatable) this.getElement(elementName, args);
////        Mouse mouse = ((HasInputDevices) driver).getMouse();
////        mouse.mouseDown(mouseDownItem.getCoordinates());
////    }
////
////    protected void mouseUp(String message, String elementName, Object... args) {
////        Reporter.log(message);
////        Locatable mouseDownItem = (Locatable) this.getElement(elementName, args);
////        Mouse mouse = ((HasInputDevices) driver).getMouse();
////        mouse.mouseUp(mouseDownItem.getCoordinates());
////    }
//
//    protected void mouseScroll(int x, int y) {
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        Reporter.log(String.format("Scrolling mouse to coordinates: %s, %s", x, y));
//        js.executeScript("scroll(" + x + ", " + y + ");");
//    }
//
//    protected void mouseScroll(int x, int y, String elementName, Object ... args) {
//        WebElement element = this.getElement(elementName, args);
//        x += element.getLocation().getX();
//        y += element.getLocation().getY();
//        JavascriptExecutor js = (JavascriptExecutor) driver;
//        Reporter.log(String.format("Scrolling mouse to coordinates: %s, %s", x, y));
//        js.executeScript("scroll(" + x + ", " + y + ");");
//    }
//
//    protected void mouseDragAndDrop(String message, WebElement elementToDrag, WebElement dragTo) {
//        Reporter.log(message);
//        new Actions(BaseTest.driver).clickAndHold(elementToDrag).moveToElement(dragTo).release().build().perform();
//    }
//
//    /**
//     * This method add element xpath to exception message (for debug)
//     * If we can not add xpath to exception message -
//     * returns unchanged exception. (It's impossible, because we get field from Throwable:) )
//     * @param script
//     * @param xpath
//     */
//    private void executeJS(String script, String xpath) {
//        try {
//            ((JavascriptExecutor) driver).executeScript(script);
//        } catch (WebDriverException wd) {
//            Field f = null;
//            try {
//                f = Throwable.class.getDeclaredField("detailMessage");
//            } catch (NoSuchFieldException e) {
//                throw wd;
//            }
//            f.setAccessible(true);
//            try {
//                String error = "\nXPath: " + xpath + "\n" + f.get(wd);
//                f.set(wd, error);
//            } catch (IllegalAccessException ia) { }
//            throw wd;
//        }
//    }
//
//    protected String getHighlightedPageText() {
//        return (String)((JavascriptExecutor) driver).executeScript("return window.getSelection().toString()");
//    }
//
//    public void openUrlInNewTab(String url) {
//        ((JavascriptExecutor) driver).executeScript("window.open(\"" + url + "\")");
//        Set<String> handles = driver.getWindowHandles();
//        String current = getCurrentPageHandler();
//        handles.remove(current);
//        for (String handle : handles) {
//            driver.switchTo().window(handle);
//        }
//    }
//
//    public String getCurrentPageHandler() {
//        return driver.getWindowHandle();
//    }
//
//    public void closeTab(String mainHandle) {
//        driver.close();
//        driver.switchTo().window(mainHandle);
//    }




}

package com.jcatalog.qa.testframework;

import com.jcatalog.qa.testframework.utils.ConfigProperties;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael Makeikin
 * on 6/7/16.
 */

public class Page extends Browser {

    public By appLogo = By.xpath("//img");
    private By headerText = By.xpath("//body/div[@class='container']/h1[1]");
    public By accessDeniedText = By.xpath("//*[contains(text(),'Access denied')]");

    public boolean click(By locator) {
        try {
            moveToElement(locator);
            if (!waitForElementVisibility_SmallTimeOut(locator)) {
                return false;
            }
//            driver.findElement(locator).click();
            webDriverWait_Medium.until(ExpectedConditions.elementToBeClickable(locator)).click();
            return true;
        } catch (NoSuchElementException ex) {
            System.out.println("Element was not found");
            ex.printStackTrace();
            return false;
        }
    }

    public boolean isChecked(By locator) {
        try {
            if (!waitForElementVisibility_SmallTimeOut(locator)) {
                return false;
            }
            return "true".equals(driver.findElement(locator).getAttribute("checked"));
        } catch (NoSuchElementException ex) {
            System.out.println("Element was not found");
            ex.printStackTrace();
            return false;
        }
    }

    public void clickMouseRightButton(By locator) {
        try {
            Actions actions = new Actions(driver);
            actions.contextClick(driver.findElement(locator)).perform();
        } catch (NoSuchElementException ex) {
            System.out.println("Element was not found");
            ex.printStackTrace();
        }
    }

    public void clickOnInvisibleElement(By locator) {
        String script = "var object = arguments[0];"
                + "var theEvent = document.createEvent(\"MouseEvent\");"
                + "theEvent.initMouseEvent(\"click\", true, true, window,"
                + " 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
                + "object.dispatchEvent(theEvent);";
        ((JavascriptExecutor) driver).executeScript(script, driver.findElement(locator));
    }

    public boolean type(By locator, String textToEnter) {
        if (!waitForElementVisibility_MediumTimeOut(locator)) {
            return false;
        }
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(textToEnter);
        return true;
    }

    public boolean setFileToInput(By locator, File file) {
        if (!waitForElementVisibility_SmallTimeOut(locator)) {
            return false;
        }
        driver.findElement(locator).sendKeys(file.getAbsolutePath());
        return true;
    }

    public String getTextFromElement(By locator) {
        try {
            if (!waitForElementVisibility_MediumTimeOut(locator)) {
                return null;
            }
            return driver.findElement(locator).getText();
        } catch (Exception e) {
            System.out.println("Element was not found");
            e.printStackTrace();
            return null;
        }
    }

    public boolean selectInListByVisibleText(By locator, String text) {
        if (!waitForElementVisibility_SmallTimeOut(locator)) {
            return false;
        }
        new Select(driver.findElement(locator)).selectByVisibleText(text);
        return true;
    }

    public boolean selectInListByValue(By locator, String value) {
        if (!waitForElementVisibility_SmallTimeOut(locator)) {
            return false;
        }
        webDriverWait_Medium.until(ExpectedConditions.elementToBeClickable(locator));
        new Select(driver.findElement(locator)).selectByValue(value);
        return true;
    }

    public boolean selectInListByIndex(By locator, int index) {
        if (!waitForElementVisibility_SmallTimeOut(locator)) {
            return false;
        }
        new Select(driver.findElement(locator)).selectByIndex(index);
        return true;
    }

    public boolean deselectInListByIndex(By locator, int index) {
        if (!waitForElementVisibility_SmallTimeOut(locator)) {
            return false;
        }
        new Select(driver.findElement(locator)).deselectByIndex(index);
        return true;
    }

    public boolean deselectAllInList(By locator) {
        if (!waitForElementVisibility_SmallTimeOut(locator)) {
            return false;
        }
        Select select = new Select(driver.findElement(locator));
        if (select.isMultiple()) {
            select.deselectAll();
        }
        return true;
    }

    public List<WebElement> getAllListOptions(By locator) {
        if (!waitForElementVisibility_SmallTimeOut(locator)) {
            return null;
        }
        return new Select(driver.findElement(locator)).getOptions();
    }

    public List<WebElement> getAllSelectedListOptions(By locator) {
        if (!waitForElementVisibility_SmallTimeOut(locator)) {
            return null;
        }
        return new Select(driver.findElement(locator)).getAllSelectedOptions();
    }

    public boolean javaScriptExecution(By locator, String script) {
        if (!waitForElementVisibility_SmallTimeOut(locator)) {
            return false;
        }
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(script, element);
        return true;
    }

    public String getTextOfInvisibleElement(By locator) {
        String script = "var element = arguments[0];return element.textContent;";
        WebElement element = driver.findElement(locator);
        if (element == null) {
            return null;
        }
        return (String) ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public boolean makeElementVisible(By locator) {
        String script = "var element = arguments[0];element.style.display='block';";
        WebElement element = driver.findElement(locator);
        if (element == null) {
            return false;
        }
        ((JavascriptExecutor) driver).executeScript(script, element);
        return true;
    }

    public String getElementAttributeValue(By locator, String nameOfAttribute) {
        if (!waitForElementVisibility_SmallTimeOut(locator)) {
            return null;
        }
        return driver.findElement(locator).getAttribute(nameOfAttribute);
    }

    public void dragAndDrop(By sourceLocator, By destinationLocator) {
        new Actions(driver).dragAndDrop(driver.findElement(sourceLocator),
                driver.findElement(destinationLocator)).build().perform();
    }

    /**
     * Check that visible element was found. Implicitly timeout was used
     * @param locator
     * @return boolean
     */
    public boolean isElementVisible(By locator) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            List<WebElement> list = driver.findElements(locator);
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(
                    ConfigProperties.getProperty("common.implicitWait.timeOut.sec")), TimeUnit.SECONDS);
            return list.size() != 0 && list.get(0).isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Check that text was found on page.
     * @param text
     * @return boolean
     */
    public boolean isTextPresent(String text) {
        try {
            return driver.getPageSource().contains(text);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    /**
     * Check that existElement is not visible. Implicitly timeout was used
     * @param locator
     * @return boolean
     */
    public boolean isElementInvisible(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return !element.isEnabled();
        } catch (NoSuchElementException ex) {
            return false;
        }
        //return webDriverWait_Big.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean isElementDisappearedFromDOM(By locator, WebDriverWait webDriverWait){
        return webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitWhileElementContainsText(By locator, String text) {
        return webDriverWait_Small.until(ExpectedConditions.textToBePresentInElement(driver.findElement(locator), text)) != null;

        //return (new WebDriverWait(driver, timeForWait)).until(ExpectedConditions.textToBePresentInElement(driver.findElement(locator), text));
    }

    public boolean waitWhileElementDisappearedFromDOM(By locator) {
        return webDriverWait_Big.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }


    /**
     * Wait while Element Visibility. Explicitly timeout was used
     * @param locator
     * @return boolean
     */
    public boolean waitForElementVisibility_BigTimeOut(By locator) {
        return waitForElementVisibility(locator, webDriverWait_Big);
    }
    public boolean waitForElementVisibility_MediumTimeOut(By locator) {
        return waitForElementVisibility(locator, webDriverWait_Medium);
    }
    public boolean waitForElementVisibility_SmallTimeOut(By locator) {
        return waitForElementVisibility(locator, webDriverWait_Small);
    }

    public void waitForElementClickable(By locator){
        webDriverWait_Medium.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private boolean waitForElementVisibility(By locator, WebDriverWait webDriverWait) {
        try {
//            return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
            return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator)) != null;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    // Can be used for dialogs: alert, confirm and prompt
    public boolean isAlertDisplayed(int timeToWaitAlert) {
        Alert alert = null;
        boolean isAlertPresent = false;
        alert = (new WebDriverWait(driver, timeToWaitAlert)).until(ExpectedConditions.alertIsPresent());
        if (alert != null) {
            isAlertPresent = true;
        }
        return isAlertPresent;
    }

    // Move cursor to the center of the WebElement
    public void moveMouseOverElement(By locator) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator)).build().perform();
    }

    // Move cursor to the point with offset of the WebElement
    public void moveMouseOverElementWithOffset(By locator, int xOffset, int yOffset) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(locator), xOffset, yOffset).build().perform();
    }

    // Can be used for dialogs: alert, confirm and prompt
    public void alertAccept() {
        try {
            if (isAlertDisplayed(10)) {
                driver.switchTo().alert().accept();
            }
        } catch (NoAlertPresentException e) {
            System.out.println("There is no dialog window is displayed.");
        }
    }

    // Can be used for dialogs: alert, confirm and prompt
    public void alertDismiss() {
        try {
            if (isAlertDisplayed(10)) {
                driver.switchTo().alert().dismiss();
            }
        } catch (NoAlertPresentException e) {
            System.out.println("There is no dialog window is displayed.");
        }
    }

    // Can be used for dialogs: alert, confirm and prompt
    public String getAlertText() {
        String alertText = "Alert didn't find or has no text.";
        try {
            if (isAlertDisplayed(10)) {
                alertText = driver.switchTo().alert().getText();
            }
        } catch (NoAlertPresentException e) {
            System.out.println("There is no dialog window is displayed.");
        }

        return alertText;
    }

    public List<WebElement> getVisibleElements(By locator) {
        List<WebElement> resultList = new ArrayList<>();
        List<WebElement> elements = driver.findElements(locator);
        if (elements == null || elements.isEmpty()) {
            return resultList;
        }
        for (WebElement element : elements) {
            if (element.isEnabled()) {
                resultList.add(element);
            }
        }
        return resultList;
    }

    public int getVisibleElementsCount(By locator) {
        return getVisibleElements(locator).size();
    }

    public Integer getElementsCounts(By locator) {
        return driver.findElements(locator).size();
    }

    public String getPageHeader() {
        return getTextFromElement(headerText);
    }

    public void savePageAs(File file) throws Exception{
        String page = driver.getPageSource();
        PrintStream out = new PrintStream(new FileOutputStream(file));
            out.println(page);
    }

    public void moveToElement(By locator){
        int elementPosition = driver.findElement(locator).getLocation().getY();
        String js = String.format("window.scroll(0, %s)", elementPosition);
        ((JavascriptExecutor)driver).executeScript(js);
    }

}

package com.epam.test.transAvia;

import com.epam.test.transAvia.utils.ConfigProperties;
import com.google.common.base.Function;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by hkap on 2/8/18.
 */
public class Browser {

    public static WebDriver driver;
    private static ChromeDriverService service;
    private static String PathToChromeDriver;
    public static final Long BIG_TIMEOUT = Long.parseLong(ConfigProperties.getProperty("common.explicit.big.timeOut.sec"));
    public static final Long MEDIUM_TIMEOUT = Long.parseLong(ConfigProperties.getProperty("common.explicit.medium.timeOut.sec"));
    public static final Long SMALL_TIMEOUT = Long.parseLong(ConfigProperties.getProperty("common.explicit.small.timeOut.sec"));
    public static WebDriverWait webDriverWait_Big;
    public static WebDriverWait webDriverWait_Medium;
    public static WebDriverWait webDriverWait_Small;

    public final static String APP_LINK = ConfigProperties.getProperty("appLink");
    protected String pathToScreenshots = System.getProperty("user.dir") + File.separator + "target" + File.separator;

    @BeforeSuite
    public static void createAndStartService() throws IOException {
        PathToChromeDriver = System.getenv("webdriver.chrome.driver");

        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(PathToChromeDriver))
                .usingPort(9515)
                .build();
        service.start();
    }

    public void startBrowser() throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        options.addArguments("disable-web-security");
        options.addArguments("ignore-certificate-errors");
//        options.addArguments("incognito");
        options.addArguments("disable-popup-blocking");
        options.addArguments("disable-extensions");
        options.addArguments("phantomProtection");
        options.addArguments("chromeHeadlessProtection");
//        options.addArguments("headless");

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        driver = new RemoteWebDriver(service.getUrl(), capabilities);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ConfigProperties.getProperty("common.implicitWait.timeOut.sec")), TimeUnit.SECONDS);
        long sleepTime = Long.parseLong(ConfigProperties.getProperty("common.explicit.sleepTime.millisec"));
        webDriverWait_Big = new WebDriverWait(driver, BIG_TIMEOUT, sleepTime);
        webDriverWait_Medium = new WebDriverWait(driver, MEDIUM_TIMEOUT, sleepTime);
        webDriverWait_Small = new WebDriverWait(driver, SMALL_TIMEOUT, sleepTime);

    }

    public static void stopBrowser() {
        driver.quit();
        service.stop();
    }

    public boolean click(WebElement element) {
        try {
            moveToElement(element);
            if (!waitForElementVisibility_SmallTimeOut(element)) {
                return false;
            }
            webDriverWait_Medium.until(ExpectedConditions.elementToBeClickable(element)).click();
            return true;
        } catch (NoSuchElementException ex) {
            System.out.println("Element was not found");
            ex.printStackTrace();
            return false;
        }
    }

    public void fluentWait(final WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(2, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return element;
            }
        });
    }

    public boolean isElementVisible(WebElement element) {
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            return element.getSize() != null && element.isEnabled();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String captureScreen() {
        String path;
        try {
            WebDriver augmentedDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
            path = pathToScreenshots + source.getName();
            FileUtils.copyFile(source, new File(path));
        } catch (IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        return path;
    }

    public void goToUrl(String url) {
        driver.get(url);
    }

    public boolean waitForElementVisibility_BigTimeOut(WebElement element) {
        return waitForElementVisibility(element, webDriverWait_Big);
    }
    public boolean waitForElementVisibility_MediumTimeOut(WebElement element) {
        return waitForElementVisibility(element, webDriverWait_Medium);
    }
    public boolean waitForElementVisibility_SmallTimeOut(WebElement element) {
        return waitForElementVisibility(element, webDriverWait_Small);
    }

    private boolean waitForElementVisibility(WebElement element, WebDriverWait webDriverWait) {
        try {
//            return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
            return webDriverWait.until(ExpectedConditions.visibilityOf(element)) != null;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public void moveToElement(WebElement element){
        webDriverWait_Small.until(ExpectedConditions.visibilityOf(element));
        int elementPosition = element.getLocation().getY();
        String js = String.format("window.scroll(0, %s)", elementPosition);
        ((JavascriptExecutor)driver).executeScript(js);
    }

}

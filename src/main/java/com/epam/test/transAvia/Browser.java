package com.epam.test.transAvia;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

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
    public static WebDriverWait webDriverWait_Big;
    public static WebDriverWait webDriverWait_Medium;
    public static WebDriverWait webDriverWait_Small;

    protected String pathToScreenshots = System.getProperty("user.dir") + File.separator + "target" + File.separator;

    @BeforeClass
    public static void createAndStartService() throws IOException {
        PathToChromeDriver = System.getenv("webdriver.chrome.driver");

        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(PathToChromeDriver))
                .usingPort(9515)
                .build();
        service.start();
    }

    @BeforeClass
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

        driver.manage().timeouts().implicitlyWait(Integer.parseInt("30"), TimeUnit.SECONDS);
        webDriverWait_Big = new WebDriverWait(driver, 60, 500);

        driver.get("https://www.transavia.com/en-EU/home/");

        Thread.sleep(20000);

    }

    @AfterClass
    public static void createAndStopService() {
        driver.quit();
        service.stop();
    }

    public WebElement fluentWait(final WebElement element) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        return wait.until(driver1 -> element);
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

}

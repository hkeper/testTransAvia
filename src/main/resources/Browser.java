package com.jcatalog.qa.testframework;

import com.jcatalog.qa.testframework.utils.ConfigProperties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Michael Makeikin
 * on 6/7/16.
 */

public class Browser {

    public static WebDriver driver;

    public static final Long BIG_TIMEOUT = Long.parseLong(ConfigProperties.getProperty("common.explicit.big.timeOut.sec"));
    public static final Long MEDIUM_TIMEOUT = Long.parseLong(ConfigProperties.getProperty("common.explicit.medium.timeOut.sec"));
    public static final Long SMALL_TIMEOUT = Long.parseLong(ConfigProperties.getProperty("common.explicit.small.timeOut.sec"));

    public static WebDriverWait webDriverWait_Big;
    public static WebDriverWait webDriverWait_Medium;
    public static WebDriverWait webDriverWait_Small;

    protected String testPath = System.getProperty("user.dir") + File.separator;
    protected String pathToScreenshots = System.getProperty("user.dir") + File.separator + "target" + File.separator;
    protected String pathToDownload = System.getProperty("user.dir") + File.separator + "src" + File.separator +
            "main" + File.separator + "resources" + File.separator + "downloadFolder" + File.separator;


    /**
     * Specifies the amount of time the test should webDriverWait_Big when searching for an element if it is
     * not immediately present.
     *
     */
    public void startBrowser() {

        FirefoxBinary binary = new FirefoxBinary(new File("/home/hkap/Install/firefox452esr/firefox"));
        FirefoxProfile profile = new FirefoxProfile();
        //Set Location to store files after downloading.
        profile.setPreference("browser.download.dir", pathToDownload);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream,text/csv,application/vnd.ms-excel");
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("pdfjs.disabled", true);

//        driver = new FirefoxDriver(profile);

        driver = new FirefoxDriver(binary, profile);

//        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ConfigProperties.getProperty("common.implicitWait.timeOut.sec")), TimeUnit.SECONDS);
        long sleepTime = Long.parseLong(ConfigProperties.getProperty("common.explicit.sleepTime.millisec"));
        webDriverWait_Big = new WebDriverWait(driver, BIG_TIMEOUT, sleepTime);
        webDriverWait_Medium = new WebDriverWait(driver, MEDIUM_TIMEOUT, sleepTime);
        webDriverWait_Small = new WebDriverWait(driver, SMALL_TIMEOUT, sleepTime);
    }

    public void stopBrowser() {
        if (driver == null) return;
        driver.quit();
        driver = null;
    }

    public String title() {
        return driver.getTitle();
    }

    /**
     * Redirect page to url "host/url"
     *
     * @param url
     */
    public void goToUrl(String url) {
        driver.get(url);
    }

    /**
     * Remove all files from folder "src/main/resources/downloadFolder"
     */
    public void clearDownloadFolder() {
        try {
            File folder = new File(pathToDownload);
            if (!folder.exists()) {
                return;
            }
            File[] files = folder.listFiles();
            if (files != null) {
                for (File f : files) {
                    if (f.isFile()) {
                        f.delete();
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Return list of files name from folder "src/main/resources/downloadFolder"
     *
     * @return List<String>
     */
    public List<String> getDownloadedFileList() {
        List<String> fileList = new ArrayList<>();
        File folder = new File(pathToDownload);
        if (!folder.exists()) {
            return fileList;
        }
        File[] files = folder.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isFile()) {
                    fileList.add(f.getName());
                }
            }
        }
        return fileList;
    }

    /**
     * Check existing file in "src/main/resources"
     *
     * @param fileName
     * @return boolean
     */
    public boolean checkExistDownloadFile(String fileName) {
        return getDownloadedFileList().contains(fileName);
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

    public String getCurrentWindowHandle() {
        return driver.getWindowHandle();
    }

    public boolean switchToNewWindow() {
        String currentHandle = getCurrentWindowHandle();
        for (String winHandle : driver.getWindowHandles()) {
            if (!currentHandle.equals(winHandle)) {
                driver.switchTo().window(winHandle);
                return true;
            }
        }
        return false;
    }

    public void backToWindow(String windowHandle) {
        driver.switchTo().window(windowHandle);
    }

    public void closeWindow() {
        driver.close();
    }

    public void navigateBack(){
        driver.navigate().back();
    }

    public void pageReload(){
        driver.navigate().refresh();
    }
}

package com.epam.test.transAvia;

import com.epam.test.transAvia.pages.HomePage;
import com.epam.test.transAvia.pages.SearchResultPage;
import com.epam.test.transAvia.pages.WelcomePage;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.testng.Assert.fail;

/**
 * Created by hkap on 2/8/18.
 */
public class TestBase extends Browser{

    public WelcomePage welcomePage;
    public HomePage homePage;
    public SearchResultPage searchResultPage;

    Date date = new Date();
    String DATE_FORMAT = "dd MMM yyyy";
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
    public String today =  sdf.format(date);
    DateFormat logDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS", Locale.ENGLISH);
    Date logDate;
    String StartTimeClass, EndTimeClass, StartTimeTest, EndTimeTest;
    public String indent;

    @BeforeClass
    public void start() {
        try {

            startBrowser();

            welcomePage = PageFactory.initElements(driver, WelcomePage.class);
            homePage = PageFactory.initElements(driver, HomePage.class);
            searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);

            indent = "";
            Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
            log("Browser version - " + caps.getVersion());
            logDate = new Date();
            StartTimeClass = logDateFormat.format(logDate);
            log("[" + StartTimeClass + "] - START time of class.");
            log("--------------------------------------------------------------------------------------------------");
            log("----------------- Start testing of class " + getClass().getSimpleName()
                    + " -----------------");
            log("--------------------------------------------------------------------------------------------------");

        }catch (Exception e) {
            captureScreen();
            e.printStackTrace();
            fail();
        }
    }

    @BeforeMethod
    public void beforeMethod(){
        indent = "    ";
        logDate = new Date();
        StartTimeTest = logDateFormat.format(logDate);
        log("[" + StartTimeTest + "] - START time of test.");
        indent = "        ";
        log("**************************************************************************************************");
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        log("**************************************************************************************************");
        if(ITestResult.FAILURE == result.getStatus()){
            log("!!!!!FAIL!!!!! Capture screenshot: " + captureScreen());
        }
        indent = "    ";
        logDate = new Date();
        EndTimeTest = logDateFormat.format(logDate);
        log("[" + EndTimeTest + "] - END time of test.");
        indent = "";
    }

    @AfterClass
    public void stop(){
        stopBrowser();
        indent = "";
        logDate = new Date();
        EndTimeClass = logDateFormat.format(logDate);
        log("--------------------------------------------------------------------------------------------------");
        log("--------------- Stop testing of class " + getClass().getSimpleName()
                + " ---------------");
        log("--------------------------------------------------------------------------------------------------");
        log("[" + EndTimeClass + "] - END time of class.");
    }

    public
    void log(String text) {
        System.out.println(indent + text);
    }

    public void checkHomePageOpened() throws InterruptedException {

        webDriverWait_Big.until(ExpectedConditions.titleContains("Transavia"));

        if(welcomePage.isWelcomePageOpened()){
            welcomePage.clickOtherCountryLink();
        }

        webDriverWait_Big.until(ExpectedConditions.titleContains("Transavia"));
    }

}
package com.epam.test.transAvia;

import com.epam.test.transAvia.pages.HomePage;
import com.epam.test.transAvia.pages.WelcomePage;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.testng.Assert.*;

/**
 * Created by hkap on 2/8/18.
 */
public class TestBase extends Browser{

    public WelcomePage welcomePage;
    public HomePage homePage;

    Date date = new Date();
    String DATE_FORMAT = "dd MMM yyyy";// HH:mm:SS";
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    public String today =  sdf.format(date);

    @org.testng.annotations.Test
    public void test() throws InterruptedException {

        welcomePage = PageFactory.initElements(driver, WelcomePage.class);
        homePage = PageFactory.initElements(driver, HomePage.class);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);  // number of days to add
        String todayPlus1 = sdf.format(c.getTime());

        System.out.println(today);

        if(welcomePage.isWelcomePageOpened()){
            welcomePage.clickOtherCountryLink();
        }

        assertTrue(homePage.isHomePageIsOpened(), "Home page was not opened!");
//        homePage.chooseFromPoint("Paris");
//        homePage.chooseToPoint("Amsterdam");
        homePage.chooseDate(todayPlus1);
        assertTrue(homePage.unckeckReturnOn(), "Checkbox 'Return On' was not unchecked!");


        Thread.sleep(3000);

    }

}
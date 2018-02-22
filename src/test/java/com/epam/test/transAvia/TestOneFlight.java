package com.epam.test.transAvia;

import java.util.Calendar;

import static org.testng.Assert.assertTrue;

/**
 * Created by hkap on 2/21/18.
 */
public class TestOneFlight extends TestBase{

    @org.testng.annotations.Test
    public void test() throws InterruptedException {

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, 1);  // number of days to add
        String todayPlus1 = sdf.format(c.getTime());

        goToUrl(APP_LINK);

        checkPageOpened();

        assertTrue(homePage.isHomePageIsOpened(), "Home page was not opened!");
        homePage.chooseFromPoint("Paris");
        homePage.chooseToPoint("Amsterdam");
        homePage.chooseDate(todayPlus1);
        assertTrue(homePage.unckeckReturnOn(), "Checkbox 'Return On' was not unchecked!");
        homePage.clickSearchButton();
        assertTrue(searchResultPage.isSearchResultPageIsOpened(), "Search Result page was not opened!");
        assertTrue(searchResultPage.isFlightInSevenDaysFound(), "No one flight was found!");

        Thread.sleep(3000);
    }

}

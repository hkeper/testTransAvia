package com.epam.test.transAvia;

import static org.testng.Assert.assertTrue;

/**
 * Created by hkap on 2/21/18.
 * Test that at least one flight is found for chosen date and destination
 */
public class TestOneFlight extends TestBase{

    String fromPoint = "Paris";
    String toPoint = "Amsterdam";

    @org.testng.annotations.Test
    public void testFlightToOneDirection() throws InterruptedException {

        goToUrl(APP_LINK);

        checkPageOpened();

        assertTrue(homePage.isHomePageIsOpened(), "Home page was not opened!");
        homePage.chooseFromPoint(fromPoint);
        homePage.chooseToPoint(toPoint);
        homePage.chooseDate(todayPlus1);
        assertTrue(homePage.unckeckReturnOn(), "Checkbox 'Return On' was not unchecked!");
        homePage.clickSearchButton();
        assertTrue(searchResultPage.isSearchResultPageIsOpened(), "Search Result page was not opened!");
        assertTrue(searchResultPage.isFlightInSevenDaysFound(), "No one flight was found!");

        Thread.sleep(3000);
    }

}

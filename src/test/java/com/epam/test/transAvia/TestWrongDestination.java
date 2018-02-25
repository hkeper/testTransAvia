package com.epam.test.transAvia;

import static org.testng.Assert.assertTrue;

/**
 * Created by hkap on 2/25/18.
 */
public class TestWrongDestination extends TestBase{

    String fromPoint = "Dubai";
    String toPoint = "Agadir";

    @org.testng.annotations.Test
    public void testWrongFlightDirection() throws InterruptedException {

        goToUrl(APP_LINK);

        checkPageOpened();

        assertTrue(homePage.isHomePageIsOpened(), "Home page was not opened!");
        homePage.chooseFromPoint(fromPoint);
        homePage.chooseToPoint(toPoint);
        homePage.clickSearchButton();
        assertTrue(searchResultPage.isSearchResultPageIsOpened(), "Search Result page was not opened!");
        assertTrue(searchResultPage.getErrorMessage().equals("Unfortunately we do not fly from Dubai," +
                " United Arab Emirates to Agadir, Morocco. However, we do fly from other airports to Agadir," +
                " Morocco. Please change your departure airport and try again"), "Error message is wrong!");
    }

}

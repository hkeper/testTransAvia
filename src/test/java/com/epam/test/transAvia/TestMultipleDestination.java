package com.epam.test.transAvia;

import static org.testng.Assert.assertTrue;

/**
 * Created by hkap on 2/25/18.
 */
public class TestMultipleDestination extends TestBase {

    String fromPointOutbound = "Bologna";
    String toPointOutbound = "Eindhoven";
    String dateOutbound = "3 May 2018";
    String fromPointInbound = "Amsterdam";
    String toPointInbound = "Casablanca";
    String dateInbound = "9 May 2018";

    @org.testng.annotations.Test
    public void testMultipleDestination() throws InterruptedException {

        goToUrl(APP_LINK);

        checkPageOpened();

        assertTrue(homePage.isHomePageIsOpened(), "Home page was not opened!");
        homePage.clickLinkAddMultDestination();

        assertTrue(searchResultPage.isSearchResultPageIsOpened(), "Search Result page was not opened!");
        searchResultPage.setFromOutbound(fromPointOutbound);
        searchResultPage.setToOutbound(toPointOutbound);
        searchResultPage.setDateOutbound(dateOutbound);
        searchResultPage.setFromInbound(fromPointInbound);
        searchResultPage.setToInbound(toPointInbound);
        searchResultPage.setDateInbound(dateInbound);
        searchResultPage.clickSearchButton();
        assertTrue(searchResultPage.isFlightInSevenDaysFound(), "No one flight was found!");
        searchResultPage.clickButtonSelectOfTheOutboundFlight();
        searchResultPage.clickButtonSelectOfInboundFlight();
        System.out.println("Total amount = " + selectOptionsPage.getTotalAmount());

    }

}

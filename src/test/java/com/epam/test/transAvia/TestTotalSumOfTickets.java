package com.epam.test.transAvia;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by hkap on 2/24/18.
 */
public class TestTotalSumOfTickets extends TestBase{

    String fromPoint = "Amsterdam";
    String toPoint = "Paris";

    @Test
    public void testTotalSum() throws InterruptedException {
        Integer actualSum = 0;
        Integer expectedSum = 0;
        Integer ticketCost = 0;

        goToUrl(APP_LINK);

        checkPageOpened();

        assertTrue(homePage.isHomePageIsOpened(), "Home page was not opened!");
        homePage.chooseFromPoint(fromPoint);
        homePage.chooseToPoint(toPoint);
        homePage.chooseDate(todayPlus1);
        assertTrue(homePage.unckeckReturnOn(), "Checkbox 'Return On' was not unchecked!");
        homePage.chooseAdultsPassengers(2);
        homePage.chooseChildrenPassengers(1);
        homePage.clickSearchButton();
        assertTrue(searchResultPage.isSearchResultPageIsOpened(), "Search Result page was not opened!");
        assertTrue(searchResultPage.isFlightInSevenDaysFound(), "No one flight was found!");

        ticketCost = searchResultPage.getPersonTicketCost();
        expectedSum = (3 * ticketCost) + 36 * 3;
        System.out.println("Expected Price = " + expectedSum);
        assertTrue(searchResultPage.clickButtonSelectOfTheFirstFlight(), "Flight was not selected!");
        searchResultPage.clickButtonNext();

        assertTrue(selectOptionsPage.isSelectOptionsPageIsOpened(), "Select Options page was not opened!");
        assertTrue(selectOptionsPage.clickSelectPlusButton(), "Plus was not selected!");

        actualSum = selectOptionsPage.getTotalAmount();
        System.out.println("Actual Price = " + actualSum);
        assertTrue(actualSum.equals(expectedSum), "Expected total amount is not equal to Actual total amount!");

    }

}

package com.epam.test.transAvia;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by hkap on 2/25/18.
 */
public class TestBookingFlight extends TestBase{

    String bookingNumber = "MF8C9R";
    String name = "kukharau";
    String flightDate = "9 June 2016";

    @Test
    public void testBookingFlightFound(){
        goToUrl(APP_LINK);

        checkPageOpened();

        assertTrue(homePage.isHomePageIsOpened(), "Home page was not opened!");
        homePage.clickManageYourBookingLink();
        homePage.clickViewYourBookingLink();

        assertTrue(loginPage.isLoginPageIsOpened(), "Login page was not opened!");
        loginPage.setBookingNumber(bookingNumber);
        loginPage.setLastName(name);
        loginPage.setFlightDate(flightDate);
        loginPage.clickButtonViewBooking();

    }

}

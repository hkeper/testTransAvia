package com.epam.test.transAvia;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * Created by hkap on 2/25/18.
 */
public class TestVideoLink extends TestBase {

    @Test
    public void testBookingFlightFound() throws InterruptedException {
        String videoLink;

        goToUrl(APP_LINK);
        checkPageOpened();
        assertTrue(homePage.isHomePageIsOpened(), "Home page was not opened!");
        homePage.clickServiceLink();
        homePage.clickHandLuggageLink();

        assertTrue(handLuggagePage.isHandLuggagePageIsOpened(), "Hand Luggage page was not opened!");
        videoLink = handLuggagePage.getVideoLink();
        assertTrue(handLuggagePage.getVideoLink().equals("https://youtu.be/fQMuhniqWAg"),
                "Wrong video link!'");

        goToUrl(videoLink);
        assertTrue(videoPage.isVideoPageIsOpened(), "Video hand luggage page was not opened!");
        assertTrue(videoPage.getVideoName().equals("Luggage"), "The video name is not 'Luggage!'");
        assertTrue(videoPage.getVideoAuthor().equals("Transavia"), "The video name is not 'Transavia!'");

    }

}

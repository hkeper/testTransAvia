package com.epam.test.transAvia.pages;

import com.epam.test.transAvia.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by hkap on 2/21/18.
 */
public class SearchResultPage extends Browser {

    final WebDriver driver;

    @FindBy(how= How.XPATH, xpath="//section[@class='flight outbound']")
    WebElement outboundFlight;
    @FindBy(how= How.XPATH, xpath="//div[contains(@class, 'day day-with-availability')]")
    WebElement availableDays;

    public SearchResultPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isSearchResultPageIsOpened(){
        webDriverWait_Medium.until(ExpectedConditions.titleContains("Book a flight"));
        return isElementVisible(outboundFlight);
    }

    public Boolean isFlightInSevenDaysFound(){
        return isElementVisible(availableDays);
    }

}

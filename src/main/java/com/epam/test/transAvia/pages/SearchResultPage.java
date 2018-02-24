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
    @FindBy(how= How.XPATH, xpath="//div[contains(@class, 'panel flight-result active')]")
    WebElement availableDays;
    @FindBy(how= How.XPATH, xpath="//div[contains(@class,'price')]")
    WebElement priceOfTheFirstFlight;
    @FindBy(how= How.XPATH, xpath="//button[.//div[@class='select']]")
    WebElement buttonSelectOfTheFirstFlight;
    @FindBy(how= How.CLASS_NAME, className="panel flight-result active selected")
    WebElement selectedFlight;
    @FindBy(how= How.NAME, className="next_button")
    WebElement buttonNext;

    public SearchResultPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isSearchResultPageIsOpened(){
        webDriverWait_Medium.until(ExpectedConditions.titleContains("Book a flight"));
        return isElementVisible(outboundFlight);
    }

    public Boolean isFlightInSevenDaysFound(){
        return waitForElementVisibility_MediumTimeOut(availableDays);
    }

    public Integer getPersonTicketCost(){
        System.out.println(priceOfTheFirstFlight.getText().substring(2));
        return Integer.parseInt(priceOfTheFirstFlight.getText().substring(2));
    }

    public Boolean clickButtonSelectOfTheFirstFlight(){
        buttonSelectOfTheFirstFlight.click();
        return waitForElementVisibility_SmallTimeOut(selectedFlight);
    }

    public void clickButtonNext(){
        buttonNext.click();
    }


}

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

    @FindBy(how= How.ID, id="top")
    WebElement mainContainer;
    @FindBy(how= How.XPATH, xpath="//section[@class='flight outbound']")
    WebElement outboundFlight;
    @FindBy(how= How.XPATH, xpath="//div[contains(@class, 'panel flight-result active')]")
    WebElement availableDays;
    @FindBy(how= How.XPATH, xpath="//div[contains(@class,'price')]")
    WebElement priceOfTheFirstFlight;
    @FindBy(how= How.XPATH, xpath="//section[@class='flight outbound']//button[.//div[@class='select']]")
    WebElement buttonSelectOfOutboundFlight;
    @FindBy(how= How.XPATH, xpath="//section[@class='flight outbound']//div[contains(@class, 'active selected')]")
    WebElement selectedOutboundFlight;
    @FindBy(how= How.NAME, name="next_button")
    WebElement buttonNext;
    @FindBy(how= How.XPATH, xpath="//div[contains(@class,'notification-error')]/p")
    WebElement errorMessage;
    @FindBy(how= How.ID, id="openJawRouteSelection_DepartureStationOutbound-input")
    WebElement fromOutbound;
    @FindBy(how= How.ID, id="openJawRouteSelection_ArrivalStationOutbound-input")
    WebElement toOutbound;
    @FindBy(how= How.ID, id="dateSelection_OutboundDate-datepicker")
    WebElement dateOutbound;
    @FindBy(how= How.ID, id="openJawRouteSelection_DepartureStationInbound-input")
    WebElement fromInbound;
    @FindBy(how= How.ID, id="openJawRouteSelection_ArrivalStationInbound-input")
    WebElement toInbound;
    @FindBy(how= How.ID, id="dateSelection_InboundDate-datepicker")
    WebElement dateInbound;
    @FindBy(how=How.XPATH, xpath="//li/strong")
    WebElement itemOfPointList;
    @FindBy(how= How.XPATH, xpath="//div[contains(@class,'panel_section--button-search')]//button[./span[contains(@class,'icon-search')]]")
    WebElement buttonSearch;
    @FindBy(how= How.XPATH, xpath="//section[@class='flight inbound']//button[.//div[@class='select']]")
    WebElement buttonSelectOfInboundFlight;
    @FindBy(how= How.XPATH, xpath="//section[@class='flight inbound']//div[contains(@class, 'active selected')]")
    WebElement selectedInboundFlight;

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
        return Integer.parseInt(priceOfTheFirstFlight.getText().substring(2));
    }

    public Boolean clickButtonSelectOfTheOutboundFlight(){
        buttonSelectOfOutboundFlight.click();
        return waitForElementVisibility_SmallTimeOut(selectedOutboundFlight);
    }

    public void clickButtonNext(){
        click(buttonNext);
    }

    public String getErrorMessage(){
        return errorMessage.getText();
    }

    public void setFromOutbound(String point){
        fromOutbound.click();
        fromOutbound.clear();
        fromOutbound.sendKeys(point);
        itemOfPointList.click();
    }

    public void setToOutbound(String point){
        toOutbound.click();
        toOutbound.clear();
        toOutbound.sendKeys(point);
        itemOfPointList.click();
    }

    public void setDateOutbound(String date){
        dateOutbound.clear();
        dateOutbound.sendKeys(date);
        mainContainer.click();
    }

    public void setFromInbound(String point){
        fromInbound.click();
        fromInbound.clear();
        fromInbound.sendKeys(point);
        itemOfPointList.click();
    }

    public void setToInbound(String point){
        toInbound.click();
        toInbound.clear();
        toInbound.sendKeys(point);
        itemOfPointList.click();
    }

    public void setDateInbound(String date){
        dateInbound.clear();
        dateInbound.sendKeys(date);
        mainContainer.click();
    }

    public void clickSearchButton(){
        click(buttonSearch);
    }

    public Boolean clickButtonSelectOfInboundFlight(){
        buttonSelectOfInboundFlight.click();
        return waitForElementVisibility_SmallTimeOut(selectedInboundFlight);
    }

}

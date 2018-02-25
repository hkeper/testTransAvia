package com.epam.test.transAvia.pages;

import com.epam.test.transAvia.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by hkap on 2/19/18.
 */
public class HomePage extends Browser{

    final WebDriver driver;

    @FindBy(how= How.ID, id="top")
    WebElement mainContainer;
    @FindBy(how= How.XPATH, xpath="//h1[contains(text(),'Where do you want to go?')]")
    WebElement sectionTitle;
    @FindBy(how= How.ID, id="routeSelection_DepartureStation-input")
    WebElement fieldFrom;
    @FindBy(how=How.XPATH, xpath="//li/strong")
    WebElement itemOfPointList;
    @FindBy(how= How.ID, id="routeSelection_ArrivalStation-input")
    WebElement fieldTo;
    @FindBy(how= How.ID, id="dateSelection_OutboundDate-datepicker")
    WebElement fieldDepartOn;
    @FindBy(how= How.ID, id="dateSelection_IsReturnFlight-datepicker")
    WebElement fieldReturnOn;
    @FindBy(how= How.XPATH, xpath="//div[./input[@id='dateSelection_IsReturnFlight']]/label")
    WebElement checkboxReturnOn;
    @FindBy(how= How.ID, id="booking-passengers-input")
    WebElement fieldPassengerBooking;
    @FindBy(how= How.XPATH, xpath="//div[@class='selectfield adults']//button[@class='button button-secondary increase']")
    WebElement buttonPlusAdults;
    @FindBy(how= How.XPATH, xpath="//div[@class='selectfield children']//button[@class='button button-secondary increase']")
    WebElement buttonPlusChildren;
    @FindBy(how= How.XPATH, xpath="//div[@class='selectfield babies']//button[@class='button button-secondary increase']")
    WebElement buttonPlusBabies;
    @FindBy(how= How.XPATH, xpath="//div[@id='buttonContainer']/button")
    WebElement buttonSavePassengers;
    @FindBy(how= How.XPATH, xpath="//div[./ul[@class='bulletless']]//button")
    WebElement buttonSearch;
    @FindBy(how= How.XPATH, xpath="//a[contains(@href, 'booking-overview')]")
    WebElement linkManageBooking;
    @FindBy(how= How.XPATH, xpath="//a[contains(@href, 'booking-overview')]/div")
    WebElement linkViewBooking;
    @FindBy(how= How.XPATH, xpath="//a[contains(@data-module,'ui/CombinationFlightHelper')]")
    WebElement linkAddMultDestination;


    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isHomePageIsOpened(){

        return isElementVisible(sectionTitle) && isElementVisible(fieldFrom) && isElementVisible(fieldTo) &&
                isElementVisible(fieldDepartOn) && isElementVisible(fieldReturnOn) &&
                isElementVisible(fieldPassengerBooking) ;
    }

    public void chooseFromPoint(String fromPoint) {
        fieldFrom.click();
        fieldFrom.clear();
        fieldFrom.sendKeys(fromPoint);
        itemOfPointList.click();
    }

    public void chooseToPoint(String fromPoint) {
        fieldTo.click();
        fieldTo.clear();
        fieldTo.sendKeys(fromPoint);
        itemOfPointList.click();
    }

    public void chooseDate(String date){
        fieldDepartOn.clear();
        fieldDepartOn.sendKeys(date);
    }

    public boolean unckeckReturnOn(){
        boolean checkboxOff = false;
        if(fieldReturnOn.getAttribute("placeholder").equals("Single flight")){
            checkboxOff = true;
        }else {
            click(checkboxReturnOn);
            checkboxOff = fieldReturnOn.getAttribute("placeholder").equals("Single flight");
        }
        return checkboxOff;
    }

    public void chooseAdultsPassengers(Integer numberOfPassengers){
        fieldPassengerBooking.click();
        for(int i=1; i<numberOfPassengers; i++){
            buttonPlusAdults.click();
        }
        buttonSavePassengers.click();
        mainContainer.click();
    }

    public void chooseChildrenPassengers(Integer numberOfPassengers){
        fieldPassengerBooking.click();
        for(int i=0; i<numberOfPassengers; i++){
            buttonPlusChildren.click();
        }
        buttonSavePassengers.click();
        mainContainer.click();
    }

    public void chooseBabiesPassengers(Integer numberOfPassengers){
        fieldPassengerBooking.click();
        for(int i=0; i<numberOfPassengers; i++){
            buttonPlusBabies.click();
        }
        buttonSavePassengers.click();
        mainContainer.click();
    }

    public void clickSearchButton(){
        click(buttonSearch);
    }

    public void clickManageYourBookingLink(){
        click(linkManageBooking);
    }

    public void clickViewYourBookingLink(){
        click(linkViewBooking);
    }

    public void clickLinkAddMultDestination(){
        click(linkAddMultDestination);
    }

}

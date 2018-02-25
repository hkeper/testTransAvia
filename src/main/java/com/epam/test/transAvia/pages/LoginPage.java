package com.epam.test.transAvia.pages;

import com.epam.test.transAvia.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by hkap on 2/25/18.
 */
public class LoginPage extends Browser {

    final WebDriver driver;

    @FindBy(how= How.ID, id="top")
    WebElement mainContainer;
    @FindBy(how= How.ID, id="retrieveBookingByLastname_RecordLocator")
    WebElement fieldBookingNumber;
    @FindBy(how= How.ID, id="retrieveBookingByLastname_LastName")
    WebElement fieldLastName;
    @FindBy(how= How.ID, id="retrieveBookingByLastname_FlightDate-datepicker")
    WebElement fieldFlightDate;
    @FindBy(how= How.XPATH, xpath="//div[@class='fields']//button")
    WebElement buttonViewBooking;


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isLoginPageIsOpened(){
        webDriverWait_Medium.until(ExpectedConditions.titleContains("Log"));
        return isElementVisible(mainContainer);
    }

    public void setBookingNumber(String number){
        fieldBookingNumber.clear();
        fieldBookingNumber.sendKeys(number);
    }

    public void setLastName(String name){
        fieldLastName.clear();
        fieldLastName.sendKeys(name);
    }

    public void setFlightDate(String date){
        fieldFlightDate.clear();
        fieldFlightDate.sendKeys(date);
    }

    public void clickButtonViewBooking(){
        click(buttonViewBooking);
    }

}

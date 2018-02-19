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
    @FindBy(how= How.XPATH, xpath="//div[./input[@id='dateSelection_IsReturnFlight']]")
    WebElement checkboxReturnOn;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isHomePageIsOpened(){
        return isElementVisible(fieldFrom);
    }

    public void chooseFromPoint(String fromPoint) throws InterruptedException {
        fieldFrom.click();
        fieldFrom.clear();
        fieldFrom.sendKeys(fromPoint);
        itemOfPointList.click();
    }

    public void chooseToPoint(String fromPoint) throws InterruptedException {
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
            checkboxReturnOn.click();
            checkboxOff = fieldReturnOn.getAttribute("placeholder").equals("Single flight");
        }
        return checkboxOff;
    }

}

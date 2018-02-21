package com.epam.test.transAvia.pages;

import com.epam.test.transAvia.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by hkap on 2/21/18.
 */
public class SearchResultPage extends Browser {

    final WebDriver driver;

    @FindBy(how= How.CLASS_NAME, className="flight outbound")
    WebElement outboundFlight;
    @FindBy(how= How.CLASS_NAME, className="panel flight-result active")
    WebElement foundFlight;

    public SearchResultPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isSearchResultPageIsOpened(){
        return isElementVisible(outboundFlight);
    }

    public Boolean isFlightFound(){
        return isElementVisible(foundFlight);
    }

}

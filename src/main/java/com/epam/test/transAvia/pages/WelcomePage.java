package com.epam.test.transAvia.pages;

import com.epam.test.transAvia.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by hkap on 2/19/18.
 */
public class WelcomePage extends Browser{

    final WebDriver driver;

    @FindBy(how=How.XPATH, xpath="//h1[text()='Welcome']")
    WebElement welcomeTitle;
    @FindBy(how=How.XPATH, xpath = "//a[@href='/en-EU/home']")
    WebElement linkOtherCountry;

    public WelcomePage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isWelcomePageOpened(){
        return isElementVisible(welcomeTitle);
    }

    public void clickOtherCountryLink(){
        linkOtherCountry.click();
    }

}

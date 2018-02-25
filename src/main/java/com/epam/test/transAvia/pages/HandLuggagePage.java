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
public class HandLuggagePage extends Browser {

    final WebDriver driver;

    @FindBy(how= How.ID, id="top")
    WebElement mainContainer;
    @FindBy(how= How.XPATH, xpath="//iframe")
    WebElement videoFrame;

    public HandLuggagePage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isHandLuggagePageIsOpened(){
        webDriverWait_Medium.until(ExpectedConditions.titleContains("hand luggage"));
        return isElementVisible(mainContainer);
    }

    public String getVideoLink(){
        return videoFrame.getAttribute("src");
    }

}

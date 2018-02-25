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
public class VideoPage extends Browser {

    final WebDriver driver;

    @FindBy(how= How.ID, id="content")
    WebElement mainContainer;
    @FindBy(how= How.XPATH, xpath="//div[@id='info-contents']//h1")
    WebElement videoName;
    @FindBy(how= How.XPATH, xpath="//div[@id='owner-container']//a")
    WebElement videoAuthor;

    public VideoPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isVideoPageIsOpened(){
        webDriverWait_Medium.until(ExpectedConditions.titleContains("Luggage"));
        return isElementVisible(mainContainer);
    }

    public String getVideoName(){
        return videoName.getText();
    }

    public String getVideoAuthor(){
        return videoAuthor.getText();
    }

}

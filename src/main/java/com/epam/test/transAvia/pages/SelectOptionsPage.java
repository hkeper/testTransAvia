package com.epam.test.transAvia.pages;

import com.epam.test.transAvia.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by hkap on 2/24/18.
 */
public class SelectOptionsPage extends Browser {

    final WebDriver driver;

    @FindBy(how= How.ID, id="top")
    WebElement mainContainer;
    @FindBy(how= How.XPATH, xpath="//th[@data-product-class='B']")
    WebElement buttonSelectPlus;
    @FindBy(how= How.XPATH, xpath="//*[contains(@class,'selected-column-2')]")
    WebElement plusSelected;
    @FindBy(how= How.CLASS_NAME, className="back")
    WebElement totalAmount;

    public SelectOptionsPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean isSelectOptionsPageIsOpened(){
        webDriverWait_Medium.until(ExpectedConditions.titleContains("Select"));
        return isElementVisible(mainContainer);
    }

    public Boolean clickSelectPlusButton(){
        buttonSelectPlus.click();
        return isElementVisible(plusSelected);
    }

    public Integer getTotalAmount(){
        return Integer.parseInt(totalAmount.getText().substring(2,5));
    }

}

package com.epam.test.transAvia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by hkap on 2/8/18.
 */
public class Browser {

    public void startBrowser() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.transavia.com/en-EU/home/");

    }


}

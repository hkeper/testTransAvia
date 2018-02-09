package com.jcatalog.qa.testframework.pages.ssm;

import com.jcatalog.qa.testframework.Page;
import org.openqa.selenium.By;

/**
 * Created by Michael Makeikin
 * on 10/19/15.
 */

public class CommonPage extends Page {

    public By appLogo = By.id("app-logo");
    public By menuButton = By.xpath("//button[@data-toggle='collapse']");
    public By linkCustomer = By.xpath("//div[@id='main-menu']//a[contains(text(),'Customer')]/b");
    public By linkDashboard = By.xpath("//a[contains(text(), 'Dashboard')]");
    public By linkCustomerProfile = By.xpath("//a[contains(@href, 'customerProfile')]");

    public By linkSupplier = By.xpath("//div[@id='main-menu']//a[contains(text(),'Supplier')]/b");
    public By linkSupplierProfile = By.xpath("//a[contains(@href, 'supplierProfile')]");

    public By linkCatalogs = By.xpath("//div[@id='main-menu']//a[contains(text(),'Catalogs')]/b");
    public By linkProcessCatalog = By.xpath("//a[contains(text(), 'Process Catalog')]");
    public By linkShowWorklist = By.xpath("//a[contains(text(), 'Show Worklist')]");
    public By linkListProcesses = By.xpath("//a[contains(text(), 'List Processes')]");

    public By linkUserDropDown = By.xpath("//li[a[@class='dropdown-toggle hidden-sm hidden-xs']]");
    public By linkLogout = By.xpath("//a[contains(@href,'logoff')]");
    public By linkKataloge = By.xpath("//a[contains(text(),'Kataloge')]");

    public By linkMainMenuDropDown = By.xpath("//a[@class='icon-nav-item dropdown-toggle']");
    public By linkSetup = By.xpath("//a[@title='Master Data']");
    public By linkCatalogServices = By.xpath("//a[@title='Catalog Services']");
    public By linkSystem = By.xpath("//a[@title='System Administration']");

    public By searchSupplier = By.xpath("//a[contains(@onclick,'Supplier')]");
    public By searchCustomer = By.xpath("//a[contains(@onclick,'Customer')]");
    public By searchCatalog = By.xpath("//a[contains(@onclick,'ProductCatalog')]");
    public By searchContract = By.xpath("//a[contains(@onclick,'contract.Contract')]");

    public By fieldSearchById = By.xpath("//input[@type='text']");
    public By buttonSearch = By.xpath("//input[@value='Search']");

    public Boolean isAppOpened(){
        return isElementVisible(appLogo);
    }

    public void clickCustomerDashboard(){
        if(waitForElementVisibility_MediumTimeOut(menuButton)){
            click(menuButton);
        }
        click(linkCustomer);
        waitForElementVisibility_MediumTimeOut(linkDashboard);
        click(linkDashboard);
    }

    public void clickCustomerProfile(){
        if(waitForElementVisibility_MediumTimeOut(menuButton)){
            click(menuButton);
        }
        click(linkCustomer);
        waitForElementVisibility_MediumTimeOut(linkCustomerProfile);
        click(linkCustomerProfile);
    }

    public void clickSupplierProfile(){
        if(waitForElementVisibility_MediumTimeOut(menuButton)){
            click(menuButton);
        }
        click(linkSupplier);
        waitForElementVisibility_MediumTimeOut(linkSupplierProfile);
        click(linkSupplierProfile);
    }

    public void clickProcessCatalog(){
        if(waitForElementVisibility_MediumTimeOut(menuButton)){
            click(menuButton);
        }
        click(linkCatalogs);
        waitForElementVisibility_MediumTimeOut(linkProcessCatalog);
        click(linkProcessCatalog);
    }

    public void clickShowWorklist(){
        if(waitForElementVisibility_MediumTimeOut(menuButton)){
            click(menuButton);
        }
        click(linkCatalogs);
        waitForElementVisibility_MediumTimeOut(linkShowWorklist);
        click(linkShowWorklist);
    }

    public void clickListProcesses(){
        if(waitForElementVisibility_MediumTimeOut(menuButton)){
            click(menuButton);
        }
        click(linkCatalogs);
//        waitForElementVisibility_MediumTimeOut(linkListProcesses);
//        click(linkListProcesses);

        click(By.xpath("//a[contains(@href,'/listIndex')]"));

    }

    public Boolean isLinkKatalogePresent(){
        if(waitForElementVisibility_MediumTimeOut(menuButton)){
            click(menuButton);
        }
        waitForElementVisibility_SmallTimeOut(linkKataloge);
        return isElementVisible(linkKataloge);
    }

    public void clickLogout(){
        click(linkUserDropDown);
        click(linkLogout);
    }

    public void clickSetup(){
        click(linkMainMenuDropDown);
        click(linkSetup);
    }

    public void clickSystem(){
        click(linkMainMenuDropDown);
        click(linkSystem);
    }

    public void clickCatalogServices(){
        click(linkMainMenuDropDown);
        click(linkCatalogServices);
    }

    public void chooseSupplier(String supplier) {
        selectInPopup(searchSupplier, supplier);
    }

    public void chooseCustomer(String customer) {
        selectInPopup(searchCustomer, customer);
    }

    public void chooseCatalog(String catalog) {
        selectInPopup(searchCatalog, catalog);
    }

    public void chooseContract(String contract) {
        selectInPopup(searchContract, contract);
    }

    public void selectInPopup(By searchWindow, String link){
        String originalWin = getCurrentWindowHandle();
        click(searchWindow);
        switchToNewWindow();
        type(fieldSearchById, link);
        click(buttonSearch);
        click(By.xpath("//a[.='" + link + "']"));
        backToWindow(originalWin);
    }

}

package com.jcatalog.qa.testframework.pages.ssm.catalogs;

import com.jcatalog.qa.testframework.pages.ssm.CommonPage;
import com.jcatalog.qa.testframework.utils.ConfigProperties;
import com.jcatalog.qa.testframework.utils.ProcessType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael Makeikin
 * on 10/19/15.
 */
public class ListProcessesPage extends CommonPage {

    private static int WAIT_VISIBLE_ELEMENT = ConfigProperties.getProperty("processList.delay.waitVisibleElement.sec") != null ?
            Integer.valueOf(ConfigProperties.getProperty("processList.delay.waitVisibleElement.sec")) : 2;

    public static String supplierPageUrl = "ssm/supplierProcess/listIndex";
    public static String customerPageUrl = "ssm/customerProcess/listIndex";

    public By pageHeading = By.xpath("//h1");
    public By el = By.xpath("//a[contains(@onclick,'product.ProductCatalog')]");
    public By listProcessType = By.id("type");
    public By fieldCatalogVersion = By.id("catalogVersion");
    public By buttonSearch = By.name("_action_search");
    public By labelActivated = By.xpath("//span[.='ACTIVATED']");
    public By labelStopped = By.xpath("//span[.='STOPPED']");
    public By labelSuspended = By.xpath("//span[.='SUSPENDED']");
    public By labelStatus = By.xpath("//td[8]/span");
    public By labelOfLastProcessCompleted = By.xpath("//tr[1]//span[.='COMPLETED']");
    public By lastProcessLink = By.xpath("//a[contains(@href,'showProcess')]");

    private By refreshLink = By.xpath("//input[@value='Refresh']");
    private By startedFrom = By.id("startedFrom");
    private By startedTo = By.id("startedTo");
    private By finishedFrom = By.id("finishedFrom");
    private By finishedTo = By.id("finishedTo");
    private By trProcesses = By.xpath("//form[@name='processListForm' and @id='processListForm']/table[@class='table table-responsive']/tbody/tr");
    private By catalogInput = By.xpath("//div[@class='input-group' and select[@id='productCatalogId']]/ul");
    private By catalogSearch = By.xpath("//div[@class='input-group' and select[@id='productCatalogId']]/span");
    private By catalogWinSearchButton = By.xpath("//input[@value='Search']");
    private By catalogWinInput = By.id("productCatalogId");
    private By catalogWinCatalogLink = By.xpath("//td/a");

    private By catalogInputInner = By.xpath("//div[@class='input-group' and select[@id='productCatalogId']]/ul/li/span/input");
    private By customerInput = By.xpath("//div[@class='input-group' and select[@id='customerId']]/ul");
    private By customerInputInner = By.xpath("//div[@class='input-group' and select[@id='customerId']]/ul/li/span/input");
    private By supplierInput = By.xpath("//div[@class='input-group' and select[@id='supplierId']]/ul");
    private By supplierInputInner = By.xpath("//div[@class='input-group' and select[@id='supplierId']]/ul/li/span/input");
    private By catalogVersionInput = By.xpath("//.[@id='catalogVersion']");//By.id("catalogVersion");
    private By contractInput = By.xpath("//div[@class='input-group' and select[@id='contractId']]/ul");
    private By contractSearch = By.xpath("//div[@class='input-group' and select[@id='contractId']]/span");
    private By contractWinSearchButton = By.xpath("//input[@value='Search']");
    private By contractWinInput = By.id("contractId");
    private By contratcWinContractLink = By.xpath("//td/a");

    private By contractInputInner = By.xpath("//div[@class='input-group' and select[@id='contractId']]/ul/li/span/input");
    private By errorMessages = By.xpath("//div[@class='bs-callout bs-callout-danger']/p");
    private By isRunningOnlyInput = By.xpath("//input[@name='isRunningOnly' and @id='isRunningOnly' and @type='checkbox']");
    private By resetButton = By.xpath("//input[@value='Reset' and @type='button']");

    //Indexes are used for click on sort links
    private final int columnID = 1;
    private final int columnType = 2;
    private final int columnCatalog = 3;
    private final int columnVersion = 4;
    private final int columnCustomer = 5;
    private final int columnSupplier = 6;
    private final int columnContract = 7;
    private final int columnStatus = 8;
    private final int columnCreated = 10;
    private final int columnFinished = 11;

    public void chooseProcessType(ProcessType processType){
        selectInListByValue(listProcessType, processType.getValue());
    }

    public void enterCatalogVersion(String catalogVersion){
        type(fieldCatalogVersion, catalogVersion);
    }

    public void clickSearchButton(){
        click(buttonSearch);
    }

    public String getCatalogVersion(String processID){
        By locator = By.xpath("//tr[./td/a[contains(text(),'" + processID + "')]]/td[4]");
        waitForElementVisibility_BigTimeOut(locator);
        return getTextFromElement(locator);
    }

    public Boolean isStatusPresent() {
        waitForElementVisibility_SmallTimeOut(labelStatus);
        return  isElementVisible(labelStatus);
    }

    public Boolean isWorflowActivated(){
        waitForElementVisibility_SmallTimeOut(labelStatus);
        return isElementVisible(labelActivated);
    }

    public Boolean isWorflowStopped(){
        waitForElementVisibility_SmallTimeOut(labelStatus);
        return isElementVisible(labelStopped);
    }

    public Boolean isWorflowSuspended(){
        waitForElementVisibility_SmallTimeOut(labelStatus);
        return isElementVisible(labelSuspended);
    }

    public Boolean isLastProcessCompleted(){
        waitForElementVisibility_SmallTimeOut(labelStatus);
        return isElementVisible(labelOfLastProcessCompleted);
    }

    public void clickProcessLink(String id){
        click(By.xpath("//a[contains(text(),'"+id+"')]"));
    }

    public void clickLastProcessLink(){
        click(lastProcessLink);
    }

    public void clickRefresh() {
        click(refreshLink);
    }

    public int getCountOfActivatedWorkflow() {
        return getVisibleElementsCount(labelActivated);
    }

    public int getCountOfStoppedWorkflow() {
        return getVisibleElementsCount(labelStopped);
    }

    public int getProcessCountByCatalogVersion(String catalogVersion){
        return getVisibleElementsCount(By.xpath("//tr/td[4][text()='" + catalogVersion + "']"));
    }

    public int getCountOfSuspendedWorkflow() {
        return getVisibleElementsCount(labelSuspended);
    }

    public void typeCatalogInput(String text){
        click(catalogInput);
        type(catalogInputInner, text);
        moveToElement(pageHeading);
        click(pageHeading);
    }

    public void SearchCatalog(String text){
        String mainWindow = getCurrentWindowHandle();
        click(catalogSearch);
        switchToNewWindow();
        type(catalogWinInput, text);
        click(catalogWinSearchButton);
        click(catalogWinCatalogLink);
        backToWindow(mainWindow);
    }

    public void chooseExistedCatalog(String text){
        chooseCatalog(text);
    }

    public void typeCustomerInput(String text){
        click(customerInput);
        type(customerInputInner, text);
    }

    public void chooseExistedCustomer(String text){
        chooseCustomer(text);
    }

    public void typeSupplierInput(String text){
        click(supplierInput);
        type(supplierInputInner, text);
    }

    public void chooseExistedSupplier(String text){
        chooseSupplier(text);
    }

    public void typeCatalogVersionInput(String text){
        type(catalogVersionInput, text);
    }

    public void typeContractInput(String text){
        click(contractInput);
        type(contractInputInner, text);
    }

    public void SearchContract(String text){
        String mainWindow = getCurrentWindowHandle();
        click(contractSearch);
        switchToNewWindow();
        type(contractWinInput, text);
        click(contractWinSearchButton);
        click(contratcWinContractLink);
        backToWindow(mainWindow);
    }

    public void chooseExistedContract(String text){
        chooseContract(text);
    }

    public boolean checkExistingErrorMessage(String messageText) {
        List<WebElement> elementList = getVisibleElements(errorMessages);
        for (int i=0; i < elementList.size(); i++) {
            WebElement element = elementList.get(i);
            if (element.getText().equals(messageText)) {
                return true;
            }
        }
        return false;
    }

    public void typeStartedFrom(String text) {
        type(startedFrom, text);
    }

    public void typeStartedTo(String text) {
        type(startedTo, text);
    }

    public void typeFinishedFrom(String text) {
        type(finishedFrom, text);
    }

    public void typeFinishedTo(String text) {
        type(finishedTo, text);
    }

    public void clickIsRunningOnlyInput() {
        click(isRunningOnlyInput);
    }

    public boolean checkIsRunningOnlyInput() {
        return isChecked(isRunningOnlyInput);
    }

    public int getProcessesCount() {
        return getVisibleElementsCount(trProcesses);
    }

    public void clickReset() {
        click(resetButton);
    }

    public String getTextStartedFrom() {
        return getTextFromElement(startedFrom);
    }

    public String getTextStartedTo() {
        return getTextFromElement(startedTo);
    }

    public String getTextFinishedTo() {
        return getTextFromElement(finishedTo);
    }

    public String getTextFinishedFrom() {
        return getTextFromElement(finishedFrom);
    }

    public int getMaxPageNumber() {
        List<WebElement> pagesLink = getVisibleElements(By.xpath("//ul[@class='pagination']/li"));
        if (pagesLink == null || pagesLink.isEmpty()) {
            return 1;
        }
        int maxNumber = 1;
        for (WebElement link : pagesLink) {
            try {
                int pageNumber = Integer.valueOf(link.getText());
                if (maxNumber < pageNumber) {
                    maxNumber = pageNumber;
                }
            } catch (NumberFormatException ex) {
            }
        }
        return maxNumber;
    }

    public void clickPage(int pageNumber) {
        By element = By.xpath("//ul[@class='pagination']/li/a[.='" + pageNumber + "']");
        if (isElementVisible(element)) {
            click(element);
        }
    }

    public int getActivePageNumber() {
        try {
            return Integer.valueOf(getTextFromElement(By.xpath("//ul[@class='pagination']/li[@class='active']/a")));
        } catch (NumberFormatException ex) {
            return 1;
        }
    }

    public void clickPreviousPage() {
        if (isElementVisible(By.xpath("//ul[@class='pagination']/li/a"))) {
            click(By.xpath("//ul[@class='pagination']/li[1]/a"));
        }
    }

    public void clickNextPage() {
        if (isElementVisible(By.xpath("//ul[@class='pagination']/li/a"))) {
            click(By.xpath("//ul[@class='pagination']/li[last()]/a"));
        }
    }

    private By getHeaderLocationByColumn(int columnIndex) {
        return By.xpath("//form[@name='processListForm' and @id='processListForm']/table[@class='table table-responsive']/thead/tr/th[" + columnIndex + "]/a");
    }

    public void clickHeaderID() {
        click(getHeaderLocationByColumn(columnID));
    }

    public void clickHeaderType() {
        click(getHeaderLocationByColumn(columnType));
    }

    public void clickHeaderCatalog() {
        click(getHeaderLocationByColumn(columnCatalog));
    }

    public void clickHeaderContract() {
        click(getHeaderLocationByColumn(columnContract));
    }

    public void clickHeaderCustomer() {
        click(getHeaderLocationByColumn(columnCustomer));
    }

    public void clickHeaderSupplier() {
        click(getHeaderLocationByColumn(columnSupplier));
    }

    public void clickHeaderVersion() {
        click(getHeaderLocationByColumn(columnVersion));
    }

    public void clickHeaderCreated() {
        click(getHeaderLocationByColumn(columnCreated));
    }

    public void clickHeaderFinished() {
        click(getHeaderLocationByColumn(columnFinished));
    }

    private List<String> getProcessListValuesByColumn(int columnIndex) {
        List<WebElement> elements;
        switch (columnIndex) {
            case columnID:
                elements = getVisibleElements(By.xpath("//form[@name='processListForm' and @id='processListForm']/table[@class='table table-responsive']/tbody/tr/td[1]/a"));
                break;
            case columnStatus:
                elements = getVisibleElements(By.xpath("//form[@name='processListForm' and @id='processListForm']/table[@class='table table-responsive']/tbody/tr/td[8]/span[1]"));
                break;
            default:
                elements = getVisibleElements(By.xpath("//form[@name='processListForm' and @id='processListForm']/table[@class='table table-responsive']/tbody/tr/td[" + columnIndex + "]/span[1]"));
        }
        List<String> listValues = new ArrayList<>();
        for (WebElement element : elements) {
            listValues.add(element.getText());
        }
        return listValues;
    }

    public List<String> getListProcessIDs() {
        return getProcessListValuesByColumn(columnID);
    }

    public List<String> getListProcessTypes() {
        return getProcessListValuesByColumn(columnType);
    }

    public List<String> getListProcessCatalogs() {
        return getProcessListValuesByColumn(columnCatalog);
    }

    public List<String> getListProcessContracts() {
        return getProcessListValuesByColumn(columnContract);
    }

    public List<String> getListProcessStatuses() {
        return getProcessListValuesByColumn(columnStatus);
    }

    public List<String> getListProcessCustomers() {
        return getProcessListValuesByColumn(columnCustomer);
    }

    public List<String> getListProcessSuppliers() {
        return getProcessListValuesByColumn(columnSupplier);
    }

    public List<String> getListProcessVersions() {
        return getProcessListValuesByColumn(columnVersion);
    }

    public List<String> getListProcessCreated() {
        return getProcessListValuesByColumn(columnCreated);
    }

    public List<String> getListProcessFinished() {
        return getProcessListValuesByColumn(columnFinished);
    }

    public void clickOpenProcessLink(int rowNumber) {
        click(By.xpath("//form[@name='processListForm' and @id='processListForm']/table[@class='table table-responsive']/tbody/tr[" + rowNumber + "]/td[12]/div/nobr/a"));
    }

    public void clickDropDownProcessLink(int rowNumber) {
        click(By.xpath("//form[@name='processListForm' and @id='processListForm']/table[@class='table table-responsive']/tbody/tr[" + rowNumber + "]/td[12]/div/nobr/button[@data-toggle='dropdown']"));
    }

    public void clickProductReviewProcessLink(int rowNumber) {
        clickDropDownProcessLink(rowNumber);
        click(By.xpath("//form[@name='processListForm' and @id='processListForm']/table[@class='table table-responsive']/tbody/tr[" + rowNumber + "]/td[12]/div/nobr/ul/li/a[./small[.='Product Review']]"));
    }

    public void clickQueryToolProcessLink(int rowNumber) {
        clickDropDownProcessLink(rowNumber);
        click(By.xpath("//form[@name='processListForm' and @id='processListForm']/table[@class='table table-responsive']/tbody/tr[" + rowNumber + "]/td[12]/div/nobr/ul/li/a[./small[.='Query Tool']]"));
    }

    public void clickCSVDownloadProcessLink(int rowNumber) {
        clickDropDownProcessLink(rowNumber);
        click(By.xpath("//form[@name='processListForm' and @id='processListForm']/table[@class='table table-responsive']/tbody/tr[" + rowNumber + "]/td[12]/div/nobr/ul/li/a[./small[.='CSV']]"));
    }

    public void clickEXCELDownloadProcessLink(int rowNumber) {
        clickDropDownProcessLink(rowNumber);
        click(By.xpath("//form[@name='processListForm' and @id='processListForm']/table[@class='table table-responsive']/tbody/tr[" + rowNumber + "]/td[12]/div/nobr/ul/li/a[./small[.='EXCEL']]"));
    }

    public void clickExportProcesses() {
        click(By.xpath("//button[contains(text(),'Export Processes List to') and @type='button']"));
    }

    public void clickExportProcessesCSV() {
        click(By.xpath("//div[button[contains(text(),'Export Processes List to') and @type='button']]/ul/li/a[./small[.='CSV']]"));
    }

    public void clickExportProcessesEXCEL() {
        click(By.xpath("//div[button[contains(text(),'Export Processes List to') and @type='button']]/ul/li/a[./small[.='EXCEL']]"));
    }

}


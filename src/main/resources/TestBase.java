package com.jcatalog.qa.testframework;

import com.jcatalog.qa.testframework.data.UserData;
import com.jcatalog.qa.testframework.pages.common.LoginPage;
import com.jcatalog.qa.testframework.pages.josso.JossoLoginPage;
import com.jcatalog.qa.testframework.pages.opc.SearchPage;
import com.jcatalog.qa.testframework.pages.pim.products.ProductCatalogActivatorPage;
import com.jcatalog.qa.testframework.pages.pim.products.WelcomePage;
import com.jcatalog.qa.testframework.pages.prov.PermissionEditorPage;
import com.jcatalog.qa.testframework.pages.ssm.CommonPage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.catalogservices.CatalogScorecardPage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.catalogservices.ProductReviewPage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.catalogservices.SchedulingPage;
import com.jcatalog.qa.testframework.pages.ssm.catalogs.*;
import com.jcatalog.qa.testframework.pages.ssm.customer.CustomerDashboardPage;
import com.jcatalog.qa.testframework.pages.ssm.customer.CustomerProfilePage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.CatalogServicesPage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.MasterDataPage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.SystemPage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.catalogservices.CatalogDBQueryToolPage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.masterdata.BusinessRulEditorPage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.masterdata.ContentBusinessRulePage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.masterdata.CreateContentBusinessRulePage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.masterdata.CustomerEditorPage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.system.AdministrateProcessesPage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.system.CMLConsolePage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.system.CustomizationareaPage;
import com.jcatalog.qa.testframework.pages.ssm.mainmenu.system.WorkareaPage;
import com.jcatalog.qa.testframework.pages.ssm.supplier.SupplierDashboardPage;
import com.jcatalog.qa.testframework.pages.ssm.supplier.SupplierProfilePage;
import com.jcatalog.qa.testframework.pages.ssm.usernavbar.ChangeAssignmentPage;
import com.jcatalog.qa.testframework.utils.ConfigProperties;
import com.jcatalog.qa.testframework.utils.ProcessType;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;


/**
 * Created by Michael Makeikin
 * on 10/19/15.
 */

public class TestBase {

    public final static String APP_LINK = ConfigProperties.getProperty("appLink");

    public String supplierWorkflowId;
    public int timeToWaitProcessEnds = 200; // It's in seconds
    public int timeToWaitWholeWorkflowEnds = 50; // It's in seconds

    public String importFileWithTestUser = System.getProperty("user.dir") + File.separator + "src" + File.separator +
            "main" + File.separator + "resources" + File.separator + "TestData_CatalogUser_Role.xml";
    public String importFile = System.getProperty("user.dir") + File.separator + "src" +
            File.separator + "main" + File.separator + "resources" + File.separator + "StandardMultiSheetReplace.xls";
    public String activationDate = "01/01/2030";

    public UserData adminUser = new UserData("jcadmin", "jcadmin");
    public UserData testUser = new UserData("qa_ssm", "`");
    public UserData supplierUser = new UserData("Supplier", "Supplier");
    public UserData customerUser = new UserData("customer", "customer");
    public String languageEN = "English";
    public String languageDE = "Deutsch";
    public String testSupplier = "hard001";
    public String testCustomer = "OC001";
    public String testCatalog = "lap001";
    public String testContract = "lap001";
    public String testImportFormat = "StandardMultiSheet";
    public String testUoM = "ISO";
    public String testClassification = "basic";
    public String indent;

    public Page executor = new Page();
    public JossoLoginPage jossoLoginPage;
    public LoginPage loginPage;
    public ChangeAssignmentPage changeAssignmentPage;
    //SSM
    public SupplierProfilePage supplierProfile;
    public CustomerProfilePage customerProfilePage;
    public WorkflowWizardPage workflowWizardPage;
    public CMLConsolePage cmlConsolePage;
    public SupplierDashboardPage supplierDashboardPage;
    public CommonPage commonPage;
    public ProcessDetailsPage processDetails;
    public SupplierProcessPage supplierProcessPage;
    public MasterDataPage masterDataPage;
    public ContentBusinessRulePage contentBusinessRulePage;
    public SystemPage systemPage;
    public WorkareaPage workareaPage;
    public ListProcessesPage listProcessesPage;
    public CustomerProcessPage customerProcessPage;
    public ShowWorklistPage showWorklistPage;
    public ApprovalPage approvalPage;
    public CustomerActivateWorklowPage customerActivateWorklowPage;
    public CreateContentBusinessRulePage createContentBusinessRulePage;
    public CustomerDashboardPage customerDashboardPage;
    public SupplierReviewPage supplierReviewPage;
    public CatalogServicesPage catalogServicesPage;
    public CatalogDBQueryToolPage catalogDBQueryToolPage;
    public AdministrateProcessesPage administrateProcessesPage;
    public SchedulingPage schedulingPage;
    public ProductReviewPage productReviewPage;
    public CustomerEditorPage customerEditorPage;
    public CatalogScorecardPage catalogScorecardPage;
    public BusinessRulEditorPage businessRulEditorPage;
    public CustomizationareaPage customizationareaPage;
    //OPC
    public SearchPage searchPage;
    //PROV
    public PermissionEditorPage permissionEditorPage;
    //PIM
    public WelcomePage welcomePage;
    public ProductCatalogActivatorPage productCatalogActivatorPage;

    private String currentAssignmentCustomer = null;
    private String currentAssignmentSupplier = null;

    public String getCurrentAssignmentCustomer() {
        return currentAssignmentCustomer;
    }
    public String getCurrentAssignmentSupplier() {
        return currentAssignmentSupplier;
    }

    Date date = new Date();
    String DATE_FORMAT = "MM/dd/yyyy";// HH:mm:SS";
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    public String today =  sdf.format(date);
    DateFormat logDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
    Date logDate;
    String StartTimeClass, EndTimeClass, StartTimeTest, EndTimeTest;


    @BeforeClass
    public void start() {
        try {
            DOMConfigurator.configure("log4j.xml");
            executor.startBrowser();
            jossoLoginPage = new JossoLoginPage();
            loginPage = new LoginPage();
            changeAssignmentPage = new ChangeAssignmentPage();
            workflowWizardPage = new WorkflowWizardPage();
            cmlConsolePage = new CMLConsolePage();
            supplierDashboardPage = new SupplierDashboardPage();
            commonPage = new CommonPage();
            processDetails = new ProcessDetailsPage();
            supplierProcessPage = new SupplierProcessPage();
            masterDataPage = new MasterDataPage();
            contentBusinessRulePage = new ContentBusinessRulePage();
            systemPage = new SystemPage();
            workareaPage = new WorkareaPage();
            listProcessesPage = new ListProcessesPage();
            customerProcessPage = new CustomerProcessPage();
            showWorklistPage = new ShowWorklistPage();
            approvalPage = new ApprovalPage();
            customerActivateWorklowPage = new CustomerActivateWorklowPage();
            createContentBusinessRulePage = new CreateContentBusinessRulePage();
            customerDashboardPage = new CustomerDashboardPage();
            searchPage = new SearchPage();
            supplierReviewPage = new SupplierReviewPage();
            permissionEditorPage = new PermissionEditorPage();
            supplierProfile = new SupplierProfilePage();
            customerProfilePage = new CustomerProfilePage();
            catalogServicesPage = new CatalogServicesPage();
            catalogDBQueryToolPage = new CatalogDBQueryToolPage();
            administrateProcessesPage = new AdministrateProcessesPage();
            schedulingPage = new SchedulingPage();
            productReviewPage = new ProductReviewPage();
            customerEditorPage = new CustomerEditorPage();
            welcomePage = new WelcomePage();
            productCatalogActivatorPage = new ProductCatalogActivatorPage();
            catalogScorecardPage = new CatalogScorecardPage();
            businessRulEditorPage = new BusinessRulEditorPage();
            customizationareaPage = new CustomizationareaPage();

            indent = "";
            Capabilities caps = ((RemoteWebDriver) Page.driver).getCapabilities();
            log("Browser version - " + caps.getVersion());
            logDate = new Date();
            StartTimeClass = logDateFormat.format(logDate);
            log("[" + StartTimeClass + "] - START time of class.");
            log("--------------------------------------------------------------------------------------------------");
            log("----------------- Start testing of class " + getClass().getSimpleName()
                    + " -----------------");
            log("--------------------------------------------------------------------------------------------------");

        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
    }

    @BeforeMethod
    public void beforeMethod(){
        indent = "    ";
        logDate = new Date();
        StartTimeTest = logDateFormat.format(logDate);
        log("[" + StartTimeTest + "] - START time of test.");
        indent = "        ";
        log("**************************************************************************************************");
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        log("**************************************************************************************************");
        if(ITestResult.FAILURE == result.getStatus()){
            log("!!!!!FAIL!!!!! Capture screenshot: " + executor.captureScreen());
        }
        goToUrl("ssm");
        if (commonPage.isAppOpened()) {
            logout();
        }
        indent = "    ";
        logDate = new Date();
        EndTimeTest = logDateFormat.format(logDate);
        log("[" + EndTimeTest + "] - END time of test.");
        indent = "";
    }

    @AfterClass
    public void stop(){
        indent = "";
        logDate = new Date();
        EndTimeClass = logDateFormat.format(logDate);
        log("--------------------------------------------------------------------------------------------------");
        log("--------------- Stop testing of class " + getClass().getSimpleName()
                + " ---------------");
        log("--------------------------------------------------------------------------------------------------");
        log("[" + EndTimeClass + "] - END time of class.");
        executor.stopBrowser();
    }

    public void importFileInCMLConsole(String importFile) {
        log("Import of file " + importFile + " to CML console");
        try {
            goToUrl("ssm/cmlConsole");
            cmlConsolePage.importFile(importFile);
            assertTrue(cmlConsolePage.isImportSuccess(), "Import of test Users is Failed!");
        } catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        } finally {

        }
    }

    public void createTestUser(String importFile) {
        log("Import of testUser.");
        try {
            login(adminUser, languageEN);
            importFileInCMLConsole(importFile);
            goToUrl("prov/permissionEditor");
            permissionEditorPage.selectUserRole(testUser.getName());
            permissionEditorPage.isTabApplicationsOpened();
            assertTrue(permissionEditorPage.isCheckboxSelectAllAppears(), "Checkbox select all has NOT appeared!");
            if(!permissionEditorPage.isCheckboxSelectAllChecked()) {
                permissionEditorPage.clickCheckboxSelectAll();
                permissionEditorPage.clickSaveButton();
            }
            assertTrue(permissionEditorPage.isCheckboxSelectAllChecked(), "Select All checkbox is NOT checked!");

            permissionEditorPage.clickTabClasses();
            permissionEditorPage.isTabClassesOpened();
            if(!permissionEditorPage.isCheckboxSelectAllCreateChecked()) {
                permissionEditorPage.clickCheckboxSelectAllCreate();
            }
            if(!permissionEditorPage.isCheckboxSelectAllEditChecked()) {
                permissionEditorPage.clickCheckboxSelectAllEdit();
            }
            if(!permissionEditorPage.isCheckboxSelectAllDeleteChecked()) {
                permissionEditorPage.clickCheckboxSelectAllDelete();
            }
            if(!permissionEditorPage.isCheckboxSelectAllViewChecked()) {
                permissionEditorPage.clickCheckboxSelectAllView();
            }
            permissionEditorPage.clickSaveButton();
            permissionEditorPage.waitForSave();

            assertTrue(permissionEditorPage.isCheckboxSelectAllCreateChecked(), "Select All checkbox is NOT checked!");
            assertTrue(permissionEditorPage.isCheckboxSelectAllEditChecked(), "Select All checkbox is NOT checked!");
            assertTrue(permissionEditorPage.isCheckboxSelectAllDeleteChecked(), "Select All checkbox is NOT checked!");
            assertTrue(permissionEditorPage.isCheckboxSelectAllDeleteChecked(), "Select All checkbox is NOT checked!");
            goToUrl("ssm");
            logout();
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
    }

    public String login(UserData user, String language) {
        return login(user.getName(), user.getPassword(), language);
    }

    public String login(String name, String password, String language) {
        log("Login with user " + name + " under Language " + language);
        try {
            if(commonPage.isAppOpened()) {
                logout();
                assertTrue(jossoLoginPage.isJossoLoginPageOpened(), "Josso Login Page was NOT opened!");
            }
            if(jossoLoginPage.isJossoLoginPageOpened()) {
                jossoLoginPage.login(name, password, language);
                assertTrue(jossoLoginPage.loginSuccess(), "Login was NOT performed successfully(appLogo element was lost)");
            }
            if(loginPage.isLoginPageOpened()){
                loginPage.login(name, password, language);
                assertTrue(loginPage.loginSuccess(), "Login was NOT performed successfully(appLogo element was lost)");
            }
        } catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        return "Login with " + name + " " + password + " " + language;
    }

    public String logout(){
        log("Logout from application");
        try {
            commonPage.clickLogout();
            assertTrue(jossoLoginPage.isJossoLoginPageOpened(), "Logout was NOT performed successfully!");
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        return "Logout from application";
    }

    public void changeAssignmentToSupplier(String supplierId) {
        log("Change assignment to Supplier " + supplierId);
        assertNotNull(supplierId, "Can't assign to null Supplier id");
        changeAssignmentPage.changeAssignmentToSupplier(supplierId);
        currentAssignmentSupplier = supplierId;
        currentAssignmentCustomer = null;
    }

    public void changeAssignmentToCustomer(String customerId) {
        log("Change assignment to Customer " + customerId);
        assertNotNull(customerId, "Can't assign to null Customer id");
        changeAssignmentPage.changeAssignmentToCustomer(customerId);
        currentAssignmentSupplier = null;
        currentAssignmentCustomer = customerId;
    }

    public void changeAssignmentToAdmin() {
        log("Change assignment to Admin");
        changeAssignmentPage.changeAssignmentToAdmin();
        currentAssignmentCustomer = null;
        currentAssignmentSupplier = null;
    }

    /**
     * Start workflow process
     * Supplier workflow processID is saved in global variable 'supplierWorkflowId'
     *
     * @param customerId
     * @param supplierId
     * @param catalogId
     * @param contractId
     * @param updateType
     * @param importFormat
     * @param UoM
     * @param classification
     * @param pathToImportFile
     * @param activationDate
     * @param approve
     * @return String catalogVersion
     */
    public String startWorkflow(String customerId, String supplierId, String catalogId, String contractId,
            String updateType, String importFormat, String UoM, String classification,
            String pathToImportFile, String activationDate, Boolean approve) {
        String workflowState;
        String catalogVersion;
        String currentAssignment=null;
        Boolean update = updateType.toLowerCase().contains("update");
        try {
            log("Start Workflow in " + updateType.toUpperCase() + " mode, for Catalog - " + catalogId
                    + ", with file - " + pathToImportFile.substring(pathToImportFile.lastIndexOf('/') + 1).trim()
                    + ", with activation date - " + activationDate);
//            commonPage.clickProcessCatalog();
            executor.goToUrl(APP_LINK +"ssm/standardWorkflow/index");
            if (workflowWizardPage.isSupplierChoosePresent()) {
                commonPage.chooseSupplier(supplierId != null ? supplierId : testSupplier);
            }
            if(workflowWizardPage.isCustomerChoosePresent()) {
                commonPage.chooseCustomer(customerId != null ? customerId : testCustomer);
            }
            if(workflowWizardPage.isCatalogChoosePresent()) {
                commonPage.chooseCatalog(catalogId != null ? catalogId : testCatalog);
            }
            if(workflowWizardPage.isContractChoosePresent()) {
                commonPage.chooseContract(contractId != null ? contractId : testContract);
            }
            if(update){
                workflowWizardPage.chooseTypeUpdate();
            }
            workflowWizardPage.clickNextOnCatalogInfo();
            workflowWizardPage.enterFormats(importFormat, UoM, classification);
            workflowWizardPage.clickNextOnFormats();
            if(importFormat.toLowerCase().contains("csv")){
                workflowWizardPage.enterUploadFilesCSV(pathToImportFile);
            }else {
                workflowWizardPage.enterUploadFile(pathToImportFile);
            }
            workflowWizardPage.enterActivationDate(activationDate);
            workflowWizardPage.clickUploadCatalog();
            if(workflowWizardPage.isCatalogLocked()){
                log("Catalog is Locked!");
                workflowState = getLastProcessWorkflowStatus(catalogId, contractId);
                if(workflowState.toLowerCase().contains("completed")){
                    startWorkflow(customerId, supplierId, catalogId, contractId, updateType, importFormat, UoM,
                            classification, pathToImportFile, activationDate, approve);
                } else if(workflowState.toLowerCase().contains("activated")) {

                    startWorkflow(customerId, supplierId, catalogId, contractId, updateType, importFormat, UoM,
                            classification, pathToImportFile, activationDate, approve);
                }  else if(workflowState.toLowerCase().contains("suspend") && approve && !update) {
                    approveWorkflowOnCustomerSide(catalogId, contractId);
                } else {
                    fail(workflowState);
                }
            }
            assertTrue(workflowWizardPage.isProcessStarted(), "Workflow was NOT started!");
            assertTrue(processDetails.isPageProcessDetailsOpened(),
                    "Supplier Workflow NOT started or Process Details page is NOT opened");
            processDetails.goToSupplierProcessPage();
            assertTrue(supplierProcessPage.isSupplierProcessPageOpened(),
                    "Supplier Workflow page was NOT opened!");
            supplierWorkflowId = supplierProcessPage.getSupplierProcessID();
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        catalogVersion = getCatalogVersion(catalogId, contractId, supplierWorkflowId);
        log("Processing Catalog version = " + catalogVersion);
        return catalogVersion;
    }

    public String getCatalogVersion(String catalog, String contract, String processID){
        log("Get catalog version");
        String catalogVersion = "";
        try {
            commonPage.clickListProcesses();
//            listProcessesPage.typeCatalogInput(catalog);
//            listProcessesPage.typeContractInput(contract);
            listProcessesPage.chooseExistedCatalog(catalog);
            listProcessesPage.chooseExistedContract(contract);
            listProcessesPage.clickSearchButton();
            for(int i = 1; i <= 30; i++){
                catalogVersion = listProcessesPage.getCatalogVersion(processID);
                if(!catalogVersion.equals("")){
                    break;
                }
                Thread.sleep(1000);
                listProcessesPage.clickSearchButton();
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        assertFalse(catalogVersion.equals(""), "Catalog Version is empty!");
        return catalogVersion;
    }

    /**
     * @param processID
     *
     * Returned map contains keys:
     * "ID","VERSION","TYPE","STATUS","CREATED_BY","CREATED_ON","FINISHED_ON"
     * "SUCCESSORS", "PREDECESSOR" can be exists
     *
     * @return Map<String,String> ProcessParams
     */
    public Map<String, String> getProcessParams(String processID) throws Exception {

        String prevSupplierAssignment = getCurrentAssignmentSupplier();
        String prevCustomerAssignment = getCurrentAssignmentCustomer();
        changeAssignmentToAdmin();

        goToUrl(processDetails.getSupplierProcessDetailsURL(processID));

        String catalogVersion = processDetails.getProcessCatalogVersion();
        if (catalogVersion.isEmpty()) {
            Thread.sleep(processDetails.SMALL_TIMEOUT * 1000);
            catalogVersion = processDetails.getProcessCatalogVersion();
            assertTrue(catalogVersion.length() > 0, "Can't get catalogVersion from process " + processID);
        }
        Map<String, String> resultMap = new HashMap<String, String>();

        resultMap.put("ID", processDetails.getProcessID());
        resultMap.put("VERSION", catalogVersion);
        resultMap.put("TYPE", processDetails.getProcessType());
        resultMap.put("STATUS", processDetails.getProcessStatus());
        resultMap.put("CREATED_BY", processDetails.getProcessCreatedBy());
        resultMap.put("CREATED_ON", processDetails.getProcessCreatedOn());
        resultMap.put("FINISHED_ON", processDetails.getProcessFinishedOn());
        String predecessor = processDetails.getProcessPredecessor();
        if (predecessor != null) {
            resultMap.put("PREDECESSOR", predecessor);
        }
        String successors = processDetails.getProcessSuccessors();
        if (successors != null) {
            resultMap.put("SUCCESSORS", successors);
        }
        if (prevCustomerAssignment != null) {
            changeAssignmentToCustomer(prevCustomerAssignment);
        }
        if (prevSupplierAssignment != null) {
            changeAssignmentToSupplier(prevSupplierAssignment);
        }
        return resultMap;
    }

    public String getLastProcessWorkflowStatus(String catalog, String contract){
        log("Get Last Process status");
        try {
            commonPage.clickListProcesses();
//            listProcessesPage.typeCatalogInput(catalog);
//            listProcessesPage.typeContractInput(contract);
            listProcessesPage.chooseExistedCatalog(catalog);
            listProcessesPage.chooseExistedContract(contract);
            listProcessesPage.clickSearchButton();
            if(listProcessesPage.isWorflowSuspended()){
                return "Workflow in the suspend state!";
            }
            if(listProcessesPage.isWorflowActivated()){
                return "Workflow activated!";
            }
            if(listProcessesPage.isLastProcessCompleted()){
                return "Last Process completed!";
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        return "Status unknown!";
    }

    public String waitForWorkflowFinished(String catalog, String contract, String catalogVersion, ProcessType processType){
        log("Wait for " + processType + " finished");
        try {
            commonPage.clickListProcesses();
//            listProcessesPage.typeCatalogInput(catalog);
//            listProcessesPage.typeContractInput(contract);
            listProcessesPage.chooseExistedCatalog(catalog);
            listProcessesPage.chooseExistedContract(contract);
            listProcessesPage.chooseProcessType(processType);
            listProcessesPage.enterCatalogVersion(catalogVersion);
            listProcessesPage.clickSearchButton();
            if(listProcessesPage.isWorflowActivated() || listProcessesPage.isWorflowSuspended() ||
                    !listProcessesPage.isStatusPresent()){
                for(int i = 1; i <= timeToWaitWholeWorkflowEnds; i++){
                    if (listProcessesPage.isLastProcessCompleted()) {
                        return "Last Process completed!";
                    }
                    if (listProcessesPage.isWorflowStopped()){
                        return "Workflow was stopped!";
                    }
                    listProcessesPage.clickSearchButton();
                    Thread.sleep(3000);
                    if(listProcessesPage.isWorflowSuspended()){
                        changeAssignmentToCustomer(testCustomer);
                        approveCustomerWorkflow(testCatalog, testContract);
                        changeAssignmentToSupplier(testSupplier);
                        commonPage.clickListProcesses();
//                        listProcessesPage.typeCatalogInput(catalog);
//                        listProcessesPage.typeContractInput(contract);
                        listProcessesPage.chooseExistedCatalog(catalog);
                        listProcessesPage.chooseExistedContract(contract);
                        listProcessesPage.chooseProcessType(processType);
                        listProcessesPage.enterCatalogVersion(catalogVersion);
                        listProcessesPage.clickSearchButton();
                    }
                }
            }
            if(listProcessesPage.isWorflowSuspended()){
                return "Workflow in the suspend state!";
            }
            if(listProcessesPage.isLastProcessCompleted()){
                return "Last Process completed!";
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        return "Status unknown!";
    }

    public String waitForSupplierWorkflowEnds(String catalog, String contract, String supplierProcessId) {
        log("Wait for SupplierWorkflow.");
        try {
            commonPage.clickListProcesses();
//            listProcessesPage.typeCatalogInput(catalog);
//            listProcessesPage.typeContractInput(contract);
            listProcessesPage.chooseExistedCatalog(catalog);
            listProcessesPage.chooseExistedContract(contract);
            listProcessesPage.clickSearchButton();
            listProcessesPage.clickProcessLink(supplierProcessId);
            assertTrue(supplierProcessPage.isSupplierProcessPageOpened(), "Supplier Process Page was NOT opened!");
            for(int i = 0; i < timeToWaitProcessEnds; i++) {
                Thread.sleep(5000);
                supplierProcessPage.clickButtonRefresh();
                if (supplierProcessPage.isWorkflowFinishedSuccessfully()) {
//                    assertTrue(supplierProcessPage.isProcessingLogLinkPresent(), "Processing Log Link is absent.");
                    assertTrue(supplierProcessPage.isWorkflowFinishedSuccessfully(), "Node finishSuccessfully is absent.");
                    return "Supplier workflow finished successfully!";
                }
                if (supplierProcessPage.isWorkflowFinishedError()) {
                    return "!!!!!Supplier workflow finished with ERRORS!!!!!";
                }
                if (supplierProcessPage.isWorkflowFinishedException()) {
                    return "!!!!!Supplier workflow finished with Exception!!!!!";
                }
                if (supplierProcessPage.isSupplierReviewStep()
                        && supplierProcessPage.getStatus().contains("SUSPENDED")) {
                    return "!Supplier workflow suspend for SupplierReview!";
                }
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        return "Supplier Workflow performs more than 2 minutes!";
    }

    public void approveWorkflowOnCustomerSide(String catalogId, String contract) {
        log("ApprovalWorkflow with Catalog "+catalogId+" on Customer Side.");
        String workflowState;
        try{
            approveCustomerWorkflow(catalogId, contract);
            commonPage.clickListProcesses();
//            listProcessesPage.typeCatalogInput(catalogId);
//            listProcessesPage.typeContractInput(contract);
            listProcessesPage.chooseExistedCatalog(catalogId);
            listProcessesPage.chooseExistedContract(contract);
            listProcessesPage.clickSearchButton();
            listProcessesPage.clickLastProcessLink();
            if(supplierProcessPage.isSupplierProcessPageOpened()) {
                supplierProcessPage.clickLinkCustomerWorkflow();
            }
            if (customerProcessPage.isCustomerProcessPageOpened()) {
                workflowState = waitForCustomerWorkflowEnds();
                if (workflowState.contains("suspend")) {
                    approveWorkflowOnCustomerSide(catalogId, contract);
                }
                if (workflowState.contains("successfully")) {
                    customerProcessPage.clickLinkCustomerActivateWorklow();
                    assertTrue(customerActivateWorklowPage.isCustomerActivateProcessPageOpened(),
                            "CustomerActivateProcess page is NOT opened!");
                    assertTrue(waitForCustomerActivateWorkflowEnds().contains("successfully"),
                            "CustomerActivateWorkflow ends NOT successfully!");
                }
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
    }

    public void approveCustomerWorkflow(String catalogId, String contract){
        String workflowState;
        log("Approve CustomerWorkflow");
        try{
            executor.goToUrl(APP_LINK +"ssm/customerProcess/listIndex");
//            listProcessesPage.typeCatalogInput(catalogId);
//            listProcessesPage.typeContractInput(contract);
            listProcessesPage.chooseExistedCatalog(catalogId);
            listProcessesPage.chooseExistedContract(contract);
            listProcessesPage.clickSearchButton();
//            if(!listProcessesPage.isWorflowSuspended()) return;
            listProcessesPage.clickLastProcessLink();
            if(supplierProcessPage.isSupplierProcessPageOpened()) {
//                assertTrue(supplierProcessPage.isSupplierProcessPageOpened(), "SupplierProcess page is NOT opened!");
                supplierProcessPage.clickLinkCustomerWorkflow();
            }
            if (customerProcessPage.isCustomerProcessPageOpened()) {
//                assertTrue(customerProcessPage.isCustomerProcessPageOpened(), "CustomerProcess page is NOT opened!");
                workflowState = waitForCustomerWorkflowEnds();
                log(workflowState);
                if (workflowState.contains("approval")) {
//                    commonPage.clickShowWorklist();
                    executor.goToUrl(APP_LINK +"ssm/customerProcess/showWorklist");
                    assertTrue(showWorklistPage.isShowWorklistPageOpened(), "ShowWorklist page is not opened!");
                    assertTrue(waitForApprovalLink(catalogId), "NO Approval link appears!");
                    showWorklistPage.clickOnApprovalLink(catalogId);
                    assertTrue(approvalPage.isApprovalPageOpened(), "Some elements on the Approval page are absent!");
                    approvalPage.clickButtonApprove();
                } /*else {
                    fail("NO Approval step appears!");
                }*/
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
    }

    public Boolean waitForApprovalLink(String catalogId){
        log("Wait for ApprovalLink.");
        try{
            for(int i = 0; i < 30; i++){
                if (showWorklistPage.isApprovalLinkPresent(catalogId)){
                    return true;
                }
                showWorklistPage.clickRefreshButton();
                Thread.sleep(1000);
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        return false;
    }

    public String waitForCustomerWorkflowEnds() {
        log("Wait for CustomerWorkflow.");
        try {
            for(int i = 1; i < timeToWaitProcessEnds; i++) {
                if (customerProcessPage.isWorkflowFinishedSuccessfully()) {
                    assertTrue(customerProcessPage.isDeltaReportLinkPresent(), "Delta Report Link is absent.");
                    return "Customer workflow finished successfully!";
                }
                if (customerProcessPage.isWorkflowFinishedError()) {
                    return "Customer workflow finished with ERRORS!";
                }
                if (customerProcessPage.isApproval1Present()
                        && customerProcessPage.getStatus().contains("SUSPENDED")) {
                    return "Customer workflow suspend for approval1.";
                }
                Thread.sleep(3000);
                customerProcessPage.clickButtonRefresh();
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        return "Customer Workflow performs more than set time!";
    }

    public String waitForCustomerActivateWorkflowEnds() {
        log("Wait for CustomerActivateWorkflow finished.");
        try {
            for(int i=1; i<timeToWaitProcessEnds; i++) {
                Thread.sleep(2000);
                customerActivateWorklowPage.clickButtonRefresh();
                if (customerActivateWorklowPage.isWorkflowFinishedSuccessfully()) {
                    return "CustomerActivate workflow finished successfully!";
                }
                if (customerActivateWorklowPage.isWorkflowFinishedError()) {
                    return "CustomerActivate workflow finished with ERRORS!";
                }
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        return "CustomerActivate Workflow performs more than set time!";
    }

    public void SupplierReviewStep(String catalog, String contract, String supplierProcessId){
        String workflowState;
        log("Perform publish of the SupplierReviewStep for "+catalog+" of Supplier process "+
                supplierProcessId+".");
        try{
            PublishSupplierReview(catalog, contract, supplierProcessId);
            commonPage.clickListProcesses();
            commonPage.chooseCatalog(catalog);
            commonPage.chooseContract(contract);
            listProcessesPage.clickSearchButton();
            listProcessesPage.clickProcessLink(supplierProcessId);
            assertTrue(supplierProcessPage.isSupplierProcessPageOpened(),"SupplierProcess page is NOT opened!");
            workflowState = waitForSupplierWorkflowEnds(catalog, contract, supplierProcessId);
            if(workflowState.contains("successfully")){
                assertTrue(supplierProcessPage.isPublishStep(), "Publish step is NOT present!");
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
    }

    public void PublishSupplierReview(String catalog, String contract, String supplierProcessId){
        log("SupplierReview step with Catalog " + catalog);
        String workflowState;
        try{
            commonPage.clickListProcesses();
            commonPage.chooseCatalog(catalog);
            commonPage.chooseContract(contract);
            listProcessesPage.clickSearchButton();
            listProcessesPage.clickProcessLink(supplierProcessId);
            assertTrue(supplierProcessPage.isSupplierProcessPageOpened(),"SupplierProcess page is NOT opened!");
            workflowState = waitForSupplierWorkflowEnds(catalog, contract, supplierProcessId);
            log(workflowState);
            if(workflowState.contains("SupplierReview")){
//                commonPage.clickShowWorklist();
                executor.goToUrl(APP_LINK+"ssm/supplierProcess/showWorklist");
                assertTrue(showWorklistPage.isShowWorklistPageOpened(), "ShowWorklist page is not opened!");
                assertTrue(waitForSupplierReviewLink(catalog),"NO SupplierReview Link appears! For catalog " + catalog);
                showWorklistPage.clickOnSupplierReviewLink(catalog);
                assertTrue(supplierReviewPage.isSupplierReviewPageOpened(),
                        "Some elements on the SupplierReview page are absent!");
                supplierReviewPage.clickButtonPublish();
            }else{
                fail("NO supplierReview step appears!");
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
    }

    public Boolean waitForSupplierReviewLink(String catalogId){
        log("Wait for SupplierReviewLink.");
        try{
            for(int i=0; i<60; i++){
                if (showWorklistPage.isSupplierReviewLinkPresent(catalogId)){
                    return true;
                }
                showWorklistPage.clickRefreshButton();
                Thread.sleep(1000);
            }
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        return false;
    }

    public void uploadCatalog(String catalogFile){
        log("Upload file to solrml folder in Workarea.");
        try {
            goToUrl("ssm");
            if(commonPage.isAppOpened()) {
                logout();
            }
            if(jossoLoginPage.isJossoLoginPageOpened()){
                login(testUser.getName(), testUser.getPassword(), languageEN);
            }
            commonPage.clickSystem();
//            systemPage.clickWorkareaLink();
            executor.goToUrl(APP_LINK +"ssm/fileManager/showWorkareaFileManager");
            workareaPage.waitForWorkareaOpened();
            if(workareaPage.isFolderLinkPresent("solrml")){
                workareaPage.clickFolderLink("solrml");
                if(workareaPage.isFolderIsOpened("solrml")) {
                    workareaPage.uploadFile(catalogFile);
                    if(workareaPage.isOverrideFilePresent()){
                        workareaPage.clickOverrideFileButton();
                    }
                }
            }
            logout();
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
    }

    public Boolean addBusinessRuleList(String listID, String type){
        log("Add BusinessRuleList " + listID);
        Boolean businessRuleListPresence = false;
        try {
            goToUrl("ssm");
            changeAssignmentToAdmin();
            commonPage.clickSetup();
            masterDataPage.clickContentBusinessRuleLink();
            if(!contentBusinessRulePage.isBusinessRuleListPresent(listID)){
                contentBusinessRulePage.clickButtonCreate();
                assertTrue(createContentBusinessRulePage.isCreateContentBusinessRulePagePageOpened(),
                        "CreateContentBusinessRulePage was NOT opened!");
                createContentBusinessRulePage.chooseBusinessRuleList(listID);
                createContentBusinessRulePage.chooseType(type);
                createContentBusinessRulePage.clickSaveButton();
                assertTrue(createContentBusinessRulePage.isContentBusinessRuleAdded(),
                        "ContentBusinessRule was NOT added.");
                createContentBusinessRulePage.clickCancelButton();
            }
            businessRuleListPresence = contentBusinessRulePage.isBusinessRuleListPresent(listID);
            log("BusinessRuleList " + listID + " was added.");
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        return businessRuleListPresence;
    }

    public Boolean deleteBusinessRuleList(String listID){
        log("Delete BusinessRuleList " + listID);
        Boolean businessRuleListPresence = false;
        try {
            goToUrl("ssm");
            changeAssignmentToAdmin();
            commonPage.clickSetup();
            masterDataPage.clickContentBusinessRuleLink();
            if(contentBusinessRulePage.isBusinessRuleListPresent(listID)){
                contentBusinessRulePage.clickButtonDeleteBusinessRuleList(listID);
            }
            businessRuleListPresence = contentBusinessRulePage.isBusinessRuleListPresent(listID);
            log("BusinessRuleList " + listID + " was deleted.");
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
        return businessRuleListPresence;
    }

    /**
     * redirect to url = APP_LINK + url
      * @param url
     */
    public void goToUrl(String url) {
        executor.goToUrl(APP_LINK + url);
    }

    public void log(String text) {
        System.out.println(indent + text);

    }

    public void reindexCatalog(String catalog){
        try {
            goToUrl("pim");
            login(testUser.getName(), testUser.getPassword(), languageEN);
            welcomePage.clickToActivateCatalogs();
            productCatalogActivatorPage.enterCatalog(catalog);
            productCatalogActivatorPage.clickButtonActivate();
            assertTrue(productCatalogActivatorPage.isActivationMessageAppears());
        }catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
    }

    public void enterValueForCustomerProfile(String uom, String classification, String format){
        log("Enter value for Customer Profile: uom - "+uom+", classification - "+classification+
                ", format of export file - "+format);
        try {
            executor.goToUrl(APP_LINK +"ssm");
            changeAssignmentToCustomer(testCustomer);
//            commonPage.clickCustomerProfile();
            executor.goToUrl(APP_LINK +"ssm/customerProfile");
            assertTrue(customerProfilePage.isCustomerProfileOpened(), "Customer Profile page is NOT opened!");
            customerProfilePage.clickContentSection();
            assertTrue(customerProfilePage.isContentSectionOpened(), "The content section is NOT opened!");
            customerProfilePage.enterExportUoM(uom);
            customerProfilePage.enterExportClassification(classification);
            customerProfilePage.enterExportFormat(format);
            customerProfilePage.clickSavebutton();
            customerProfilePage.clickContentSection();
            assertTrue(customerProfilePage.isContentSectionOpened(), "The content section is NOT opened!");

            assertTrue(customerProfilePage.getSelectedUoM().equals(uom), "UoM-"+uom+" was NOT chosen!");
            assertTrue(customerProfilePage.getSelectedClassification().equals(classification),
                    "ExportClassification-"+classification+" was NOT chosen!");
            assertTrue(format.contains(customerProfilePage.getSelectedFormat()), "ExportFormat-"+format+
                    " was NOT chosen!");
            customerProfilePage.clickSavebutton();
        } catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
    }

    public void enterValueForSupplierProfile(String uom, String classification, String format){
        log("Enter value for Supplier Profile: uom - "+uom+", classification - "+classification+
                ", format of export file - "+format);
        try {
            executor.goToUrl(APP_LINK +"ssm");
            changeAssignmentToSupplier(testSupplier);
            executor.goToUrl(APP_LINK +"ssm/supplierProfile");
            assertTrue(supplierProfile.isSupplierProfilePageOpened(), "Supplier Profile page is NOT opened!");
            supplierProfile.enterVariables(uom, classification, format);
            supplierProfile.clickSavebutton();

            assertTrue(supplierProfile.getSelectedUoM().equals(uom), "UoM-"+uom+" was NOT chosen!");
            assertTrue(supplierProfile.getSelectedClassification().equals(classification),
                    "ExportClassification-"+classification+" was NOT chosen!");
            assertTrue(format.contains(supplierProfile.getSelectedFormat()), "ExportFormat-"+format+
                    " was NOT chosen!");
            supplierProfile.clickSavebutton();
        } catch (Exception e) {
            executor.captureScreen();
            e.printStackTrace();
            fail();
        }
    }

}

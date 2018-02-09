package com.jcatalog.qa.testframework;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

/**
 * Created by Michael Makeikin
 * on 6/7/16.
 */

public class TestSSMStoryBoard extends TestBase{

    String supplierName = "Corporate Express";
    String productIDForImportTest = "Product_1";
    String productIDForUpdateTest = "Product_13";
    Map<String, String> workflowParameters = new HashMap<String, String>()
    {{
        put("customer",testCustomer);
        put("supplier","cor001");
        put("catalog","cor001");
        put("contract","cor001");
        put("importFormat", "StandardMultiSheet");
        put("uom", "ANSI");
        put("classification", "eClass_5.1.3");
        put("importFile", System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "main" + File.separator + "resources" + File.separator + "StandardMultiSheetReplace.xls");
        put("activationDate", today);
        put("importChangedFile", System.getProperty("user.dir") + File.separator + "src" + File.separator +
                "main" + File.separator + "resources" + File.separator + "StandardMultiSheetUpdateChanged.xls");
    }};

    @org.testng.annotations.Test
    public void testSSMStoryBoard(){
        log("--- Start testing of functionality - SSM StoryBoard ---");
        try {
            goToUrl("ssm");
            createTestUser(importFileWithTestUser);
            goToUrl("ssm");
            login(testUser.getName(), testUser.getPassword(), languageEN);
            enterValueForCustomerProfile(testUoM, testClassification, "excel/StandardMultiSheet");
            uploadWorkflowOnSupplierSideTest();
            approvalWorkflowOnCustomerSideTest();
            SupplierReviewUpdateImportTest();
        }catch (Exception e) {
            e.printStackTrace();
            fail();
        }finally {
            login(testUser.getName(), testUser.getPassword(), languageEN);
            assertTrue(addBusinessRuleList("deltaApprovalBusinessRuleList", "Type of Approval Rules (approval)"),
                    "DeltaApprovalBusinessRuleList was NOT added");
            logout();
//            reindexCatalog(testCatalog);
            log("--- End testing of functionality - SSM StoryBoard ---");
        }
    }

    public void uploadWorkflowOnSupplierSideTest(){
        try {
            deleteBusinessRuleList("deltaApprovalBusinessRuleList");
            executor.goToUrl(APP_LINK +"ssm/supplierDashboard/index");
            login(supplierUser.getName(), supplierUser.getPassword(), languageEN);
//            changeAssignmentToSupplier(testSupplier);
            assertTrue(supplierDashboardPage.isPageIsOpened(), "Supplier Dashboard page is NOT opened!");
            startWorkflow(workflowParameters.get("customer"), workflowParameters.get("supplier"),
                    workflowParameters.get("catalog"), workflowParameters.get("contract"), "replace",
                    workflowParameters.get("importFormat"), workflowParameters.get("uom"),
                    workflowParameters.get("classification"), workflowParameters.get("importFile"),
                    workflowParameters.get("activationDate"), false);
            log(waitForSupplierWorkflowEnds( workflowParameters.get("catalog"),
                    workflowParameters.get("contract"), supplierWorkflowId));
            assertTrue(supplierProcessPage.isWorkflowFinishedSuccessfully(),
                    "Supplier workflow was NOT finished successfully!");
            logout();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    public void approvalWorkflowOnCustomerSideTest(){
        try {
            executor.goToUrl(APP_LINK +"ssm/customerDashboard/index");
            login(customerUser.getName(), customerUser.getPassword(), languageEN);
//            changeAssignmentToCustomer(testCustomer);
            approveWorkflowOnCustomerSide(workflowParameters.get("catalog"), workflowParameters.get("contract"));
            CheckCustomerDashboard(workflowParameters.get("catalog"));
            logout();

            login(testUser.getName(), testUser.getPassword(), languageEN);
            changeAssignmentToCustomer(testCustomer);
            goToUrl("opc");
            assertTrue(CheckProductsInOPC(supplierName, workflowParameters.get("catalog"), productIDForImportTest),
                    "Product is NOT present in the OPC Search result");
            goToUrl("ssm");
            logout();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    public void SupplierReviewUpdateImportTest(){
        try {
            executor.goToUrl(APP_LINK +"ssm/supplierDashboard/index");
            login(supplierUser.getName(), supplierUser.getPassword(), languageEN);
//            changeAssignmentToSupplier(testSupplier);
            assertTrue(supplierDashboardPage.isPageIsOpened(), "Supplier Dashboard page is NOT opened!");
            startWorkflow(workflowParameters.get("customer"), workflowParameters.get("supplier"),
                    workflowParameters.get("catalog"), workflowParameters.get("contract"), "update",
                    workflowParameters.get("importFormat"), workflowParameters.get("uom"),
                    workflowParameters.get("classification"), workflowParameters.get("importChangedFile"),
                    workflowParameters.get("activationDate"), false);
            SupplierReviewStep(workflowParameters.get("catalog"), workflowParameters.get("contract"),
                    supplierWorkflowId);
            logout();

            login(customerUser.getName(), customerUser.getPassword(), languageEN);
//            changeAssignmentToCustomer(testCustomer);
            approveWorkflowOnCustomerSide(workflowParameters.get("catalog"), workflowParameters.get("contract"));
            CheckCustomerDashboard(workflowParameters.get("catalog"));
            logout();

            login(testUser.getName(), testUser.getPassword(), languageEN);
            changeAssignmentToCustomer(testCustomer);
            goToUrl("opc");
            assertTrue(CheckProductsInOPC(supplierName, workflowParameters.get("catalog"), productIDForUpdateTest),
                    "Product is NOT present in the OPC Search result");
            goToUrl("ssm");
            logout();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    public Boolean CheckCustomerDashboard(String catalogId){
        log("Check CustomerDashboard page.");
        try{
            commonPage.clickCustomerDashboard();
            assertTrue(customerDashboardPage.isPageIsOpened(), "CustomerDashboard page was NOT opened!");
            return customerDashboardPage.isCatalogWorkflowTrendLineContainsCatalogData(catalogId);
        }catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        return false;
    }

    public Boolean CheckProductsInOPC(String supplierName, String catalogId, String productId){
        log("Check that Products uploaded to the OPC");
        Boolean isProductPresent;
        try {
            Thread.sleep(10000);
            searchPage.typeInSearchField("*");
            searchPage.clickSearchButton();
            if(searchPage.isFilterResultsLinkPresent()){
                searchPage.clickFilterResult();
            }
            if(searchPage.isSupplierMoreLinkPresent()) {
                searchPage.clickSupplierMoreLink();
            }
            assertTrue(searchPage.isSupplierPresentInList(supplierName), "Supplier is NOT present in the Supplier List");
            searchPage.clickSupplierLinkInSuppliersList(supplierName);
            searchPage.typeInSearchField(productId);
            searchPage.clickSearchButton();
            if(searchPage.isFilterResultsLinkPresent()){
                searchPage.clickFilterResult();
            }
            if(searchPage.isSupplierMoreLinkPresent()) {
                searchPage.clickSupplierMoreLink();
            }
            assertTrue(searchPage.isSupplierPresentInList(supplierName), "Supplier is NOT present in the Supplier List");
        }catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        isProductPresent = searchPage.isProductPresent(catalogId, productId);
        log("Is Product present = " + isProductPresent);
        return isProductPresent;
    }


}

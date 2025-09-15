package com.company.tests;

import com.company.framework.base.BaseClass;
import com.company.framework.driver.DriverManager;
import com.company.framework.pages.CpHomePage;
import com.company.framework.pages.MensApparelPage;
import com.company.framework.utils.Log;
import com.company.framework.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Cp1 extends BaseClass {
    private WebDriver driver;
    private CpHomePage cpHomePage;
    private MensApparelPage mensApparelPage;
    private UtilityMethods utilityMethods;
    List<String> productData = new ArrayList<>();

    @BeforeMethod
    public void initializePages(){
        driver = DriverManager.getDriver();
        cpHomePage = new CpHomePage(driver);
        mensApparelPage = new MensApparelPage(driver);
        utilityMethods = new UtilityMethods(driver);
    }

    @Test
    public void findAllJackets(){
        System.out.println("is driver null ar cp1"+ driver==null);
        cpHomePage.handlePopup();
        cpHomePage.goToShopSubMenuOption();
        String parentWindow = utilityMethods.switchToChildWindow();
        mensApparelPage.selectMenMenuOption();
        mensApparelPage.selectDepartment("Jackets");
        List<WebElement> pageLinks =  mensApparelPage.getPageLinks();

       while(true){
           Log.info("Getting Product Data...");
           int productCount = mensApparelPage.getProductDataList().size();
           List<WebElement> productNameList = mensApparelPage.getProductName();
           List<WebElement> productPriceList = mensApparelPage.getProductPrice();
           Log.info("Product Count: " + productCount);
           Log.info("Product Name List Size: " + productNameList.size());
           Log.info("Product Price List Size: " + productPriceList.size());

           for(int i=0;i<productCount;i++){
               String price = productPriceList.get(i).getText();
               String productName = productNameList.get(i).getText();
               productData.add(productName + " || " + price );
           }
           try {
               if (mensApparelPage.getNextPageButton().isDisplayed()
                       && mensApparelPage.getNextPageButton().isEnabled()) {
                   mensApparelPage.getNextPageButton().click();
                   utilityMethods.waitForElementVisible(mensApparelPage.getSearchBox());
               } else {
                   break;
               }
           } catch (Exception e) {
               break;
           }

       }

        utilityMethods.createFileAndWriteData("target/jackets.txt", productData);
        utilityMethods.addFileToReport();



    }
}

package com.company.tests;

import com.company.framework.driver.DriverManager;
import com.company.framework.pages.HomePage;
import com.company.framework.pages.MensApparelPage;
import com.company.framework.utils.UtilityMethods;
import com.company.framework.utils.WaitUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cp1 {
    private WebDriver driver ;
    private HomePage homePage;
    private MensApparelPage mensApparelPage;
    List<String> productData = new ArrayList<>();

    public Cp1(){
        driver = DriverManager.createDriver("chrome");
        homePage = new HomePage(driver);
        mensApparelPage = new MensApparelPage(driver);
    }

    @BeforeTest
    public void initialize(){
        driver.get("https://www.nba.com/warriors/");
        homePage.closePopup();


    }

    @Test
    public void findAllJackets(){

        homePage.goToShopSubMenuOption();
        homePage.goToShopSubMenuOption();
        String parentWindow = UtilityMethods.switchToChildWindow(driver);
        mensApparelPage.selectDepartment("Jackets");
       List<WebElement> pageLinks =  mensApparelPage.getPageLinks();


       while(true){
           int productCount = mensApparelPage.getProductDataList().size();
           List<WebElement> productNameList = mensApparelPage.getProductName();
           List<WebElement> productPriceList = mensApparelPage.getProductPrice();

           for(int i=0;i<productCount;i++){
               String price = productPriceList.get(i).getText();
               String productName = productNameList.get(i).getText();
               productData.add(productName + " || " + price );
           }
           try {
               if (mensApparelPage.getNextPageButton().isDisplayed()
                       && mensApparelPage.getNextPageButton().isEnabled()) {
                   mensApparelPage.getNextPageButton().click();
                   WaitUtils.waitForVisible(mensApparelPage.getSearchBox());  // custom explicit wait
               } else {
                   break;
               }
           } catch (Exception e) {
               break; // No next page
           }

       }
       File file = new File("target/jackets.txt");
        try {
            FileUtils.writeLines(file ,productData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

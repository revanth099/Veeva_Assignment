package com.company.tests;

import com.aventstack.extentreports.ExtentTest;
import com.company.framework.base.BaseClass;
import com.company.framework.driver.DriverManager;
import com.company.framework.pages.CpHomePage;
import com.company.framework.pages.MensApparelPage;
import com.company.framework.reporting.ExtentReportManager;
import com.company.framework.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Cp1Test extends BaseClass {
    private WebDriver driver;
    private CpHomePage cpHomePage;
    private MensApparelPage mensApparelPage;
    private UtilityMethods utilityMethods;

    @BeforeMethod
    public void initializePages(){
        driver = DriverManager.getDriver();
        cpHomePage = new CpHomePage(driver);
        mensApparelPage = new MensApparelPage(driver);
        utilityMethods = new UtilityMethods(driver);
    }

    @Test
    public void getAllJacketsPricesWithTitles(){
        try{
            System.out.println("is driver null ar cp1"+ driver==null);
            cpHomePage.handlePopup();
            cpHomePage.goToShopSubMenuOption();
            String parentWindow = utilityMethods.switchToChildWindow();
            mensApparelPage.selectMenMenuOption();
            mensApparelPage.selectDepartment("Jackets");
            List<String> productData = mensApparelPage.getAllProductsPricesWithTitles();
            utilityMethods.createFileAndWriteData("target/jackets.txt", productData);
            utilityMethods.addFileToReport(ExtentReportManager.getTest());
        }catch (Exception e){
            throw new RuntimeException(e);

        }
    }
}

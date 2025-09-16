package com.company.tests;

import com.company.framework.base.BaseClass;
import com.company.framework.driver.DriverManager;
import com.company.framework.pages.CpHomePage;
import com.company.framework.pages.Dp2HomePage;
import com.company.framework.pages.MensApparelPage;
import com.company.framework.utils.Log;
import com.company.framework.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Dp2Test extends BaseClass {

        private WebDriver driver;
        private Dp2HomePage dp2HomePage;
        private UtilityMethods utilityMethods;

        @BeforeMethod
        public void initializePages(){
            driver = DriverManager.getDriver();
            dp2HomePage = new Dp2HomePage(driver);
            utilityMethods = new UtilityMethods(driver);
        }

        @Test
        public void getAllFooterLinks(){

            List<String> links = dp2HomePage.getAllFooterLinks();
            utilityMethods.writeLinksToCsv(links, "target/links.csv");



        }



}

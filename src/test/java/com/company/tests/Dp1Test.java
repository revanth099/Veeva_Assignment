package com.company.tests;

import com.company.framework.base.BaseClass;
import com.company.framework.driver.DriverManager;
import com.company.framework.pages.CpHomePage;
import com.company.framework.pages.Dp1HomePage;
import com.company.framework.pages.MensApparelPage;
import com.company.framework.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Dp1Test extends BaseClass {
    private WebDriver driver;
    private Dp1HomePage dp1HomePage;
    private UtilityMethods utilityMethods;
    SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void initializePages(){
        driver = DriverManager.getDriver();
        dp1HomePage = new Dp1HomePage(driver);
        utilityMethods = new UtilityMethods(driver);
    }

    @Test
    public void getContentVideoDurations(){
        dp1HomePage.wiatForElementToLoad();
        dp1HomePage.checkIfPopupIsPresent();
        int slideCount = dp1HomePage.getSlideCount();
        System.out.println("Slide Count: " + slideCount);
        List<String> slideTitles = dp1HomePage.getSlideTitle();
        softAssert.assertEquals(slideCount, slideTitles.size(), "Slide count and slide titles count do not match");

        List<Long> videoDurations = dp1HomePage.playContentVideos();
        softAssert.assertEquals(videoDurations.size(), slideCount, "Video durations count does not match slide count");
        softAssert.assertAll();
    }
}

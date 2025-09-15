package com.company.framework.base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.company.framework.driver.DriverManager;
import com.company.framework.reporting.ExtentReportManager;
import com.company.framework.utils.ConfigManager;
import com.company.framework.utils.Log;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;


public class BaseClass {
    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setupReport() {
        extent = ExtentReportManager.getReportInstance();

    }
    @AfterSuite
    public void teardownReport() {
        extent.flush();

    }

    @BeforeMethod
    @Parameters({"browser","url"})
    public void setUp(String browser,String url, Method method) {
        ConfigManager.loadConfig("configuration/config.properties");
        System.out.println("URL is: "+ConfigManager.get(url));

        test = ExtentReportManager.createTest(method.getName());
        Log.info("Starting WebDriver...");

        driver = DriverManager.setDriver(browser);;
        if (driver == null) {
            throw new IllegalStateException("WebDriver is NULL! Browser not initialized.");
        }
        driver.get(ConfigManager.get(url));
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(ConfigManager.getInt("pageLoadTimeout"), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(ConfigManager.getInt("implicitWait"), TimeUnit.SECONDS);

    }


    @AfterMethod
    public void tearDown(ITestResult result) {

        if(result.getStatus() == ITestResult.FAILURE) {

            String screenshotPath = ExtentReportManager.captureScreenshot(DriverManager.getDriver(), result.getName());
            test.fail("Test Failed.. Check Screenshot",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }


        if (driver != null) {
            Log.info("Closing Browser...");
            driver.quit();
            DriverManager.quitDriver();

        }
    }


}

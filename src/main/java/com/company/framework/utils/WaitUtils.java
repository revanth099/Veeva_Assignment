package com.company.framework.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {
    private WebDriver driver;
    private static WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        this.driver = driver;
        int timeout = Integer.parseInt(System.getProperty("explicitWait", "20"));
        this.wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(timeout));
    }

    public static WebElement waitForVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitForPageLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
}

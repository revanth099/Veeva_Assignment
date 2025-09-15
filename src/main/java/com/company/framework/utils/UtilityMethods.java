package com.company.framework.utils;

import com.company.framework.reporting.ExtentReportManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class UtilityMethods {
    private  WebDriverWait wait;
    private WebDriver driver;

    public UtilityMethods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(20));
    }


    public  Actions moveToElement(WebElement targetElement){
        Actions actions = new Actions(this.driver);
        return actions.moveToElement(targetElement);
    }



    public  String switchToChildWindow() {
        String parent = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                break;
            }
        }
        return parent;
    }


    public  void switchToParentWindow(WebDriver driver, String parentWindowId) {
        driver.switchTo().window(parentWindowId);
    }

    public  void closeAllChildWindows(WebDriver driver) {
        String parent = driver.getWindowHandle();
        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                driver.close();
            }
        }
        driver.switchTo().window(parent);
    }

    public  void writeListToTextFile(List<String> data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void WaitUtils( int timeoutSeconds) {
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(timeoutSeconds));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
       return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForPageLoad() {
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete")
        );
    }



    public void invisibilityOfElement(WebElement element) {
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    public WebElement waitForElementVisible(WebElement element) {
        Log.info("Waiting for element to be visible: " + element);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public boolean isElementVisible(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void handleStaleness(WebElement element) {
        wait.until(ExpectedConditions.stalenessOf(element));
        int maxAttempts = 3;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
                attempt++;
                if (attempt >= maxAttempts) {
                    throw e;
                }
            }
        }
    }

    public void createFileAndWriteData(String filePath, List<String> data){
        File file = new File(filePath);
        try {
            FileUtils.writeLines(file ,data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFileToReport() {
        String filePath = Paths.get(System.getProperty("user.dir"), "target/jackets.txt").toString();
        new ExtentReportManager().getTest().info("PriceList of jackets are present in the file: "
                + "<a href='file:///" +filePath + "' target='_blank'>Copy the link and paste in browser to open the file</a>");
    }

    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }


}

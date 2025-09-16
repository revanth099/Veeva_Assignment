package com.company.framework.utils;

import com.aventstack.extentreports.ExtentTest;
import com.company.framework.reporting.ExtentReportManager;
import com.opencsv.CSVWriter;
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
import java.util.*;

public class UtilityMethods {
    private  WebDriverWait wait;
    private WebDriver driver;

    public UtilityMethods(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(ConfigManager.getInt("explicitWait")));
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

    public void waitForAttributeValue(WebElement element, String attribute, String booleanValue){
        wait.until(ExpectedConditions.attributeToBe(element, attribute, booleanValue));

    }
    public void waitForAttributeValue(WebElement element, String attribute, String booleanValue, int timeoutSeconds){
        wait.withTimeout(Duration.ofSeconds(timeoutSeconds));
        wait.until(ExpectedConditions.attributeToBe(element, attribute, booleanValue));
        wait.withTimeout(Duration.ofSeconds(ConfigManager.getInt("explicitWait")));

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

    public WebElement handleStaleness(WebElement element) {
        WebElement staleElement=null;
        wait.until(ExpectedConditions.stalenessOf(element));
        int maxAttempts = 3;
        int attempt = 0;
        while (attempt < maxAttempts) {
            try {
               if(element.isDisplayed()){
                    staleElement = element;
                   break;
               }

            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element detected. Retrying... " + (attempt + 1));
                attempt++;
                if (attempt >= maxAttempts) {
                    throw e;
                }
            }
        }
        return Objects.requireNonNull(staleElement);
    }

    public void createFileAndWriteData(String filePath, List<String> data){
        File file = new File(filePath);
        try {
            FileUtils.writeLines(file ,data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFileToReport(ExtentTest extentTest) {
        String filePath = Paths.get(System.getProperty("user.dir"), "target/jackets.txt").toString();
        extentTest.info("PriceList of jackets are present in the file: "
                + "<a href='file:///" +filePath + "' target='_blank'>Copy the link and paste in browser to open the file</a>");
    }

    public void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public void writeLinksToCsv(List<String> links, String filePath) {
        // Count occurrences of each link
        Map<String, Integer> linkCount = new HashMap<>();
        for (String link : links) {
            linkCount.put(link, linkCount.getOrDefault(link, 0) + 1);
        }

        // Write to CSV
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            // Header row
            writer.writeNext(new String[]{"Hyperlink", "Duplicate?"});

            // Data rows
            for (String link : links) {
                String isDuplicate = (linkCount.get(link) > 1) ? "Yes" : "No";
                writer.writeNext(new String[]{link, isDuplicate});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Report summary in console
        boolean hasDuplicates = linkCount.values().stream().anyMatch(count -> count > 1);
        if (hasDuplicates) {
            System.out.println("Duplicate hyperlinks found:");
            linkCount.forEach((k, v) -> {
                if (v > 1) System.out.println(k + " → appears " + v + " times");
            });
        } else {
            System.out.println("No duplicate hyperlinks found.");
        }
    }





}

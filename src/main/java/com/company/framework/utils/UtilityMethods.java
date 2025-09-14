package com.company.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class UtilityMethods {

    public static Actions moveToElement(WebElement targetElement,WebDriver driver){
        Actions actions = new Actions(driver);
        return actions.moveToElement(targetElement);
    }



    public static String switchToChildWindow(WebDriver driver) {
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


    public static void switchToParentWindow(WebDriver driver, String parentWindowId) {
        driver.switchTo().window(parentWindowId);
    }

    public static void closeAllChildWindows(WebDriver driver) {
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

    public static void writeListToTextFile(List<String> data, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

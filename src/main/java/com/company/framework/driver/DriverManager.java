package com.company.framework.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;


public class DriverManager {

        private static final ThreadLocal<WebDriver> threadLocDriver = new ThreadLocal<>();

        public static WebDriver getDriver() {
            return threadLocDriver.get();
        }

        public static WebDriver createDriver(String browser) {

            if(threadLocDriver.get()==null){
                switch (browser.toLowerCase()) {
                    case "chrome":
                        threadLocDriver.set(WebDriverManager.chromedriver().create());
                        break;
                    case "firefox":
                        threadLocDriver.set(WebDriverManager.firefoxdriver().create());
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported browser: " + browser);
                }
            }

            return getDriver();
        }

        public static void quitDriver() {
            WebDriver d = threadLocDriver.get();
            if (d != null) {
                d.quit();

            }
        }

}

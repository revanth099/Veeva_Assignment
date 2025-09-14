package com.company.framework.base;

import com.company.framework.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract  class BasePage {

    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this); // called only here
    }
}


package com.company.framework.pages;

import com.company.framework.base.BasePage;
import com.company.framework.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class Dp2HomePage extends BasePage {
        UtilityMethods utilityMethods;

        @FindBy(xpath = "//footer//div[contains(@class, 'flex flex-col justify-between')]//a")
        private List<WebElement> footerLinks;

        public Dp2HomePage(WebDriver driver) {
            super(driver);
            utilityMethods = new UtilityMethods(driver);
        }



        public List<String> getAllFooterLinks() {
            List<String> links = new ArrayList<>();
            footerLinks.stream().map(element -> element.getAttribute("href")).filter(href -> href != null && !href.isEmpty()).forEach(links::add);
            return links;
        }


}

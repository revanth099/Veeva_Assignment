package com.company.framework.pages;

import com.company.framework.base.BasePage;
import com.company.framework.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dp1HomePage extends BasePage {
    UtilityMethods utilityMethods;

    @FindBy(xpath = "//*[@role='dialog']")
    private WebElement popup;

    @FindBy(xpath = "//*[@role='dialog']/descendant::div//div[contains(@class,'hover:cursor-pointer')]")
    private WebElement closePopup;
    @FindBy(xpath = "//li[contains(@data-testid,'shop.warriors.com')]/child::a")
    private WebElement shopButton;

    @FindBy(xpath = "//nav[@id='nav-dropdown-desktop-1059653']/descendant::li//a[@title='Men''s']")
    private WebElement shopSubmenu;

    public Dp1HomePage(WebDriver driver){
        super(driver);
        utilityMethods = new UtilityMethods(driver);
    }
}

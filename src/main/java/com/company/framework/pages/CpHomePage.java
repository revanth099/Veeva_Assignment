package com.company.framework.pages;

import com.company.framework.base.BasePage;
import com.company.framework.utils.UtilityMethods;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CpHomePage extends BasePage {

    UtilityMethods utilityMethods;

    @FindBy(xpath = "//*[@role='dialog']")
    private WebElement popup;

    @FindBy(xpath = "//*[@role='dialog']/descendant::div//div[contains(@class,'hover:cursor-pointer')]")
    private WebElement closePopup;
    @FindBy(xpath = "//li[contains(@data-testid,'shop.warriors.com')]/child::a")
    private WebElement shopButton;

    @FindBy(xpath = "//nav[@id='nav-dropdown-desktop-1059653']/descendant::li//a[@title='Men''s']")
    private WebElement shopSubmenu;

    public CpHomePage(WebDriver driver){
        super(driver);
        utilityMethods = new UtilityMethods(driver);
    }
    public void goToShopSubMenuOption(){
        utilityMethods.waitForElementToBeClickable(shopButton);
        System.out.println("is SHOP button available"+ shopButton.isDisplayed());
        shopButton.click();
       /* Log.info("Moving to Shop Button");
        Actions mainMenu = utilityMethods.moveToElement(shopButton);
        Actions subMenu = mainMenu.moveToElement(shopSubmenu);
        subMenu.click().build().perform();*/



    }
    public void handlePopup() {
        try{
            WebElement closeBtn = utilityMethods.waitForElementToBeClickable(closePopup);
            closeBtn.click();
            utilityMethods.invisibilityOfElement(popup);
        } catch (TimeoutException e) {
            System.out.println("No popup appeared.");
        }
    }


}

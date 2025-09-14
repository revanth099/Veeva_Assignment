package com.company.framework.pages;

import com.company.framework.ShopSubMenuOptions;
import com.company.framework.base.BasePage;
import com.company.framework.utils.UtilityMethods;
import com.company.framework.utils.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.company.framework.utils.UtilityMethods.*;

public class HomePage  extends BasePage {
    private final WebDriver driver;
    String subMenuOption;
    private UtilityMethods utilityMethods;

    @FindBy(xpath = "//*[@role='dialog']/descendant::div//div[contains(@class,'hover:cursor-pointer')]")
    private WebElement closePopup;
    @FindBy(xpath = "//li[contains(@data-testid,'shop.warriors.com')]/child::a//span[text()='Shop']")
    private WebElement ShopButton;

    @FindBy(xpath = "//nav[@id='nav-dropdown-desktop-1059653']/descendant::li//a[@title='Men''s']")
    private WebElement shopSubmenu;

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    public void goToShopSubMenuOption(){
        moveToElement(ShopButton,driver).build().perform();
        WaitUtils.waitForVisible(shopSubmenu).click();

    }
    public void closePopup(){
        WaitUtils.waitForVisible(closePopup).click();
    }


}

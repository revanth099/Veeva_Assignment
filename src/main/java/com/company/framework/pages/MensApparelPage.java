package com.company.framework.pages;

import com.company.framework.base.BasePage;
import com.company.framework.utils.Log;
import com.company.framework.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class MensApparelPage extends BasePage {
    String subMenuOption;
    private UtilityMethods utilityMethods;
    //div[@data-trk-id='all-departments-boxes']/descendant::ul/li//a/span[text()='Accessories']

    @FindBy(xpath = "//a[@data-trk-id='topnav-group-ga-1_men']")
    private WebElement menOption;
    @FindBy(xpath = "//div[@data-trk-id='all-departments-boxes']/descendant::ul/li/a")
    private List<WebElement> departmentElements;

    @FindBy(xpath = "//input[@id='typeahead-input-desktop']")
    private WebElement searchBox;



    @FindBy(xpath = "//div[@class='product-grid-bottom-area']//ul[@class='pagination-list-container']/li/a")
    private List<WebElement> paginationGrid;

    @FindBy(css = ".pagination-list-container li.next-page a")
    private WebElement nextPageButton;

    @FindBy(css = ".pagination-list-container li.show-for-large a")
    private List<WebElement> pageLinks;

    @FindBy(xpath = "//div[@class='layout-row product-grid']/descendant::div//div[@class='product-card row']/div[2]")
    private List<WebElement> productDataList;

    @FindBy(xpath = "//div[@class='layout-row product-grid']/descendant::div//div[@class='prices']/div[1]/descendant::span//span[@class='money-value'][1]")
    private List<WebElement> productPrice;

    @FindBy(xpath = "//div[@class='layout-row product-grid']/descendant::div//div[@class='product-card row']/div[2]/div[@class='product-card-title']/a")
    private List<WebElement> productName;

    public MensApparelPage(WebDriver driver){
        super(driver);
        utilityMethods = new UtilityMethods(driver);
    }

    public void selectMenMenuOption(){
        utilityMethods.handleStaleness(menOption);
        utilityMethods.waitForElementToBeClickable(menOption);
    }

    public void selectDepartment(String target){
        Log.info("Selecting Department: " + target);
        for(WebElement element: departmentElements){
            if(element.getAttribute("href").contains("men-jackets")){
                Log.info("Found Department: " + target);
                utilityMethods.waitForElementVisible(element).click();
                break;
            }
        }

    }

    public WebElement getSearchBox() {
        return searchBox;
    }

    public List<WebElement> getPaginationGrid() {
        return paginationGrid;
    }

    public List<WebElement> getProductDataList() {
        return productDataList;
    }

    public List<WebElement> getProductPrice() {
        return productPrice;
    }

    public List<WebElement> getProductName() {
        return productName;
    }

    public WebElement getNextPageButton() { return nextPageButton; }
    public List<WebElement> getPageLinks() { return pageLinks; }

   public  List<String> getAllProductPrices(){
        List<String> productPricesList = new ArrayList<>();
        for(WebElement element: productPrice){
            productPricesList.add(element.getText());

        }
        return productPricesList;
   }
    public  List<String> getAllProductTitles(){
        List<String> productTitleList = new ArrayList<>();
        for(WebElement element: productName){
            productTitleList.add(element.getText());

        }
        return productTitleList;
    }

    public WebElement getMenOption() {
        return menOption;
    }
}

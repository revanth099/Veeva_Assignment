package com.company.framework.pages;

import com.company.framework.base.BasePage;
import com.company.framework.utils.UtilityMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class Dp1HomePage extends BasePage {
    UtilityMethods utilityMethods;

    @FindBy (xpath = "//div[@id='onetrust-close-btn-container']/button")
    private WebElement closePopup;

    @FindBy(xpath = "//div[@id='onetrust-banner-sdk']")
    private WebElement popup;

    @FindBy(xpath = "//div[contains(@class,'TileHero_tileHeroContent')]/descendant::h1")
    private List<WebElement> slideData;

    @FindBy(xpath = "//div[@data-testid='tile-hero-stories']")
    private WebElement heroCard;

    @FindBy(xpath = "//div[contains(@class,'TileHero_tileHeroContent')]/child::div//button")
    private List<WebElement> contentVideos;


    public Dp1HomePage(WebDriver driver){
        super(driver);
        utilityMethods = new UtilityMethods(driver);
    }

    public int getSlideCount(){
        return slideData.size();
    }

    public List<String> getSlideTitle(){
        List<String> slideTitles = new ArrayList<>();
        List<WebElement> notConsideredSlides = new ArrayList<>();

        utilityMethods.scrollToElement(slideData.get(0));
        for(WebElement element: slideData){
            utilityMethods.waitForAttributeValue(element, "aria-hidden", "true");
            String text = element.getText();
            if(!text.isBlank()){

                System.out.println(text);
                slideTitles.add(text);
            }else{
                notConsideredSlides.add(element);
            }

        }
        if(notConsideredSlides.size()>0){
            for(WebElement element: notConsideredSlides){
                utilityMethods.waitForAttributeValue(element, "aria-hidden", "true",40);
                String text = element.getText();
                if(!text.isBlank()){
                    slideTitles.add(text);
                }
            }
        }
        return slideTitles;
    }
    public void wiatForElementToLoad(){
        utilityMethods.waitForElementVisible(heroCard);
    }
    public void checkIfPopupIsPresent(){

        if(utilityMethods.isElementVisible(popup)){
            utilityMethods.waitForElementToBeClickable(closePopup).click();
        }

    }

    public List<Long> playContentVideos(){
        List<Long> videoDurations = new ArrayList<>();
        utilityMethods.scrollToElement(slideData.get(0));
        for(WebElement element: contentVideos){
            utilityMethods.waitForAttributeValue(element, "aria-selected", "true");
            //if(Boolean.getBoolean(element.getAttribute("aria-label"))){
                long startTime = System.currentTimeMillis();
                //utilityMethods.waitForSlideToLoad(element);
            utilityMethods.waitForAttributeValue(element, "aria-selected", "false");
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                System.out.println("Video played for: " + duration + "ms");
                videoDurations.add(duration);
            //}
        }
        return videoDurations;
    }

    
}

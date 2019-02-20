package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import static org.openqa.selenium.By.*;

public class WelcomePageObject extends MainPageObject{


    public  static  final  String
    STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:New ways to explore",
    STEP_ADD_OR_EDIT_PREFERED_LAN_LINK = "id:Add or edit preferred languages",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "id:Learn more about data collected",
    NEXT_LINK = "id:Next",
    GET_STARTED_BUTTON = "id:Get started";



    public WelcomePageObject(AppiumDriver driver){

        super(driver);

    }

    public void waitForLearnMoreLink(){
        //this.waitForElementPresent(By.xpath("//XCUIElementTypeButton[@name='Learn more about Wikipedia']"), "Cannot find 'Learn more about Wikipedia' link", 10);
        this.waitForElementPresent(STEP_LEARN_MORE_LINK, "Cannot find 'Learn more about Wikipedia' link", 10);
    }

    public void clickNextButton(){
        this.waitForElementAndClick(NEXT_LINK, "Cannot find and click 'Next' link", 10);
    }

    public void waitForNewWayToExplorereText(){
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT, "Cannot find 'New ways to explore' link", 10);
    }

    public void waitForAddOrEditPreferLangText(){
        this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERED_LAN_LINK, "Cannot find 'Add or edit prefer languages'", 10);
    }

    public void waitForLearnMoreAvoutDataCollectedText(){
        this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK, "Cannot find 'Lear more about data collected'", 10);
    }

    public void clickGetStartedButtonButton(){
        this.waitForElementAndClick(GET_STARTED_BUTTON, "Cannot find and click 'Get Started' link", 10);
    }

}

package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends  MainPageObject{

    private  static final String
                MY_LIST_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

    public NavigationUI(AppiumDriver driver){
        super(driver);

    }

    public  void  clickMyList(){

        this.waitForElementAndClick(
                MY_LIST_LINK,
                "Cannot find navigation button to My List ",
                5
        );
    }
}

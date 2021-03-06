package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLE ="id:org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
        OPTIONS_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "xpath://*[@text='OK']",
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
        MY_LIST_FOLDER_TPL = "xpath://*[@text='{SUBSTRING}']"
    ;

    public  ArticlePageObject(AppiumDriver driver){

       super(driver);
    }


    /* TEMPLATE METHODS */
    private  static  String getReadingListFolderElement(String substring){
        return  MY_LIST_FOLDER_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */


    public  void clickByReadingListFolderWithSubstring(String substring){

            this.waitForElementAndClick(substring,"", 10);
    }



    public WebElement waitForTitleElement(){

        return this.waitForElementPresent(TITLE, "cannot find article title on page", 15);
}

    public WebElement waitForTitleElement(int timeout){

        return this.waitForElementPresent(TITLE, "cannot find article title on page", timeout);
    }


public String getArticleTitle(){

        WebElement title_element  = waitForTitleElement();
        return  title_element.getAttribute("text");

}

public void swipeToFooter(){

        this.swipeUpToFindElement(
                (FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
}

public void addArticleToAlreadyExistFolderList(String  name_of_folder){

        this.waitForElementAndClick(
                (OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                (OPTIONS_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );

        String folder_element_xpath = getReadingListFolderElement(name_of_folder);
        this.clickByReadingListFolderWithSubstring(folder_element_xpath);
    }

    public void addArticleToMyList(String  name_of_folder){

        this.waitForElementAndClick(
                (OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                (OPTIONS_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );

            this.waitForElementAndClick(
                    (ADD_TO_MY_LIST_OVERLAY),
                    "Cannot find 'Got it' tip overlay",
                    5
            );

            this.waitForElementAndClear(
                    (MY_LIST_NAME_INPUT),
                    "Cannot find input to set name of article folder",
                    5
            );

            this.waitForElementAndSendKeys(
                    (MY_LIST_NAME_INPUT),
                    name_of_folder,
                    "Cannot put text into articles folder input",
                    5
            );

            this.waitForElementAndClick(
                    (MY_LIST_OK_BUTTON),
                    "Cannot press 'OK' button",
                    5
            );



    }


public  void closeArticle(){
        this.waitForElementAndClick(
                (CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link ",
                5
        );

    }



}

package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPageObject extends  MainPageObject {

private static final String
    SEARCH_INIT_ELEMENT = "xpath://*[contains(@text,'Search Wikipedia')]",
    SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]",
    SEARCH_CANCEL_BUTTON = "id:org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
    SEARCH_RESULT_BY_TITLE_AND_DESC_TPL = "xpath://*[@resource-id='org.wikipedia:id/page_list_item_container'][//*[@text='{TITLE}'] and //*[@text='{DESCRIPTION}']]",
    SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
    SEARCH_EMPTY_RESULT_ELEMENT = "xpath://*[@text='No results found']",
    SEARCH_RESULT_ELEMENT_TITILE = "id:org.wikipedia:id/page_list_item_title"
    ;



public SearchPageObject(AppiumDriver driver){
    super(driver);
}

/* TEMPLATE METHODS */
private  static  String getResultSearchElement(String substring){
    return  SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
}

    private  static  String getResultSearchElementByTitleAndDescription(String title, String description){
        return  SEARCH_RESULT_BY_TITLE_AND_DESC_TPL.replace("{TITLE}", title).replace( "{DESCRIPTION}",description);
    }
/* TEMPLATE METHODS */

    public  void waitForElementByTitleAndDescription(String title, String description){
        String Search_result_xpath =  getResultSearchElementByTitleAndDescription(title, description);

        this.waitForElementPresent(Search_result_xpath, "Cannot find search result with title and description");
    }



public  void initSearchInput(){
    this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init element", 5);
    this.waitForElementPresent(SEARCH_INPUT, "Cannot find search input after clcking search init element");
}



    public String getPlaceholderTextIntoSearchElement(){

        WebElement searchInput = waitForElementPresent(SEARCH_INPUT, "Cannot find search input");
        String placeholder_text = searchInput.getAttribute("text");
    return  placeholder_text;
}

public void waitForCancelButtonAppear(){

    this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find search cancel button", 5);
}

public void waitForCancelButtonToDisappear(){

    this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Search cancel button is still present", 5);

}

public void clickCancelSearch(){
    this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Connot find and click search cancel button", 5);

}

public void  typeSearchLine(String search_line){
this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find and  type into search input", 5);

}

public  void clickByArticleWithSubstring(String substring){
    String Search_result_xpath =  getResultSearchElement(substring);

    this.waitForElementAndClick(Search_result_xpath, "Cannot find search result with substring "  + substring, 10);
}

public  void waitForSearchResult(String substring){
        String Search_result_xpath =  getResultSearchElement(substring);

        this.waitForElementAndClick(Search_result_xpath, "Cannot find and click search result and substring"  + substring, 10);
    }

public  int getAmountOfFoundArticles(){

    this.waitForElementPresent(
            SEARCH_RESULT_ELEMENT,
            "Cannot find anything by the request ",
            15
    );

    return  this.getAmountOFElements(SEARCH_RESULT_ELEMENT);
}

public List<String> getTitlesOfFoundArticles(){
    By by = getLocatorByString(SEARCH_RESULT_ELEMENT_TITILE);
    //SEARCH_RESULT_ELEMENT_TITILE
    List<WebElement> foundElements = driver.findElements(by);

    List<String> titles = new ArrayList<>();

    for (WebElement element : foundElements) {
        String text = element.getAttribute("text");
        titles.add(text);
    }
    return  titles;

}

public  void waitForEmptyResultsLabel(){

this.waitForElementPresent(SEARCH_EMPTY_RESULT_ELEMENT, "Cannot empty result element", 15);

}

public  void assertThereIsNoResultOfSearch(){

    this.assertElementNotPresent(SEARCH_RESULT_ELEMENT, "We supposed not to find any results");

}

}

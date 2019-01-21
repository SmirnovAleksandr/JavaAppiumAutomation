package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPageObject extends  MainPageObject {

private static final String
    SEARCH_INIT_ELEMENT = "//*[contains(@text,'Search Wikipedia')]",
    SEARCH_INPUT = "//*[contains(@text,'Search…')]",
    SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
    SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
    SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']",
    SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results found']",
    SEARCH_RESULT_ELEMENT_TITILE = "org.wikipedia:id/page_list_item_title"
    ;



public SearchPageObject(AppiumDriver driver){
    super(driver);
}

/* TEMPLATE METHODS */
private  static  String getResultSearchElement(String substring){
    return  SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
}
/* TEMPLATE METHODS */


public  void initSearchInput(){
    this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
    this.waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find search input after clcking search init element");
}



    public String getPlaceholderTextIntoSearchElement(){

        WebElement searchInput = waitForElementPresent(By.xpath(SEARCH_INPUT), "Cannot find search input");
        String placeholder_text = searchInput.getAttribute("text");
    return  placeholder_text;
}

public void waitForCancelButtonAppear(){

    this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
}

public void waitForCancelButtonToDisappear(){

    this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);

}

public void clickCancelSearch(){
    this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Connot find and click search cancel button", 5);

}

public void  typeSearchLine(String search_line){
this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and  type into search input", 5);

}

public  void clickByArticleWithSubstring(String substring){
    String Search_result_xpath =   getResultSearchElement(substring);

    this.waitForElementAndClick(By.xpath(Search_result_xpath), "Cannot find search result with substring "  + substring, 10);
}

public  void waitForSearchResult(String substring){
        String Search_result_xpath =  getResultSearchElement(substring);

        this.waitForElementAndClick(By.xpath(Search_result_xpath), "Cannot find and click search result and substring"  + substring, 10);
    }

public  int getAmountOfFoundArticles(){

    this.waitForElementPresent(
            By.xpath(SEARCH_RESULT_ELEMENT),
            "Cannot find anything by the request ",
            15
    );

    return  this.getAmountOFElements(By.xpath(SEARCH_RESULT_ELEMENT));
}

public List<String> getTitlesOfFoundArticles(){

    List<WebElement> foundElements = driver.findElements(By.id(SEARCH_RESULT_ELEMENT_TITILE));

    List<String> titles = new ArrayList<>();

    for (WebElement element : foundElements) {
        String text = element.getAttribute("text");
        titles.add(text);
    }
    return  titles;

}

public  void waitForEmptyResultsLabel(){

this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot empty result element", 15);

}

public  void assertThereIsNoResultOfSearch(){

    this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");

}

}

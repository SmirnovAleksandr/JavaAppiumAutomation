package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

import java.util.List;

public class SearchTests  extends CoreTestCase {

    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch1() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmountNotEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park Diskography";

        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();


        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "sadada";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();

        SearchPageObject.typeSearchLine("c#");

        ArticlePageObject ArticlePageObject = new  ArticlePageObject(driver);
        int foundElements = SearchPageObject.getAmountOfFoundArticles();
        assertTrue("Multiple articles not found", foundElements >= 2);
        SearchPageObject.clickCancelSearch();
        int foundElementsAfterClearField = SearchPageObject.getAmountOfFoundArticles();
        assertTrue("Search result isn't missing", foundElements == 0);

    }

    @Test
    public void testWordSearchInSearch() {

        String valueForSearch = "Selenium";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(valueForSearch);

        List<String> titles = SearchPageObject.getTitlesOfFoundArticles();
        assertTrue("Тot every search result has value." + valueForSearch , titles.contains(valueForSearch));

    }

    @Test
    public  void testResultsPresentInSearch(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Bali";
        SearchPageObject.typeSearchLine(search_line);
        int count_of_articles = SearchPageObject.getAmountOfFoundArticles();
        assertTrue("Count of articles less then three", count_of_articles >=3);

        SearchPageObject.waitForElementByTitleAndDescription("Bali", "Island of Indonesia, located in Bali Province");
        SearchPageObject.waitForElementByTitleAndDescription("Baltimore", "City in Maryland, United States");
        SearchPageObject.waitForElementByTitleAndDescription("Baltimore Orioles", "Baseball team and Major League Baseball franchise in Baltimore, Maryland, United States");
    }

}

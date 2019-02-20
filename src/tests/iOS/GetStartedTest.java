package tests.iOS;

import lib.iOSTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class GetStartedTest  extends iOSTestCase {

    @Test
    public  void testPassThroughWelcom(){
        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExplorereText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferLangText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAvoutDataCollectedText();
        WelcomePage.clickGetStartedButtonButton();
    }
}

package lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    private  static  final String PLATFORM_IOS = "ios";
    private  static  final String PLATFORM_ANDROID = "android";

    private  static  final String DRIVER_IOS = "iOSDriver";
    private  static  final String DRIVER_ANDROID = "androidDriver";

    protected AppiumDriver driver;
    private static String  AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = this.getCApabilitiesByPlatformEnv();

        driver = this.getDriverByDriverEnv(capabilities);

        this.rotateScreenPortrait();
    }

    @Override
    protected void tearDown() throws Exception{

        driver.quit();
        super.tearDown();
    }

    protected  void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected  void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

protected  void backgroundApp(int seconds){

    Duration duration = Duration.ofSeconds(seconds);
    driver.runAppInBackground(duration);
}

private  DesiredCapabilities getCApabilitiesByPlatformEnv() throws Exception

{
    String platform = System.getenv("PLATFORM");
    DesiredCapabilities capabilities = new DesiredCapabilities();
    if(platform.equals(PLATFORM_ANDROID)){

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/admin/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

    }  else if (platform.equals(PLATFORM_IOS)){

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app", "/Users/admin/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");

    }  else{

        throw  new Exception("Cannot get run platform from env variable. Platfotm is:"+ platform);
    }

    return  capabilities;

}

private AppiumDriver getDriverByDriverEnv(DesiredCapabilities capabilities)throws  Exception{

        String driver = System.getenv("DRIVER");

        if(driver.equals(DRIVER_ANDROID)){

            this.driver = new AndroidDriver(new URL(AppiumUrl), capabilities);

        }  else if(driver.equals(DRIVER_IOS)){

            this.driver = new IOSDriver(new URL(AppiumUrl), capabilities);

        }  else{

            throw  new Exception("Cannot get run driver from env variable. Driver is:"+ this.driver);
        }

        return  this.driver;

}


}

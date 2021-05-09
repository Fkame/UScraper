package my.grandwork.Util;

import java.security.InvalidParameterException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import my.grandwork.Util.enums.Browser;

public class ConfigurationFabric {
    
    public static WebDriver getConfiguratedDriver(Browser br) {
        WebDriver wb = null;
        switch (br)
        {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                wb = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                wb = new ChromeDriver();
                break;
            default:
                throw new InvalidParameterException("Unknown browser type");
        }

        wb.manage().window().maximize();
        wb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return wb;
    }

    public static WebDriver getHeadlessChrome(int implicitlyWait) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.setBinary(System.getProperty("webdriver.chrome.driver"));
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        return driver;
    }

    /* 
    public static SelectorsData getConfigsForMtuci() {
        SelectorsData data = new SelectorsData();  

        return data;
    }
     */
}

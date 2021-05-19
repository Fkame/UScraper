package my.grandwork.UniversitiesParser.Util;

import java.security.InvalidParameterException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import my.grandwork.UniversitiesParser.Util.enums.Browser;
import my.grandwork.UniversitiesParser.parserAppInfo.ParserPathsConfig;

public class WebDriverCreationFabric {
    
    public static WebDriver getConfiguratedWebDriver(Browser br, boolean headless, int implicitlyWaitInSeconds) {
        if (headless) 
            return WebDriverCreationFabric.getHeadlessWebDriver(br, implicitlyWaitInSeconds);
        return WebDriverCreationFabric.getMaximizedVisibleDriver(br, implicitlyWaitInSeconds);
    }

    public static WebDriver getHeadlessWebDriver(Browser br, int implicitlyWaitInSeconds) {
        WebDriver wb = null;
        switch (br)
        {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", ParserPathsConfig.FolderWithDrivers + "geckodriver.exe");
                FirefoxOptions fOpts = new FirefoxOptions();
                fOpts.setHeadless(true);
                wb = new FirefoxDriver(fOpts);
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", ParserPathsConfig.FolderWithDrivers + "chromedriver.exe");
                ChromeOptions cOpts = new ChromeOptions();
                cOpts.addArguments("--headless");
                wb = new ChromeDriver(cOpts);
                break;
            default:
                throw new InvalidParameterException("Unknown browser type");
        }

        wb.manage().timeouts().implicitlyWait(implicitlyWaitInSeconds, TimeUnit.SECONDS);
        return wb;
    }

    public static WebDriver getMaximizedVisibleDriver(Browser br, int implicitlyWaitInSeconds) {
        WebDriver wb = null;
        switch (br)
        {
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", ParserPathsConfig.FolderWithDrivers + "geckodriver.exe");
                wb = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", ParserPathsConfig.FolderWithDrivers + "chromedriver.exe");
                wb = new ChromeDriver();
                break;
            default:
                throw new InvalidParameterException("Unknown browser type");
        }

        wb.manage().window().maximize();
        wb.manage().timeouts().implicitlyWait(implicitlyWaitInSeconds, TimeUnit.SECONDS);
        return wb;
    } 

    public static WebDriver getHeadlessChrome(int implicitlyWait) {
        System.setProperty("webdriver.chrome.driver", ParserPathsConfig.FolderWithDrivers + "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.setBinary(System.getProperty("webdriver.chrome.driver"));
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        return driver;
    }
}

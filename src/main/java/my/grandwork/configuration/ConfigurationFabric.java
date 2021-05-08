package my.grandwork.configuration;

import java.security.InvalidParameterException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ConfigurationFabric {
    
    public static WebDriver configurateDriver(Browsers br) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
        WebDriver wb = null;
        switch (br)
        {
            case FIREFOX:
                wb = new FirefoxDriver();
                break;
            case CHROME:
                wb = new ChromeDriver();
                break;
            default:
                throw new InvalidParameterException("Unknown browser type");
        }

        wb.manage().window().maximize();
        wb.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return wb;
    }

    /* 
    public static SelectorsData getConfigsForMtuci() {
        SelectorsData data = new SelectorsData();  

        return data;
    }
     */
}

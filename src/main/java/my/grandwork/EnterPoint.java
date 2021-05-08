package my.grandwork;

import org.openqa.selenium.WebDriver;

import my.grandwork.Parsers.BaumanParser;
import my.grandwork.Parsers.MtuciParser;
import my.grandwork.configuration.Browsers;
import my.grandwork.configuration.ConfigurationFabric;

/*
 * EnterPoint
 */
public class EnterPoint 
{
    public static void main( String[] args )
    {
        System.out.println("Start project in:" + System.getProperty("user.dir"));
        
        WebDriver driver = null;
        try {
            driver = ConfigurationFabric.configurateDriver(Browsers.CHROME);

            //System.out.println("Mtuci parser");
            //MtuciParser p = new MtuciParser(driver);
            //var data1 = p.getDirectionsInfo();
            driver.get("https://bmstu.ru/content/image/files/PK/Docs/Rules/Rules-p2_1.pdf");
            new BaumanParser(driver).DownloadPdf();
        } 
        finally {
            driver.quit();
        }
    }

}

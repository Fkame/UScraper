package my.grandwork;

import org.openqa.selenium.WebDriver;

import my.grandwork.Parsers.BaumanParser;
import my.grandwork.Parsers.MtuciParser;
import my.grandwork.Util.ConfigurationFabric;
import my.grandwork.Util.enums.Browser;

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
            driver = ConfigurationFabric.getConfiguratedDriver(Browser.CHROME);

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

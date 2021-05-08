package my.grandwork.Parsers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;

public class BaumanParser {
    
    WebDriver driver;
    public BaumanParser(WebDriver driver) {
        this.driver = driver;
    }

    public String getDirectionsInfo() {
        
        return null;
    }   

    public String DownloadPdf() {

        String output = null;
        try {
            URL website = new URL(driver.getCurrentUrl());
            InputStream is = website.openStream();
            BufferedInputStream fileToParse = new BufferedInputStream(is);
            PDDocument document = null;
            try {
                document = PDDocument.load(fileToParse);
                //document.save(System.getProperty("user.dir") + "/resources/pdf/baumanDirections.pdf");
                PDFTextStripper striper = new PDFTextStripper();
                output = striper.getText(document);
                System.out.println(output);
            } finally {
                if (document != null) {
                    document.close();
                }
                fileToParse.close();
                is.close();
            }
        } catch (Exception e) { 
            e.printStackTrace();
            System.out.println("\n" + e.getMessage()); 
        }
        
        return output;
    }
}

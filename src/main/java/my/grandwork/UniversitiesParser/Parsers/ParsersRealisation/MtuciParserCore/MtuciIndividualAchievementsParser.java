package my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.MalformedInputException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.SubWrappers.DocInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.IndividualAchievementInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.UrlParsingStatus;
import my.grandwork.UniversitiesParser.Data.enums.InfoTypeClassification;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.parserAppInfo.ParserPathsConfig;

public class MtuciIndividualAchievementsParser {
    public static void parseInfo(WebDriver driver, ParserWorkResult result) {

        String url = "https://abitur.mtuci.ru/files.php?action=get&file_id=603681fe4efd7#mtuci_id.pdf";
        UrlParsingStatus pStatus = new UrlParsingStatus();

        try {
            pStatus.url = url;
            //driver.get(url);
            getBachelorsAchievents(driver, result, url);
            pStatus.isSuccess = true;
        }
        catch (WebDriverException wde) {
            pStatus.isSuccess = false;
            pStatus.webDriverException = wde;
        }
        catch (Exception e) {
            pStatus.isSuccess = false;
            pStatus.exception = e;
        }
        finally {
            result.urlParsingStatusList.add(pStatus);
        }

        pStatus = new UrlParsingStatus();
        try {
            url = "https://abitur.mtuci.ru/#!magistratura_dostig";
            pStatus.url = url;
            driver.get(url);
            getMastersAchievents(driver, result);
            pStatus.isSuccess = true;
        }
        catch (WebDriverException wde) {
            pStatus.isSuccess = false;
            pStatus.webDriverException = wde;
        }
        catch (Exception e) {
            pStatus.isSuccess = false;
            pStatus.exception = e;
        }
        finally {
            result.urlParsingStatusList.add(pStatus);
        }

        pStatus = new UrlParsingStatus();
        try {
            url = "https://abitur.mtuci.ru/#!aspirantura_dostig";
            pStatus.url = url;
            driver.get(url);
            getPostGraduatedsAchievents(driver, result);
            pStatus.isSuccess = true;
        }
        catch (WebDriverException wde) {
            pStatus.isSuccess = false;
            pStatus.webDriverException = wde;
        }
        catch (RuntimeException ee) {
            pStatus.isSuccess = false;
            pStatus.exception = ee;
        }
        catch (Exception e) {
            pStatus.isSuccess = false;
            pStatus.exception = e;
        }
        finally {
            result.urlParsingStatusList.add(pStatus);
        }
    }

    private static void getBachelorsAchievents(WebDriver driver, ParserWorkResult result, String url) {

        String pathToSave = ParserPathsConfig.FolderToSaveDownloadedFiles;
        try {
            URL website = new URL(url);
            ReadableByteChannel rbc = Channels.newChannel(website.openStream());    

            String name = url.substring(url.lastIndexOf("#") + 1);
            FileOutputStream fos = new FileOutputStream(pathToSave + name);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();

            File file = new File(System.getProperty("user.dir") + "\\" + pathToSave + name);
            boolean isSuccess = file.exists();
            if (!isSuccess) throw new Exception();

            result.universityInfoWrapper.docsList.add(new DocInfo(file, InfoTypeClassification.INDIVIDUAL_ACHIEVEMENTS));
        }
        catch (FileNotFoundException e1) { }
        catch (MalformedInputException e2) { }
        catch (IOException e3) {  }
        catch (Exception e4) { }
        finally {
             
        }
    }

    private static void getMastersAchievents(WebDriver driver, ParserWorkResult result) {
        String waitSelector = "#divbody  p ~ ul";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(waitSelector)));

        List<WebElement> list = driver.findElements(By.cssSelector(waitSelector));
        for (WebElement part : list) {
            String[] parts = part.getText().split(": ");
            String descr = parts[1];
            String scoreStr = parts[0].split(" ")[0];
            Integer score = Integer.valueOf(scoreStr);
            IndividualAchievementInfo info = new IndividualAchievementInfo(descr, score, StudyGrade.MASTER);
            result.universityInfoWrapper.individualAchievementsList.add(info);
        }
    }

    private static void getPostGraduatedsAchievents(WebDriver driver, ParserWorkResult result) {
        /*
        String waitSelector = ".data ul li";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(waitSelector)));

        String rowsSelector = ".data tbody tr";
        List<WebElement> rowsList = driver.findElements(By.cssSelector(rowsSelector));
        rowsList.remove(0);

        rowsList.clear();
        */
        throw new RuntimeException("This parser part is not ready!");
    }
}

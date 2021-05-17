package my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore;

import java.security.InvalidParameterException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.SubWrappers.UrlParsingStatus;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;

public class MtuciTimeAdmissionParser {
    public static void parseTimeAdmissionForAllGrades(WebDriver driver, ParserWorkResult result) {    
        UrlParsingStatus pStatus = new UrlParsingStatus();

        for (StudyGrade grade : StudyGrade.values()) {
            try {
                getTimeAdmissionForTargetGrade(driver, result, grade);
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
                pStatus.url = driver.getCurrentUrl();
                result.urlParsingStatusList.add(pStatus);
            }
        }    
    }

    private static void getTimeAdmissionForTargetGrade(WebDriver driver, ParserWorkResult result, StudyGrade grade) {
        String url = null;
        switch (grade) {
            case BACHELOR:
                url = "https://abitur.mtuci.ru/#!sroki_bakalavriata";
                break;
            case MASTER:
                url = "https://abitur.mtuci.ru/#!sroki_magistraturi";
                break;
            case POST_GRADUATE:
                url = "https://abitur.mtuci.ru/#!aspirantura_sroki";
                break;
            default:
                throw new InvalidParameterException("Unknown grade.");
        }

        String selector = ".page > *";
        driver.get(url);
        try { Thread.sleep(1000); } catch (InterruptedException e) { } 

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));

        List<WebElement> list = driver.findElements(By.cssSelector(selector));
        list.remove(0);

        StringBuilder data = new StringBuilder();
        for (WebElement webElement : list) {
            List<WebElement> childs = webElement.findElements(By.cssSelector("*"));
            for (WebElement elem : list) {
                data.append(elem.getText()).append("\n");
            }
            data.append("\n");
        }

        switch (grade) {
            case BACHELOR:
                result.universityInfoWrapper.bachelorTimeAdmission = data.toString();
                break;
            case MASTER:
                result.universityInfoWrapper.masterTimeAdmission = data.toString();
                break;
            case POST_GRADUATE:
                result.universityInfoWrapper.postgradeTimeAdmission = data.toString();
                break;
        }
    }
}

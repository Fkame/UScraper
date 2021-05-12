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
import my.grandwork.UniversitiesParser.Data.SubWrappers.StudyDirectionInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.UrlParsingStatus;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Data.enums.StudyType;

public class MtuciDirectionsListParser {
    
    public static void parseAllDirections(WebDriver driver, ParserWorkResult result) {
        for (StudyGrade grade : StudyGrade.values()) {

            UrlParsingStatus pStatus = new UrlParsingStatus();
            try {
                parseDirectionsForTargetGrade(driver, result, grade);
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

    private static void parseDirectionsForTargetGrade(WebDriver driver, ParserWorkResult result, StudyGrade grade) {
        switch (grade) 
        {
            case BACHELOR:
                parseBachelogDirections(driver, result);
                break;
            case MASTER:
                parseMasterDirections(driver, result);
                break;
            case POST_GRADUATE:
                parsePostGraduatedDirections(driver, result);
                break;
            default:       
                throw new InvalidParameterException("Unknown grade");
        }

    }

    private static void parseBachelogDirections(WebDriver driver, ParserWorkResult result) {
        String url = "https://abitur.mtuci.ru/#!napr";
        driver.get(url);
    
        StudyGrade grade = StudyGrade.BACHELOR;
        StudyType type = StudyType.FULL_TIME;
        String waitElementTargetSelector = "#divbody table td";
        String tablesSelector = "#divbody h3 ~ table";
        String rowsSelector = "tbody tr";
        String cellsDataSelector = "td";

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(waitElementTargetSelector)));

        List<StudyDirectionInfo> dirsList = result.universityInfoWrapper.directionsInfoList;

        List<WebElement> tables = driver.findElements(By.cssSelector(tablesSelector));

        for (int table = 0; table < tables.size(); table++) {

            if (table < StudyType.values().length) {
                type = StudyType.values()[table];
            }
            List<WebElement> rows = tables.get(table).findElements(By.cssSelector(rowsSelector));
            if (rows.size() == 0) continue;
            rows.remove(0);

            for (int row = 0; row < rows.size(); row++) {
                List<WebElement>cells = rows.get(row).findElements(By.cssSelector(cellsDataSelector));
                if (cells.size() == 0) continue;

                String name = cells.get(1).getText();
                String code = cells.get(cells.size() - 1).getText();

                StudyDirectionInfo info = new StudyDirectionInfo();
                info.directoryCode = code;
                info.nameOfDirectory = name;
                dirsList.add(info);
            }
        }
    }

    public static void parseMasterDirections(WebDriver driver, ParserWorkResult result) {
        String url = "https://abitur.mtuci.ru/#!mag_fakultets";
        driver.get(url);

        StudyGrade grade = StudyGrade.MASTER;
        StudyType type = StudyType.FULL_TIME;

        throw new RuntimeException("Method does not already over!");
    }

    public static void parsePostGraduatedDirections(WebDriver driver, ParserWorkResult result) {
        String url = "https://abitur.mtuci.ru/#!aspirantura_napr";
        driver.get(url);

        StudyGrade grade = StudyGrade.POST_GRADUATE;
        StudyType type = StudyType.FULL_TIME;

        String selector = "#divbody .page div ~ .animate-appear > li";
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));

        List<StudyDirectionInfo> dirsList = result.universityInfoWrapper.directionsInfoList;

        List<WebElement> listOfDirs = driver.findElements(By.cssSelector(selector));

        for (int list = 0; list < listOfDirs.size(); list++) {
            String data = listOfDirs.get(list).getText();
            String[] dataArray = data.split(" - ");

            // Выделяем данные, удаляя лишние участки
            String code = dataArray[0];
            int cut = dataArray[1].endsWith(":") ? 2 : 1;
            String name = dataArray[1].substring(1, dataArray[1].length() - cut);

            StudyDirectionInfo info = new StudyDirectionInfo();
            info.directoryCode = code;
            info.nameOfDirectory = name;
            dirsList.add(info);
        }
    }
}

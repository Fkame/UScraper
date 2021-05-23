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
import my.grandwork.UniversitiesParser.Data.MajorWrappers.UniversityInfoWrapper;
import my.grandwork.UniversitiesParser.Data.SubWrappers.StudyDirectionInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.UrlParsingStatus;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Data.enums.StudyType;

public class MtuciScoresParser {

    public static void parseScoresForTargerStudyGrade(WebDriver driver, ParserWorkResult result, StudyGrade grade) {
        String url = null;
        UrlParsingStatus pStatus = new UrlParsingStatus();

        // Страницы одинаковые, только ссылки разные
        switch (grade) 
        {
            case BACHELOR:
                url = "https://abitur.mtuci.ru/#!livetable";
                break;
            case MASTER:
                url = "https://abitur.mtuci.ru/#!magistratura_livetable";
                break;
            case POST_GRADUATE:
                url = "https://abitur.mtuci.ru/#!aspirantura_livetable";
                break;
            default:
                pStatus.isSuccess = false;
                pStatus.exception = new InvalidParameterException("Unknown grade");
                result.urlParsingStatusList.add(pStatus);
                return;
        }
        pStatus.url = url;

        // Закидываение по url и парсинг + отлов ошибок и запись в отчёт парсинга
        try {
            driver.get(url);
            getGradeInfo(driver, result, grade);
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
    }

    private static void getGradeInfo(WebDriver driver, ParserWorkResult result, StudyGrade grade) {
        String studyTypesSplitterSelector = "#divbody hr";
        String waitElementTargetSelector = "#divbody table td";
        String tablesWithContentSelector = "#divbody div table";
        String rowsInTableSelector = "tbody tr";
        String cellsDataSelector = "td";
        StudyType typeStudy = StudyType.FULL_TIME;

        // В этот список добавим спарсенные направления
        List<StudyDirectionInfo> dirsList = result.universityInfoWrapper.directionsInfoList;

        // Ожидание, так как страница прогружается не сразу и вылетает ошибка драйвера
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try { Thread.sleep(2000); } catch (InterruptedException e) { }
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(waitElementTargetSelector)));
        
        // Типу обучения могут соответствовать несколько таблиц, нужно их различать
        List<WebElement> studyTypesSplitters = driver.findElements(By.cssSelector(studyTypesSplitterSelector));
        List<WebElement> tables = driver.findElements(By.cssSelector(tablesWithContentSelector));

        int splitterPointer = 0;
        for (int table = 0; table < tables.size(); table++) {
            List<WebElement> rows = tables.get(table).findElements(By.cssSelector(rowsInTableSelector));
            if (rows.size() == 0) continue;
            
            if (studyTypesSplitters.size() != 0) {
                // Таблицы типов обучения разделены такими линиями на странице, поэтому тип обучения и эти линии можно соотнести
                int tablePointY = tables.get(table).getLocation().getY();
                int currentSplitterPointY = studyTypesSplitters.get(splitterPointer).getLocation().getY();
                if (currentSplitterPointY < tablePointY) {
                    splitterPointer += 1;
                    try { 
                        typeStudy = StudyType.values()[splitterPointer]; 
                    } catch (Exception e) { 
                        splitterPointer -= 1;
                        typeStudy = StudyType.values()[splitterPointer]; 
                    }
                }
            }
            
            // Выбор нужных ячеек, считывание и запоминание данных
            for (int row = 0; row < rows.size(); row++) {
                List<WebElement>cells = rows.get(row).findElements(By.cssSelector(cellsDataSelector));
                if (cells.size() == 0) continue;

                String code = cells.get(1).getText();
                String planStr = cells.get(2).getText();
                Integer plan = null;
                try { plan = Integer.valueOf(planStr); } finally { }

                String wavesStr = cells.get(cells.size() - 1).getText();
                String[] waves = wavesStr.split("/");
                Integer firstWaveScore = null;
                Integer secondWaveScore = null;
                try { 
                    firstWaveScore = waves.length > 0 ? Integer.valueOf(waves[0]) : null; 
                    secondWaveScore = waves.length > 1 ? Integer.valueOf(waves[1]) : null;
                } finally { }     
                
                StudyDirectionInfo info = new StudyDirectionInfo();
                info.directoryCode = code;
                info.admissionPlanForFree = plan;
                info.firstWaveScoreOrGeneralScore = firstWaveScore;
                info.secondWaveScore = secondWaveScore;
                info.typesOfStudy = typeStudy;
                info.studyGrades = grade;
                dirsList.add(info);
            }
        }
    }

    public static void addScoresToExistingWorkResults(WebDriver driver, ParserWorkResult result) {
        ParserWorkResult result2 = new ParserWorkResult();

        MtuciScoresParser.parseScoresForTargerStudyGrade(driver, result2, StudyGrade.BACHELOR);
        MtuciScoresParser.parseScoresForTargerStudyGrade(driver, result2, StudyGrade.MASTER);
        MtuciScoresParser.parseScoresForTargerStudyGrade(driver, result2, StudyGrade.POST_GRADUATE);
        
        // Добавляем статус парсинга в конец
        result.urlParsingStatusList.addAll(result2.urlParsingStatusList);

        // Дополняем данные баллами за волны и планом приёма
        List<StudyDirectionInfo> mainList = result.universityInfoWrapper.directionsInfoList;
        List<StudyDirectionInfo> listToMerge = result2.universityInfoWrapper.directionsInfoList;

        for (int i = 0; i < listToMerge.size(); i++) {
            StudyDirectionInfo dataToAdd = listToMerge.get(i);
            for (int j = 0; j < mainList.size(); j++) {
                StudyDirectionInfo dataToBeCompleted = mainList.get(j);
                if (dataToAdd.directoryCode == dataToBeCompleted.directoryCode &&
                    dataToAdd.studyGrades == dataToBeCompleted.studyGrades &&
                    dataToAdd.typesOfStudy == dataToBeCompleted.typesOfStudy) 
                {
                    dataToBeCompleted.admissionPlanForFree = dataToAdd.admissionPlanForFree;
                    dataToBeCompleted.firstWaveScoreOrGeneralScore = dataToAdd.firstWaveScoreOrGeneralScore;
                    dataToBeCompleted.secondWaveScore = dataToAdd.secondWaveScore;
                }
            }
        }
    }

    public static void getCurrentScoresWithReplace(WebDriver driver, ParserWorkResult result) {
        MtuciScoresParser.parseScoresForTargerStudyGrade(driver, result, StudyGrade.BACHELOR);
        MtuciScoresParser.parseScoresForTargerStudyGrade(driver, result, StudyGrade.MASTER);
        MtuciScoresParser.parseScoresForTargerStudyGrade(driver, result, StudyGrade.POST_GRADUATE);
    }

    public static void concatSameDirections(ParserWorkResult result) {
        List<StudyDirectionInfo> mainList = result.universityInfoWrapper.directionsInfoList;
    }
}



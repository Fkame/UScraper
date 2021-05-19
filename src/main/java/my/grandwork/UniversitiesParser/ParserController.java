package my.grandwork.UniversitiesParser;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.SubWrappers.UrlParsingStatus;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Parsers.IUniversityParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.BaumanParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MISiSParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.StankinkParser;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;
import my.grandwork.UniversitiesParser.Util.WebDriverCreationFabric;
import my.grandwork.UniversitiesParser.Util.enums.Browser;

public class ParserController {
    
    public boolean useMultiThreads = false;
    private int maxNumOfThreads = 32;
    public Browser browser = Browser.CHROME;
    public boolean headlessMode = false;

    public ParserController() { }

    public ParserController(Browser browser, boolean headlessMode, boolean useMultiThreads) {
        this.browser = browser;
        this.headlessMode = headlessMode;
        this.useMultiThreads = useMultiThreads;
    }

    public boolean setMaxNumOfThreads(int amount) {
        if (amount > 1024 | amount < 1) return false;
        this.maxNumOfThreads = amount;
        return true;
    }

    public IUniversityParser createTargetUniversityParser(WebDriver driver, University university) {
        IUniversityParser parser = null;
        switch (university)
        {
            case MTUCI: 
                parser = new MtuciParser(driver);
                break;
            case BMSTU:
                parser = new BaumanParser(driver);
                break;
            case STANKINK:
                parser = new StankinkParser(driver);
                break;
            case MISiS:
                parser = new MISiSParser(driver);
            ///TODO: после реализации парсера сюда нужно добавить его вызов
            default: 
                throw new InvalidParameterException("This univesity does not supported yet");
        } 
        return parser;
    }
   
    public List<ParserWorkResult> parseAllUniversitiesInfo() {
        List<ParserWorkResult> list = new ArrayList<ParserWorkResult>();
        List<University> univers = Arrays.asList(University.values());
        for (University university : univers) {
            list.add(this.parseTargetUniversityInfo(university));
        }

        return list;
    }
   
    public ParserWorkResult parseTargetUniversityInfo(University university) {
        WebDriver driver = null;
        ParserWorkResult result = null;
        try {
            driver = WebDriverCreationFabric.getConfiguratedWebDriver(browser, headlessMode, 0);
            IUniversityParser parser = this.createTargetUniversityParser(driver, university);
            result = parser.parseAllUniversityInfo();
        }
        catch (WebDriverException ex) {
            String url = null;
            if (driver != null) url = driver.getCurrentUrl();
            UrlParsingStatus status = new UrlParsingStatus(url, false, "WebDriverError get the controller.");
            status.webDriverException = ex;
            result.urlParsingStatusList.add(status);
        }
        catch (Exception e) {
            String url = null;
            if (driver != null) url = driver.getCurrentUrl();
            UrlParsingStatus status = new UrlParsingStatus(url, false, "Seems like system error get the controller.");
            status.exception = e;
            result.urlParsingStatusList.add(status);
        }
        finally {
            if (driver != null) driver.quit();
        }

        return result;
    }

    
    public List<ParserWorkResult> parseCurrentScoresForAllGradesInAllUniversities() {
        List<ParserWorkResult> list = new ArrayList<ParserWorkResult>();
        List<University> univers = Arrays.asList(University.values());
        for (University university : univers) {
            list.add(this.parseCurrentScoresForAllGradesInTargetUniversity(university));
        }

        return list;
    }

   
    public ParserWorkResult parseCurrentScoresForAllGradesInTargetUniversity(University university) {
        WebDriver driver = null;
        ParserWorkResult result = null;
        try {
            driver = WebDriverCreationFabric.getConfiguratedWebDriver(browser, headlessMode, 0);
            IUniversityParser parser = this.createTargetUniversityParser(driver, university);
            result = parser.parseCurrentScoresForAllGradesInfo();
        }
        catch (WebDriverException ex) {
            String url = null;
            if (driver != null) url = driver.getCurrentUrl();
            UrlParsingStatus status = new UrlParsingStatus(url, false, "WebDriverError get the controller.");
            status.webDriverException = ex;
            result.urlParsingStatusList.add(status);
        }
        catch (Exception e) {
            String url = null;
            if (driver != null) url = driver.getCurrentUrl();
            UrlParsingStatus status = new UrlParsingStatus(url, false, "Seems like system error get the controller.");
            status.exception = e;
            result.urlParsingStatusList.add(status);
        }
        finally {
            if (driver != null) driver.quit();
        }
        
        return result;
    }


    public List<ParserWorkResult> parseCurrentScoresForTargetGradeInAllUniversities(StudyGrade grade) {
        List<ParserWorkResult> list = new ArrayList<ParserWorkResult>();
        List<University> univers = Arrays.asList(University.values());
        for (University university : univers) {
            list.add(this.parseCurrentScoresForTargetGradeInTargetUniversity(university, grade));
        }

        return list;
    }

  
    public ParserWorkResult parseCurrentScoresForTargetGradeInTargetUniversity(University university, StudyGrade grade) {
        WebDriver driver = null;
        ParserWorkResult result = null;
        try {
            driver = WebDriverCreationFabric.getConfiguratedWebDriver(browser, headlessMode, 0);
            IUniversityParser parser = this.createTargetUniversityParser(driver, university);
            result = parser.parseCurrentScoresForTargetGradeInfo(grade);
        }
        catch (WebDriverException ex) {
            String url = null;
            if (driver != null) url = driver.getCurrentUrl();
            UrlParsingStatus status = new UrlParsingStatus(url, false, "WebDriverError get the controller.");
            status.webDriverException = ex;
            result.urlParsingStatusList.add(status);
        }
        catch (Exception e) {
            String url = null;
            if (driver != null) url = driver.getCurrentUrl();
            UrlParsingStatus status = new UrlParsingStatus(url, false, "Seems like system error get the controller.");
            status.exception = e;
            result.urlParsingStatusList.add(status);
        }
        finally {
            if (driver != null) driver.quit();
        }
        
        return result;
    }

}

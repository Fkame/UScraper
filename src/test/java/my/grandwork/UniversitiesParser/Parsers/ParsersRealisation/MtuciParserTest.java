package my.grandwork.UniversitiesParser.Parsers.ParsersRealisation;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.SubWrappers.StudyDirectionInfo;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore.*;
import my.grandwork.UniversitiesParser.Util.WebDriverCreationFabric;
import my.grandwork.UniversitiesParser.Util.enums.Browser;

public class MtuciParserTest {
    
    WebDriver driver;
    ParserWorkResult result;

    @Before
    public void prepareDriverAndResultWrapper() {
        this.driver = WebDriverCreationFabric.getConfiguratedWebDriver(Browser.CHROME, false, 0);
        this.result = new ParserWorkResult();
    }

    @After
    public void freeResources() {
        driver.close();
        driver = null;
        result = null;
    }

    @Test
    public void testMtuciIndividualAchievementsParser() {
        MtuciIndividualAchievementsParser.parseInfo(driver, result);
        assertNotNull(result);
    }

    @Test
    public void testMtuciTimeAdmissionParser() { 
        MtuciTimeAdmissionParser.parseTimeAdmissionForAllGrades(driver, result);
        String plan = result.universityInfoWrapper.bachelorTimeAdmission;
        System.out.println(plan);
        assertNotNull(result);
    }

    @Test
    public void testMtuciDirectionsListParser() {
        MtuciDirectionsListParser.parseAllDirections(driver, result);
        assertNotNull(result);
    }

    @Test
    public void testMtuciScoresParser() {
        MtuciScoresParser.getCurrentScoresWithReplace(driver, result);
        MtuciScoresParser.concatSameDirections(result);
        for (StudyDirectionInfo dir : result.universityInfoWrapper.directionsInfoList) {
            System.out.println("id=" + dir.directoryCode + ", name=" + dir.nameOfDirectory + ", type=" + dir.typesOfStudy + ", grade=" + dir.studyGrades);
        }
    }

    @Test 
    public void testRegulars() {
        String[] words = { "word", "word(faf)", "word (123 gfdg)"};
        String answer = "word";

        for (String word : words) {
            String check = word.replaceAll("\\s*\\(.+\\)", "");
            assertEquals(answer, check);
        }
        
    }
}

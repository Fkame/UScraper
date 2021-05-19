package my.grandwork.UniversitiesParser.Parsers.ParsersRealisation;

import static org.junit.Assert.assertNotNull;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
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
        assertNotNull(result);
    }

    @Test
    public void testMtuciDirectionsListParser() {
        MtuciDirectionsListParser.parseAllDirections(driver, result);
        assertNotNull(result);
    }

    @Test
    public void testMtuciScoresParser() {
        
    }
}

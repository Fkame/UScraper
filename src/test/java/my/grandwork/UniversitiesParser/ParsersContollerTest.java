package my.grandwork.UniversitiesParser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.SubWrappers.UrlParsingStatus;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;
import my.grandwork.UniversitiesParser.Util.enums.Browser;

public class ParsersContollerTest {
    
    @Test
    public void testParseAllUniversitiesInfo() {
       
    }

    @Test
    public void testParseTargetUniversityInfo() {
       University university = University.MTUCI;
       ParserController controller = new ParserController(Browser.CHROME, false, false);  
       ParserWorkResult rez = controller.parseTargetUniversityInfo(university);
       assertNotNull(rez);

       for (UrlParsingStatus status : rez.urlParsingStatusList) {
        assertEquals(status.isSuccess, true);
       }
    }

    @Test
    public void testParseCurrentScoresForAllGradesInAllUniversities() {

    }

    @Test
    public void testParseCurrentScoresForTargetGradeInAllUniversities() {
        
    }

    @Test
    public void testParseCurrentScoresForAllGradesInTargetUniversity() {
        University university = University.MTUCI;
        ParserController controller = new ParserController(Browser.CHROME, false, false);
        List<ParserWorkResult> rezes = new ArrayList<>();
        for (StudyGrade grade : StudyGrade.values()) {
            ParserWorkResult rez = controller.parseCurrentScoresForTargetGradeInTargetUniversity(university, grade);
            rezes.add(rez);
        }
        assertEquals(true, true);
    }

    @Test
    public void testParseCurrentScoresForTargetGradeInTargetUniversity() {
        University university = University.MTUCI;
        StudyGrade grade = StudyGrade.BACHELOR;
        ParserController controller = new ParserController(Browser.CHROME, false, false);  
        ParserWorkResult rez = controller.parseCurrentScoresForTargetGradeInTargetUniversity(university, grade);

        UrlParsingStatus status = rez.urlParsingStatusList.get(0);
        System.out.println(status.isSuccess + "\n" + status.url);
        if (status.exception != null) System.out.println(status.exception.getStackTrace().toString());
        if (status.webDriverException != null) System.out.println(status.webDriverException.getStackTrace().toString());
        assertEquals(rez.urlParsingStatusList.get(0).isSuccess, true);
    }

    
}

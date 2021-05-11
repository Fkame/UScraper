package my.grandwork.UniversitiesParser;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;
import my.grandwork.UniversitiesParser.Util.enums.Browser;

public class ParsersContollerTest {
    
    @Test
    public void parseAllUniversitiesInfo() {
       
    }

    @Test
    public void parseTargetUniversityInfo(University university) {
       
    }

    @Test
    public void parseCurrentScoresForAllGradesInAllUniversities() {

    }

    @Test
    public void parseCurrentScoresForTargetGradeInAllUniversities(StudyGrade grade) {
        
    }

    @ParameterizedTest
    //@EnumSource(University.class)
    @EnumSource(names = { "MTUCI" })
    public void parseCurrentScoresForAllGradesInTargetUniversity(University university) {
        ParserController controller = new ParserController(Browser.CHROME, false, false);
        for (StudyGrade grade : StudyGrade.values()) {
            ParserWorkResult rez = controller.parseCurrentScoresForTargetGradeInTargetUniversity(university, grade);
        }
        ;
    }

    @Test
    public void parseCurrentScoresForTargetGradeInTargetUniversity(University university, StudyGrade grade) {
        
    }
}

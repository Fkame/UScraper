package my.grandwork.UniversitiesParser.Parsers;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;

public class IUniversityParserTest {

    @ParameterizedTest
    @EnumSource(University.class)
    public void testParseAllUniversityInfo(University u) {
        
    }

    @Test
    public void testParseCurrentScoresForAllGradesInfo() {

    }

    @Test
    public void testParseCurrentScoresForTargetGradeInfo(StudyGrade grade) {

    }

    @Test
    public void testParseAdditionaDataAndPdfs(StudyGrade grade) {
        
    }
}

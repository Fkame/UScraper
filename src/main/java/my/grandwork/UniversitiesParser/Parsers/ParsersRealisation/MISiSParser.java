package my.grandwork.UniversitiesParser.Parsers.ParsersRealisation;

import org.openqa.selenium.WebDriver;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Parsers.IUniversityParser;

public class MISiSParser implements IUniversityParser {
    
    private WebDriver driver;
    private ParserWorkResult result;

    public MISiSParser(WebDriver driver) {
        this.driver = driver;
        result = new ParserWorkResult();
    }

    @Override
    public ParserWorkResult parseAllUniversityInfo() {
        return null;
    }

    @Override
    public ParserWorkResult parseCurrentScoresForAllGradesInfo() {
        return null;
    }

    @Override
    public ParserWorkResult parseCurrentScoresForTargetGradeInfo(StudyGrade grade) {
        return null;
    }
}

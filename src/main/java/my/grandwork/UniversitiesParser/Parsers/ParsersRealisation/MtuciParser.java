package my.grandwork.UniversitiesParser.Parsers.ParsersRealisation;

import org.openqa.selenium.WebDriver;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Parsers.IUniversityParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore.MtuciScoresParser;


public class MtuciParser implements IUniversityParser {
    private WebDriver driver;
    private ParserWorkResult result;

    public MtuciParser(WebDriver driver) {
        this.driver = driver;
        result = new ParserWorkResult();
    }

    @Override
    public ParserWorkResult parseAllUniversityInfo() {
        
        return result;
    }

    @Override
    public ParserWorkResult parseCurrentScoresForAllGradesInfo() {
        this.parseCurrentScoresForTargetGradeInfo(StudyGrade.BACHELOR);
        this.parseCurrentScoresForTargetGradeInfo(StudyGrade.MASTER);
        this.parseCurrentScoresForTargetGradeInfo(StudyGrade.POST_GRADUATE);
        return result;
    }

    @Override
    public ParserWorkResult parseCurrentScoresForTargetGradeInfo(StudyGrade grade) {
        MtuciScoresParser.parseScoresForTargerStudyGrade(driver, result, grade);
        return result;
    }
}

package my.grandwork.UniversitiesParser.Parsers.ParsersRealisation;

import org.openqa.selenium.WebDriver;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Parsers.IUniversityParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore.MtuciAboutPaidInformationParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore.MtuciAboutUniversityParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore.MtuciDirectionsListParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore.MtuciIndividualAchievementsParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore.MtuciLastYearScoresParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore.MtuciScoresParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore.MtuciTimeAdmissionParser;


public class MtuciParser implements IUniversityParser {
    private WebDriver driver;
    private ParserWorkResult result;

    public MtuciParser(WebDriver driver) {
        this.driver = driver;
        result = new ParserWorkResult();
    }

    @Override
    public ParserWorkResult parseAllUniversityInfo() {
        MtuciAboutUniversityParser.parseAboutUniversityInfo(driver, result);
        MtuciIndividualAchievementsParser.parseInfo(driver, result);
        MtuciTimeAdmissionParser.parseTimeAdmissionForAllGrades(driver, result);
        MtuciLastYearScoresParser.addDocsAboutLastYearsEducation(driver, result);
        MtuciDirectionsListParser.parseAllDirections(driver, result);
        MtuciScoresParser.addScoresToExistingWorkResults(driver, result);
        MtuciAboutPaidInformationParser.addAllInfoAboutPaidEducation(driver, result);
        
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

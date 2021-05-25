package my.grandwork.UniversitiesParser.Parsers.ParsersRealisation;

import org.openqa.selenium.WebDriver;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Parsers.IUniversityParser;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore.*;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;


public class MtuciParser implements IUniversityParser {
    private WebDriver driver;
    private ParserWorkResult result;

    public MtuciParser(WebDriver driver) {
        this.driver = driver;
        result = new ParserWorkResult();

        result.universityInfoWrapper.fullNameOfUniversity = "Московский технический университет связи и информатики";
        result.universityInfoWrapper.shortNameOfUniversity = "МТУСИ";
        result.universityInfoWrapper.programNameOfUniversity = University.MTUCI;
    }

    @Override
    public ParserWorkResult parseAllUniversityInfo() {
        MtuciIndividualAchievementsParser.parseInfo(driver, result);
        MtuciTimeAdmissionParser.parseTimeAdmissionForAllGrades(driver, result);
        MtuciLastYearScoresParser.addDocsAboutLastYearsEducation(driver, result);
        //MtuciDirectionsListParser.parseAllDirections(driver, result);
        MtuciScoresParser.getCurrentScoresWithReplace(driver, result);
        MtuciScoresParser.concatSameDirections(result);
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

package my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserCore;

import org.openqa.selenium.WebDriver;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;

public class MtuciAboutPaidInformationParser {

    public static void addAllInfoAboutPaidEducation(WebDriver driver, ParserWorkResult result) {
        MtuciAboutPaidInformationParser.addToDirsInformationAboutCost(driver, result);
        MtuciAboutPaidInformationParser.addDocAboutPaidEducation(driver, result);
    }

    public static void addToDirsInformationAboutCost(WebDriver driver, ParserWorkResult result) {

    }

    public static void addDocAboutPaidEducation(WebDriver driver, ParserWorkResult result) {

    }
}

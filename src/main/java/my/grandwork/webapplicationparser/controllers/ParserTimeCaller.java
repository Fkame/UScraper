package my.grandwork.webapplicationparser.controllers;

import java.util.List;

import my.grandwork.UniversitiesParser.ParserController;
import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Util.enums.Browser;
import my.grandwork.webapplicationparser.dao.DatabaseInformationWorker;

public class ParserTimeCaller {

    DatabaseInformationWorker dbWorker;
    public ParserTimeCaller() {
        dbWorker = new DatabaseInformationWorker();
    }

    public void runParsingAllContent() {
        ParserController pcont = new ParserController(Browser.CHROME, true, false);
        List<ParserWorkResult> rez = pcont.parseCurrentScoresForAllGradesInAllUniversities();

    }

    public void runParsingCompetitionTable() {
        ParserController pcont = new ParserController(Browser.CHROME, true, false);
        List<ParserWorkResult> rez = pcont.parseCurrentScoresForAllGradesInAllUniversities();
    }
    
}

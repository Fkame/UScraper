package my.grandwork.webapplicationparser.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import my.grandwork.UniversitiesParser.ParserController;
import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;
import my.grandwork.UniversitiesParser.Util.enums.Browser;
import my.grandwork.webapplicationparser.dao.DatabaseInformationWorker;

public class ParserTimeCaller {

    DatabaseInformationWorker dbWorker;
    public ParserTimeCaller() {
        dbWorker = new DatabaseInformationWorker();
    }

    public void runParsingAllContent() {
        ParserController pcont = new ParserController(Browser.CHROME, true, false);
        List<ParserWorkResult> rez = pcont.parseAllUniversitiesInfo();
        dbWorker.putInfoToDbByCleaning(rez);
    }

    public void runParsingCompetitionTable() {
        ParserController pcont = new ParserController(Browser.CHROME, true, false);
        List<ParserWorkResult> rez = pcont.parseCurrentScoresForAllGradesInAllUniversities();
        dbWorker.putCompetitionTableToDbByCleaning(rez);
    }

    /* Функционал ещё не реализован, поэтому лучше его отсюда убрать
    public void runParsingAllContentInTargetUniversity(University university) {
        ParserController pcont = new ParserController(Browser.CHROME, true, false);
        List<ParserWorkResult> rez = Arrays.asList(pcont.parseTargetUniversityInfo(university));
        
    }

    public void runParsingCompetitionTableInTargetUniversity(University university) {
        ParserController pcont = new ParserController(Browser.CHROME, true, false);
        List<ParserWorkResult> rez = Arrays.asList(pcont.parseCurrentScoresForAllGradesInTargetUniversity(university));
    }
    */
    
}

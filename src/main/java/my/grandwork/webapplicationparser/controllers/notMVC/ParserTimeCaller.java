package my.grandwork.webapplicationparser.controllers.notMVC;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import my.grandwork.UniversitiesParser.ParserController;
import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Util.enums.Browser;
import my.grandwork.webapplicationparser.dao.DatabaseInformationWorker;

public class ParserTimeCaller {

    // Время срабатывания - каждый час
    public static int delayTimeInSec = 3600;

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

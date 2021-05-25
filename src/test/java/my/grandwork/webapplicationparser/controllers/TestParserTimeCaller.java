package my.grandwork.webapplicationparser.controllers;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import my.grandwork.webapplicationparser.controllers.notMVC.ParserTimeCaller;

public class TestParserTimeCaller {
    
    public ParserTimeCaller parseCaller;

    @Before
    public void beforeTest() {
        this.parseCaller = new ParserTimeCaller();
    }

    @Test
    public void runParsingAllContentTest() {
        parseCaller.runParsingAllContent();
    }

    @Test
    public void runParsingCompetitionTableTest() {
        parseCaller.runParsingCompetitionTable();
    }
}

package my.grandwork.webapplicationparser.controllers;

import org.junit.Before;
import org.junit.Test;

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

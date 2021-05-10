package my.grandwork.UniversitiesParser;

import org.junit.Test;

import my.grandwork.UniversitiesParser.ParserController;

public class ParsersContollerTest {
    
    @Test
    public void testGetAllUniversitiesInfo() {
        (new ParserController()).parseAllUniversitiesInfo();
    }
}

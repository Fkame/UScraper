package my.grandwork.webapplicationparser.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import my.grandwork.webapplicationparser.dao.DatabaseInformationWorker;

public class TestSearchEngine {
    
    @Test
    public void adaptStringTest() {
        DatabaseInformationWorker db_worker = new DatabaseInformationWorker();
        SearchEngine enj = new SearchEngine(db_worker);

        String answer = "мтуси";
        String[] data = { "МТУСИ", "МтУСи", " мтуси", "мтуси ", "мт уси", "м Т Уси" };

        for (String value : data) {
            String check = enj.adaptString(value);
            assertEquals(answer, check);
        }
    }
}

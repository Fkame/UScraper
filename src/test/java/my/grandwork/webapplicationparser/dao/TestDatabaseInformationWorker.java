package my.grandwork.webapplicationparser.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;

import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.webapplicationparser.dao.dataWrappers.UniversityName;

public class TestDatabaseInformationWorker {
    
    @Test
    public void getUniversitiesNamesListTest() {
        DatabaseInformationWorker db = new DatabaseInformationWorker();
        List<UniversityName> names = db.getUniversitiesNamesList();
        assertTrue(names != null);
        assertTrue(names.size() > 0);

        for (UniversityName name : names) {
            System.out.println(name.fullName + " (" + name.shortName + ")");
        }
    }

    @Test
    public void getUniversityByIdTest() {
        DatabaseInformationWorker db = new DatabaseInformationWorker();
        UniversityName name = db.getUniversityById(1);
        assertNotNull(name);
    }

    @Test
    public void getTimeOfAdmissionByGradeAndIdTest() {
        DatabaseInformationWorker db = new DatabaseInformationWorker();
        String rez = db.getTimeOfAdmissionByGradeAndId(1, StudyGrade.BACHELOR);
        assertNotNull(rez);

        rez = db.getTimeOfAdmissionByGradeAndId(1, StudyGrade.MASTER);
        assertNotNull(rez);

        rez = db.getTimeOfAdmissionByGradeAndId(1, StudyGrade.POST_GRADUATE);
        assertNotNull(rez);
    }
}

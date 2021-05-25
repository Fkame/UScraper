package my.grandwork.webapplicationparser.service.UniversityDataCore;

import org.springframework.ui.Model;

import my.grandwork.UniversitiesParser.Data.enums.InfoTypeClassification;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.webapplicationparser.dao.DatabaseInformationWorker;

public class UniversityDataModelFiller {

    private DatabaseInformationWorker db_worker;
    public UniversityDataModelFiller(DatabaseInformationWorker db_worker) {
        this.db_worker = db_worker;
    }

    public void fillUniverDataByInfoAndGradeType(int id, String infoTypeStr, String gradeTypeStr, Model model) {
        InfoTypeClassification infoType = InfoTypeClassification.valueOf(infoTypeStr);
        StudyGrade studyGrade = StudyGrade.valueOf(gradeTypeStr);
        switch (infoType) {
            case TIME_OF_ADMISSION:
                String data = db_worker.getTimeOfAdmissionByGradeAndId(id, studyGrade);
                model.addAttribute("texts", data.split("\n"));
                model.addAttribute("text", String.class);
                break;
            case COMPETITION_TABLE:
                break;
        }
    }
}

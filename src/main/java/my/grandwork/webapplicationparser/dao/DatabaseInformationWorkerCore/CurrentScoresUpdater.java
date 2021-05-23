package my.grandwork.webapplicationparser.dao.DatabaseInformationWorkerCore;

import java.sql.Connection;
import java.util.List;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.UniversityInfoWrapper;
import my.grandwork.UniversitiesParser.Data.SubWrappers.StudyDirectionInfo;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;

public class CurrentScoresUpdater {
    private Connection connect;
    public CurrentScoresUpdater (Connection connect) {
        this.connect = connect;
    }

    public boolean updateData(UniversityInfoWrapper data) {
        boolean isSuccess = true;
        List<StudyDirectionInfo> dirs = data.directionsInfoList;

        for (StudyDirectionInfo dir : dirs) {
            switch (dir.studyGrades) {
                case BACHELOR: 
                    isSuccess = isSuccess & updateBachelor(dir, data.programNameOfUniversity); 
                    break;
                case MASTER: 
                    isSuccess = isSuccess & updateMaster(dir, data.programNameOfUniversity); 
                    break;
                case POST_GRADUATE: 
                    isSuccess = isSuccess & updatePostGrade(dir, data.programNameOfUniversity); 
                    break;
            }
        }
        return isSuccess;
    }

    private boolean updateBachelor(StudyDirectionInfo direction, University university ) {
        return false;
    }

    private boolean updateMaster(StudyDirectionInfo direction, University university) {
        return false;
    }

    private boolean updatePostGrade(StudyDirectionInfo direction, University university) {
        return false;
    }
}

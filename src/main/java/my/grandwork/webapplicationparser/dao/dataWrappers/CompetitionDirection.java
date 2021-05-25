package my.grandwork.webapplicationparser.dao.dataWrappers;

import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Data.enums.StudyType;

public class CompetitionDirection {
    public String code;
    public String name;
    public String scores;
    public String studyTime;

    public CompetitionDirection (String code, String name, String studyTime, String scores) {
        this.code = code;
        this.name = name;
        this.scores = scores;
        this.studyTime = StudyType.valueOf(studyTime).getTitle();
    }
}

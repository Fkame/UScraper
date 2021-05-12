package my.grandwork.UniversitiesParser.Data.SubWrappers;

import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;

public class IndividualAchievementInfo {
    public String description;
    public Integer givenScores;
    public StudyGrade forWhatGrade;

    public IndividualAchievementInfo() { }

    public IndividualAchievementInfo (String description, Integer givenScores, StudyGrade forWhatGrade) {
        this.description = description;
        this.givenScores = givenScores;
        this.forWhatGrade = forWhatGrade;
    }
}

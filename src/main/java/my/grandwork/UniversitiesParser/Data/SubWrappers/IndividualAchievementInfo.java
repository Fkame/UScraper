package my.grandwork.UniversitiesParser.Data.SubWrappers;

import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;

public class IndividualAchievementInfo {
    public String description;
    public int givenScores;
    public StudyGrade forWhatGrade;

    public IndividualAchievementInfo() { }

    IndividualAchievementInfo (String description, int givenScores, StudyGrade forWhatGrade) {
        this.description = description;
        this.givenScores = givenScores;
        this.forWhatGrade = forWhatGrade;
    }
}

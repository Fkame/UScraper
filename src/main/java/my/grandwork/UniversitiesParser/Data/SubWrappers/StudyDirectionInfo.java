package my.grandwork.UniversitiesParser.Data.SubWrappers;

import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Data.enums.StudyType;

public class StudyDirectionInfo {

    // Код направления
    public String directoryCode;

    // Название направления
    public String nameOfDirectory;

    // Текущий проходной балл (на время конкурса)
    public Integer firstWaveScore;
    public Integer secondWaveScore;

    // Форма обучения (очный и т.д.)
    public StudyType typesOfStudy;

    // Уровень образования (бакалавриат и т.д.)
    public StudyGrade studyGrades;

    // Стоимость обучения
    public Integer costOfEducation;

    // План приёма на бюджет
    public Integer admissionPlanForFree;

    // План приёма на целевое
    public Integer admissionPlanForTarget;

    // План приёма на платное
    public Integer admussionPlanForPrice; 
}

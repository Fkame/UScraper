package my.grandwork.Data.SubWrappers;

import my.grandwork.Data.enums.StudyGrade;
import my.grandwork.Data.enums.StudyType;

public class StudyDirectionInfo {

    // Код направления
    public String directoryCode;

    // Название направления
    public String nameOfDirectory;

    // Короткое название направления
    public String shortNameOfDirectory;

    // Текущий проходной балл (на время конкурса)
    public Integer nowDirectoryScore;

    // Вид обучения (очный и т.д.)
    public StudyType typesOfStudy;

    // Уровень образования (бакалавриат и т.д.)
    public StudyGrade studyGrades;

    // Стоимость обучения
    public Integer costOfEducation;

    // План приёма на бюджет
    public Integer admissionPlanForFree;

    // План приёма общий
    public Integer admissionPlanAll;

    // План приёма на целевое
    public Integer admissionPlanForTarget;

    // План приёма на платное
    public Integer admussionPlanForPrice;

}

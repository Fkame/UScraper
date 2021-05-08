package my.grandwork.Data;

public class StudyDirectionsInformation {

    // Код направления
    public String directoryCode;

    // Название направления
    public String nameOfDirectory;

    // Короткое название направления
    public String shortNameOfDirectory;

    // Текущий проходной балл (на время конкурса)
    public Integer nowDirectoryScore;

    // Вид обучения (очный и т.д.)
    public StudyTypes typesOfStudy;

    // Уровень образования (бакалавриат и т.д.)
    public StudyGrades studyGrades;

    // Стоимость обучения
    public Integer costOfEducation;

    // План приёма на бюджет
    public Integer admissionPlanForFree;

    // План приёма общий
    public Integer admissionPlanAll;
}

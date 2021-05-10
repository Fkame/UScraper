package my.grandwork.UniversitiesParser.Data.enums;

/**
 * Классификация хранимой информации о вузе. 
 * Перечисление нужно для классификации типа информации для .pdf файлах. Чтобы знать какой файл примерно что содержит.
 */
public enum InfoTypeClassification {

    // Конкурсная таблица
    COMPETITION_TABLE("Конкурсная таблица"),

    // Список направлений
    STUDY_DIRECTIONS_LIST("Список образовательных направлений"),

    // План приёма
    ADMISSION_PLAN("План приёма"),

    // Итоги приёмной кампании прошлых лет
    LAST_YEARS_SCORES("Итоги приёмных кампаний прошлых лет"),

    // Сроки приёма
    TIME_OF_ADMISSION("Сроки приёма"),

    // Индивидуальные достижения
    INDIVIDUAL_ACHIEVEMENTS("Индивидуальные достижения"),

    // Список предметов необходимых для направлений
    SUBJECTS_FOR_DIRECTIONS("Список предметов необходимых для направлений"),

    // Про общежитие
    //ABOUT_HOSTEL,

    // Платное обучение
    PAID_EDUCATION("Платное обучение");

    private String title;

    InfoTypeClassification(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

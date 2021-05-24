package my.grandwork.UniversitiesParser.Data.enums;

public enum StudyGrade {

    // Бакалавр
    BACHELOR("Бакалавриват"),

    // Магистр
    MASTER("Магистратура"),

    // Аспирант
    POST_GRADUATE("Аспирантура");

    private String title;

    StudyGrade(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

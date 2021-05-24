package my.grandwork.UniversitiesParser.Data.enums;

public enum StudyType {

    // Очное обучение
    FULL_TIME("Очное"), 

    // Заочное обучение
    PART_TIME("Заочное"), 

    // Очно-заочное обучение
    СOMBINED_TIME("Очно-заочное");

    private String title;

    StudyType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

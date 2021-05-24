package my.grandwork.webapplicationparser.dao.dataWrappers;

public class UniversityName {
    public int id;
    public String fullName;
    public String shortName;

    public UniversityName(int id, String fullName, String shortName) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public UniversityName() { }
}

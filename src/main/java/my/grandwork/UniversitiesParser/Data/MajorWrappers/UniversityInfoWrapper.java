package my.grandwork.UniversitiesParser.Data.MajorWrappers;

import java.util.ArrayList;
import java.util.List;

import my.grandwork.UniversitiesParser.Data.SubWrappers.DocInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.IndividualAchievementInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.StudyDirectionInfo;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;

public class UniversityInfoWrapper {

    // Полное название университета
    public String fullNameOfUniversity;

    // Локальное наименование университета
    public University programNameOfUniversity;

    // Короткое имя университета
    public String shortNameOfUniversity;

    // Информация о направлениях бакалавриата, магистратуры и аспирантуры
    public List<StudyDirectionInfo> directionsInfoList;

    // Информация об индивидуальных достижениях
    public List<IndividualAchievementInfo> individualAchievementsList;

    // Информация о ЕГЭ для направлений бакалавра
    public List<String> subjectsForBachelor;

    // Сроки приёмной кампании
    public String bachelorTimeAdmission;
    public String masterTimeAdmission;
    public String postgradeTimeAdmission;

    // Документы с сайта
    public List<DocInfo> docsList;

    public UniversityInfoWrapper() {
        directionsInfoList = new ArrayList<StudyDirectionInfo>();
        individualAchievementsList = new ArrayList<IndividualAchievementInfo>();
        subjectsForBachelor = new ArrayList<String>();
        docsList = new ArrayList<DocInfo>();
    }
}

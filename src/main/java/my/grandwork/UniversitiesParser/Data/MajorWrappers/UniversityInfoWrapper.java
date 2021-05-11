package my.grandwork.UniversitiesParser.Data.MajorWrappers;

import java.util.ArrayList;
import java.util.List;

import my.grandwork.UniversitiesParser.Data.SubWrappers.AdditionalInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.DocInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.StudyDirectionInfo;

public class UniversityInfoWrapper {

    // Информация о направлениях
    public List<StudyDirectionInfo> directionsInformationList;

    // Информация о вузе
    public AdditionalInfo additionalInfo;

    // Документы с сайта
    public List<DocInfo> docsList;

    public UniversityInfoWrapper() {
        directionsInformationList = new ArrayList<StudyDirectionInfo>();
        docsList = new ArrayList<DocInfo>();
    }
}

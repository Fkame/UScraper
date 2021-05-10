package my.grandwork.UniversitiesParser.Data.MajorWrappers;

import java.util.ArrayList;
import java.util.List;

import my.grandwork.UniversitiesParser.Data.SubWrappers.AdditionalInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.DocInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.StudyDirectionInfo;

public class UniversityInfoWrapper {

    // Информация о направлениях
    public List<StudyDirectionInfo> directionsInformation;

    // Информация о вузе
    public AdditionalInfo additionalInfo;

    // Документы с сайта
    public List<DocInfo> docs;

    public UniversityInfoWrapper() {
        directionsInformation = new ArrayList<StudyDirectionInfo>();
        docs = new ArrayList<DocInfo>();
    }
}

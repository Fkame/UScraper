package my.grandwork.UniversitiesParser.Data.MajorWrappers;

import java.util.ArrayList;

import my.grandwork.UniversitiesParser.Data.SubWrappers.AdditionalInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.DocInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.StudyDirectionInfo;

public class UniversityInfoWrapper {

    // Информация о направлениях
    public ArrayList<StudyDirectionInfo> directionsInformation;

    // Информация о вузе
    public AdditionalInfo additionalInfo;

    // Документы с сайта
    public ArrayList<DocInfo> docs;
}

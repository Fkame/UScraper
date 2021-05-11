package my.grandwork.UniversitiesParser.Data.MajorWrappers;

import java.util.ArrayList;
import java.util.List;

import my.grandwork.UniversitiesParser.Data.SubWrappers.UrlParsingStatus;

public class ParserWorkResult {
    public UniversityInfoWrapper universityInfoWrapper;
    public List<UrlParsingStatus> urlParsingStatusList;

    public ParserWorkResult() {
        urlParsingStatusList = new ArrayList<UrlParsingStatus>();
    }
}

package my.grandwork.UniversitiesParser.Data.SubWrappers;

import java.io.File;

import my.grandwork.UniversitiesParser.Data.enums.InfoTypeClassification;

public class DocInfo {
    public InfoTypeClassification typeOfInfo;
    public File docFile;

    public DocInfo() { }

    public DocInfo(File docfile, InfoTypeClassification typeOfInfo) {
        this.docFile = docfile;
        this.typeOfInfo = typeOfInfo;
    }
}

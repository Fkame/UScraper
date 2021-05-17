package my.grandwork.webapplicationparser.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.enums.InfoTypeClassification;

public class DatabaseInformationWorker {
    
    @Autowired
    @Qualifier("UserDataConnection")
    private Connection userDataConnection;

    @Autowired
    @Qualifier("EditorDataConnection")
    private Connection editorDataConnection;

    @Autowired
    @Qualifier("EditorParserLogConnection")
    private Connection editorParserLogConnection;

    public DatabaseInformationWorker() {

    }

    public void putInfoToDbByCleaning(List<ParserWorkResult> rezList) {

    }

    public void putInfoToDbByAddIfNotExist(List<ParserWorkResult> rezList) {

    }

    public ResultSet getDataByType(InfoTypeClassification infoType) {
        return null;
    }
}

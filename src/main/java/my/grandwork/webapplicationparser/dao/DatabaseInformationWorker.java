package my.grandwork.webapplicationparser.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.MajorWrappers.UniversityInfoWrapper;
import my.grandwork.UniversitiesParser.Data.SubWrappers.DocInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.UrlParsingStatus;
import my.grandwork.UniversitiesParser.Data.enums.InfoTypeClassification;
import my.grandwork.webapplicationparser.configurations.DbConnectionsCreator;
import my.grandwork.webapplicationparser.dao.DatabaseInformationWorkerCore.CurrentScoresUpdater;
import my.grandwork.webapplicationparser.dao.DatabaseInformationWorkerCore.DataSimpleAdder;
import my.grandwork.webapplicationparser.dao.DatabaseInformationWorkerCore.ReportAppender;
import my.grandwork.webapplicationparser.dao.DatabaseInformationWorkerCore.TablesCleaner;

public class DatabaseInformationWorker {
    
    //private Connection userDataConnection;

    //private Connection editorDataConnection;

    //private Connection editorParserLogConnection;

    private DbConnectionsCreator connectGetter;

    public DatabaseInformationWorker() {
        connectGetter = new DbConnectionsCreator();
    }

    public void putInfoToDbByCleaning(List<ParserWorkResult> rezList) { 
        Connection connectData = connectGetter.getEditorDataConnection();
        Connection connectLog = connectGetter.getEditorParserLogConnection();
        
        if (connectData == null | connectLog == null) {
            if (connectData == null)
                System.out.println("Error while appending parser results - connectData == null");
            if (connectLog == null)
                System.out.println("Error while appending parser results - connectLog == null");
            return;
        }

        try {
            TablesCleaner tCleaner = new TablesCleaner(connectData);
            tCleaner.clearAllTables();
        } catch (Exception e) { 
            e.printStackTrace();
            return;
        }

        DataSimpleAdder dataSimpleAdder = new DataSimpleAdder(connectData);
        ReportAppender reportAppender = new ReportAppender(connectLog);

        for (ParserWorkResult parserWorkResult : rezList) {
            if (parserWorkResult == null) continue;
            UniversityInfoWrapper infoWrapper = parserWorkResult.universityInfoWrapper;
            List<UrlParsingStatus> reportList = parserWorkResult.urlParsingStatusList;
            if (infoWrapper == null | reportList == null) continue;

            dataSimpleAdder.addData(infoWrapper);
            reportAppender.appendData(reportList, infoWrapper.programNameOfUniversity);
        }

        try {
            connectData.close();
            connectLog.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void putCompetitionTableToDbByCleaning(List<ParserWorkResult> rezList) { 
        Connection connectData = connectGetter.getUserDataConnection();
        Connection connectLog = connectGetter.getEditorParserLogConnection();
        
        if (connectData == null | connectLog == null) {
            if (connectData == null)
                System.out.println("Error while appending parser results - connectData == null");
            if (connectLog == null)
                System.out.println("Error while appending parser results - connectLog == null");
            return;
        }

        CurrentScoresUpdater scoresUpdater = new CurrentScoresUpdater(connectData);
        ReportAppender reportAppender = new ReportAppender(connectLog);

        for (ParserWorkResult parserWorkResult : rezList) {
            UniversityInfoWrapper infoWrapper = parserWorkResult.universityInfoWrapper;
            List<UrlParsingStatus> reportList = parserWorkResult.urlParsingStatusList;

            scoresUpdater.updateData(infoWrapper);
            reportAppender.appendData(reportList, infoWrapper.programNameOfUniversity);
        }

        try {
            connectData.close();
            connectLog.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getDataByType(InfoTypeClassification infoType) {
        return null;
    }

    public void deleteParsedFiles(List<DocInfo> fileList) {

    }


}

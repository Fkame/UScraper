package my.grandwork.webapplicationparser.dao.DatabaseInformationWorkerCore;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import my.grandwork.UniversitiesParser.Data.SubWrappers.UrlParsingStatus;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;

public class ReportAppender {

    private String insertTemplate = "insert into url_parsing_report" + 
                                "(prog_university_name, url, is_success, commentary, exception_text, webdriver_exception_text)" + 
                                "values('?','?','?','?','?','?')";
                                
    private Connection connect;
    public ReportAppender(Connection connect) {
        this.connect = connect;
    }

    public void appendData(List<UrlParsingStatus> dataList, University university) {
        for (UrlParsingStatus urlParsingStatus : dataList) {
            appendData(urlParsingStatus, university);
        }
    }

    public void appendData(UrlParsingStatus data, University university) {
        String exceptionText = this.convertStackTraceToStr(data.exception);
        String webExceptionText = this.convertStackTraceToStr(data.webDriverException);
        
        try (PreparedStatement stat = connect.prepareStatement(insertTemplate)) {
            stat.setString(1, university.toString());
            stat.setString(2, data.url);
            stat.setBoolean(3, data.isSuccess);
            stat.setString(4, data.specifyDetails);
            stat.setString(5, exceptionText);
            stat.setString(6, webExceptionText);  
            
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    public String convertStackTraceToStr(Exception e) {
        if (e == null) return null;
        
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String rez = sw.toString();
        try {
            pw.close();
        } catch (Exception ee) { }
        return rez;
    }
}

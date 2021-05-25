package my.grandwork.webapplicationparser.dao.DatabaseInformationWorkerCore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.webapplicationparser.dao.dataWrappers.UniversityName;

public class DataGetter {
    
    private Connection connect;
    public DataGetter(Connection connect) {
        this.connect = connect;
    }

    public List<UniversityName> getUniversitiesNamesList() {
        String sql = "SELECT distinct id_university, full_name, short_name from university";
        ResultSet rez = null;
        List<UniversityName> list = new ArrayList<UniversityName>();
        try (Statement stat = connect.createStatement()) {
            rez = stat.executeQuery(sql);
            while (rez.next()) {
                list.add(new UniversityName(rez.getInt(1), rez.getString(2), rez.getString(3)));
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }

        return list;
    }

    public UniversityName getUniversityById(int id) {
        UniversityName un = null;
        String sql = "SELECT full_name, short_name from university where id_university=?";
        try (PreparedStatement stat = connect.prepareStatement(sql)) {
            stat.setInt(1, id);
            ResultSet rez = stat.executeQuery();

            while (rez.next()) {
                un = new UniversityName(id, rez.getString(1), rez.getString(2));
            }
        } catch (SQLException e) { 
            e.printStackTrace();
        }
        
        return un;
    }

    public String getTimeOfAdmissionByGradeAndId(int idUniversity, StudyGrade grade) {
        String sql = null;
        switch (grade) {
            case BACHELOR:
                sql = "SELECT bachelor_time_admission FROM university where id_university=?";
                break;
            case MASTER:
                sql = "SELECT master_time_admission FROM university where id_university=?";
                break;
            case POST_GRADUATE:
                sql = "SELECT postgrade_time_admission FROM university where id_university=?";
                break;
            default: return null;
        }

        try (PreparedStatement stat = connect.prepareStatement(sql)) {
            stat.setInt(1, idUniversity);
            ResultSet rez = stat.executeQuery();

            rez.next();
            return rez.getString(1);
            
        } catch (SQLException e) { 
            e.printStackTrace();
        }

        return null;
    }
}

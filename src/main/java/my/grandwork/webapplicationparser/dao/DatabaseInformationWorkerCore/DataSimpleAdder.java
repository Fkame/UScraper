package my.grandwork.webapplicationparser.dao.DatabaseInformationWorkerCore;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.UniversityInfoWrapper;
import my.grandwork.UniversitiesParser.Data.SubWrappers.DocInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.StudyDirectionInfo;
import my.grandwork.UniversitiesParser.Data.SubWrappers.SubjectForBachelor;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;

public class DataSimpleAdder {
    
    private Connection connect;
    public DataSimpleAdder(Connection connect) {
        this.connect = connect;
    }

    public boolean addData(UniversityInfoWrapper data) {
        boolean isSuccess = true;
        isSuccess = isSuccess & fillUniversity(data);
        isSuccess = isSuccess & fillUniversityFiles(data);
        isSuccess = isSuccess & fillDirections(data);  
        isSuccess = isSuccess & fillGradesDirectories(data);
        return isSuccess;
    }

    private boolean fillUniversity (UniversityInfoWrapper data) {
        String sql = "insert into university (full_name, short_name, prog_name, bachelog_time_admisstion," +  
                        "master_time_admisstion, postgrade_time_admisstion) values ('?', '?', '?', '?', '?', '?')";
        try (PreparedStatement stat = connect.prepareStatement(sql)) {
            stat.setString(1, data.fullNameOfUniversity);
            stat.setString(2, data.shortNameOfUniversity);
            stat.setString(3, data.programNameOfUniversity.toString());
            stat.setString(4, data.bachelorTimeAdmission);
            stat.setString(5, data.masterTimeAdmission);
            stat.setString(6, data.postgradeTimeAdmission);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean fillUniversityFiles(UniversityInfoWrapper data) {
        String sql = "insert into university_file (id_university, type_of_save_info, file_name, file_data) " +
                        "values ('?', '?', '?', '?')";

        int id = getIdUniversityByProgName(data.programNameOfUniversity);
        if (id == -1) return false;

        List<DocInfo> docsList = data.docsList;

        for (DocInfo docInfo : docsList) {
            try (FileInputStream fileStream = new FileInputStream(docInfo.docFile);) {
                try (PreparedStatement stat = connect.prepareStatement(sql)) {
                    stat.setInt(1, id);
                    stat.setString(2, docInfo.typeOfInfo.toString());
                    stat.setString(3, docInfo.docFile.getName());
                    stat.setBlob(4, fileStream);
                    stat.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            } catch (IOException ex) {  return false; }  
        }
        
        return true;
    }

    private boolean fillDirections(UniversityInfoWrapper data) {
        String sql = "insert into studyDirection (id_direction, direction_name) values ('?', '?')";

        List<StudyDirectionInfo> dirs = data.directionsInfoList;
        for (StudyDirectionInfo dir : dirs) {
            try (PreparedStatement stat = connect.prepareStatement(sql)) {
                stat.setString(1, dir.directoryCode);
                stat.setString(2, dir.nameOfDirectory);
                stat.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private boolean fillGradesDirectories(UniversityInfoWrapper data) {
        List<StudyDirectionInfo> dirs = data.directionsInfoList;
        boolean isSuccess = true;

        for (StudyDirectionInfo dir : dirs) {
            switch (dir.studyGrades) {
                case BACHELOR: 
                    isSuccess = isSuccess & addToBachelor(dir, data.programNameOfUniversity); 
                    break;
                case MASTER: 
                    isSuccess = isSuccess & addToMaster(dir, data.programNameOfUniversity); 
                    break;
                case POST_GRADUATE: 
                    isSuccess = isSuccess & addToPostGrade(dir, data.programNameOfUniversity); 
                    break;
            }
        }
        return isSuccess;
    }

    private boolean addToBachelor(StudyDirectionInfo dir, University university) {
        String sql = "insert into bachelor_direction (id_university, id_direction, studytime_type, " + 
                        "firstwave_score, secondwave_score, admissionplan_for_free, admissionplan_for_target) " +
                        "admissionplan_for_price, cost_of_education " +
                        "values ('?', '?', '?', '?', '?', '?', '?', '?', '?')";

        try (PreparedStatement stat = connect.prepareStatement(sql)) {
            stat.setInt(1, this.getIdUniversityByProgName(university));
            stat.setString(2, dir.directoryCode);
            stat.setString(3, dir.typesOfStudy.toString());
            stat.setInt(4, dir.firstWaveScoreOrGeneralScore);
            stat.setInt(5, dir.secondWaveScore);
            stat.setInt(6, dir.admissionPlanForFree);
            stat.setInt(7, dir.admissionPlanForTarget);
            stat.setInt(8, dir.admussionPlanForPrice);
            stat.setInt(9, dir.costOfEducation);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;  
    }

    private boolean addToMaster(StudyDirectionInfo dir, University university) {
        String sql = "insert into master_direction (id_university, id_direction, studytime_type, " + 
                        "now_score, admissionplan_for_free, admissionplan_for_target) " +
                        "admissionplan_for_price, cost_of_education " +
                        "values ('?', '?', '?', '?', '?', '?', '?', '?')";
        try (PreparedStatement stat = connect.prepareStatement(sql)) {
            stat.setInt(1, this.getIdUniversityByProgName(university));
            stat.setString(2, dir.directoryCode);
            stat.setString(3, dir.typesOfStudy.toString());
            stat.setInt(4, dir.firstWaveScoreOrGeneralScore);
            stat.setInt(5, dir.admissionPlanForFree);
            stat.setInt(6, dir.admissionPlanForTarget);
            stat.setInt(7, dir.admussionPlanForPrice);
            stat.setInt(8, dir.costOfEducation);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;  
    }

    private boolean addToPostGrade(StudyDirectionInfo dir, University university) {
        String sql = "insert into postgrade (id_university, id_direction, studytime_type, " + 
                        "now_score, admissionplan_for_free, admissionplan_for_target) " +
                        "admissionplan_for_price, cost_of_education " +
                        "values ('?', '?', '?', '?', '?', '?', '?', '?')";
        try (PreparedStatement stat = connect.prepareStatement(sql)) {
            stat.setInt(1, this.getIdUniversityByProgName(university));
            stat.setString(2, dir.directoryCode);
            stat.setString(3, dir.typesOfStudy.toString());
            stat.setInt(4, dir.firstWaveScoreOrGeneralScore);
            stat.setInt(5, dir.admissionPlanForFree);
            stat.setInt(6, dir.admissionPlanForTarget);
            stat.setInt(7, dir.admussionPlanForPrice);
            stat.setInt(8, dir.costOfEducation);
            stat.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;  
    }

    private boolean fillSubjects(UniversityInfoWrapper data) {
        String sql = "insert into subject_for_bachelor_direction(subject_name, id_university, id_direction, studytime_type, " +
                        "min_required_score) values('?', '?', '?', '?', '?')";
        List<SubjectForBachelor>subjects = data.subjectsForBachelor;
        for (SubjectForBachelor s : subjects) {
            try (PreparedStatement stat = connect.prepareStatement(sql)) {
                stat.setString(1, s.subjectName);
                stat.setInt(2, getIdUniversityByProgName(data.programNameOfUniversity));
                stat.setString(3, s.direction.directoryCode);
                stat.setString(4, s.direction.typesOfStudy.toString());
                stat.setInt(5, s.minRequiredScore);
                stat.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    
    private int getIdUniversityByProgName(University university) {
        String sql = "select id_university from university where prog_name='?'";
        int id = -1;

        try (PreparedStatement stat = connect.prepareStatement(sql)) {
            stat.setString(1, university.toString());
            ResultSet resSet = stat.executeQuery();
            resSet.next();
            id = resSet.getInt(1);
            resSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }

    private int getIdSubjectByName(String subject) {
        String sql = "select id_subject from school_exams_subject where subject_name='?'";
        int id = -1;

        try (PreparedStatement stat = connect.prepareStatement(sql)) {
            stat.setString(1, subject);
            ResultSet resSet = stat.executeQuery();
            resSet.next();
            id = resSet.getInt(1);
            resSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return id;
    }
}

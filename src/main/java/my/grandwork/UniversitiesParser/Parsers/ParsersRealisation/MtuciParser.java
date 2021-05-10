package my.grandwork.UniversitiesParser.Parsers.ParsersRealisation;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import my.grandwork.UniversitiesParser.Data.SubWrappers.StudyDirectionInfo;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserSupport.MtuciParserCore;


public class MtuciParser {
    
    WebDriver driver;

    public MtuciParser(WebDriver driver) {
        this.driver = driver;
    }

    public ArrayList<StudyDirectionInfo> getDirectionsInfo() {
        ArrayList<StudyDirectionInfo> directories = new ArrayList<StudyDirectionInfo>();

        // Парсим названия направлений для магистратуры, бакалавра, аспирантуры
        driver.get("https://abitur.mtuci.ru/#!livetable");
        try { Thread.sleep(1000);} catch (Exception e) {}
        directories.addAll(MtuciParserCore.fillDirectionsInfo(driver, StudyGrade.BACHELOR));
        
        driver.get("https://abitur.mtuci.ru/#!magistratura_livetable");
        try { Thread.sleep(1000);} catch (Exception e) {}
        directories.addAll(MtuciParserCore.fillDirectionsInfo(driver, StudyGrade.MASTER)); 

        driver.get("https://abitur.mtuci.ru/#!aspirantura_livetable");
        try { Thread.sleep(1000);} catch (Exception e) {}
        
        directories.addAll(MtuciParserCore.fillDirectionsInfo(driver, StudyGrade.POST_GRADUATE));

      
        this.printAllData1(directories);

        // Добавляем короткие имена к направлениям
        return directories;
    }

    public void getLastYearScores() {

    }

    public void getPastYearsScores() {

    }

    // Сроки приёмной кампании
    public String getTermsOfAdmission() {
        String parsePage = "https://abitur.mtuci.ru/#!sroki_bakalavriata";

        return null;
    }

    public void printAllData1(ArrayList<StudyDirectionInfo> list) {
        for (StudyDirectionInfo info : list) {
            String output = info.directoryCode + " | " + 
                            info.nameOfDirectory + " | " + 
                            info.admissionPlanForFree + " | " +
                            info.typesOfStudy.toString() + " | " + 
                            info.studyGrades.name();
            System.out.println(output);
        }
    }
}

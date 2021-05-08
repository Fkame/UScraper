package my.grandwork.Parsers;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import my.grandwork.Data.StudyDirectionsInformation;
import my.grandwork.Data.StudyGrades;


public class MtuciParser {
    
    WebDriver driver;

    public MtuciParser(WebDriver driver) {
        this.driver = driver;
    }

    public void ParseFromConfigFiles() {
        //driver.quit();
    }

    public ArrayList<StudyDirectionsInformation> getDirectionsInfo() {
        ArrayList<StudyDirectionsInformation> directories = new ArrayList<StudyDirectionsInformation>();

        // Парсим названия направлений для магистратуры, бакалавра, аспирантуры
        driver.get("https://abitur.mtuci.ru/#!livetable");
        try { Thread.sleep(1000);} catch (Exception e) {}
        directories.addAll(MtuciParserCore.fillDirectionsInfo(driver, StudyGrades.BACHELOR));
        
        driver.get("https://abitur.mtuci.ru/#!magistratura_livetable");
        try { Thread.sleep(1000);} catch (Exception e) {}
        directories.addAll(MtuciParserCore.fillDirectionsInfo(driver, StudyGrades.MASTER)); 

        driver.get("https://abitur.mtuci.ru/#!aspirantura_livetable");
        try { Thread.sleep(1000);} catch (Exception e) {}
        
        directories.addAll(MtuciParserCore.fillDirectionsInfo(driver, StudyGrades.POST_GRADUATE));

      
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

    public void printAllData1(ArrayList<StudyDirectionsInformation> list) {
        for (StudyDirectionsInformation info : list) {
            String output = info.directoryCode + " | " + 
                            info.nameOfDirectory + " | " + 
                            info.admissionPlanForFree + " | " +
                            info.typesOfStudy.toString() + " | " + 
                            info.studyGrades.name();
            System.out.println(output);
        }
    }
}

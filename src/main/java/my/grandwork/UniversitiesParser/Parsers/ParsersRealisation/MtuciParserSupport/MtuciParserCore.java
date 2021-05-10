package my.grandwork.UniversitiesParser.Parsers.ParsersRealisation.MtuciParserSupport;

import org.openqa.selenium.WebDriver;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;

public class MtuciParserCore {

    /*
    public static ArrayList<StudyDirectionInfo> fillDirectionsInfo(WebDriver driver, StudyGrade grade) {
        ArrayList<StudyDirectionInfo> list = new ArrayList<StudyDirectionInfo>();

        List<WebElement> headers = driver.findElements(By.cssSelector("#divbody h3"));
        List<WebElement> tables = driver.findElements(By.cssSelector("#divbody table"));

        // 3 таблицы максимум: очно-заочно-очнозаочно, в каждой лежат направления
        for (int table = 0; table < tables.size(); table++)
        {
            StudyType tableTypes = StudyType.FULL_TIME;
            String studyTypeText = null;
            if (headers.size() == 0) studyTypeText = "очная";
            else if (tables.size() > headers.size()) {     
                for (int header = headers.size() - 1; header > -1; header--)
                {
                    Point headerLoc = headers.get(header).getLocation();
                    Point tableLoc = tables.get(table).getLocation();
                    if (headerLoc.y < tableLoc.y) {
                        studyTypeText = headers.get(header).getText();
                        break;
                    }
                }        
            }
            else {
                studyTypeText = headers.get(table).getText();
            }
            if (studyTypeText.toLowerCase().contains("очная")) tableTypes = StudyType.FULL_TIME;
            if (studyTypeText.toLowerCase().contains("заочная")) tableTypes = StudyType.PART_TIME;
            if (studyTypeText.toLowerCase().contains("очно-заочная")) tableTypes = StudyType.СOMBINED_TIME;
            
            // В каждой таблице есть строки
            List<WebElement> rows = tables.get(table).findElements(By.tagName("tr"));
           
            // Строку-заголовок
            if (rows.get(0).findElements(By.tagName("th")).size() != 0 ) rows.remove(0);
            for (int row = 0; row < rows.size(); row++)
            {
                List<WebElement> cells = rows.get(row).findElements(By.tagName("td"));
                if (cells.size() == 0) continue;
                
                StudyDirectionInfo info = new StudyDirectionInfo();
                info.typesOfStudy = tableTypes;
                info.studyGrades = grade;

                // Интересуют только первые 3 ячейки
                for (int cell = 0; cell < 3; cell++)
                {
                    switch (cell + 1)
                    {
                        case 1: info.nameOfDirectory = cells.get(cell).getText();
                            break;
                        case 2: info.directoryCode = cells.get(cell).getText();
                            break;
                        case 3: try { info.admissionPlanForFree = Integer.parseInt(cells.get(cell).getText()); }
                            catch (Exception e) { 
                                System.out.println(String.format("site=%s, table=%d, row=%d, cell=%d, valueCell=%s", driver.getCurrentUrl(), table, row, cell, cells.get(cell).getText()));
                            }
                            break;
                    }
                }
                list.add(info);
            }  
        }
        return list;
    }

    public static void fillPlanAndCostInfo(WebDriver driver, ArrayList<StudyDirectionInfo> list) {

    }

    public static void getLastYearScores() {

    }
    */
    
    public static void parseBachelorScores(WebDriver driver, ParserWorkResult result) {
        driver.get("https://abitur.mtuci.ru/#!livetable"); 
    }

    public static void parseMasterScores(WebDriver driver, ParserWorkResult result) {
        driver.get("https://abitur.mtuci.ru/#!magistratura_livetable");
    }

    public static void parsePostGraduatedScores(WebDriver driver, ParserWorkResult result) {
        driver.get("https://abitur.mtuci.ru/#!aspirantura_livetable");
    }
}



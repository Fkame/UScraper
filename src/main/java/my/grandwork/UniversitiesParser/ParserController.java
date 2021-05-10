package my.grandwork.UniversitiesParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;

public class ParserController {
    
    public boolean useMultiThreads = false;
    public ParserController() { }

    /**
     * Собирает все данные обо всех поддерживаемых вузах.
     * @return
     */
    public ArrayList<ParserWorkResult> parseAllUniversitiesInfo() {
        ArrayList<ParserWorkResult> list = new ArrayList<ParserWorkResult>();

        List<University> univers = Arrays.asList(University.values());
        for (University university : univers) {
            list.add(this.parseTargetUniversityInfo(university));
        }

        return list;
    }

    /**
     * Собирает все данные о конкретном вузе.
     * @param university Локальное именование вуза в системе.
     * @return 
     */
    public ParserWorkResult parseTargetUniversityInfo(University university) {
        ParserWorkResult wrapper = new ParserWorkResult();

        return wrapper;
    }

     /**
     * Собирает данные из конкурсной таблице о текущих баллах из всех поддерживаемых университетов.
     * @return 
     */
    public List<ParserWorkResult> parseCurrentScoresForAllGradesInAllUniversities() {
        return null;
    }

    /**
     * Собирает данные из конкурсной таблицы о текущих баллах лишь об одном уровне образования из всех поддерживаемых университетов.
     * @param grade Уровень образования (магистр, бакалавр, аспирант).
     * @return
     */
    public List<ParserWorkResult> parseCurrentScoresForTargetGradeInAllUniversities(StudyGrade grade) {
        return null;
    }

    /**
     * Собирает данные из конкурсной таблицы о текущих баллах в указанном университете
     * @param university Локальное именование вуза в системе.
     * @return 
     */
    public ParserWorkResult parseCurrentScoresForAllGradesInTargetUniversity(University university) {
        return null;
    }

    /**
     * Собирает данные из конкурсной таблицы о текущих баллах указанног уровня в указанном университете
     * @param university  Локальное именование вуза в системе.
     * @param grade Уровень образования (магистр, бакалавр, аспирант).
     * @return
     */
    public ParserWorkResult parseCurrentScoresForTargetGradeInTargetUniversity(University university, StudyGrade grade) {
        return null;
    }


}

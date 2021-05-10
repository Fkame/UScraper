package my.grandwork.UniversitiesParser.Parsers;

import java.util.List;

import my.grandwork.UniversitiesParser.Data.MajorWrappers.ParserWorkResult;
import my.grandwork.UniversitiesParser.Data.enums.StudyGrade;
import my.grandwork.UniversitiesParser.Parsers.emuns.University;

/** Контракт для всех парсеров */
public interface IUniversaryParser {
    
    /**
     * Сбор всех данных о вузе.
     * @return Обёртка, содержащая запрашиваемую информацию и отчёт о ходе парсинга.
     */
    ParserWorkResult parseAllUniversityInfo();

    /**
     * Сбор Конкурсной таблицы для всех Уровней обучения.
     * @return Обёртка, содержащая запрашиваемую информацию и отчёт о ходе парсинга.
     */
    ParserWorkResult parseCurrentScoresForAllGradesInfo();

    /**
     * Сбор Конкурсной таблицы для конкретного Уровня образования.
     * @param grade Уровень образования.
     * @return Обёртка, содержащая запрашиваемую информацию и отчёт о ходе парсинга.
     */
    ParserWorkResult parseCurrentScoresForTargetGradeInfo(StudyGrade grade);

    /**
     * Сбор всех данных, кроме конкурсной таблицы
     * @param grade Уровень образования.
     * @return Обёртка, содержащая запрашиваемую информацию и отчёт о ходе парсинга.
     */
    ParserWorkResult parseAdditionaDataAndPdfs(StudyGrade grade);
   
}

package my.grandwork.webapplicationparser.configurators;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.grandwork.webapplicationparser.controllers.notMVC.ParserTimeCaller;
import my.grandwork.webapplicationparser.dao.DatabaseInformationWorker;

@Configuration
public class AppConfig {

    @Bean
    public DatabaseInformationWorker databaseInformationWorker() {
        return new DatabaseInformationWorker();
    }

    @Bean 
    public ParserTimeCaller parserTimeCaller() {
        return new ParserTimeCaller();
    }
}

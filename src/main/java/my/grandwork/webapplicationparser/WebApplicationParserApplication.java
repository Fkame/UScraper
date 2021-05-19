package my.grandwork.webapplicationparser;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import my.grandwork.webapplicationparser.dao.SupportUtil.ConnectionsTester;
import my.grandwork.webapplicationparser.dao.db_info.MySqlDatabaseInfo;

@SpringBootApplication
public class WebApplicationParserApplication {

	public static void main(String[] args) {

		// Пробуем подгрузить коннектор для driver manager и проверить доступ к базам данных
		// Если не получается, программу запускать смысла нет.

		try {
			Class.forName(MySqlDatabaseInfo.DriverClass).getDeclaredConstructor().newInstance();
			ConnectionsTester.TestConnections();
		} catch (Exception e) {
			System.out.println(String.format("Error when trying to load database connector - [%s]", MySqlDatabaseInfo.DriverClass));
			e.printStackTrace();

			return;
		}

		try {
			ConnectionsTester.TestConnections();
		} catch (SQLException e) {
			System.out.println(String.format("Error when checking connections from MySqlDatabaseInfo by driver - [%s]", 
				MySqlDatabaseInfo.DriverClass));
			e.printStackTrace();

			return;
		}

		// Запуск настроек Spring Boot
		SpringApplication.run(WebApplicationParserApplication.class, args);
	}

}

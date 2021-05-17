package my.grandwork.webapplicationparser.TestCodesFragments;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import my.grandwork.webapplicationparser.dao.db_info.MySqlDatabaseInfo;

public class JConnectorTest {

    public boolean createDriverInstance(String driverClass) {
        try {
            Class.forName(MySqlDatabaseInfo.DriverClass).getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Connection getDriverConnection() {
        String url = MySqlDatabaseInfo.ConnectionUrlToDb1;
        String user = MySqlDatabaseInfo.Name1;
        String password = MySqlDatabaseInfo.Password1;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean testConnection() throws Exception {

        // Runtime загрузка драйвера
        if (!this.createDriverInstance(MySqlDatabaseInfo.DriverClass)) {
            throw new Exception("Can not find driver with class name " + MySqlDatabaseInfo.DriverClass);
        }

        // Получение подключения
        try {
            Connection connect = this.getDriverConnection();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

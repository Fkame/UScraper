package my.grandwork.webapplicationparser.dao.SupportUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import my.grandwork.webapplicationparser.dao.db_info.MySqlDatabaseInfo;

public class ConnectionsTester {
    public static void TestConnections() throws SQLException {

        String url = null;
        String user = null;
        String password = null;
        Connection connection = null;
        try {
            url = MySqlDatabaseInfo.ConnectionUrlToDb1;
            user = MySqlDatabaseInfo.Name1;
            password = MySqlDatabaseInfo.Password1;
            connection = DriverManager.getConnection(url, user, password);
            connection.close();

            url = MySqlDatabaseInfo.ConnectionUrlToDb2;
            user = MySqlDatabaseInfo.Name2;
            password = MySqlDatabaseInfo.Password2;
            connection = DriverManager.getConnection(url, user, password);
            connection.close();

            url = MySqlDatabaseInfo.ConnectionUrlToDb3;
            user = MySqlDatabaseInfo.Name3;
            password = MySqlDatabaseInfo.Password3;
            connection = DriverManager.getConnection(url, user, password);
            connection.close();
        } catch (SQLException e) {
            throw e;
        }
        finally {
            if (connection != null) connection.close();
        }
    }
}

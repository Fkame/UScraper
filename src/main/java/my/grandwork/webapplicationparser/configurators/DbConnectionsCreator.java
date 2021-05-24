package my.grandwork.webapplicationparser.configurators;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import my.grandwork.webapplicationparser.dao.db_info.MySqlDatabaseInfo;

public class DbConnectionsCreator {
    
    public DbConnectionsCreator() {
        
    }

    public Connection getUserDataConnection() {
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

    public Connection getEditorDataConnection() {
        String url = MySqlDatabaseInfo.ConnectionUrlToDb2;
        String user = MySqlDatabaseInfo.Name2;
        String password = MySqlDatabaseInfo.Password2;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Connection getEditorParserLogConnection() {
        String url = MySqlDatabaseInfo.ConnectionUrlToDb3;
        String user = MySqlDatabaseInfo.Name3;
        String password = MySqlDatabaseInfo.Password3;

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

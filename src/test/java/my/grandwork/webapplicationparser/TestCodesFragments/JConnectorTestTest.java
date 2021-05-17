package my.grandwork.webapplicationparser.TestCodesFragments;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import my.grandwork.webapplicationparser.dao.db_info.MySqlDatabaseInfo;

@SpringBootTest
public class JConnectorTestTest {

    @Test
    public void UseConnect() {
        JConnectorTest testsql = new JConnectorTest();
		try {
			if (testsql.testConnection()) {
                System.out.println("Поздравляю, вы пидор!\nА ещё база данных, кажется работает!!");
                return;
            }	
		}
		catch (Exception e) { }
        finally {
            
        }
		
        System.out.println("Connection is Failed");
    }

    @Test 
    public void testStatementSelect() {
        JConnectorTest testsql = new JConnectorTest();
        testsql.createDriverInstance(MySqlDatabaseInfo.DriverClass);
        Connection c = testsql.getDriverConnection();

        Statement statement = null;
        ResultSet rez = null;

        try {
            statement = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rez = statement.executeQuery("Select * from Users;");
            rez.beforeFirst();
            while (rez.next()) {
                int id = rez.getInt("id_user");
                String name = rez.getString("name");
                String email = rez.getString("email");
                String pass = rez.getString("password");
                System.out.println(String.format("%d | %s, %s, %s", id, name, email, pass));
            }

        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        finally {
            if (rez != null) {
                try {
                    rez.close();
                } catch (SQLException sqlEx) { } // ignore
        
                rez = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqlEx) { } // ignore
        
                statement = null;
            }
        }

    }

    @Test 
    public void testStatementShowTables() throws Exception{
        JConnectorTest testsql = new JConnectorTest();
        testsql.createDriverInstance(MySqlDatabaseInfo.DriverClass);
        Connection c = testsql.getDriverConnection();

        if (c == null) throw new Exception("Dies from cringe (and connection == null)!");

        Statement statement = null;
        ResultSet rez = null;

        try {
            statement = c.createStatement();
            String querty = "show tables;";
            rez = statement.executeQuery(querty);

            while (rez.next()) {
                // Колонки начинаются с 1
                String output1 = rez.getString(1);
                System.out.println("[0]=" + output1);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        finally {
            if (rez != null) {
                try {
                    rez.close();
                } catch (SQLException sqlEx) { } // ignore
        
                rez = null;
            }

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqlEx) { } // ignore
        
                statement = null;
            }

            c.close();
        }
    }
}

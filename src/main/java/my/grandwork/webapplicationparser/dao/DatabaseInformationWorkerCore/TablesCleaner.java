package my.grandwork.webapplicationparser.dao.DatabaseInformationWorkerCore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TablesCleaner {

    private Connection connect;
    public TablesCleaner(Connection connect) {
        this.connect = connect;
    } 

    public boolean clearAllTables() throws Exception {
        List<String> tables = this.getTablesNames();

        boolean noErrors = true;
        for (String table : tables) {
            try {
                clearTargetTable(table);
            } catch (Exception e) {
                e.printStackTrace();
                noErrors = false;
            }
        }

        return noErrors;
    }

    public void clearTargetTable(String tableName) throws Exception {
        this.disableForeignKeyCheck();
        Statement stat = connect.createStatement();
        stat.executeUpdate("truncate table " + tableName);
        stat.close();

        this.enableForeignKeyCheck();
    }

    public List<String> getTablesNames() throws Exception {
        String getAllTablesNames = "show tables";
        ArrayList<String> tablesList = new ArrayList<String>();

        Statement stat = connect.createStatement();
        ResultSet rs = stat.executeQuery(getAllTablesNames);
        
        while (rs.next()) {
            tablesList.add(rs.getString(1));
        }

        rs.close();
        stat.close();

        return tablesList;
    }

    public void disableForeignKeyCheck() throws Exception {
        String sql = "SET FOREIGN_KEY_CHECKS = 0";
        Statement stat = connect.createStatement();
        stat.executeUpdate(sql);
        stat.close();
    }

    public void enableForeignKeyCheck() throws Exception {
        String sql = "SET FOREIGN_KEY_CHECKS = 1";
        Statement stat = connect.createStatement();
        stat.executeUpdate(sql);
        stat.close();
    }
    
}

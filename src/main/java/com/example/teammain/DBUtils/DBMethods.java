package com.example.teammain.DBUtils;

import java.sql.*;

public class DBMethods {
    public static boolean insert() {
        try (Connection conn = DriverManager.getConnection(DBData.URL);   // For MySQL only
             Statement stmt = conn.createStatement();) {
             String Query = "";
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static DBPlayer find(int id) {
        return new DBPlayer(23, "gdf", "dfd", "sdfs");
    }

}

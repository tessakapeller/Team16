package com.example.teammain.DBUtils;

import com.example.teammain.Player;

import java.sql.*;

public class DBMethods {
    public static boolean insert(Player player) {
        try (Connection conn = DriverManager.getConnection(DBData.URL);
             Statement stmt = conn.createStatement();) {
             String sql = "INSERT into player (id,first_name,last_name,codename) ";
             sql += "VALUES ('" + player.idProperty().getValue() + "', ";
             sql += "'" + player.firstNameProperty().getValue() + "', ";
             sql += "'" + player.lastNameProperty().getValue() + "', ";
             sql += "'" + player.codeNameProperty().getValue() + "');";
             stmt.executeUpdate(sql);
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    public static Player find(String id) {
        Player temp = new Player(id);
        try (Connection conn = DriverManager.getConnection(DBData.URL);
             Statement stmt = conn.createStatement();) {
             String sql = "SELECT * FROM player WHERE id = '" + id + "';";
             ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                temp.setFirstName(rs.getString("first_name"));
                temp.setLastName(rs.getString("last_name"));
                temp.setCodeName(rs.getString("codename"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return temp;
    }

}

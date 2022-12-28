package com.peaksoft;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws SQLException {
            creatTable();
    }
    public static void creatTable(){
        String SQL= " CREATE TABLE IF NOT EXISTS city ("+
                "id SERIAL PRIMARY KEY,"+
                "name VARCHAR(50));";
        try(Connection conn = DB.connection();
                Statement statement = conn.createStatement()){
            statement.executeUpdate(SQL);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}

package com.peaksoft;
import java.sql.*;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws SQLException {
//        creatTableCountry();
//        creatTableCity();
//        creatTableMayer();
//        addCountry("Kyrgyzstan",1);
//        addCountry("Kazakhstan",2);
//        addCountry("GreatBritain",3);
//        addCountry("Russian",4);
//        addCountry("USA",5);
//        addCity(1,"NewYork",5);
//        addCity(2,"Moscow",4);
//        addCity(3,"London",3);
//        addCity(4,"Nursultan",2);
//        addCity(5,"Bishkek",1);
//        addMayer(1,"Эмилбек Абдыкадыров",5);
//        addMayer(2,"Женис Касымбеков",4);
//        addMayer(3,"Садик Хан",3);
//        addMayer(4,"Сергей Собянин",2);
//        addMayer(5,"Эрик Адамс",1);
//        addCityToArrayList();
//        addCountryToArrayList();
        getCityByid(5);
    }

    public static void creatTableCity() {
        String SQL = " CREATE TABLE IF NOT EXISTS city (" +
                "id INTEGER PRIMARY KEY ," +
                "name VARCHAR(50)," +
                "country_id INTEGER REFERENCES country(id) NOT NULL)";
        try (Connection conn = DB.connection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void creatTableCountry() {
        String SQL = " CREATE TABLE IF NOT EXISTS country (" +
                "id INTEGER PRIMARY KEY," +
                "name VARCHAR(50))";
        try (Connection conn = DB.connection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void creatTableMayer() {
        String SQL = " CREATE TABLE IF NOT EXISTS mayer (" +
                "id INTEGER," +
                "name VARCHAR(50)," +
                "city_id INTEGER REFERENCES city(id)NOT NULL )";
        try (Connection conn = DB.connection();
             Statement statement = conn.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCountry(String name,int id) {
        String SQL = "INSERT INTO country(name,id) VALUES(?,?)";
        try (Connection conn = DB.connection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1,name);
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addCity(int id,String name,int id2) {
        String SQL = "INSERT INTO city(id,name,country_id) VALUES(?,?,?)";
        try (Connection conn = DB.connection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setInt(3,id2);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addMayer(int id,String name,int mer) {
        String SQL = "INSERT INTO mayer(id,name,city_id) VALUES(?,?,?)";
        try (Connection conn = DB.connection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1,id);
            statement.setString(2,name);
            statement.setInt(3,mer);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void addCityToArrayList() {
               ArrayList<String> arrayList = new ArrayList<>();
        String SQL = "SELECT * FROM city";
        try (Connection conn = DB.connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                arrayList.add((rs.getInt("id") + " "
                        + rs.getString("name") + " "
                        + rs.getInt("country_id")));
            }
           System.out.println(arrayList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void addCountryToArrayList() {
        ArrayList<String> arrayList2 = new ArrayList<>();
        String SQL = "SELECT * FROM country";
        try (Connection conn = DB.connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                arrayList2.add((rs.getInt("id") + " "
                        + rs.getString("name")));
            }
            System.out.println(arrayList2);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static void getCityByid(int id){
            String SQL = "SELECT * FROM city WHERE id=?";
        try (Connection conn = DB.connection();
             PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1,id);
             ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println((rs.getInt("id") + " "
                        + rs.getString("name") + " "
                        + rs.getInt("country_id")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


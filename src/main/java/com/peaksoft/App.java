package com.peaksoft;

import java.sql.*;
import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException {
//        creatTableCountry();
//        creatTableCity();
//        creatTableMayer();
//        addCountry("Kyrgyzstan",996);
//        addCountry("Kazakhstan",997);
//        addCountry("GreatBritain",44);
//        addCountry("Russian",7);
//        addCountry("USA",1);
//        addCity(1,"NewYork",1);
//        addCity(2,"Moscow",7);
//        addCity(3,"London",44);
//        addCity(4,"Nursultan",997);
//        addCity(5,"Bishkek",996);
//        addMayer(1,"Эмилбек Абдыкадыров",5);
//        addMayer(2,"Женис Касымбеков",4);
//        addMayer(3,"Садик Хан",3);
//        addMayer(4,"Сергей Собянин",2);
//        addMayer(5,"Эрик Адамс",1);
//        addCityAndCountryToArrayList();
//        getCityByid(3);
    }

    public static void creatTableCity() {
        String SQL = " CREATE TABLE IF NOT EXISTS city (" + "id INTEGER PRIMARY KEY ," + "name VARCHAR(50)," + "country_id INTEGER REFERENCES country(id) NOT NULL)";
        try (Connection conn = DB.connection(); Statement statement = conn.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void creatTableCountry() {
        String SQL = " CREATE TABLE IF NOT EXISTS country (" + "id INTEGER PRIMARY KEY," + "name VARCHAR(50))";
        try (Connection conn = DB.connection(); Statement statement = conn.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void creatTableMayer() {
        String SQL = " CREATE TABLE IF NOT EXISTS mayer (" + "id INTEGER," + "name VARCHAR(50)," + "city_id INTEGER REFERENCES city(id)NOT NULL )";
        try (Connection conn = DB.connection(); Statement statement = conn.createStatement()) {
            statement.executeUpdate(SQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCountry(String name, int id) {
        String SQL = "INSERT INTO country(name,id) VALUES(?,?)";
        try (Connection conn = DB.connection(); PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCity(int id, String name, int id2) {
        String SQL = "INSERT INTO city(id,name,country_id) VALUES(?,?,?)";
        try (Connection conn = DB.connection(); PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, id2);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addMayer(int id, String name, int mer) {
        String SQL = "INSERT INTO mayer(id,name,city_id) VALUES(?,?,?)";
        try (Connection conn = DB.connection(); PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setInt(3, mer);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCityAndCountryToArrayList() {
        ArrayList<String> arrayList = new ArrayList<>();
        String SQL = "SELECT city.id AS city_id,city.name AS city_name,c.name AS country_name,c.id AS country_id  FROM city JOIN country c ON c.id = city.country_id";
        try (Connection conn = DB.connection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                arrayList.add((rs.getInt("city_id") + " " + rs.getString("city_name") + " " + rs.getString("country_name") + " " + rs.getInt("country_id")));
            }
            System.out.println(arrayList);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void getCityByid(int id) {
        String SQL = "SELECT c.name AS country_name,m.name AS mer_name,city.name As city_name FROM city JOIN country c on c.id = city.country_id JOIN mayer m on city.id = m.city_id WHERE city.id=?";
        try (Connection conn = DB.connection(); PreparedStatement statement = conn.prepareStatement(SQL)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                System.out.println("Город: "+rs.getString("city_name") + "  Страна: " + rs.getString("country_name") + " Мэр города: " + rs.getString("mer_name"));
                System.out.println("Запрос выполнен...");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}


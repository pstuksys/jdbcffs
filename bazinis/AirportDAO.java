package com.company.bazinis;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

public class AirportDAO {
    final static String URL = "jdbc:mysql://localhost/airports?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public void add(Airport airport) {
        String query = "insert into sb_airports (name, address, city)" + "values(?,?,?)";

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, airport.getName());
            preparedStatement.setString(2, airport.getAddress());
            preparedStatement.setString(3, airport.getCity());
            preparedStatement.executeUpdate();

            System.out.println("Pavyko iterpti nauja irasa");
        } catch (SQLException e) {
            System.out.println("Ivyko klaida kuriant nauja irasa");
            e.printStackTrace();
        }
    }

    public ArrayList<Airport> searchByName(String name) {
        String query2 = "select * from sb_airports WHERE name LIKE name";
        ArrayList<Airport> airports = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query2);
            ResultSet resultSet = preparedStatement.executeQuery(query2);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name2 = resultSet.getString("name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                airports.add(new Airport(id, name, address, city));
            }
            preparedStatement.close();
            connection.close();
            System.out.println("Pavyko atlikti paieska");
            System.out.println(airports);
        } catch (SQLException e) {
            System.out.println("Ivyko klaida atliekant paieska");
            e.printStackTrace();
        }
        return airports;
    }

    public void editById(Airport airport) {
        String query = "update sb_airports set name=?, address=?, city=?"+
        "WHERE id=?" + "values(?,?,?)";

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, airport.getName());
            preparedStatement.setString(2, airport.getAddress());
            preparedStatement.setString(3, airport.getCity());
            preparedStatement.setString(4, airport.getId());
            preparedStatement.executeUpdate();

            System.out.println("Pavyko redaguoti nauja irasa");
        } catch (SQLException e) {
            System.out.println("Ivyko klaida redaguojant irasa");
            e.printStackTrace();
        }
    }
    public void deleteById(Airport airport){
        String query = "delete sb_airports id=?";

        try {
            Connection connection = DriverManager.getConnection(URL, "root", "");
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, airport.getId());
            preparedStatement.executeUpdate();

            System.out.println("Pavyko redaguoti nauja irasa");
        } catch (SQLException e) {
            System.out.println("Ivyko klaida redaguojant irasa");
            e.printStackTrace();
    }
}
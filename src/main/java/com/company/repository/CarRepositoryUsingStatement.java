package com.company.repository;

import com.company.config.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarRepositoryUsingStatement {

    private static CarRepositoryUsingStatement singleton = null;

    private CarRepositoryUsingStatement() {

    }

    //Singleton object getter
    public static CarRepositoryUsingStatement getInstance()
    {
        if(singleton == null)
            singleton = new CarRepositoryUsingStatement();
        return singleton;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS car " +
                "(brand varchar(30), " +
                "model varchar(30), " +
                "carPlate varchar(30) PRIMARY KEY)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCar(String brand, String model, String carPlate) {
        String insertCarSql = "INSERT INTO car(brand, model, carPlate) VALUES('Dacia', '1300', 'VL99DCC')";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            stmt.executeUpdate(insertCarSql); // returns no of altered lines
        } catch (SQLException e) {
            System.out.println("Eroare, numarul VL99DCC exista deja!");
            e.printStackTrace();
        }
    }

    public void displayCar() {
        String selectSql = "SELECT * FROM car";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Brand:" + resultSet.getString(1));
                System.out.println("Model:" + resultSet.getString(2));
                System.out.println("CarPlate:" + resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
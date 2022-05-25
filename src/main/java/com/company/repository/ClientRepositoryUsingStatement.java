package com.company.repository;

import com.company.config.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientRepositoryUsingStatement {

    private static ClientRepositoryUsingStatement singleton = null;

    private ClientRepositoryUsingStatement() {

    }

    //Singleton object getter
    public static ClientRepositoryUsingStatement getInstance()
    {
        if(singleton == null)
            singleton = new ClientRepositoryUsingStatement();
        return singleton;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS client " +
                "(firstname varchar(30), " +
                "lastname varchar(30), " +
                "carPlate varchar(30) PRIMARY KEY)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCar() {
        String insertClientSql = "INSERT INTO client(firstname, lastname, carPlate) VALUES('Gigel', 'Andreescu', 'VL99DCC')";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            stmt.executeUpdate(insertClientSql); // returns no of altered lines
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayClient() {
        String selectSql = "SELECT * FROM client";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Firstname:" + resultSet.getString(1));
                System.out.println("Lastname:" + resultSet.getString(2));
                System.out.println("CarPlate:" + resultSet.getString(3));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
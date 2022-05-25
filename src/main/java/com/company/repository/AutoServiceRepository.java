package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.entities.Car;
import com.company.models.AutoService;

import java.sql.*;

public class AutoServiceRepository {

    private static AutoServiceRepository singleton = null;

    private AutoServiceRepository() {

    }

    //Singleton object getter
    public static AutoServiceRepository getInstance()
    {
        if(singleton == null)
            singleton = new AutoServiceRepository();
        return singleton;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS autoservice " +
                "(address varchar(30), " +
                "name varchar(30) PRIMARY KEY)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAutoService(AutoService autoService) {
        String insertCarSql = "INSERT INTO AutoService(address, name) VALUES(?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCarSql)) {
            preparedStatement.setString(1, autoService.getAddress());
            preparedStatement.setString(2, autoService.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public AutoService getServiceByName(String name) {
        String selectSql = "SELECT * FROM autoservice WHERE name=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(2, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToAutoService(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCarModel(String model, String carPlate) {
        String updateNameSql = "UPDATE car SET model=? WHERE carPlate=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, carPlate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private AutoService mapToAutoService(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new AutoService(resultSet.getString(1), resultSet.getString(2));
        }
        return null;
    }

    public void displayAutoService() {
        String selectSql = "SELECT * FROM autoservice";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Address:" + resultSet.getString(1));
                System.out.println("Name:" + resultSet.getString(2));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
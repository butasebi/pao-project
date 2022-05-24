package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.entities.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRepositoryUsingPreparedStatement {
    public void insertPerson(Car car) {
        String insertPersonSql = "INSERT INTO car(brand, model, carPlate) VALUES(?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertPersonSql)) {
            preparedStatement.setString(1, car.getBrand());
            preparedStatement.setString(2, car.getModel());
            preparedStatement.setString(3, car.getCarPlate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Car getCarByCarPlate(String carPlate) {
        String selectSql = "SELECT * FROM car WHERE carPlate=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(3, carPlate);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToCar(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCarModel(String model, String carPlate) {
        String updateNameSql = "UPDATE person SET model=? WHERE carPlate=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {
            preparedStatement.setString(2, model);
            preparedStatement.setString(3, carPlate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Car mapToCar(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Car(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        return null;
    }
}
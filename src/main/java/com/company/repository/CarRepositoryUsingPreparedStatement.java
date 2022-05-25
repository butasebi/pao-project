package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.entities.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRepositoryUsingPreparedStatement {

    private static CarRepositoryUsingPreparedStatement singleton = null;

    private CarRepositoryUsingPreparedStatement() {

    }

    //Singleton object getter
    public static CarRepositoryUsingPreparedStatement getInstance()
    {
        if(singleton == null)
            singleton = new CarRepositoryUsingPreparedStatement();
        return singleton;
    }

    public void insertCar(Car car) {
        String insertCarSql = "INSERT INTO car(brand, model, carPlate) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCarSql)) {
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

    private Car mapToCar(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Car(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        return null;
    }
}
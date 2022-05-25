package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.entities.Car;

import java.sql.*;

public class CarRepository {

    private static CarRepository singleton = null;

    private CarRepository() {

    }

    //Singleton object getter
    public static CarRepository getInstance()
    {
        if(singleton == null)
            singleton = new CarRepository();
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

    //CRUD

    //CREATE
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


    //READ

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


    //UPDATE
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

    //DELETE
    public void deleteCarByCarPlate(String carPlate) {
        String deleteCarSql = "DELETE FROM car WHERE carPlate =  ?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteCarSql)) {
            preparedStatement.setString(1, carPlate);
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

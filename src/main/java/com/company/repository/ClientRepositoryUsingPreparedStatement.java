package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.entities.Car;
import com.company.models.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRepositoryUsingPreparedStatement {

    private static ClientRepositoryUsingPreparedStatement singleton = null;

    private ClientRepositoryUsingPreparedStatement() {

    }

    //Singleton object getter
    public static ClientRepositoryUsingPreparedStatement getInstance()
    {
        if(singleton == null)
            singleton = new ClientRepositoryUsingPreparedStatement();
        return singleton;
    }

    public void insertClient(Client client) {
        String insertCarSql = "INSERT INTO client(firstname, lastname, carPlate) VALUES(?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertCarSql)) {
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getLastName());
            preparedStatement.setString(3, client.getCarPlate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Client getClientByCarPlate(String carPlate) {
        String selectSql = "SELECT * FROM client WHERE carPlate=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(3, carPlate);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToClient(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateClientName(String firstname, String carPlate) {
        String updateNameSql = "UPDATE client SET firstname=? WHERE carPlate=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {

            preparedStatement.setString(2, firstname);
            preparedStatement.setString(3, carPlate);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Client mapToClient(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Client(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
        }
        return null;
    }
}
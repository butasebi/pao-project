package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.models.Client;

import java.sql.*;

public class ClientRepository {

    private static ClientRepository singleton = null;

    private ClientRepository() {

    }

    //Singleton object getter
    public static ClientRepository getInstance()
    {
        if(singleton == null)
            singleton = new ClientRepository();
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

package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.entities.Car;
import com.company.models.AutoService;
import com.company.models.Manager;

import java.sql.*;

public class ManagerRepository {

    public static int booleanToNumber(Boolean a)
    {
        if(a == Boolean.TRUE)return 1;
        return 0;
    }

    public static Boolean numberToBoolean(int a)
    {
        if(a == 1)return Boolean.TRUE;
        return Boolean.FALSE;
    }


    private static ManagerRepository singleton = null;

    private ManagerRepository() {

    }

    //Singleton object getter
    public static ManagerRepository getInstance()
    {
        if(singleton == null)
            singleton = new ManagerRepository();
        return singleton;
    }

    public void createTable() {
        String createTableSql = "CREATE TABLE IF NOT EXISTS manager " +
                "(firstname varchar(30), " +
                "lastname varchar(30), " +
                "nrTel varchar(30) PRIMARY KEY, " +
                "salary int, " +
                "founder int) ";        //bool, 1 if true, 0 if false

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertManager(Manager manager) {
        String insertManagerSql = "INSERT INTO Manager(firstname, lastname, nrtel, salary, founder) VALUES(?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertManagerSql)) {
            preparedStatement.setString(1, manager.getFirstName());
            preparedStatement.setString(2, manager.getLastName());
            preparedStatement.setString(3, manager.getNrTel());
            preparedStatement.setInt(4, manager.getSalary());
            preparedStatement.setInt(5, ManagerRepository.booleanToNumber(manager.getFounder()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Manager getManagerByNrTel(String nrTel) {
        String selectSql = "SELECT * FROM autoservice WHERE nrtel=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSql)) {
            preparedStatement.setString(2, nrTel);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToManager(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateManagerNrTel(String nrTel) {
        String updateNameSql = "UPDATE car SET nrTel=? WHERE nrTel=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql)) {
            preparedStatement.setString(3, nrTel);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Manager mapToManager(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            return new Manager(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), ManagerRepository.numberToBoolean(resultSet.getInt(5)));
        }
        return null;
    }

    public void displayManager() {
        String selectSql = "SELECT * FROM manager";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try (Statement stmt = connection.createStatement()) { //try with resources
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next()) {
                System.out.println("Firstname:" + resultSet.getString(1));
                System.out.println("Lastname:" + resultSet.getString(2));
                System.out.println("Numar telefon:" + resultSet.getInt(3));
                System.out.println("Salariu:" + resultSet.getInt(4));
                System.out.println("Fondator:" + ManagerRepository.numberToBoolean(resultSet.getInt(5)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
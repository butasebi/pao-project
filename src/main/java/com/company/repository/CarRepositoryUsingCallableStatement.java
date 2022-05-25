package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.entities.Car;
import com.company.services.ServiceAudit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CarRepositoryUsingCallableStatement {

    private static CarRepositoryUsingCallableStatement singleton = null;

    private CarRepositoryUsingCallableStatement() {

    }

    //Singleton object getter
    public static CarRepositoryUsingCallableStatement getInstance()
    {
        if(singleton == null)
            singleton = new CarRepositoryUsingCallableStatement();
        return singleton;
    }

    public void insertCar(Car car) {
        String preparedSql = "{call insertCar(?,?,?)}";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (CallableStatement cstmt = connection.prepareCall(preparedSql)) {
            cstmt.setString(1, car.getBrand());
            cstmt.setString(2, car.getModel());
            cstmt.setString(3, car.getCarPlate());

            cstmt.registerOutParameter(3, Types.VARCHAR); // id - result of the procedure call

            cstmt.execute();
            System.out.println("Added car with the carplate:" + cstmt.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
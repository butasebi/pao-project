package com.company.repository;

import com.company.config.DatabaseConfiguration;
import com.company.entities.Car;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CarRepositoryUsingCallableStatement {

    // run in mysql workbench
    /**
     DELIMITER //
     CREATE PROCEDURE insertPerson(OUT id int, IN name varchar(30), IN age double)
     BEGIN
     INSERT INTO person(name, age) VALUES (name, age);
     SET id = LAST_INSERT_ID();
     END //
     DELIMITER ;
     **/
    public void insertCar(Car car) {
        String preparedSql = "{call insertCar(?,?,?)}";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (CallableStatement cstmt = connection.prepareCall(preparedSql)) {
            cstmt.setString(1, car.getBrand());
            cstmt.setString(2, car.getModel());
            cstmt.setString(3, car.getCarPlate());

            cstmt.registerOutParameter(3, Types.VARCHAR); // id - result of the procedure call

            cstmt.execute();
            System.out.println("Added user with the carplate:" + cstmt.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
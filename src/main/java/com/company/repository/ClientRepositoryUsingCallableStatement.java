package com.company.repository;

import com.company.config.DatabaseConfiguration;

import com.company.models.Client;
import com.company.services.ServiceAudit;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class ClientRepositoryUsingCallableStatement {

    private static ClientRepositoryUsingCallableStatement singleton = null;

    private ClientRepositoryUsingCallableStatement() {

    }

    //Singleton object getter
    public static ClientRepositoryUsingCallableStatement getInstance()
    {
        if(singleton == null)
            singleton = new ClientRepositoryUsingCallableStatement();
        return singleton;
    }

    public void insertClient(Client client) {
        String preparedSql = "{call insertclient(?,?,?)}";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try (CallableStatement cstmt = connection.prepareCall(preparedSql)) {
            cstmt.setString(1, client.getFirstName());
            cstmt.setString(2, client.getLastName());
            cstmt.setString(3, client.getCarPlate());

            cstmt.registerOutParameter(3, Types.VARCHAR); // id - result of the procedure call

            cstmt.execute();
            System.out.println("Added client with the carplate:" + cstmt.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
package com.smartdrive;

import utilities.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class VehicleRepository {

    public CompletionStage<Vehicle> getVehicle(int userId) {
        Connection cnn = DBConn.getConnection();
        String sqlq = "select * from vehicle where uid = ?";
        return CompletableFuture.supplyAsync(() -> {
            try {
                PreparedStatement preparedStatement;
                preparedStatement = cnn.prepareStatement(sqlq);
                preparedStatement.setInt(1, userId);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet != null) {
                    resultSet.next();
                    return (Vehicle.builder()
                            .uid(resultSet.getInt(1))
                            .vid(resultSet.getInt(2))
                            .numberPlate(resultSet.getString(3))
                            .vehicleType(resultSet.getString(4))
                            .make(resultSet.getString(5))
                            .status(resultSet.getBoolean(6))
                            .rcnumber(resultSet.getString(7))
                            .pucnumber(resultSet.getString(8))
                            .build());
                } else {
                    throw new RuntimeException();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException();
            }
        });
    }

}

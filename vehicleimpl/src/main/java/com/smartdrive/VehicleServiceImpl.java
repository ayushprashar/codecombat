package com.smartdrive;

import com.lightbend.lagom.javadsl.api.ServiceCall;
import utilities.DBConn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class VehicleServiceImpl implements VehicleService {
    @Override
    public ServiceCall<Vehicle, String> addVehicle() {
        Connection cnn = DBConn.getConnection();
        String sqlq = "INSERT INTO \"vehicle\" VALUES (?,?,?,?,?,?)";

        return vehicle -> {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sqlq);

                pstmt.setInt(1, vehicle.getUid());
                pstmt.setInt(2, vehicle.getVid());
                pstmt.setString(3, vehicle.getNumberPlate());
                pstmt.setString(4, vehicle.getVehicleType());
                pstmt.setString(5, vehicle.getMake());
                pstmt.setBoolean(6, vehicle.getStatus());
                pstmt.setString(7,vehicle.getRcnumber());
                pstmt.setString(8,vehicle.getPucnumber());
                pstmt.executeUpdate();

            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return CompletableFuture.completedFuture("Data added");
        };
    }

    public ServiceCall<Integer,Optional<Vehicle>> getVehicle(){
        Connection cnn = DBConn.getConnection();
        String sqlq = "select * from vehicle where uid = ?";

        return user ->{
            try {
                PreparedStatement preparedStatement;
                preparedStatement = cnn.prepareStatement(sqlq);
                preparedStatement.setInt(1,user);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet != null){
                    return Vehicle.builder()
                            .uid(resultSet.getInt(1))
                            .vid(resultSet.getInt(2))
                            .numberPlate(resultSet.getString(3))
                            .vehicleType(resultSet.getString(4))
                            .make(resultSet.getString(5))
                            .status(resultSet.getBoolean(6))
                            .rcnumber(resultSet.getString(7))
                            .pucnumber(resultSet.getString(8))
                            .build();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        };

    }
    /**
     * public int getTeacherID(String tname) //needs teacher name in gui
     *     {
     *     	System.out.println(tname);
     *         String sqlq = "select teacherid from teacher where TNAME = ?" ;
     *         Connection cnn = ConnectionFactory.getconnection();
     *             PreparedStatement stmt;
     *             try {
     * 				stmt=cnn.prepareStatement(sqlq);
     * 		          stmt.setString(1, tname);
     * 		            ResultSet rs = stmt.executeQuery();
     * 		            if(rs.next())
     * 		                teacherID=rs.getInt("TEACHERID");
     * 		            System.out.println("teacher ID is:"+this.teacherID);
     *                        } catch (SQLException e) {
     * 				// TODO Auto-generated catch block
     * 				e.printStackTrace();
     *            }
     *
     *
     *         return teacherID;
     *     }
     *
     */


}

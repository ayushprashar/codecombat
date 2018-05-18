package com.smartdrive;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import utilities.DBConn;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    @Inject
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public ServiceCall<Vehicle, String> addVehicle() {
        Connection cnn = DBConn.getConnection();
        String sqlq = "INSERT INTO \"vehicle\" VALUES (?,?,?,?,?,?,?,?)";

        return vehicle -> {
            try {
                PreparedStatement pstmt = cnn.prepareStatement(sqlq);

                pstmt.setInt(1, vehicle.getUid());
                pstmt.setInt(2, vehicle.getVid());
                pstmt.setString(3, vehicle.getNumberPlate());
                pstmt.setString(4, vehicle.getVehicleType());
                pstmt.setString(5, vehicle.getMake());
                pstmt.setBoolean(6, vehicle.getStatus());
                pstmt.setString(7, vehicle.getRcnumber());
                pstmt.setString(8, vehicle.getPucnumber());
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return CompletableFuture.completedFuture("Data added");
        };
    }

    public ServiceCall<NotUsed, Vehicle> getVehicle(int vehicleId) {
        return notUsed -> vehicleRepository.getVehicle(vehicleId);

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

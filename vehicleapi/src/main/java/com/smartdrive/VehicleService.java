package com.smartdrive;

import akka.NotUsed;
import com.lightbend.lagom.javadsl.api.Descriptor;
import com.lightbend.lagom.javadsl.api.Service;
import com.lightbend.lagom.javadsl.api.ServiceCall;
import com.lightbend.lagom.javadsl.api.transport.Method;

import java.util.Optional;

import static com.lightbend.lagom.javadsl.api.Service.named;
import static com.lightbend.lagom.javadsl.api.Service.restCall;


public interface VehicleService extends Service {
    ServiceCall<Vehicle, String> addVehicle();

    ServiceCall<NotUsed, Vehicle> getVehicle(int vehicleId);

    @Override
    default Descriptor descriptor() {
        return named("vehicle").withCalls(
                restCall(Method.POST, "/vehicle/addVehicle", this::addVehicle),
                restCall(Method.GET, "/vehicle/:vehicleId", this::getVehicle)
                // restCall(Method.POST," ")
        ).withAutoAcl(true);
    }
}

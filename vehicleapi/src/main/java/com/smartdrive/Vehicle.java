package com.smartdrive;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Vehicle {
    int uid;
    int vid;
    String numberPlate;
    String vehicleType;
    String make;
    Boolean status;
    String rcnumber;
    String pucnumber;
}


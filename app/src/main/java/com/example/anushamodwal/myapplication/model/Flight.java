package com.example.anushamodwal.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flight {

    @SerializedName("flightNumber")
    @Expose
    private String flightNumber;

    @SerializedName("operatingFlightLeg")
    @Expose
    private OperatingFlightLeg operatingLeg;


    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public OperatingFlightLeg getOperatingLeg() {
        return operatingLeg;
    }

    public void setOperatingLeg(OperatingFlightLeg operatingLeg) {
        this.operatingLeg = operatingLeg;
    }

    @Override
    public String toString() {
        return "Flight{" +
                ", flightNumber='" + flightNumber + '\'' +
                ", operatingLeg=" + operatingLeg +
                '}';
    }
}

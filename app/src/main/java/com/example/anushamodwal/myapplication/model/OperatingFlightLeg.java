package com.example.anushamodwal.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OperatingFlightLeg{
    @SerializedName("departsFrom")
    @Expose
    private AirportDetails departsFrom;

    @SerializedName("arrivesOn")
    @Expose
    private AirportDetails arrivesOn;

    @SerializedName("scheduledDepartureDateTime")
    @Expose
    private String scheduledDepartureDateTime;

    @SerializedName("scheduledArrivalDateTime")
    @Expose
    private String scheduledArrivalDateTime;

    @SerializedName("flightStatus")
    @Expose
    private String flightStatusOF;;

    public AirportDetails getDepartsFrom() {
        return departsFrom;
    }

    public void setDepartsFrom(AirportDetails departsFrom) {
        this.departsFrom = departsFrom;
    }

    public AirportDetails getArrivesOn() {
        return arrivesOn;
    }

    public void setArrivesOn(AirportDetails arrivesOn) {
        this.arrivesOn = arrivesOn;
    }

    public String getScheduledDepartureDateTime() {
        return scheduledDepartureDateTime;
    }

    public void setScheduledDepartureDateTime(String scheduledDepartureDateTime) {
        this.scheduledDepartureDateTime = scheduledDepartureDateTime;
    }

    public String getScheduledArrivalDateTime() {
        return scheduledArrivalDateTime;
    }

    public void setScheduledArrivalDateTime(String scheduledArrivalDateTime) {
        this.scheduledArrivalDateTime = scheduledArrivalDateTime;
    }

    public String getFlightStatusOF() {
        return flightStatusOF;
    }

    public void setFlightStatusOF(String flightStatusOF) {
        this.flightStatusOF = flightStatusOF;
    }


    @Override
    public String toString() {
        return "OperatingFlightLeg{" +
                "departsFrom=" + departsFrom +
                ", arrivesOn=" + arrivesOn +
                ", scheduledDepartureDateTime='" + scheduledDepartureDateTime + '\'' +
                ", scheduledArrivalDateTime='" + scheduledArrivalDateTime + '\'' +
                ", flightStatusOF='" + flightStatusOF +
                '}';
    }
}

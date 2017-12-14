package com.example.anushamodwal.myapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FlightInfo {

    @SerializedName("flights")
    @Expose
    private ArrayList<Flight> flights = new ArrayList<>();

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "FlightInfo{" +
                "flights=" + flights +
                '}';
    }
}

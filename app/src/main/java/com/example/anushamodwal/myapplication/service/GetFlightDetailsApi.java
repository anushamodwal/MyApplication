package com.example.anushamodwal.myapplication.service;


import com.example.anushamodwal.myapplication.model.FlightInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetFlightDetailsApi {

    @GET("flightstatuses")
    Call<FlightInfo> getFlightStatusData(@Query("departureDate") String date,
                             @Query("flightNumber") String fn);

    @GET("flightstatuses")
    Call<FlightInfo> getFLightOptionsData(@Query("originAirportCode") String dep,
                             @Query("destinationAirportCode") String dest);


}

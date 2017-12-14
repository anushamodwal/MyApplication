package com.example.anushamodwal.myapplication.util;

import com.example.anushamodwal.myapplication.model.Airport;

import java.util.ArrayList;

public class Constants {

    public static final int BUFFER_SIZE = 1024;
    public static final String BASE_URL = "https://fox.klm.com/fox/json/";
    public static final String IMAGE_FILE_NAME = "destination_images.properties";
    public static final String AIRPORT_DATA_URL = "https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat";
    public static final String OPENWEATHER_API_KEY = "d500179d72d9458cbb6912d13d661b66";
    public static final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public static ArrayList<Airport> airportList = new ArrayList<Airport>();
}

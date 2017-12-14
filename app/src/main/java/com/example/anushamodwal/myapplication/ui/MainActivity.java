package com.example.anushamodwal.myapplication.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.anushamodwal.myapplication.R;
import com.example.anushamodwal.myapplication.model.Airport;
import com.example.anushamodwal.myapplication.util.Constants;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new ReadFile().execute();
        }

    @OnClick(R.id.button_byFlight_details)
    public void onClick(){
        Intent i = new Intent(this, FlightByNumberActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.button_byDestination_details)
    void openActivityForDestinationDetails(View view){
        Intent i = new Intent(this, FlightByDestActivity.class);
        startActivity(i);

    }

    class ReadFile extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            URL url = null;
            try {
                url = new URL(Constants.AIRPORT_DATA_URL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Scanner s = null;
            try {
                s = new Scanner(url.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String line = "";

            while (s.hasNext()){

                line = s.nextLine();
                String values[] = line.split(",");
                Airport airport = new Airport();
                if(values.length == 14){
                    airport.setAirportName(removeExtraChar(values[1]));
                    airport.setCity(removeExtraChar(values[2]));
                    airport.setCountry(removeExtraChar(values[3]));
                    airport.setAirportCode(removeExtraChar(values[4]));
                    airport.setLatitude(values[6]);
                    airport.setLongitude(values[7]);
                }// To handle cases where there are commas within the string itself
                else if(values.length > 14){
                    airport.setAirportName(removeExtraChar(values[1])+","+removeExtraChar(values[2]));
                    airport.setCity(removeExtraChar(values[3]));
                    airport.setCountry(removeExtraChar(values[4]));
                    airport.setAirportCode(removeExtraChar(values[5]));
                    airport.setLatitude(values[7]);
                    airport.setLongitude(values[8]);
                }
                //Populate all the list of aiports available on the URL in an ArrayList
                Constants.airportList.add(airport);
            }

            s.close();

            return null;
        }
    }

    public String removeExtraChar(String s){
        s = s.replace("\"", "");
        return s;
    }

}

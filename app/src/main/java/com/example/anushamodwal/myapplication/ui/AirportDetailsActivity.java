package com.example.anushamodwal.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.anushamodwal.myapplication.R;
import com.example.anushamodwal.myapplication.util.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AirportDetailsActivity extends AppCompatActivity {

    @BindView(R.id.depDetail)
    TextView depDetail;

    @BindView(R.id.destDetail)
    TextView destDetail;

    private String dep;
    private String dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        dep = intent.getStringExtra("depAirport");
        dest = intent.getStringExtra("destAirport");

        // Set Airport Details like name, city, country
        setAirportDetails();
    }


    public void setAirportDetails(){

        String detail;
        String airportCode;

        for(int i = 0; i < Constants.airportList.size(); i++){
            airportCode = removeExtraChar(Constants.airportList.get(i).getAirportCode());

            detail = removeExtraChar(Constants.airportList.get(i).getAirportName())+" ,"
                    +removeExtraChar(Constants.airportList.get(i).getCity())+" ,"
                    +removeExtraChar(Constants.airportList.get(i).getCountry());

            if(airportCode.equals(dep)){
                depDetail.setText(detail);
            }

            else if(airportCode.equals(dest)){
                destDetail.setText(detail);
            }

        }
    }

    public String removeExtraChar(String s){
        s = s.replace("\"", "");
        return s;
    }

}

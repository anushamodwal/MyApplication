package com.example.anushamodwal.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.anushamodwal.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlightByNumberActivity extends AppCompatActivity {

    @BindView(R.id.flightNumber)
    EditText flightNum;

    @BindView(R.id.datePicker)
    DatePicker datePicker;

    @BindView(R.id.button_getStatus)
    Button getStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_flight_number);
        ButterKnife.bind(this);
    }
    // OnClick get data from the parameters entered by user
    @OnClick(R.id.button_getStatus)
    void onClick(){

        Intent i = new Intent(this, DisplayFSbyFlightNumber.class);
        i.putExtra("departureDate", getDate());
        i.putExtra("flightNumber", flightNum.getText().toString());
        startActivity(i);
    }

    //For getting date from date picker in correct format
    public String getDate(){
        String day = datePicker.getDayOfMonth()+"";
        if(day.length() == 1)
            day = "0"+day;

        String mon;
        // months in date picker are indexed from 0, hence adding 1
        int month = datePicker.getMonth()+1;
        if((month+"").length() == 1)
            mon = "0"+month;
        else
            mon = month+"";

        int year  = datePicker.getYear();

        return year+"-"+mon+"-"+day;
    }
}

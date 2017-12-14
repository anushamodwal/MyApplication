package com.example.anushamodwal.myapplication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import com.example.anushamodwal.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FlightByDestActivity extends AppCompatActivity {

    @BindView(R.id.depAirport)
    EditText depAirport;

    @BindView(R.id.destAirport)
    EditText destAirport;

    @BindView(R.id.button_getStatusbyDest)
    Button getStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_flight_destination);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button_getStatusbyDest)
    void onClick(){
        Intent i = new Intent(this, DisplayFSbyDepDest.class);
        i.putExtra("depAirport", depAirport.getText().toString());
        i.putExtra("destAirport", destAirport.getText().toString());
        startActivity(i);
    }

}

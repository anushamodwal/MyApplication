package com.example.anushamodwal.myapplication.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anushamodwal.myapplication.R;
import com.example.anushamodwal.myapplication.model.Flight;
import com.example.anushamodwal.myapplication.model.FlightInfo;
import com.example.anushamodwal.myapplication.service.GetFlightDetailsApi;
import com.example.anushamodwal.myapplication.util.Constants;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayFSbyFlightNumber extends AppCompatActivity {

    public static final String TAG="DisplayFSbyFlightNumber";

    public static String destPlace;

    @BindView(R.id.fstatus)
    TextView fStatus;

    @BindView(R.id.depDetails)
    TextView dep;

    @BindView(R.id.destDetails)
    TextView dest;

    @BindView(R.id.abtDest)
    TextView abtDest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_flight_status);
        ButterKnife.bind(this);

        new GetData().execute();
    }

    @OnClick(R.id.abtDest)
    public void onClick(){
        Intent i = new Intent(this, DestDetailsActivity.class);
        i.putExtra("destCode", destPlace);
        startActivity(i);
    }

    private class GetData extends AsyncTask<Void, Void, String> {

        ProgressDialog pd;
        ArrayList<Flight> flightList;

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(DisplayFSbyFlightNumber.this, "",
                    "Fetching Details...", false);
        }

        @Override
        protected String doInBackground(Void... voids) {
            //For getting data entered by user
            Intent intent = getIntent();

            //Building URL for getting data
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            GetFlightDetailsApi detailsApi = retrofit.create(GetFlightDetailsApi.class);
            Call<FlightInfo> call = detailsApi.getFlightStatusData(intent.getStringExtra("departureDate"),
                    intent.getStringExtra("flightNumber"));

            call.enqueue(new Callback<FlightInfo>() {
                @Override
                public void onResponse(Call<FlightInfo> call, Response<FlightInfo> response) {
                    if (response != null) {
                        Log.d(TAG, "data: " + response.body().toString());

                        //Parse data into arraylist
                        flightList = response.body().getFlights();
                        if(flightList.size() !=0 ){
                            setFLightArrivalDetails();
                        }
                        else{
                            Toast toast = Toast.makeText(getBaseContext(), "Enter valid Input", Toast.LENGTH_LONG);
                            toast.show();
                        }

                    } else {
                        Log.d(TAG, "No Data " + flightList.size());
                    }

                }

                @Override
                public void onFailure(Call<FlightInfo> call, Throwable t) {
                    Log.e(TAG, "on Faliure: something went wrong. " + t.getMessage());
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            pd.dismiss();
        }

        //setting flight Status and time details in text views
        private void setFLightArrivalDetails() {

            String flight_status = flightList.get(0).getOperatingLeg().getFlightStatusOF();
            fStatus.setText(flight_status);

            String depPlace, depDate, depTime;
            String destDate, destTime;

            //Formatting String from response for displaying
            String depDetails[] = (flightList.get(0).getOperatingLeg().getScheduledDepartureDateTime()).split("T");
            depPlace = flightList.get(0).getOperatingLeg().getDepartsFrom().getCodeIATA();
            depDate = depDetails[0];
            depTime = depDetails[1];

            String destDetails[] = (flightList.get(0).getOperatingLeg().getScheduledArrivalDateTime()).split("T");

            destPlace = flightList.get(0).getOperatingLeg().getArrivesOn().getCodeIATA();
            destDate = destDetails[0];
            destTime = destDetails[1];

            dep.setText(depPlace+"      "+depDate+"    "+depTime);
            dest.setText(destPlace+"      "+destDate+"    "+destTime);

        }

    }

}

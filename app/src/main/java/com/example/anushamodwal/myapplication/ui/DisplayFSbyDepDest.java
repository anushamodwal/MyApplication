package com.example.anushamodwal.myapplication.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anushamodwal.myapplication.R;
import com.example.anushamodwal.myapplication.model.Flight;
import com.example.anushamodwal.myapplication.model.FlightInfo;
import com.example.anushamodwal.myapplication.service.GetFlightDetailsApi;
import com.example.anushamodwal.myapplication.util.Constants;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayFSbyDepDest extends AppCompatActivity {

    public static final String TAG="DisplayFSbyDepDest";

    public static String dep;
    public static String dest;

    ListView listView;
    TextView abtAirports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_options_list);

        Intent intent = getIntent();
        dep = intent.getStringExtra("depAirport");
        dest = intent.getStringExtra("destAirport");

        abtAirports = (TextView) findViewById(R.id.abtAirports);
        listView = (ListView) findViewById(R.id.listFlightOptions);

        abtAirports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DisplayFSbyDepDest.this, AirportDetailsActivity.class);
                i.putExtra("depAirport", dep);
                i.putExtra("destAirport", dest);
                startActivity(i);
            }
        });

        // Get list of Flight options available for dep and dest entered by user
        new GetData().execute();

    }

    private class GetData extends AsyncTask<Void, Void, String> {

        ArrayList<Flight> flightList;
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            pd = ProgressDialog.show(DisplayFSbyDepDest.this, "",
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
            Call<FlightInfo> call = detailsApi.getFLightOptionsData(intent.getStringExtra("depAirport"),
                    intent.getStringExtra("destAirport"));

            call.enqueue(new Callback<FlightInfo>() {
                @Override
                public void onResponse(Call<FlightInfo> call, Response<FlightInfo> response) {
                    if (response != null) {
                        Log.d(TAG, "data: " + response.body().toString());

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

        //setting data in a list view
        private void setFLightArrivalDetails() {
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(),
                    flightList);
            listView.setAdapter(adapter);

        }

    }


}

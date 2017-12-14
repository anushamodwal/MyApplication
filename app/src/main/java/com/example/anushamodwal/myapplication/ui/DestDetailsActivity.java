package com.example.anushamodwal.myapplication.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anushamodwal.myapplication.R;
import com.example.anushamodwal.myapplication.util.Constants;
import com.example.anushamodwal.myapplication.util.ReadImageURL;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DestDetailsActivity extends AppCompatActivity {

    public static final String TAG = "DestDetailsActivity";

    @BindView(R.id.imageView)
    ImageView imageView;

    String code = "";
    String imgURL = "";
    String cityName;

    @BindView(R.id.city_field)
    TextView cityField;

    @BindView(R.id.details_field)
    TextView detailsField;

    @BindView(R.id.current_temperature_field)
    TextView currentTemperatureField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_dest_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        //Get destination code from previous activity
        code = intent.getStringExtra("destCode");

        //Get corresponding city name to the code
        cityName = getCityName(code);

        try {
            //Read image URL of the code of the city
            imgURL = ReadImageURL.getProperty(code, getApplicationContext());

        } catch (IOException e) {
            e.printStackTrace();
        }
        //Check if image of the city is available or not
        if(imgURL != null){
            Uri imageUri = Uri.parse(imgURL);
            imageView.setImageURI(imageUri);
        }
        else{
            imageView.setImageDrawable(getDrawable(R.drawable.defaultimage));
        }

        new getWeatherData().execute();
    }

    private class getWeatherData extends AsyncTask<Void, Void, Void> {

        JSONObject data = null;

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Building URL for openweathermap api with key and city name
                URL url = new URL(Constants.WEATHER_URL+cityName+"&APPID="+Constants.OPENWEATHER_API_KEY);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuffer json = new StringBuffer(Constants.BUFFER_SIZE);
                String tmp = "";

                while((tmp = reader.readLine()) != null)
                    json.append(tmp).append("\n");
                reader.close();

                data = new JSONObject(json.toString());

                if(data.getInt("cod") != 200) {
                    System.out.println("Cancelled");
                    return null;
                }


            } catch (Exception e) {

                System.out.println("Exception "+ e.getMessage());
                return null;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void Void) {
            if(data!=null){
                renderWeather();
                Log.d("Success",data.toString());
            }

        }

        private void renderWeather() {
            try {
                cityField.setText(data.getString("name").toUpperCase(Locale.US) +
                        ", " +
                        data.getJSONObject("sys").getString("country"));

                JSONObject details = data.getJSONArray("weather").getJSONObject(0);
                JSONObject main = data.getJSONObject("main");
                detailsField.setText(
                        details.getString("description").toUpperCase(Locale.US) +
                                "\n" + "Humidity: " + main.getString("humidity") + "%" +
                                "\n" + "Pressure: " + main.getString("pressure") + " hPa");

                currentTemperatureField.setText(
                        String.format("%.2f", main.getDouble("temp")) + " ℃");


            } catch (Exception e) {
                Log.e(TAG, "One or more fields not found in the JSON data");
            }
        }

    }
    //Find city name given Airport Code
    public String getCityName(String codeAiport) {
        String code = "";

        for (int i = 0; i < Constants.airportList.size(); i++) {
            code = Constants.airportList.get(i).getAirportCode();
            if (code.equals(codeAiport)) {
                return Constants.airportList.get(i).getCity();
            }
        }
        return "Not Found";
    }

}

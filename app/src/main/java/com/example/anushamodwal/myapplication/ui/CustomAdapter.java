package com.example.anushamodwal.myapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.anushamodwal.myapplication.R;
import com.example.anushamodwal.myapplication.model.Flight;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Flight> {

    TextView fNum;
    TextView fStat;
    TextView fDep;
    TextView fDest;

    public CustomAdapter(Context context, ArrayList<Flight> flights) {
        super(context, 0, flights);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Flight flight = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_fs_dep_dest, parent, false);
        }

        fNum = (TextView)convertView.findViewById(R.id.fNum);
        fStat = (TextView)convertView.findViewById(R.id.fStat);
        fDep = (TextView)convertView.findViewById(R.id.fdep);
        fDest = (TextView)convertView.findViewById(R.id.fdest);

        fNum.setText("Flight Number: "+flight.getFlightNumber());
        fStat.setText(flight.getOperatingLeg().getFlightStatusOF());
        fDep.setText(DisplayFSbyDepDest.dep);
        fDest.setText(DisplayFSbyDepDest.dest);

        return convertView;
    }

}

package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import java.text.DecimalFormat;
import java.util.ArrayList;



/**
 * Created by Puja on 13/12/16.
 */

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {
    private static final String LOCATION_SEPERATOR="of";

    public EarthQuakeAdapter (Context context, ArrayList<EarthQuake> earthQuakes){
        super(context,0,earthQuakes);

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView=convertView;

        if(listView==null){
            listView= LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list,parent,false);
        }

        EarthQuake eQ=getItem(position);

        DecimalFormat dMag=new DecimalFormat("0.0");

        String formatted=dMag.format(Double.parseDouble(eQ.getMagnitude())).toString();
        TextView v1=(TextView)listView.findViewById(R.id.magnitude);
        GradientDrawable magnitudeCircle = (GradientDrawable) v1.getBackground();
        int magnitudeColor = getMagnitudeColor(Double.parseDouble(eQ.getMagnitude()));
        magnitudeCircle.setColor(magnitudeColor);

        v1.setText(formatted);


        String originalLocation=eQ.getCity();
        String Primarylocation;
        String LocationOffset;
        if(originalLocation.contains(LOCATION_SEPERATOR)){
            String parts[]=originalLocation.split(LOCATION_SEPERATOR);
            LocationOffset=parts[0]+LOCATION_SEPERATOR;
            Primarylocation=parts[1];
        }else{
            LocationOffset=getContext().getString(R.string.near_the);
            Primarylocation=originalLocation;
        }

        TextView v2=(TextView)listView.findViewById(R.id.location_offset);
        v2.setText(LocationOffset);

        TextView v3=(TextView)listView.findViewById(R.id.primary_location);
        v3.setText(Primarylocation);


        String oDate;
        String oTime;
        String rDate=eQ.getDate();
        String parts[]=rDate.split(":");
        oDate=parts[0];
        oTime=parts[1];

        TextView v4=(TextView)listView.findViewById(R.id.date);
        //DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        v4.setText(oDate);

        TextView v5=(TextView)listView.findViewById(R.id.time);
        v5.setText(oTime);

        return listView;
    }
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}

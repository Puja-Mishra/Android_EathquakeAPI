package com.example.android.quakereport;

import java.util.Date;


import static com.example.android.quakereport.R.id.date;

/**
 * Created by Puja on 13/12/16.
 */

public class EarthQuake {
    private String eCity;
    private String eMagnitude;
    private String eDate;
    private String eUrl;

    public EarthQuake(String magnitude,String city,String date,String url){
        eCity=city;
        eMagnitude=magnitude;
        eDate=date;
        eUrl=url;
    }

    public String getCity(){return eCity;}
    public String getMagnitude(){return eMagnitude;}
    public String getDate(){return eDate;}
    public String getUrl(){return eUrl;}

}

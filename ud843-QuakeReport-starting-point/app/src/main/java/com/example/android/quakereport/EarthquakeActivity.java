/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URI;
import java.util.Date;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    private static final String USGS_REQUEST_URL =
            "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.



        /* Commented by me for new code*/
//        ArrayList<EarthQuake> earthquakes =QueryUtils.extractEarthquakes();
        EartthquakeAsyncTask task=new EartthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);
        // Find a reference to the {@link ListView} in the layout
        /* Commented by me for new code*/
//        final EarthQuakeAdapter adapter=new EarthQuakeAdapter(this,earthquakes);
//        ListView earthquakeListView = (ListView) findViewById(R.id.list);
//
//        // Create a new {@link ArrayAdapter} of earthquakes
//
//
//        // Set the adapter on the {@link ListView}
//        // so the list can be populated in the user interface
//        earthquakeListView.setAdapter(adapter);
//        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                EarthQuake earth_quake=adapter.getItem(position);
//                Uri earthQuakeUri=Uri.parse(earth_quake.getUrl());
//
//                Intent websiteIntent=new Intent(Intent.ACTION_VIEW,earthQuakeUri);
//                startActivity(websiteIntent);
//            }
//        });
    }

    private class EartthquakeAsyncTask extends AsyncTask<String,Void,ArrayList<EarthQuake>> {
        ArrayList<EarthQuake> earthquake;
        @Override
        protected ArrayList<EarthQuake> doInBackground(String... params) {
            // Perform the HTTP request for earthquake data and process the response.
            earthquake = QueryUtils.fetchEarthQuakeData(params[0]);
            return earthquake;
        }

        @Override
        protected void onPostExecute(ArrayList<EarthQuake> event) {
            // Update the information displayed to the user.
            updateUi(earthquake);
        }
    }

    private void updateUi(ArrayList<EarthQuake> earthquake) {
        // Find a reference to the {@link ListView} in the layout
        final EarthQuakeAdapter adapter=new EarthQuakeAdapter(this,earthquake);
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EarthQuake earth_quake=adapter.getItem(position);
                Uri earthQuakeUri=Uri.parse(earth_quake.getUrl());

                Intent websiteIntent=new Intent(Intent.ACTION_VIEW,earthQuakeUri);
                startActivity(websiteIntent);
            }
        });
    }
}

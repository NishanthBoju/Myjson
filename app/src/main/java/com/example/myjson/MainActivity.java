package com.example.myjson;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.net.ssl.HttpsURLConnection;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn;
    String responseText;
    StringBuffer response;
    Activity activity;
    URL url;
    ListView listView;
    ArrayList<Main2Activity.Country> countries = new ArrayList<Main2Activity.Country>();
    String path = "https://api.myjson.com/bins/nab3w";

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.list1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countries.clear();
                new GetData().execute();
            }
        });

    }


    class GetData extends AsyncTask {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            return getWebServicecalls();
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            CustomContentList customContentList = new CustomContentList(activity, countries);
            listView.setAdapter(customContentList);
        }
    }
        protected Object getWebServicecalls() {
            try {

                url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                int responsecode = connection.getResponseCode();
                if (responsecode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String output;
                    response = new StringBuffer();
                    while ((output = in.readLine()) != null) {
                        response.append(output);
                    }
                    in.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            responseText = response.toString();

            try {
                JSONArray jsonArray = new JSONArray(responseText);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int id = jsonObject.getInt("id");
                    String country = jsonObject.getString("country_name");
                    Main2Activity.Country countryobj = new Main2Activity.Country(country, id);
                    countries.add(countryobj);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }
    }




package com.example.aisha.project;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String from_station = intent.getStringExtra("from_station");
        String to_station = intent.getStringExtra("to_station");
        //String date = intent.getStringExtra("date");
        String date = "31.12.2016";
        Log.d("res", from_station + ":" + to_station + ":" + date);
        new ConnTask().execute(from_station, to_station, date);
    }


    class ConnTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String fromSt = params[0];
            String toSt = params[1];
            String dt = params[2];

            HttpClient client = new DefaultHttpClient();
            String URL = null;
            try {
                URL = "http://mynameisanon.pythonanywhere.com/?from_station=" + URLEncoder.encode(fromSt, "UTF-8") + "&to_station=" + URLEncoder.encode(toSt, "UTF-8") + "&date=" + URLEncoder.encode(dt, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String responseString = "";

            try {
                HttpGet get = new HttpGet(URL);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                responseString = client.execute(get, responseHandler);
                Log.d("res", "answer::::> " + responseString);
//                HttpResponse response = client.execute(post);
//                HttpEntity entity = response.getEntity();
//                responseString = EntityUtils.toString(entity, "UTF-8");
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("res", responseString.toString() + " >>>>");
            return responseString;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
//            pb.setVisibility(View.GONE);

            try {
                JSONObject jObj = new JSONObject(json);

                //Log.d("formings", jObj.getString("result"));
                //tv.setText(jObj.getString("result"));
                //usr.setText("");
                //pass.setText("");
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            return;
        }
    }
}

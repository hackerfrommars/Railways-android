package com.example.aisha.project;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import org.apache.http.client.ResponseHandler;
import org.apache.http.impl.client.BasicResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

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
    ProgressBar progressBar;
    TextView result;
    ExpandableListView elv;
    SimpleExpandableListAdapter sa;

    ArrayList<ArrayList<HashMap<String, String>>> childData;
    ArrayList<HashMap<String, String>> groupData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String from_station = intent.getStringExtra("from_station");
        String to_station = intent.getStringExtra("to_station");
        String date = intent.getStringExtra("date");
        Log.d("res", from_station + ":" + to_station + ":" + date);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        result = (TextView)findViewById(R.id.result);
        result.setVisibility(View.INVISIBLE);
        elv = (ExpandableListView)findViewById(R.id.lvExp);

        new ConnTask().execute(from_station, to_station, date);

    }


    class ConnTask extends AsyncTask<String, Void, String> {

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
            return responseString;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            progressBar.setVisibility(View.GONE);
            try {
                JSONObject jObj = new JSONObject(json);
                JSONArray res = jObj.getJSONArray("res");
                JSONObject fir = res.getJSONObject(0);
                String fromStat = fir.getString("from_station");
                String toStat = fir.getString("to_station");
                String day = fir.getString("date");
                if(res.length() < 2){
                    result.setVisibility(View.VISIBLE);
                    result.setText("No trains are available at this day");
                }
                else {
                    ArrayList<HashMap<String, String>> result_group =
                            new ArrayList();
                    ArrayList<ArrayList<HashMap<String, String>>> result_item =
                            new ArrayList();
                    for (int i = 1; i < res.length(); i++) {
                        JSONObject train = res.getJSONObject(i);
                        String path = train.getString("path");
                        String time_from = train.getString("time_from");
                        String time_to = train.getString("time_to");
                        String price_second = train.getString("price_second");
                        String price_compartment = train.getString("price_compartment");
                        String price_luxury = train.getString("price_luxury");

                        Log.d("res", "after parse:::::>>>>> " + path + " : " + time_from + " : " + time_to);


                        HashMap<String, String> groupmap = new HashMap();
                        groupmap.put("train", "train");
                        groupmap.put("route", path);
                        result_group.add(groupmap);

                        ArrayList<HashMap<String, String>> itemList = new ArrayList();
                        HashMap<String, String> timeFromMap = new HashMap();
                        timeFromMap.put("keyitem", "Time");
                        timeFromMap.put("valueitem", time_from);
                        itemList.add(timeFromMap);

                        HashMap<String, String> timeToMap = new HashMap();
                        timeToMap.put("keyitem", "Time");
                        timeToMap.put("valueitem", time_to);
                        itemList.add(timeToMap);

                        HashMap<String, String> priceSecondMap = new HashMap();
                        priceSecondMap.put("keyitem", "Price second");
                        priceSecondMap.put("valueitem", price_second);
                        itemList.add(priceSecondMap);

                        HashMap<String, String> priceCompartmentMap = new HashMap();
                        priceCompartmentMap.put("keyitem", "Price compartment");
                        priceCompartmentMap.put("valueitem", price_compartment);
                        itemList.add(priceCompartmentMap);

                        HashMap<String, String> priceLuxuryMap = new HashMap();
                        priceLuxuryMap.put("keyitem", "Price luxury");
                        priceLuxuryMap.put("valueitem", price_luxury);
                        itemList.add(priceLuxuryMap);

                        result_item.add(itemList);
                    }
                    groupData = result_group;
                    childData = result_item;

                }
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            sa = new SimpleExpandableListAdapter(ResultActivity.this , groupData, R.layout.list_group,
                    new String[]{"train", "route"}, new int[]{R.id.train, R.id.route},
                    childData, R.layout.list_item, new String[]{"keyitem", "valueitem"},
                    new int[]{R.id.keyitem, R.id.valueitem});
            elv.setAdapter(sa);
        }
    }
}

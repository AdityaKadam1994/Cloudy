package com.moneyfrog.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLEncoder;

public class Weather extends AppCompatActivity {

    Typeface typeface;
    TextView temperature,weathercond,loc,cont,tv;
    ImageView imageView;
    Button button;
    String string,stream;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String prefs="prefs";
    String setcity,getcity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        typeface = Typeface.createFromAsset(getAssets(),"limo.ttf");
        temperature = (TextView) findViewById(R.id.temptv);
        weathercond = (TextView) findViewById(R.id.weathertv);
        loc = (TextView) findViewById(R.id.loctv);
        tv=(TextView)findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.weathericon);
        button = (Button) findViewById(R.id.getinfo);
        cont = (TextView) findViewById(R.id.cont);
        temperature.setTypeface(typeface);
        weathercond.setTypeface(typeface);
        loc.setTypeface(typeface);
        button.setTypeface(typeface);
        cont.setTypeface(typeface);

        sharedPreferences=getSharedPreferences(prefs, Context.MODE_PRIVATE);
        setcity = (sharedPreferences.getString("city", ""));
        editor=sharedPreferences.edit();
        editor.apply();
        System.out.println(setcity);


        /*Glide
                .with(getApplicationContext())
                .load(R.drawable.raincloud)
                .into(imageView);*/


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loc.setText("");
                temperature.setText("");
                cont.setText("");
                weathercond.setText("");
                tv.setText("");
                imageView.setImageDrawable(getDrawable(R.drawable.na));
                getcity=String.format(setcity);
               // string = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D\"Mumbai%2C%20MH\")%20and%20u%3D%27c%27&format=json";
              //  String yql= String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"Mumbai\") and u='c'");
                String yql= String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"Mumbai\") and u='c'");

                String endpoints=String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(yql));
                System.out.print(endpoints);


                new ProcessJSON().execute(endpoints);
            }
        });







    }
    private class ProcessJSON extends AsyncTask<String,Void,String> {

        protected String doInBackground(String... strings) {
            stream = null;
            String string = strings[0];
            System.out.println(string);
            Handler hh = new Handler();
            stream = hh.GetHTTPData(string);
            System.out.println("query :" + stream);
            return stream;


        }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);


        if(stream !=null) {
            try {
                // Get the full HTTP Data as JSONObject

                JSONObject query_stream = new JSONObject(stream);
                JSONObject query = query_stream.getJSONObject("query");
                JSONObject res = query.getJSONObject("results");
                JSONObject chanel = res.getJSONObject("channel");
                JSONObject location = chanel.getJSONObject("location");
                System.out.println("location" + location);
                String city = location.getString("city");
                System.out.println("city :" + city);
                String country = location.getString("country");
                System.out.println("country :" + country);
                String region = location.getString("region");
                System.out.println("region" + region);
                JSONObject item = chanel.getJSONObject("item");
                System.out.println("item" + item);
                JSONObject condition = item.getJSONObject("condition");
                System.out.println(condition);
                String temp = condition.getString("temp");
                System.out.println("temp :" + temp);
                String cond=condition.getString("text");
                System.out.println("text:"+ cond);
                String code =condition.getString("code");
                System.out.println("code"+ code);
                int resourceId = getResources().getIdentifier("drawable/a"+code, null, getPackageName());
                System.out.println(resourceId);
               //JSONObject forecast=item.getJSONObject("forecast");
               JSONArray forecast=item.getJSONArray("forecast");
               // int j=forecast.length();
                //j=4;
                for (int i=1;i<4;i++){
                    JSONObject newcode=forecast.getJSONObject(i);
                    String mycode=newcode.getString("code");
                    JSONObject newdate=forecast.getJSONObject(i);
                    String mydate=newdate.getString("date");
                    System.out.println("forecast"+mycode);
                    System.out.println("forecast"+mydate);
                   tv.setText(mydate);

                }



                //Setting values to the textviews and Image view
                imageView.setImageDrawable(getResources().getDrawable(resourceId));
                loc.setText(city +",");
                temperature.setText(temp+"\u2103");
                cont.setText(country);
                weathercond.setText(cond);

            }catch (Exception e){
                System.out.println(e);
            }
        }

            }


            }


}

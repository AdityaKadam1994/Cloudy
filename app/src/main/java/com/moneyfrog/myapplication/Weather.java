package com.moneyfrog.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLEncoder;

public class Weather extends AppCompatActivity {

    Typeface typeface;
    TextView temperature,weathercond,loc,cont,tv;
    ImageView imageView;
    Button button,change;
    String string,stream;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String prefs="prefs";
    String setcity,getcity,endpoints,setcountry,getcountry;
    int length=200;
    NetworkInfo networkInfo;
    ConnectivityManager connectivityManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        typeface = Typeface.createFromAsset(getAssets(), "limo.ttf");
        change = (Button) findViewById(R.id.change_city);
        temperature = (TextView) findViewById(R.id.temptv);
        weathercond = (TextView) findViewById(R.id.weathertv);
        loc = (TextView) findViewById(R.id.loctv);
        tv = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.weathericon);
        button = (Button) findViewById(R.id.getinfo);
        cont = (TextView) findViewById(R.id.cont);
        temperature.setTypeface(typeface);
        weathercond.setTypeface(typeface);
        loc.setTypeface(typeface);
        button.setTypeface(typeface);
        change.setTypeface(typeface);
        cont.setTypeface(typeface);

        sharedPreferences = getSharedPreferences(prefs, Context.MODE_PRIVATE);
        setcity = (sharedPreferences.getString("city", ""));
        setcountry=(sharedPreferences.getString("country",""));
        editor = sharedPreferences.edit();
        editor.apply();
        System.out.println(setcity);
        connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();

        /*Glide
                .with(getApplicationContext())
                .load(R.drawable.raincloud)
                .into(imageView);*/


        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                loc.setText("");
                temperature.setText("");
                cont.setText("");
                weathercond.setText("");
                tv.setText("");
                imageView.setImageResource(R.drawable.na);
                // imageView.setImageDrawable(getDrawable(R.drawable.na));
                getcity = String.format(setcity);
                System.out.println(getcity);
                getcountry=String.format(setcountry);


                String yql = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\" "+    getcity+ ","+getcountry)+"\")"+"and u='c'";

                System.out.println(yql);

                endpoints = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(yql));
                System.out.print(endpoints);

                new ProcessJSON().execute(endpoints);

            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences(prefs, Context.MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();


                Intent i = new Intent(Weather.this, Selection.class);
                startActivity(i);

            }
        });

        android.os.Handler handler = new android.os.Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                if (networkInfo!=null&&networkInfo.isConnected()){



                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Weather.this,Blank.class);
                    startActivity(i);
                }

            }
        },length);










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
                Toast.makeText(getApplicationContext(),"No weather found",Toast.LENGTH_LONG).show();
            }
        }

            }


            }




            public void onBackPressed(){

            }


}

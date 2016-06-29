package com.moneyfrog.myapplication;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Weather extends AppCompatActivity {

    Typeface typeface;
    TextView temperature,condition,location;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        typeface= Typeface.createFromAsset(getAssets(),"vinatge.otf");
        temperature=(TextView) findViewById(R.id.temptv);
        condition=(TextView) findViewById(R.id.weathertv);
        location=(TextView)findViewById(R.id.loctv);
        imageView=(ImageView)findViewById(R.id.weathericon);
        temperature.setTypeface(typeface);
        condition.setTypeface(typeface);
        location.setTypeface(typeface);

        /*Glide
                .with(getApplicationContext())
                .load(R.drawable.raincloud)
                .into(imageView);*/


    }
}

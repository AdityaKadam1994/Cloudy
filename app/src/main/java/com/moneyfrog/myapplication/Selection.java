package com.moneyfrog.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.Preference;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Selection extends AppCompatActivity {
        EditText get_city;
        Button getweather_info;
        Typeface typeface;
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        String city;
        String prefs="prefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        get_city=(EditText) findViewById(R.id.getcity);
        getweather_info=(Button)findViewById(R.id.getweather);
        typeface=Typeface.createFromAsset(getAssets(),"limo.ttf");
        getweather_info.setTypeface(typeface);
        get_city.setTypeface(typeface);
        sharedPreferences=getSharedPreferences(prefs, Context.MODE_PRIVATE);

        getweather_info.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

                editor=sharedPreferences.edit();
                city=get_city.getText().toString();
                editor.putString("city",city);
                editor.apply();
                Intent intent= new Intent(Selection.this,Weather.class);
                startActivity(intent);
            }



        });

    }

    public void onBackPressed(){

    }
}

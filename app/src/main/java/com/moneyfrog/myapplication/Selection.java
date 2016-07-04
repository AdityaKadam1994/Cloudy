package com.moneyfrog.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.*;
import android.os.Handler;
import android.preference.Preference;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Selection extends AppCompatActivity {
    EditText get_city, get_country;
    Button getweather_info;
    Typeface typeface;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String city, country;
    String prefs = "prefs";

    int length = 2800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);
        get_city = (EditText) findViewById(R.id.getcity);
        get_country = (EditText) findViewById(R.id.getcountry);
        getweather_info = (Button) findViewById(R.id.getweather);
        typeface = Typeface.createFromAsset(getAssets(), "limo.ttf");
        getweather_info.setTypeface(typeface);
        get_city.setTypeface(typeface);
        get_country.setTypeface(typeface);
        sharedPreferences = getSharedPreferences(prefs, Context.MODE_PRIVATE);



        getweather_info.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                if (get_city.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Please Enter city name", Toast.LENGTH_LONG).show();
                } else {

                    editor = sharedPreferences.edit();
                    city = get_city.getText().toString();
                    country = get_country.getText().toString();
                    editor.putString("city", city);
                    editor.putString("country", country);
                    editor.apply();
                    Intent intent = new Intent(Selection.this, Weather.class);
                    startActivity(intent);

                }

            }


        });


    }

    public void onBackPressed() {

    }
}

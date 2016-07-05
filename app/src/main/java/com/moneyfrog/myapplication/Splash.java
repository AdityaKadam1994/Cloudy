package com.moneyfrog.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.*;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Splash extends AppCompatActivity {
    Intent intent;
    ImageView l1;
    final int splash_length=2850;
    TextView msg;
    Typeface typeface;
    SharedPreferences sharedPreferences;
    String prefs="prefs";
    NetworkInfo networkInfo;
    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        typeface = Typeface.createFromAsset(getAssets(), "limo.ttf");
        l1 = (ImageView) findViewById(R.id.logo1);
        msg = (TextView) findViewById(R.id.wc_message);

        msg.setTypeface(typeface);

        connectivityManager=(ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
       networkInfo=connectivityManager.getActiveNetworkInfo();





        android.os.Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {
                if (networkInfo!=null&&networkInfo.isConnected()){

                    sharedPreferences = getSharedPreferences(prefs, MODE_PRIVATE);
                    String storedcity = (sharedPreferences.getString("city", ""));


                    if (!storedcity.equalsIgnoreCase("")) {
                        intent = new Intent(Splash.this, Weather.class);
                        startActivity(intent);
                        finish();
                    }

                    else {
                        intent = new Intent(Splash.this, Selection.class);
                        startActivity(intent);
                    }

                    startActivity(intent);
                    finish();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(Splash.this,Blank.class);
                    startActivity(i);

                }

            }
        }, splash_length);


    }


}

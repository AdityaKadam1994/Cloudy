package com.moneyfrog.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.*;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Splash extends AppCompatActivity {
    Intent intent;
    ImageView l1;
    final int splash_length=2850;
    TextView msg;
    Typeface typeface;
    SharedPreferences sharedPreferences;
    String prefs="prefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        typeface = Typeface.createFromAsset(getAssets(), "limo.ttf");
        l1=(ImageView)findViewById(R.id.logo1);
        msg=(TextView)findViewById(R.id.wc_message);

        msg.setTypeface(typeface);


       /* android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
               Intent intent=new Intent(Splash.this,Selection.class);
                startActivity(intent);

            }

        },splash_length);*/
        android.os.Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            public void run() {


                sharedPreferences = getSharedPreferences(prefs, MODE_PRIVATE);
                String storedcity = (sharedPreferences.getString("city", ""));

                if (!storedcity .equalsIgnoreCase("")) {
                    intent  = new Intent(Splash.this,Weather.class);
                    startActivity(intent);
                    finish();

                }else {
                    intent  = new Intent(Splash.this, Selection.class);
                    startActivity(intent);
                }

                startActivity(intent);
                finish();
            }
        }, splash_length);








    }
}

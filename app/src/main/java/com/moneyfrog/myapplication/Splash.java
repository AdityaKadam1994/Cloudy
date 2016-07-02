package com.moneyfrog.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Splash extends AppCompatActivity {
    ImageView l1;
    final int splash_length=10090;
    TextView msg;
    Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        typeface = Typeface.createFromAsset(getAssets(), "limo.ttf");
        l1=(ImageView)findViewById(R.id.logo1);
        msg=(TextView)findViewById(R.id.wc_message);

        msg.setTypeface(typeface);





        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
               Intent intent=new Intent(Splash.this,Weather.class);
                startActivity(intent);

            }

        },splash_length);
    }
}

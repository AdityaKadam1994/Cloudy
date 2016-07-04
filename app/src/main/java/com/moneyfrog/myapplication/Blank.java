package com.moneyfrog.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Blank extends AppCompatActivity {

    Button button;
    int length=200;
    NetworkInfo networkInfo;
    ConnectivityManager connectivityManager;
    TextView tv;
    Typeface typeface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        connectivityManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        typeface=Typeface.createFromAsset(getAssets(),"limo.ttf");
        button=(Button)findViewById(R.id.button);
        tv=(TextView)findViewById(R.id.textView2);
        button.setTypeface(typeface);
        tv.setTypeface(typeface);








        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (networkInfo!=null&&networkInfo.isConnected()){
                    Intent i =new Intent(Blank.this,Splash.class);
                    startActivity(i);


                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(Blank.this,Blank.class);
                    startActivity(i);
                }

            }
        });

    }
}

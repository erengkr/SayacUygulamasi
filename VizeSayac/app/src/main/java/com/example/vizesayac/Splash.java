package com.example.vizesayac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler activityHandler=new Handler(Looper.getMainLooper());
        activityHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
            finish();
            }
        }, 2000);
    }
}
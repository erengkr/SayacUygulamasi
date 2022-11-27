package com.example.vizesayac;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView value;
    Button plus, minus, ayar;
    int counter = 0;
    int currentvalue;
    int upperlimit;
    int lowerlimit;

    boolean uppervib;
    boolean lowervib;
    boolean uppersound;
    boolean lowersound;

    Vibrator vibrator=null;
    MediaPlayer player =null;


    SetupClass setupClass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, Splash.class);
        startActivity(intent);

        value = (TextView) findViewById(R.id.value);
        plus = (Button) findViewById(R.id.plus);
        minus = (Button) findViewById(R.id.minus);
        ayar = (Button) findViewById(R.id.ayar);
        Context context = getApplicationContext();
        setupClass = SetupClass.getInstance(context);
        vibrator =(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        player=MediaPlayer.create(context,R.raw.aa);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               valueUpdate(1);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valueUpdate(-1);
            }
        });

        ayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupClass.loadValues();
        getPreferences();

    }

    @Override
    protected void onPause() {
        super.onPause();
        setupClass.setValues(currentvalue);
    }
    private void getPreferences() {
        currentvalue =setupClass.currentvalue;
        upperlimit=setupClass.upperlimit;
        lowerlimit=setupClass.lowerlimit;
        uppervib=setupClass.uppervib;
        lowervib=setupClass.lowervib;
        uppersound=setupClass.uppersound;
        lowersound=setupClass.lowersound;



    }
    private void valueUpdate(int step){
        if(step<0){
            if(currentvalue+step<lowerlimit){
                currentvalue=lowerlimit;
                player.start();
                vibrator.vibrate(100);
            }
            else{
                currentvalue+=step;
            }
        }
        else{
            if(currentvalue+step>upperlimit){
                currentvalue=upperlimit;
                player.start();
                vibrator.vibrate(100);
            }
            else{
                currentvalue+=step;
            }
        }
        value.setText(String.valueOf(currentvalue));
    }
    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        switch(keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if(action == KeyEvent.ACTION_DOWN){
                    valueUpdate(+5);
                    return true;
                }
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (action==KeyEvent.ACTION_DOWN)
                    valueUpdate(-5);
                return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
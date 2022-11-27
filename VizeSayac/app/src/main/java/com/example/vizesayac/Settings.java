package com.example.vizesayac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.Set;

public class Settings extends AppCompatActivity {

    Button upminus,upplus,lowminus,lowplus;
    EditText upvalue,downvalue;
    CheckBox upsound,upvib,lowsound,lowvib;
    int upperlimit=20;
    int lowerlimit=0;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        upplus=(Button) findViewById(R.id.upplus);
        upminus=(Button) findViewById(R.id.upminus);
        lowminus=(Button) findViewById(R.id.lowminus);
        lowplus=(Button) findViewById(R.id.lowplus);
        upvalue=(EditText) findViewById(R.id.upperlimit);
        downvalue=(EditText) findViewById(R.id.lowerlimit);
        upsound=(CheckBox) findViewById(R.id.upsound);
        upvib=(CheckBox) findViewById(R.id.upvib);
        lowsound=(CheckBox) findViewById(R.id.lowsound);
        lowvib=(CheckBox) findViewById(R.id.lowvib);

        Context context=getApplicationContext();
        sharedPreferences=context.getSharedPreferences(context.getPackageName(),Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        SetupClass setupClass=SetupClass.getInstance(context);


        upplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperlimit++;
                upvalue.setText(String.valueOf(upperlimit));
            }
        });
        upminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upperlimit--;
                upvalue.setText(String.valueOf(upperlimit));
            }
        });
        lowplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowerlimit++;
                downvalue.setText(String.valueOf(lowerlimit));
            }
        });
        lowminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowerlimit--;
                downvalue.setText(String.valueOf(lowerlimit));
            }
        });
    }
    @Override
    protected void onPause(){
        super.onPause();
        Context context=getApplicationContext();
        sharedPreferences=context.getSharedPreferences("settings",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        SetupClass setupClass=SetupClass.getInstance(context);
        setupClass.setValues(upperlimit, lowerlimit,upvib.isChecked(),
                upsound.isChecked(),lowvib.isChecked(),lowsound.isChecked());
        editor.putInt("upperlimit",upperlimit);
        editor.commit();


    }
}
package com.example.vizesayac;

import android.content.Context;
import android.content.SharedPreferences;

public class SetupClass {
    int upperlimit;
    int lowerlimit;
    int currentvalue;

    boolean uppervib;
    boolean lowervib;
    boolean uppersound;
    boolean lowersound;

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    static SetupClass setupClass=null;

    private SetupClass(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences("vizesayac", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public static SetupClass getInstance(Context context){
      if(setupClass==null){
          setupClass=new SetupClass(context);
      }
      return setupClass;
    }
    public void setValues(int upperlimit,int lowerlimit,boolean uppervib
            ,boolean uppersound,boolean lowersound,boolean lowervib){
        this.upperlimit=upperlimit;
        this.lowerlimit=lowerlimit;
        this.uppervib=uppervib;
        this.uppersound=uppersound;
        this.lowersound=lowersound;
        this.lowervib=lowervib;
        saveValues();
    }
    public void setValues(int currentvalue){
        this.currentvalue=currentvalue;
        saveValues();
    }
    public void saveValues(){
        editor.putInt("upperlimit",upperlimit);
        editor.putInt("lowerlimit",lowerlimit);
        editor.putInt("currentvalue",currentvalue);
        editor.putBoolean("uppervib",uppervib);
        editor.putBoolean("uppersound",uppersound);
        editor.putBoolean("lowersound",lowersound);
        editor.putBoolean("lowervib",lowervib);
        editor.commit();
    }
    public void loadValues(){
        upperlimit=sharedPreferences.getInt("upperlimit",20);
        lowerlimit=sharedPreferences.getInt("lowerlimit",0);
        currentvalue=sharedPreferences.getInt("currentvalue",0);
        uppervib=sharedPreferences.getBoolean("uppervib",true);
        uppersound=sharedPreferences.getBoolean("uppersound",true);
        lowersound=sharedPreferences.getBoolean("lowersound",true);
        lowervib=sharedPreferences.getBoolean("lowervib",true);

    }


}

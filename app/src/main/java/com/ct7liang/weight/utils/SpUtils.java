package com.ct7liang.weight.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SpUtils {

    private static SharedPreferences sp;

    public static void init(Context context){
        sp = context.getSharedPreferences("app_configs", Context.MODE_PRIVATE);
    }

    public static float getTargetWeight(){
        return sp.getFloat("TargetWeight", 0.0f);
    }
    public static void setTargetWeight(float f){
        sp.edit().putFloat("TargetWeight", f).apply();
    }

}

package com.example.eaten.DTO;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.eaten.HomeActivity;
import com.example.eaten.MainActivity;

public class Shared {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Context context;
    int mode =0 ;
    String fileName = "sdfile";
    String Data = "b";
    public Shared(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(fileName,mode);
        editor = sharedPreferences.edit();
    }

    public void secondtime(){
        editor.putBoolean(Data, false);
        editor.commit();
    }
    public void logouttime(){
        editor.putBoolean(Data, true);
        editor.commit();
    }
//    public void firsttime(){
//        if(!this.Login()){
//            //((MainActivity)context).finish();
//            Intent intent = new Intent(context, HomeActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            context.startActivity(intent);
//            //((MainActivity)context).finish();
//        }
//    }
    public boolean firsttime(){
        if(!this.Login()){
//            Intent intent = new Intent(context, HomeActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            context.startActivity(intent);
            return true;
        }
        return false;
    }

    private boolean Login() {
        return sharedPreferences.getBoolean(Data,true);
    }
}

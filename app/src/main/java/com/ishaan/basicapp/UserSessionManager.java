//package com.ishaan.basicapp;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//public class UserSessionManager {
//    SharedPreferences sp;
//    static SharedPreferences.Editor editor;
//    String spn = "session";
//    static String sk = "sku";
//
//    public UserSessionManager(Context context){
//        sp = context.getSharedPreferences(spn,Context.MODE_PRIVATE);
//        editor = sp.edit();
//    }
//
//    public static void saveSession(User user){
//        int id = user.getId();
//        editor.putInt(sk,id).commit();
//    }
//
//    public int getSession(){
//        return sp.getInt(sk,-1);
//    }
//
//    public void finishSession(){
//        editor.putInt(sk,-1).commit();
//    }
//}

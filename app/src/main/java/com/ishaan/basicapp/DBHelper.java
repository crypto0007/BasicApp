package com.ishaan.basicapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ishaan.basicapp.model.GetSet;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper  extends SQLiteOpenHelper {
    public static final String DN = "Restaurant.db";
    public static final String Reg_Table = "Registration_tb";
    public static final String C  = "ID";
    public static final String C1 = "email";
    public static final String C2 = "password";
    public static final String C3 = "name";
    //public static final String C3 = "version";



    public DBHelper(@Nullable Context context) {
        super(context, DN, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_table = " CREATE TABLE  IF NOT EXISTS " +Reg_Table+ "('ID' INTEGER PRIMARY KEY AUTOINCREMENT,'name' TEXT,'email' TEXT,'password' TEXT)";
        db.execSQL(Create_table);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + Reg_Table);
        onCreate(db);
    }

    void insertD(GetSet reg){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(C1, reg.getEmail());
        cv.put(C2, reg.getPassword());
        cv.put(C3, reg.getName());
        //cv.put(C3, reg.getAndroid());
        db.insert(Reg_Table,null,cv);
        db.close();

    }

    public Boolean emailExist(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Reg_Table + " where " + C1 + " = ?", new String[]{email});
        if (cursor.getCount()>0)
            return false;
        else
            return true;
    }

    public Cursor getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.rawQuery("Select * from "+Reg_Table+" where email='"+email+"'",null);
        Cursor cursor = db.rawQuery("Select * from " + Reg_Table + " where " + C1  +" = '"+email+"'",null);
        return cursor;
    }

    public Boolean loginAuth(String email, String password)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + Reg_Table + " where " + C1 + "= ? and " + C2 +" = ?", new String[]{email,password});
        Log.i("database","Authenticatting!");
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }

//    public boolean updateUser(String name,String email){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//
////        Log.i("Db Update ","IN UPDATE METHOD");
//        values.put(C3,name);
////        try {
////            db.execSQL("UPDATE " + Reg_Table + " SET name = " + "'" + name + "' " + "WHERE email = " + "'" + email + "'");
////        }catch (Exception e){
////            Log.i("Db Update ",e.getMessage());
////        }
//                long res = db.update(Reg_Table,values,"email = ? ",new String[]{email});
//        //db.close();
//        return res != -1;
//    }

    public boolean updateUser(String id, String name) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(C,id);
        cv.put(C3,name);
        db.update(Reg_Table, cv ,"id=?",new String[]{id});
        return true;
    }

    public boolean deleteProfile(String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Reg_Table, C1 + "= '" + email +"'", null) > 0;
    }


//    public Boolean changePassword(String email,String password){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
//        ContentValues values = new ContentValues();
//        values.put(C2,password);
//        sqLiteDatabase.update(Reg_Table,values,C1 + "= '" + email +"'",null);
//
//        return true;
//    }



//    public boolean loginvaild(String email, String password){
//
//    }



//    public Boolean emailExist(String email){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor positioin = db.rawQuery("Select * from " +Reg_Table +"where " + email + "=? ",new String[] {email});
//        if (positioin.getCount()>0){
//            return false;
//        }else{
//            return true;
//        }
//    }
}

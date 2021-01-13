//package com.ishaan.basicapp;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteException;
//import android.database.sqlite.SQLiteOpenHelper;
//
//import androidx.annotation.Nullable;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class DB2 extends SQLiteOpenHelper {
//    public static final String DATABASE_NAME = "salaryDatabase3";
//    public static final String CONTACTS_TABLE_NAME = "SalaryDetails";
//
//    public DB2(@Nullable Context context) {
//        super(context,DATABASE_NAME,null,1);
//    }
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        try {
//            db.execSQL(
//                    "create table "+ CONTACTS_TABLE_NAME +"(id INTEGER PRIMARY KEY, name text,salary text)"
//            );
//        } catch (SQLiteException e) {
//            try {
//                throw new IOException(e);
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+CONTACTS_TABLE_NAME);
//        onCreate(db);
//    }
//
//    public boolean insert(String s, String s1) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("name", s);
//        contentValues.put("salary", s1);
//        db.replace(CONTACTS_TABLE_NAME, null, contentValues);
//        return true;
//    }
//
//    public ArrayList getAllCotacts() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        ArrayList<String> array_list = new ArrayList<String>();
//        Cursor res = db.rawQuery( "select * from "+CONTACTS_TABLE_NAME+" WHERE salary >'10' OR name LIKE 'Sa%' ", null );
//        res.moveToFirst();
//
//        while(res.isAfterLast() == false) {
//            array_list.add(res.getString(res.getColumnIndex("name")));
//            res.moveToNext();
//        }
//        return array_list;
//    }
//
//    public boolean update(String s, String s1) {
//        SQLiteDatabase db = this.getReadableDatabase();
//        db.execSQL("UPDATE "+CONTACTS_TABLE_NAME+" SET name = "+"'"+s+"' "+ "WHERE salary = "+"'"+s1+"'");
//        return true;
//    }
//}

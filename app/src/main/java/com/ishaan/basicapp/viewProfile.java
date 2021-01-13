package com.ishaan.basicapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class viewProfile extends AppCompatActivity {
    EditText et, et2;
    Button bt;
    TextView lab,lab2,lab3;
    final DBHelper mydb = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        //et = (EditText) findViewById(R.id.name);
        //et2 = (EditText) findViewById(R.id.email);
        bt = (Button) findViewById(R.id.update);
        lab = (TextView) findViewById(R.id.name);
        lab2 = (TextView) findViewById(R.id.email);
        lab3 = (TextView) findViewById(R.id.vid);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String currentUser;

        SharedPreferences pref = getSharedPreferences(MainActivity.pref2, 0);
        currentUser = pref.getString("userMail", "");

        SQLiteDatabase db;
        db = mydb.getReadableDatabase();
        Cursor cursor;
        cursor = mydb.getUser(currentUser);

        if (cursor.moveToFirst()) {
            String id,email, name;

            name = cursor.getString(1);
            email = cursor.getString(2);
            id = cursor.getString(3);


            lab.setText(name);
            lab2.setText(email);
            lab3.setText(id);
        }
//        else {
//            //final AlertDialog.Builder builder = new AlertDialog.Builder(viewProfile.this);
//            builder.setTitle("Error");
//            builder.setMessage("Record Not found");
//            builder.show();
//        }

//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent in = new Intent(viewProfile.this,edit.class);
//                startActivity(in);
//                boolean b = DBHelper.updateUser(et2.getText().toString());
//                    if (b) {
//                        builder.setTitle("Update");
//                        builder.setMessage("Record updated");
//                        builder.show();
//                    } else {
//                        builder.setTitle("Error");
//                        builder.setMessage("Record Not update");
//                        builder.show();
//                    }
//                }

//                String name;
//                //email = et.getText().toString();
//                name = et2.getText().toString();
//                try {
//                    boolean i = mydb.updateUser(name);
//                    if (i) {
//                        Toast.makeText(viewProfile.this, "Updated successfully!", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(viewProfile.this, "Error while updating!", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (Exception e) {
//                    Toast.makeText(viewProfile.this, "Problem while updating your profile!", Toast.LENGTH_SHORT).show();
//                    Log.i("UpdateError", e.getMessage());
//                }
//
//            }
//        });
    }
    public void withEditText(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Name");

        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                  //String id,name;
//                id = lab3.getText().toString();
//                name = lab.getText().toString();
               boolean update = mydb.updateUser(lab3.getText().toString(),lab.getText().toString());
               if(update == true){
                   Toast.makeText(viewProfile.this, "Updated successfully!", Toast.LENGTH_SHORT).show();
               }
               else{
                   Toast.makeText(viewProfile.this, "Error while updating!", Toast.LENGTH_SHORT).show();
               }
            }
        });
        builder.show();
    }
}
package com.ishaan.basicapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText et, et2;
    Button b;
    TextView tv;
    SharedPreferences pref;
    public static final String pref2 = "user_details";
    DBHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        b = (Button) findViewById(R.id.SubBTlo);
        et = (EditText) findViewById(R.id.EmailET);
        et2 = (EditText) findViewById(R.id.PassET);

        tv = (TextView) findViewById(R.id.loTV);

        pref = getSharedPreferences(pref2 ,MODE_PRIVATE);
        Intent in = new Intent(MainActivity.this,list_view.class);
        if(pref.contains("username") && pref.contains("password")){
            startActivity(in);
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et.getText().toString();
                String password = et2.getText().toString();

                Log.i("Email",username);
                Log.i("Password",password);

                Boolean checkAuth = db.loginAuth(username, password);
                if (checkAuth == true) {

                    SharedPreferences.Editor editor = pref.edit();
                    //  sharedPreferences.edit().putBoolean("loggedIn",true);
                    editor.putString("userMail", username);
                    editor.putString("userPass", password);
                    editor.commit();
                    //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(in);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Email or Password wrong.", Toast.LENGTH_SHORT).show();
                }

//                Intent in = new Intent(MainActivity.this,list_view.class);
//                startActivity(in);

//                if (et.getText().toString().equals("admin") && et2.getText().toString().equals("password")) {
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putString("username",username);
//                    editor.putString("password",password);
//                    editor.commit();
//                    Toast.makeText(getApplicationContext(), "Login Successful",Toast.LENGTH_SHORT).show();
//                    //Intent log = new Intent(MainActivity.this, list_view.class);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(getApplicationContext(),
//                            "Wrong Credentials", Toast.LENGTH_SHORT).show();
//                }
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(MainActivity.this, Registration.class);
                startActivity(in);
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        UserSessionManager usm = new UserSessionManager(MainActivity.this);
//        int uid = usm.getSession();
//
//        if (uid != -1){
//            listv();
//        }
//    }
//
//    public void login(View view){
//        User user = new User(12,"ishaan");
//        UserSessionManager usm = new UserSessionManager(MainActivity.this);
//        UserSessionManager.saveSession(user);
//        listv();
//
//    }
//    private void listv(){
//        Intent in = new Intent(MainActivity.this,list_view.class);
//        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(in);
//    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        Toast.makeText(this,"Please Login",Toast.LENGTH_LONG).show();
//    }
}
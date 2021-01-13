package com.ishaan.basicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class smssend extends AppCompatActivity {
    Button b;
    EditText et, et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smssend);
        b = (Button) findViewById(R.id.send);
        et = (EditText) findViewById(R.id.noet);
        et2 = (EditText) findViewById(R.id.mget);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                        Mymessage();
                    }else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }
            }
        });
    }
    private void Mymessage() {
        try {
            String phoneno = et.getText().toString().trim();
            String message = et2.getText().toString().trim();
            SmsManager sm = SmsManager.getDefault();
            sm.sendTextMessage(phoneno,null,message,null,null);
            Toast.makeText(this,"Message sent",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this,"Message wasn't sent",Toast.LENGTH_SHORT).show();
        }

    }
}

//        int per = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
//
//        if (per == PackageManager.PERMISSION_GRANTED){
//            Mymessage();
//        }
//        else{
//            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
//        }
//    }
//
//    private void Mymessage() {
//        String phoneno = et.getText().toString().trim();
//        String message = et2.getText().toString().trim();
//        SmsManager sm = SmsManager.getDefault();
//        sm.sendTextMessage(phoneno,null,message,null,null);
//        Toast.makeText(this,"Message send",Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch(requestCode){
//            case 0:
//                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
//                    Mymessage();
//                }
//                else{
//                    Toast.makeText(this,"Don't have Permission",Toast.LENGTH_SHORT).show();
//                }
//        }
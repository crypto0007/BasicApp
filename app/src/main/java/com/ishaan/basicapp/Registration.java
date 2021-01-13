package com.ishaan.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ishaan.basicapp.model.GetSet;

public class Registration extends Activity {
    //implements AdapterView.OnItemSelectedListener {
    //String[] av = { "Pie", "Q", "R", "Other"};
    EditText email, password, cnpass, name;
    //Spinner sp;
    Button bt;
    TextView tv;
    DBHelper db;
    String st,st2,st3,st4;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);
            email = (EditText) findViewById(R.id.email);
            password = (EditText) findViewById(R.id.pass);
            cnpass = (EditText) findViewById(R.id.cnpass);
            name = (EditText) findViewById(R.id.name);
            //sp = (Spinner) findViewById(R.id.sp);
            bt = (Button) findViewById(R.id.SubBTri);
            tv = (TextView) findViewById(R.id.riTV);
            db = new DBHelper(this);

//            Spinner sp = (Spinner) findViewById(R.id.sp);
//
//            sp.setOnItemSelectedListener(this);
//            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,av);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//            sp.setAdapter(adapter);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    st  = email.getText().toString();
                    st2 = password.getText().toString();
                    st3 = cnpass.getText().toString();
                    st4 = name.getText().toString();
                    //st4 = sp.getSelectedItem().toString();

                    if(st2.equals(st3)){
                        boolean emailExist = db.emailExist(st);
                        if (emailExist = true) {
                            db.insertD(new GetSet(st, st2,st4));
//                           if (insertD = true) {
                            Toast.makeText(Registration.this, "Registration successful", Toast.LENGTH_SHORT).show();
                            Intent in2 = new Intent(Registration.this, list_view.class);
                            startActivity(in2);
//                        }
                    }
                        else{
                            Toast.makeText(Registration.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                        }
                        }
                    else{
                        Toast.makeText(Registration.this, "Check your password", Toast.LENGTH_SHORT).show();
                    }
                    }

//                    Intent in2 = new Intent(Registration.this,MainActivity.class);
//                    startActivity(in2);

            });
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(Registration.this, MainActivity.class);
                    startActivity(in);
                }
            });
//            Spinner sp = (Spinner) findViewById(R.id.sp);
//
//            sp.setOnItemSelectedListener(this);
//            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, av);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//            sp.setAdapter(adapter);
        }
        @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this,"Please Logout",Toast.LENGTH_LONG).show();
    }

//            @Override
//    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> adapterView) {
//
//    }
}
package com.ishaan.basicapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

//import android.widget.Toolbar;

public class list_view extends AppCompatActivity {
    ListView l;
    String[] values = new String[]{"Dialog activity", "Tab Activity", "Send SMS", "Call"};
    Toolbar tb;
    NavigationView navigationView;
    SharedPreferences prf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        l = (ListView) findViewById(R.id.lv);
        tb = (Toolbar) findViewById(R.id.tb);
        DrawerLayout dLayout;
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        setNavigationDrawer();
        setSupportActionBar(tb);
        navigationView = findViewById(R.id.navigation);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        //Navigation


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.profile:
                        Intent in = new Intent(list_view.this, viewProfile.class);
                        startActivity(in);
//                        Toast.makeText(list_view.this, "Profile Selected", Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;
            }
        });

        //End
        l.setAdapter(adapter);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Intent i1 = new Intent(view.getContext(), Dialogact.class);
                    startActivityForResult(i1, 0);
                }
                if (i == 1) {
                    Intent i2 = new Intent(view.getContext(), Tabact.class);
                    startActivityForResult(i2, 0);
                }
                if (i == 2) {
                    Intent i3 = new Intent(view.getContext(), smssend.class);
                    startActivityForResult(i3, 0);
                }
                if (i == 3) {
                    Intent i4 = new Intent(view.getContext(), Call.class);
                    startActivityForResult(i4, 0);
                }
//                if (i == 4) {
//                    Intent i5 = new Intent(view.getContext(), json2.class);
//                    startActivityForResult(i5, 0);
//                }
            }

        });
        registerForContextMenu(l);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.set: {
                Toast.makeText(this, "Setting selected", Toast.LENGTH_SHORT).show();
                finish();
            }
            case R.id.lo: {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.contextm, menu);
        menu.setHeaderTitle("Options");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.del) {
            Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_LONG).show();
        } else {
            return false;
        }
        return false;
    }

    public void logout(MenuItem item) {
        prf = getSharedPreferences("user_details",MODE_PRIVATE);
        Intent intent = new Intent(list_view.this,MainActivity.class);
        SharedPreferences.Editor editor = prf.edit();
        editor.clear();
        editor.commit();
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Please logout", Toast.LENGTH_LONG).show();
    }

    public void del(MenuItem item) {
        DBHelper myd = new DBHelper(list_view.this);
        final String currentUser;

        SharedPreferences pref = getSharedPreferences(MainActivity.pref2, 0);
        currentUser = pref.getString("userMail", "");
        boolean dp = myd.deleteProfile(currentUser);
        if (dp == true){
            Intent in = new Intent(list_view.this,MainActivity.class);
            startActivity(in);
        }else {
            Toast.makeText(list_view.this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }


//    private void toLogin() {
//        Intent in = new Intent(list_view.this,list_view.class);
//        in.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(in);
//    }
}
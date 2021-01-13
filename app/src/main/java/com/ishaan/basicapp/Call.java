package com.ishaan.basicapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Call extends AppCompatActivity {
    private static final int MAKE_CALL_PERMISSION_REQUEST_CODE = 1;
    private EditText mnet;
    private ImageButton clibt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        mnet = (EditText) findViewById(R.id.mnet);
        clibt = (ImageButton) findViewById(R.id.clibt);

        clibt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneno = mnet.getText().toString();
                if (checkPermission()) {
                    Intent call = new Intent(Intent.ACTION_CALL,Uri.parse("tel:" + phoneno));
                    startActivity(call);
                }else{
                    Toast.makeText(Call.this,"No Permission given",Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (checkPermission()) {
            clibt.setEnabled(true);
        } else {
            clibt.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MAKE_CALL_PERMISSION_REQUEST_CODE);
        }
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MAKE_CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                clibt.setEnabled(true);
                Toast.makeText(this, "You can call the number by clicking on the button", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


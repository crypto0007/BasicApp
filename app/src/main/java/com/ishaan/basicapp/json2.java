package com.ishaan.basicapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class json2 extends AppCompatActivity {
    private static final String FILE_NAME = "Details";
    ListView mylistView = (ListView) findViewById(R.id.listView);
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //listV = findViewById(R.id.listview);

        //Initialize JSON Array
        String item_array = "{\"Details\":[\n" +
                "{\"name\":\"Ishaan\",\"email\":\"ispa198@gmail.com\",\"job\":\"CEO\"},\n" +
                "{\"name\":\"Deep\",\"email\":\"deepparekh01@gmail.com\",\"job\":\"student\"},\n" +
                "{\"name\":\"Redd\",\"email\":\"imdanger@gmail.com\",\"job\":\"FBI Consultant\"},\n" +
                "{\"name\":\"keen\",\"email\":\"agentKenn@gmail.com\",\"job\":\"FBI\"}]\n" +
                "}";

        //Fetching the JSON Array

        try {
            JSONObject jsonObject = new JSONObject(item_array);
            JSONArray jsonArray = jsonObject.getJSONArray("Details");
            for (int i=0; i<jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String detailName = object.getString("name");
                String detailEmail = object.getString("email");
                String detailJob = object.getString("job");
                arrayList.add("Name: "+detailName + "\nEmail:" +  detailEmail +"\nJob:" + detailJob);

                String user = jsonObject.toString();

                File file = new File(this.getFilesDir(),FILE_NAME);
                FileWriter fileWriter = new FileWriter(file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(user);
                bufferedWriter.close();

            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }


        //Initialize Array Adapter
        arrayAdapter = new ArrayAdapter<>(json2.this,android.R.layout.simple_list_item_1,arrayList);
        //Set Array Adapter to List View
        mylistView.setAdapter(arrayAdapter);

        //Display Toast message OnClickItem
        mylistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),arrayList.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }
}

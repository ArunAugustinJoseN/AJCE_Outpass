package com.example.hp.ajcegosafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class AllStudents extends AppCompatActivity {

    ListView lv;
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<Profile> listProfile = new ArrayList<Profile>();
    Profile profile=new Profile();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        lv=(ListView)findViewById(R.id.listview);

        dbHelp db=new dbHelp(this);
        listProfile = db.getSearch();
        for (int i=0;i<listProfile.size();i++){
            list.add(listProfile.get(i).getName());
        }
        ArrayAdapter<String> ada = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(ada);


    }
}

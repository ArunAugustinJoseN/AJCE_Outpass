package com.example.hp.ajcegosafe;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminOutpassHistory extends AppCompatActivity {

    ArrayList<OutpassHistoryAdmin> outpassProfile = new ArrayList<OutpassHistoryAdmin>();
    OutpassHistoryAdmin outpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_outpass_history);
        setTitle("History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ListView listView=(ListView)findViewById(R.id.listView1);


            dbHelp db = new dbHelp(this);
            Cursor data = db.getOutpassHistoryAdmin();
            int numRows = data.getCount();
//        if (numRows == 0){
//            Toast.makeText(StudentOutpassHistory.this,"There is nothing to show..!",Toast.LENGTH_LONG).show();
//        }
//        else{
            while(data.moveToNext()){
                outpass = new OutpassHistoryAdmin(data.getString(0),data.getString(1), data.getString(2), data.getString(3),String.valueOf(numRows), data.getInt(4));
                outpassProfile.add(outpass);
            }
            OutpassHistorypage_Admin_listAdapter adapter = new OutpassHistorypage_Admin_listAdapter(this,R.layout.admin_outpass_column_row,outpassProfile);
            listView.setAdapter(adapter);

//        }



        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }


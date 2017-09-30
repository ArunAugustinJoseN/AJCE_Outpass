package com.example.hp.ajcegosafe;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class StudentOutpassHistory extends AppCompatActivity {

    ArrayList<OutpassHistory> outpassProfile = new ArrayList<OutpassHistory>();
    OutpassHistory outpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_outpass_history);
        setTitle("History");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Integer admno = Integer.parseInt(getIntent().getExtras().getString("admno"));
        ListView listView=(ListView)findViewById(R.id.listView1);


        dbHelp db = new dbHelp(this);
        Cursor data = db.getOutpassHistory(admno);
        int numRows = data.getCount();
//        if (numRows == 0){
//            Toast.makeText(StudentOutpassHistory.this,"There is nothing to show..!",Toast.LENGTH_LONG).show();
//        }
//        else{
            while(data.moveToNext()){
                outpass = new OutpassHistory(data.getString(3), data.getString(4), data.getString(2));
                outpassProfile.add(outpass);
            }
            OutpassHistorypage_listAdapter adapter = new OutpassHistorypage_listAdapter(this,R.layout.colmn_row,outpassProfile);
            listView.setAdapter(adapter);

//        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;
                Toast.makeText(StudentOutpassHistory.this, Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();
            }

        });

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

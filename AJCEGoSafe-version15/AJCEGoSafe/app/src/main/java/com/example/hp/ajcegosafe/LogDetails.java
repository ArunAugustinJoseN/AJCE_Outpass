package com.example.hp.ajcegosafe;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LogDetails extends AppCompatActivity {

    ArrayList<LogDetailsContent> logProfile = new ArrayList<LogDetailsContent>();
    LogDetailsContent log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Log Details");

        ListView listView=(ListView)findViewById(R.id.listView1);

//
//        dbHelp db = new dbHelp(this);
//        Cursor data = db.getLogDetails();
//        int numRows = data.getCount();
////        if (numRows == 0){
////            Toast.makeText(StudentOutpassHistory.this,"There is nothing to show..!",Toast.LENGTH_LONG).show();
////        }
////        else{
//        while(data.moveToNext()){
//            log = new LogDetailsContent(data.getString(2), data.getString(3),String.valueOf(numRows));
//            logProfile.add(log);
//        }
        LogDetaild_listAdapter adapter = new LogDetaild_listAdapter(this,R.layout.colmn_row,logProfile);
        listView.setAdapter(adapter);

//        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id)
            {
                int pos=position+1;
//                Toast.makeText(LogDetails.this, Integer.toString(pos)+" Clicked", Toast.LENGTH_SHORT).show();
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

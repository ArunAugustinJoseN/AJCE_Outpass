package com.example.hp.ajcegosafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminOutpass extends AppCompatActivity {
    ListView userList;
    UserCustomAdapter userAdapter;
    ArrayList<OutpassData> userArray = new ArrayList<OutpassData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_outpass);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Outpass Request");

        try {
            dbHelp db = new dbHelp(this);
            userArray = db.getOutpass();

            userAdapter = new UserCustomAdapter(AdminOutpass.this, R.layout.admin_outpass_lstview,
                    userArray);
            userList = (ListView) findViewById(R.id.lstView);
            userList.setItemsCanFocus(false);
            userList.setAdapter(userAdapter);

            userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View v,
                                        final int position, long id) {
                    Log.i("List View Clicked", "**********");
                    Toast.makeText(AdminOutpass.this,
                            "List View Clicked:" + position, Toast.LENGTH_LONG)
                            .show();
                }
            });
        }
        catch (Exception e){
            Toast.makeText(AdminOutpass.this, "Nothing to show..!",
                    Toast.LENGTH_LONG).show();
        }

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

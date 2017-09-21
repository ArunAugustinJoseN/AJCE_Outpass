package com.example.hp.ajcegosafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.*;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecurityHome extends AppCompatActivity {

    ListView userList;
    SecurityCustomAdapter securityAdapter;
    ArrayList<SecurityOutpassData> securityArray = new ArrayList<SecurityOutpassData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_home);
        setTitle("Outpass");

//        try {
//            dbHelp db = new dbHelp(this);
//            securityArray = db.getOutpassForSecurity();
//
//            securityAdapter = new SecurityCustomAdapter(SecurityHome.this, R.layout.security_outpass_lstview, securityArray);
//            userList = (ListView) findViewById(R.id.securityLstView);
//            userList.setItemsCanFocus(false);
//            userList.setAdapter(securityAdapter);
//
//            userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//                public void onItemClick(AdapterView<?> parent, View v,
//                                        final int position, long id) {
//                    Log.i("List View Clicked", "**********");
//                    Toast.makeText(SecurityHome.this,
//                            "List View Clicked:" + position, Toast.LENGTH_LONG)
//                            .show();
//                }
//            });
//        }
//        catch (Exception e){
//            Toast.makeText(SecurityHome.this, "Nothing to show..!",
//                    Toast.LENGTH_LONG).show();
//        }

    }

}

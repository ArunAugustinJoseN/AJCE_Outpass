package com.example.hp.ajcegosafe;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchStudent extends AppCompatActivity {

    Spinner spinner,spinner2,spinner3;
    TextView tv,tv2,tv3;

    ListView listView;
    ArrayList<String> list;
    ArrayList<Profile> listProfile = new ArrayList<Profile>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Students");

        listView = (ListView) findViewById(R.id.ListView);
        list = new ArrayList<>();

        dbHelp db=new dbHelp(this);
        listProfile = db.getSearch();
        for (int i=0;i<listProfile.size();i++){
            list.add(listProfile.get(i).getName());
        }
        ArrayAdapter<String> ada = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(ada);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String name = listView.getItemAtPosition(i).toString();
                Intent intent = new Intent(SearchStudent.this, StudentDelete.class);
                intent.putExtra("s_name", name);
                startActivity(intent);
//                Toast.makeText(SearchStudent.this,""+name, Toast.LENGTH_SHORT).show();

            }
        });

        tv = (TextView)findViewById(R.id.textView);
        tv2 = (TextView)findViewById(R.id.textView2);
        tv3 = (TextView)findViewById(R.id.textView3);

         /* Stream spinner */

        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String year = parent.getItemAtPosition(position).toString();
                tv.setText(year);
                if (year.equals("Stream")){
                    View v1 = spinner.getSelectedView();
                    ((TextView)v1).setTextColor(Color.GRAY);
                }
                else {
                    View v1 = spinner.getSelectedView();
                    ((TextView)v1).setTextColor(Color.BLACK);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        loadStreamSpinnerData();

        /* Department Spinner click listener */
        spinner2 = (Spinner) findViewById(R.id.spinner2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String batch = parent.getItemAtPosition(position).toString();
                tv.setText(batch);
                if (batch.equals("Branch")){
                    View v = spinner2.getSelectedView();
                    ((TextView)v).setTextColor(Color.GRAY);
                }
                else {
                    View v = spinner2.getSelectedView();
                    ((TextView)v).setTextColor(Color.BLACK);
                }

                // Showing selected spinner item
                // Toast.makeText(parent.getContext(), "You selected: " + batch,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        // Loading spinner data from database
        loadDeptSpinnerData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);

        MenuItem searchItem=menu.findItem(R.id.item_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem) ;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> templist = new ArrayList<String>();

                for (String temp : list){
                    if (temp.toLowerCase().contains(newText.toLowerCase())){
                        templist.add(temp);
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(SearchStudent.this,android.R.layout.simple_list_item_1,templist);
                listView.setAdapter(adapter);

                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
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

    /* Stream spinner */

    private void loadStreamSpinnerData() {
        // database handler
        dbHelp db = new dbHelp(getApplicationContext());
        // Toast.makeText(this,"id:"+stream, Toast.LENGTH_LONG).show();
        // Spinner Drop down elements
        List<String> streams = db.getAllStrams();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, streams){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    /* Department spinner */

    private void loadDeptSpinnerData() {
        // database handler
        dbHelp db = new dbHelp(getApplicationContext());

        tv = (TextView)findViewById(R.id.textView);
        String stream = tv.getText().toString();
        // Toast.makeText(this,"id:"+stream, Toast.LENGTH_LONG).show();
        // Spinner Drop down elements
        List<String> batches = db.getAllBatches(stream);

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, batches){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv2 = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv2.setTextColor(Color.GRAY);
                }
                else {
                    tv2.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        // Drop down layout style - list view with radio button
        dataAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter);
    }

}


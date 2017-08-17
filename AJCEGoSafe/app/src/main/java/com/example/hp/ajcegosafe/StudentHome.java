package com.example.hp.ajcegosafe;

/**
 * Created by hp on 15-08-2017.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StudentHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public int layoutpressed = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.Linear_layout);
        final LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.Linear_layout2);
        final LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.Linear_layout3);
        final LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.Linear_layout4);
//        linearLayout.setOnClickListener(new View.OnClickListener() {

//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(getApplicationContext(), "Hello World!",
//                        Toast.LENGTH_LONG).show();
//
//            }
//        });
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN){
                    linearLayout.setBackgroundResource(R.drawable.rounded_edges_pressed);
                    linearLayout.setPadding(10, 10, 10, 10);
                    layoutpressed = arg0.getId();
                }
                else if (arg1.getAction()== MotionEvent.ACTION_UP){
                    linearLayout.setBackgroundResource(R.drawable.rounded_edges_normal);
                    linearLayout.setPadding(10, 10, 10, 10);
                    if(layoutpressed == arg0.getId()){
                        Intent intent = new Intent(StudentHome.this, StudentOutpass.class);
                        startActivity(intent);
                    }
                }

                return true;
            }
        });

        linearLayout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN){
                    linearLayout2.setBackgroundResource(R.drawable.rounded_edges_pressed);
                    linearLayout2.setPadding(10, 10, 10, 10);
                    layoutpressed = arg0.getId();
                }
                else if (arg1.getAction()== MotionEvent.ACTION_UP){
                    linearLayout2.setBackgroundResource(R.drawable.rounded_edges_normal);
                    linearLayout2.setPadding(10, 10, 10, 10);
                    if(layoutpressed == arg0.getId()){
                        Toast.makeText(getApplicationContext(), "Status",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                return true;
            }
        });

        linearLayout3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN){
                    linearLayout3.setBackgroundResource(R.drawable.rounded_edges_pressed);
                    linearLayout3.setPadding(10, 10, 10, 10);
                    layoutpressed = arg0.getId();
                }
                else if (arg1.getAction()== MotionEvent.ACTION_UP){
                    linearLayout3.setBackgroundResource(R.drawable.rounded_edges_normal);
                    linearLayout3.setPadding(10, 10, 10, 10);
                    if(layoutpressed == arg0.getId()){
                        Toast.makeText(getApplicationContext(), "History..",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                return true;
            }
        });

        linearLayout4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN){
                    linearLayout4.setBackgroundResource(R.drawable.rounded_edges_pressed);
                    linearLayout4.setPadding(10, 10, 10, 10);
                    layoutpressed = arg0.getId();
                }
                else if (arg1.getAction()== MotionEvent.ACTION_UP){
                    linearLayout4.setBackgroundResource(R.drawable.rounded_edges_normal);
                    linearLayout4.setPadding(10, 10, 10, 10);
                    if(layoutpressed == arg0.getId()){
                        Toast.makeText(getApplicationContext(), "Reminder",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                return true;
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(StudentHome.this, StudentProfile.class);
            startActivity(intent);
        } else if (id == R.id.nav_dates) {

        } else if (id == R.id.nav_notifications) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_help) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


package com.example.hp.ajcegosafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AdminHome extends AppCompatActivity {
    public int layoutpressed = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        setTitle(R.string.Admin_home);

        final LinearLayout linearLayout4 = (LinearLayout) findViewById(R.id.Linear_layout4);

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
                        Intent intent = new Intent(AdminHome.this, CourseStream.class);
                        startActivity(intent);
                    }
                }

                return true;
            }
        });
    }
}

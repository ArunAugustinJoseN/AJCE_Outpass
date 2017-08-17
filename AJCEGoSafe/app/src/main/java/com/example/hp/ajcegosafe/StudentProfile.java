package com.example.hp.ajcegosafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

public class StudentProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        setTitle(R.string.Student_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView myImage = (ImageView) findViewById(R.id.imageView);
        myImage.setAlpha(0.2f);
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

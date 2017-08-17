package com.example.hp.ajcegosafe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class LoginActivity extends AppCompatActivity {

    private String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.title_activity_login);

        this.arraySpinner = new String[] {
                "1", "2", "3"
        };
        final Spinner s = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                int aa = Integer.parseInt(s.getSelectedItem().toString());
                if (aa==1) {
                    Intent intent = new Intent(LoginActivity.this, StudentHome.class);
                    startActivity(intent);
                }
                else if(aa==2)
                {
                    Intent intent = new Intent(LoginActivity.this, AdminHome.class);
                    startActivity(intent);
                }
            }
        });
    }
}

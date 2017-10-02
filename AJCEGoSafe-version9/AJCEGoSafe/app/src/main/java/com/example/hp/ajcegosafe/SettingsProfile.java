package com.example.hp.ajcegosafe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Edit Profile");

       final EditText mob = (EditText) findViewById(R.id.editText4);
        Button save = (Button) findViewById(R.id.button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mob.getText().toString().trim().length()==0){
                    mob.setError("Enter Mobile number..");
                    mob.requestFocus();
                }
                else if(mob.getText().toString().trim().length() != 10){
                    mob.setError("Enter a valid Mobile number..");
                    mob.requestFocus();
                }
                else {

                    String mobno = mob.getText().toString();

                    String admno = getIntent().getExtras().getString("admno");

//                    Toast.makeText(SettingsProfile.this, "dmission no:" + admno + "mob" + mobno, Toast.LENGTH_SHORT).show();
                    dbHelp dbH = new dbHelp(getApplicationContext());
                    SQLiteDatabase db = dbH.getWritableDatabase();
                    db.execSQL("UPDATE " + dbHelp.TABLE_NAME + " SET " + dbHelp.STNO + " = " + mobno + " WHERE " + dbHelp.ADNO2 + " = " + admno);
                    db.close();

                    TextView updated = (TextView) findViewById(R.id.txtupdated);
                    updated.setText("Your Mobile number is Updated.");
                    mob.setText("");
                }
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

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}

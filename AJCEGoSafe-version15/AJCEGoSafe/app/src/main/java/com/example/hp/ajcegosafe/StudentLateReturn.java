package com.example.hp.ajcegosafe;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class StudentLateReturn extends AppCompatActivity {

    TextView t1,t2;
    RadioGroup permission;
    RadioButton radio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_late_return);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Late Note");

        t1 = (TextView) findViewById(R.id.txtLateAdno);
        t2 = (TextView) findViewById(R.id.txtLateName);
        Button b = (Button) findViewById(R.id.btnLateSubmit);
        permission = (RadioGroup)findViewById(R.id.radioPermission);

        final Integer oid = getIntent().getExtras().getInt("oid");
//        Toast.makeText(AdminOutpassDetails.this, " Clicked "+adno+"  "+date, Toast.LENGTH_SHORT).show();

//        dbHelp dbH = new dbHelp(getApplicationContext());
//        SQLiteDatabase db = dbH.getWritableDatabase();
//        String query = "SELECT * from " + dbHelp.TABLE_NAME1+" WHERE "+dbHelp.OID+" = "+oid;
//        Cursor c = db.rawQuery(query, null);
//        c.moveToFirst();
//        Integer pid = Integer.parseInt(c.getString(c.getColumnIndex(dbHelp.PID)));
//        db.close();
//
//        dbHelp dbH1 = new dbHelp(getApplicationContext());
//        SQLiteDatabase db1 = dbH1.getWritableDatabase();
//        String query1 = "SELECT * from " + dbHelp.TABLE_NAME+" WHERE "+dbHelp.ID+" = "+pid;
//        Cursor c1 = db1.rawQuery(query1, null);
//        c1.moveToFirst();
//        String adno = c1.getString(c1.getColumnIndex(dbHelp.ADNO2));
//        final String name = c1.getString(c1.getColumnIndex(dbHelp.NAME));
//        db1.close();
//
//        t1.setText(String.valueOf(adno));
//        t2.setText(name);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = permission.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radio = (RadioButton) findViewById(selectedId);

                if (permission.getCheckedRadioButtonId() == -1)
                {
                    if (permission.getCheckedRadioButtonId() == -1) {
                        Toast.makeText(StudentLateReturn.this, " Please choose one item", Toast.LENGTH_SHORT).show();
                        permission.requestFocus();
                    }
                }

                String permission = radio.getText().toString();

                if (permission.equals("Have Permission")){

                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                    String formattedDate = df.format(c.getTime());

//                    dbHelp db = new dbHelp(StudentLateReturn.this);
//                    db.changeStatusToFour(oid, formattedDate);
//
//                    Snackbar.make(v, name + " Return back.", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                    Toast.makeText(StudentLateReturn.this, " have permssion", Toast.LENGTH_SHORT).show();
                }

                else{
                    Calendar c = Calendar.getInstance();
                    SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                    String formattedDate = df.format(c.getTime());

//                    dbHelp db = new dbHelp(StudentLateReturn.this);
//                    db.changeStatusToFive(oid, formattedDate);
//
//                    Snackbar.make(v, name + " Return back.", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                    Toast.makeText(StudentLateReturn.this, " Don't have permission", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(StudentLateReturn.this, AdminOutpassDetails.class);
                startActivity(intent);

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

package com.example.hp.ajcegosafe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class StudentOutpassStatus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_outpass_status);
        setTitle("Late Note");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView t1 = (TextView) findViewById(R.id.txtStatus);

        String adno = getIntent().getExtras().getString("admno");

        dbHelp dbH = new dbHelp(getApplicationContext());
        SQLiteDatabase db = dbH.getWritableDatabase();

        Integer admno = Integer.parseInt(adno);
        String query = "SELECT * from " + dbHelp.TABLE_NAME+" WHERE "+dbHelp.ADNO2+" = "+admno;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        Integer id = Integer.parseInt(c.getString(c.getColumnIndex(dbHelp.ID)));
        db.close();

        dbHelp dbH1 = new dbHelp(getApplicationContext());
        SQLiteDatabase db1 = dbH1.getWritableDatabase();
        String query1 = "SELECT * from " + dbHelp.TABLE_NAME1+" WHERE "+dbHelp.PID+" = "+id+" ORDER BY "+dbHelp.OID+" DESC limit 1";
        Cursor c1 = db1.rawQuery(query1, null);
        c1.moveToFirst();
        Integer status = Integer.parseInt(c1.getString(c1.getColumnIndex(dbHelp.STATUS)));
        db1.close();

        if (status == 0){
            t1.setText("Requested");
        }
        else if (status == 1){
            t1.setText("Approved");
        }
        else if (status == 2){
            t1.setText("Rejected");
        }
        else if (status == 3){
            t1.setText("Went Out");
        }
        else if (status == 4){
            t1.setText("Returned");
        }
        else if (status == 5){
            t1.setText("Late");
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

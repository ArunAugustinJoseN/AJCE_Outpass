package com.example.hp.ajcegosafe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class AdminOutpassDetails extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_outpass_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Outpass Details");

        t1 = (TextView)findViewById(R.id.txtAdmno);
        t2 = (TextView) findViewById(R.id.txtName);
        t3 = (TextView) findViewById(R.id.txtTol);
        t4 = (TextView) findViewById(R.id.txtTor);
        t5 = (TextView) findViewById(R.id.txtPur);
        t6 = (TextView) findViewById(R.id.txtDol);
        t7 = (TextView) findViewById(R.id.txtDor);

        Integer oid = getIntent().getExtras().getInt("oid");
        Integer adno = getIntent().getExtras().getInt("adno");
        String date = getIntent().getExtras().getString("date");
//        Toast.makeText(AdminOutpassDetails.this, " Clicked "+oid, Toast.LENGTH_SHORT).show();

        dbHelp dbH = new dbHelp(getApplicationContext());
        SQLiteDatabase db = dbH.getWritableDatabase();
        String query = "SELECT * from " + dbHelp.TABLE_NAME+" WHERE "+dbHelp.ADNO2+" = "+adno;
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        Integer id = Integer.parseInt(c.getString(c.getColumnIndex(dbHelp.ID)));
        String name = c.getString(c.getColumnIndex(dbHelp.NAME));
        db.close();

        dbHelp dbH1 = new dbHelp(getApplicationContext());
        SQLiteDatabase db1 = dbH1.getWritableDatabase();
        String query1 = "SELECT * from " + dbHelp.TABLE_NAME1+" WHERE "+dbHelp.OID+" = "+oid;
        Cursor c1 = db1.rawQuery(query1, null);
        c1.moveToFirst();
        String tol = c1.getString(c1.getColumnIndex(dbHelp.TOL));
        String tor = c1.getString(c1.getColumnIndex(dbHelp.TOR));
        String pur = c1.getString(c1.getColumnIndex(dbHelp.PUR));
        String dol = c1.getString(c1.getColumnIndex(dbHelp.DOL));
        String dor = c1.getString(c1.getColumnIndex(dbHelp.DOR));
        db1.close();

        t1.setText(String.valueOf(adno));
        t2.setText(name);
        t3.setText(tol);
        t4.setText(tor);
        t5.setText(pur);
        t6.setText(dol);
        t7.setText(dor);
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

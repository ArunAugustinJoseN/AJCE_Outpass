package com.example.hp.ajcegosafe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StudentProfile extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        setTitle(R.string.Student_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Integer logid = getIntent().getExtras().getInt("logid");
        String admno = getIntent().getExtras().getString("admno");
        Toast.makeText(this,"id:"+admno, Toast.LENGTH_LONG).show();

        t1 = (TextView)findViewById(R.id.textView);
        t4 = (TextView)findViewById(R.id.textView10);
        t5 = (TextView)findViewById(R.id.textView11);
        t6 = (TextView)findViewById(R.id.textView12);
        t7 = (TextView)findViewById(R.id.textView6);
        t8 = (TextView)findViewById(R.id.textView7);


        dbHelp dbH=new dbHelp(getApplicationContext());
        SQLiteDatabase db=dbH.getWritableDatabase();
        String qu = "select * from "+dbHelp.TABLE_NAME+" WHERE "+dbHelp.LOGID+"="+logid;
        Cursor cur = db.rawQuery(qu, null);
        cur.moveToNext();
        String name = cur.getString(cur.getColumnIndex(dbHelp.NAME));
        String dob = cur.getString(cur.getColumnIndex(dbHelp.DOB));
        String gender = cur.getString(cur.getColumnIndex(dbHelp.GEN));
        String stno = cur.getString(cur.getColumnIndex(dbHelp.STNO));
        String ptno = cur.getString(cur.getColumnIndex(dbHelp.PTNO));

        t1.setText(name);
        t4.setText(dob);
        t5.setText(gender);
        t6.setText(admno);
        t7.setText(stno);
        t8.setText(ptno);

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

package com.example.hp.ajcegosafe;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        new CountDownTimer(5000, 1000) {
            public void onFinish() {


//                dbHelp dbH1=new dbHelp(getApplicationContext());
//                SQLiteDatabase db1=dbH1.getWritableDatabase();
//                Cursor c1= db1.rawQuery("SELECT * from "+dbHelp.TABLE_START,null);
//
//                StringBuffer buffer=new StringBuffer();
//        while(c1.moveToNext())
//        {
//            buffer.append(" id:"+c1.getString(c1.getColumnIndex(dbHelp.SID))+"\n");
//            buffer.append("adno:"+c1.getString(c1.getColumnIndex(dbHelp.ADNO3))+"\n");
//            buffer.append("role:"+c1.getString(c1.getColumnIndex(dbHelp.ROLE_ID))+"\n\n\n");
//
//        }
//        showMessege("Student details",buffer.toString());
//                db1.close();

                Button tv = (Button) findViewById(R.id.Btnajce);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dbHelp db=new dbHelp(StartActivity.this);
                        db.deleteStartTableContent();
                    }
                });
                dbHelp dbH = new dbHelp(getApplicationContext());
                SQLiteDatabase db = dbH.getWritableDatabase();
                String qu = "select * from " + dbHelp.TABLE_START;
                Cursor cur = db.rawQuery(qu, null);
                cur.moveToNext();
                if (cur.getCount() != 0) {
                    Integer rid = Integer.parseInt(cur.getString(cur.getColumnIndex(dbHelp.ROLE_ID)));
                    if (rid == 0) {
                        Intent startActivity = new Intent(StartActivity.this, AdminHome.class);
                        startActivity(startActivity);
                        finish();
                    } else if (rid == 1) {
                        Intent startActivity = new Intent(StartActivity.this, SecuritiesHome.class);
                        startActivity(startActivity);
                        finish();
                    } else {
                        Intent startActivity = new Intent(StartActivity.this, StudentHome.class);
                        startActivity(startActivity);
                        finish();
                    }
                }

                else {
                    Intent startActivity = new Intent(StartActivity.this, LoginActivity.class);
                    startActivity(startActivity);
                    finish();
                }
            }

            public void onTick(long millisUntilFinished) {
            }

        }.start();
    }

    public void showMessege(String title,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

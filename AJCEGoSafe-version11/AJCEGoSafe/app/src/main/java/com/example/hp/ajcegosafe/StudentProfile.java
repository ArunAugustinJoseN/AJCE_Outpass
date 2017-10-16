package com.example.hp.ajcegosafe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StudentProfile extends AppCompatActivity {


    private Context mContext=StudentProfile.this;

    private static final int REQUEST = 112;

    TextView t1,t2,t3,t4,t5,t6,t7,t8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        setTitle(R.string.Student_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Integer logid = getIntent().getExtras().getInt("logid");
        String admno = getIntent().getExtras().getString("admno");
        //Toast.makeText(this,"id:"+admno, Toast.LENGTH_LONG).show();

        t1 = (TextView)findViewById(R.id.textView);
        t2 = (TextView)findViewById(R.id.textView3);
        t3 = (TextView)findViewById(R.id.textView4);
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
        Integer branch_id = cur.getInt(cur.getColumnIndex(dbHelp.BID2));
        String dob = cur.getString(cur.getColumnIndex(dbHelp.DOB));
        String year = cur.getString(cur.getColumnIndex(dbHelp.YEAR));
        String gender = cur.getString(cur.getColumnIndex(dbHelp.GEN));
        String stno = cur.getString(cur.getColumnIndex(dbHelp.STNO));
        String ptno = cur.getString(cur.getColumnIndex(dbHelp.PTNO));
        db.close();

        dbHelp dbH1=new dbHelp(getApplicationContext());
        SQLiteDatabase db1=dbH1.getWritableDatabase();
        String query = "select * from "+dbHelp.TABLE_BATCH+" WHERE "+dbHelp.BID+" = "+branch_id;
        Cursor c = db1.rawQuery(query, null);
        c.moveToNext();
        String branch_name = c.getString(c.getColumnIndex(dbHelp.BNAME));
        db1.close();

        t1.setText(name);
        t2.setText(branch_name);
        t3.setText(year);
        t4.setText(dob);
        t5.setText(gender);
        t6.setText(admno);
        t7.setText(stno);
        t8.setText(ptno);


        ImageView stcall = (ImageView) findViewById(R.id.imgStudCalling);

        stcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = t7.getText().toString();
                if (Build.VERSION.SDK_INT >= 23) {
                    String[] PERMISSIONS = {android.Manifest.permission.CALL_PHONE};
                    if (!hasPermissions(mContext, PERMISSIONS)) {
                        ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST );
                    } else {
                        makeCall(number);
                    }
                } else {
                    makeCall(number);
                }

            }
        });

        ImageView ptcall = (ImageView) findViewById(R.id.imgParCalling);

        ptcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = t8.getText().toString();
                if (Build.VERSION.SDK_INT >= 23) {
                    String[] PERMISSIONS = {android.Manifest.permission.CALL_PHONE};
                    if (!hasPermissions(mContext, PERMISSIONS)) {
                        ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST );
                    } else {
                        makeCall(number);
                    }
                } else {
                    makeCall(number);
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
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    String number = t7.getText().toString();
                    makeCall(number);
                } else {
                    Toast.makeText(mContext, "The app was not allowed to call.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
    public void makeCall(String number)
    {
        Intent intent4=new Intent(Intent.ACTION_CALL);
        intent4.setData(Uri.parse("tel:"+number));
        startActivity(intent4);
    }


}

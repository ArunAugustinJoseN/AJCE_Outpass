package com.example.hp.ajcegosafe;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.title_activity_login);
//
//        dbHelp db=new dbHelp(this);
//        db.insertRole();
//        db.admin();
//        db.insertBranch();
//        db.insertBatch();


        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText et = (EditText)findViewById(R.id.editText4);
                String aa = et.getText().toString();
                EditText et2 = (EditText)findViewById(R.id.editText2);
                String pass = et2.getText().toString();
                String admin = "Admin";
                if (aa.equals(admin)) {
                    Intent intent = new Intent(LoginActivity.this, AdminHome.class);
                    startActivity(intent);
                }
                else /*if(aa.equals("A"))*/
                {
                    dbHelp dbH=new dbHelp(getApplicationContext());
                    SQLiteDatabase db=dbH.getWritableDatabase();
                    String qu = "select * from "+dbHelp.TABLE_NAME3+" WHERE "+dbHelp.ADNO+"='"+aa+"' AND "+dbHelp.PASS+"='"+pass+"'";
                    Cursor cur = db.rawQuery(qu, null);
                    cur.moveToNext();
                    Integer logid= cur.getInt(cur.getColumnIndex(dbHelp.LID));
                    Toast.makeText(LoginActivity.this,"no:"+aa+" pas:"+qu+" "+cur.getCount(), Toast.LENGTH_LONG).show();

                    if (cur.getCount()!=0) {
                        Intent intent = new Intent(LoginActivity.this, StudentHome.class);
                        intent.putExtra("logid", logid);
                        intent.putExtra("admno", aa);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}

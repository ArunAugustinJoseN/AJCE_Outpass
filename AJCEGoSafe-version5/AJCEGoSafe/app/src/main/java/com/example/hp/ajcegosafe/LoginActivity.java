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
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private String[] arraySpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(R.string.title_activity_login);

//        dbHelp db=new dbHelp(this);
//        db.insertRole();
//        db.admin();
//        db.insertBranch();
//        db.insertBatch();
        final TextView error = (TextView) findViewById(R.id.textViewError);
        final EditText et = (EditText)findViewById(R.id.editText);
        final EditText et1 = (EditText)findViewById(R.id.editText2);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                if(et.getText().toString().trim().length()==0 && et1.getText().toString().trim().length()==0){
                    et.setError("Enter Admission No. and Password");
                    et.requestFocus();
                }
                else if(et.getText().toString().trim().length()==0){
                    et.setError("Admission No. is not entered");
                    et.requestFocus();
                }
                else if(et1.getText().toString().trim().length()==0){
                    et1.setError("Password is not entered");
                    et1.requestFocus();
                }
                else {

                    String adno = et.getText().toString();
                    adno = adno.toLowerCase();
                    String pass = et1.getText().toString();
                    try {
                        dbHelp dbH = new dbHelp(getApplicationContext());
                        SQLiteDatabase db = dbH.getWritableDatabase();
                        String qu = "select * from " + dbHelp.TABLE_NAME3 + " WHERE " + dbHelp.ADNO + "='" + adno + "' AND " + dbHelp.PASS + "='" + pass + "'";
                        Cursor cur = db.rawQuery(qu, null);
                        cur.moveToNext();
                        Integer logid = cur.getInt(cur.getColumnIndex(dbHelp.LID));
                        Integer rid = cur.getInt(cur.getColumnIndex(dbHelp.RID2));
                        db.close();
                        // Toast.makeText(LoginActivity.this,"no:"+aa+" pas:"+qu+" "+cur.getCount(), Toast.LENGTH_LONG).show();

                        if (cur.getCount() != 0 && rid == 0) {
                            Intent intent = new Intent(LoginActivity.this, AdminHome.class);
                            startActivity(intent);
                            et.setText("");
                            et1.setText("");
                            error.setText("");

                        } else if (cur.getCount() != 0 && rid == 2) {
                            Intent intent = new Intent(LoginActivity.this, StudentHome.class);
                            intent.putExtra("logid", logid);
                            intent.putExtra("admno", adno);
                            startActivity(intent);
                            et.setText("");
                            et1.setText("");
                            error.setText("");
                        }
                    } catch (Exception e) {
                        TextView error = (TextView) findViewById(R.id.textViewError);
                        error.setText("Admission No. or Password is incorrect..!");
                        et.setText("");
                        et1.setText("");
                    }
                }
            }
        });
    }

    /* Validation function */
}

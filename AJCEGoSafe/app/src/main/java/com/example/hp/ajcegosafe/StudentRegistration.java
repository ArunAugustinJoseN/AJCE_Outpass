package com.example.hp.ajcegosafe;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.id;

public class StudentRegistration extends AppCompatActivity {
    private String[] branchSpinner;

    Button b;
    EditText t1,t2,t3,t4,t5;
    private int sYear;
    private int sMonth;
    private int sDay;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        setTitle(R.string.Student_registration);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.branchSpinner = new String[] {
                "Branch","ME", "MCA", "CE","AU","IT"
        };
        final Spinner s = (Spinner) findViewById(R.id.spinner4);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, branchSpinner);
        s.setAdapter(adapter1);

        t1 = (EditText)findViewById(R.id.editText1);
        t2 = (EditText) findViewById(R.id.editText2);
        t3 = (EditText) findViewById(R.id.editText7);
        t4 = (EditText) findViewById(R.id.editText3);
        t5 = (EditText) findViewById(R.id.editText6);
        b = (Button)findViewById(R.id.button3);

        /* Culender */
        ImageView ib = (ImageView)findViewById(R.id.imageView);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                fromupdateDisplay();

            }
        });

        /* Button click event */

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String admno = t1.getText().toString();
                String name = t2.getText().toString();
                String dob = t3.getText().toString();
                String mob = t4.getText().toString();
                String pmob = t5.getText().toString();
                String password = "0000";
                String roleid = "2";
                String gender="male";

                dbHelp dbH=new dbHelp(getApplicationContext());
                SQLiteDatabase db=dbH.getWritableDatabase();

                ContentValues value2 = new ContentValues();
                value2.put(dbHelp.RID2,roleid);
                value2.put(dbHelp.ADNO,admno);
                value2.put(dbHelp.PASS,password);

                db.insert(dbHelp.TABLE_NAME3,null,value2);
                Toast.makeText(StudentRegistration.this,"Inserted in login", Toast.LENGTH_LONG).show();

                dbHelp dbH1=new dbHelp(getApplicationContext());
                SQLiteDatabase db1=dbH1.getWritableDatabase();
                Cursor c1= db1.rawQuery("SELECT * from "+dbHelp.TABLE_NAME,null);
                StringBuffer buffer=new StringBuffer();
        while(c1.moveToNext())
        {
            buffer.append(" id:"+c1.getString(c1.getColumnIndex(dbHelp.ID))+"\n");
            buffer.append("admo:"+c1.getString(c1.getColumnIndex(dbHelp.NAME))+"\n");
            buffer.append("pass:"+c1.getString(c1.getColumnIndex(dbHelp.DOB))+"\n");
            buffer.append("rid:"+c1.getString(c1.getColumnIndex(dbHelp.STNO))+"\n");
        }
        showMessege("Student details",buffer.toString());

                 dbHelp dbH2=new dbHelp(getApplicationContext());
                SQLiteDatabase db2=dbH2.getWritableDatabase();

                String query = "SELECT "+dbHelp.LID+" from "+dbHelp.TABLE_NAME3+" order by "+dbHelp.LID+" DESC limit 1";
                Cursor c = db2.rawQuery(query,null);
                c.moveToFirst();
                String logid = c.getString(0).toString(); //The 0 is the column index, we only have 1 column, so the index is 0

                dbHelp dbH3=new dbHelp(getApplicationContext());
                SQLiteDatabase db3=dbH3.getWritableDatabase();
                ContentValues value=new ContentValues();
                value.put(dbHelp.LOGID,logid);
                value.put(dbHelp.NAME,name);
                value.put(dbHelp.DOB,dob);
                value.put(dbHelp.GEN,gender);
                value.put(dbHelp.STNO,mob);
                value.put(dbHelp.PTNO,pmob);
                db3.insert(dbHelp.TABLE_NAME,null,value);

                Toast.makeText(StudentRegistration.this,"Inserted in profile", Toast.LENGTH_LONG).show();
                clearText();

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

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, from_dateListener, sYear, sMonth, sDay);
        }
        return null;
    }

    //update month day year
    private void fromupdateDisplay() {
        EditText t3 = (EditText)findViewById(R.id.editText7);
        t3.setText(//this is the edit text where you want to show the selected date
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(sDay).append("-")
                        .append(sMonth + 1).append("-")
                        .append(sYear).append(""));
    }
    // the call back received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener from_dateListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    sYear = year;
                    sMonth = monthOfYear;
                    sDay = dayOfMonth;
                    fromupdateDisplay();
                }
            };


    public void showMessege(String title,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText(){
        t1.setText("");
        t2.setText("");
        t4.setText("");
        t5.setText("");
        t1.requestFocus();
    }
}

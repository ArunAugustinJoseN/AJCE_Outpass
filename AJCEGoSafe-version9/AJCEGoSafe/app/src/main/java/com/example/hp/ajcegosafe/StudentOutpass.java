package com.example.hp.ajcegosafe;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class StudentOutpass extends AppCompatActivity {
    public int layoutpressed = -1;
    private Button mPickDate;
    private int sYear,eYear;
    private int sMonth,eMonth;
    private int sDay,eDay;
    static final int DATE_DIALOG_ID = 0;
    static final int END_DATE_DIALOG_ID = 1;

    EditText et1,et2,et3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_outpass);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.Student_outpass);
        // OR You can also use the line below
        // setTitle("MyTitle")



        ImageView ib = (ImageView)findViewById(R.id.imageView);
        ImageView ib2 = (ImageView)findViewById(R.id.imageView2);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.Linear_layout5);
        final LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.Linear_layout6);

        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN){
                    linearLayout.setBackgroundResource(R.drawable.rounded_edges_pressed);
                    linearLayout.setPadding(10, 10, 10, 10);
                    layoutpressed = arg0.getId();
                }
                else if (arg1.getAction()== MotionEvent.ACTION_UP){
                    linearLayout.setBackgroundResource(R.drawable.rounded_edges_normal);
                    linearLayout.setPadding(10, 10, 10, 10);
                }

                return true;
            }
        });
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                fromupdateDisplay();
            }
        });



        linearLayout2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN){
                    linearLayout2.setBackgroundResource(R.drawable.rounded_edges_pressed);
                    linearLayout2.setPadding(10, 10, 10, 10);
                    layoutpressed = arg0.getId();
                }
                else if (arg1.getAction()== MotionEvent.ACTION_UP){
                    linearLayout2.setBackgroundResource(R.drawable.rounded_edges_normal);
                    linearLayout2.setPadding(10, 10, 10, 10);
                }

                return true;
            }
        });
        ib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(END_DATE_DIALOG_ID);
                toupdateDisplay();

            }
        });

//        final Calendar c1 = Calendar.getInstance();
//        eYear = c1.get(Calendar.YEAR);
//        eMonth = c1.get(Calendar.MONTH);
//        eDay = c1.get(Calendar.DAY_OF_MONTH);


        // display the current date

        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText5);
        et3 = (EditText)findViewById(R.id.editText2);

        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String admno = getIntent().getExtras().getString("admno");
                Integer adno = Integer.parseInt(admno);

                /* validation */

                if(et1.getText().toString().trim().length()==0){
                    et1.setError("Select leaving date");
                    et1.requestFocus();
                }
                else if(et2.getText().toString().trim().length()==0){
                    et2.setError("Select return date");
                    et2.requestFocus();
                }

                else if(et3.getText().toString().trim().length()==0){
                    et3.setError("Enter purpose");
                    et3.requestFocus();
                }
                else {

                    String dol = et1.getText().toString();
                    String dor = et2.getText().toString();
                    String pur = et3.getText().toString();
                    String tol = "00:00";
                    String tor = "00:00";
                    Integer status = 0;
                    String date = dol;
//                    Calendar calendar = Calendar.getInstance();
//                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//                    String date = df.format(calendar.getTime());

                    try {

                        /*Get profile id from profile table */
                        dbHelp dbH2 = new dbHelp(getApplicationContext());
                        SQLiteDatabase db2 = dbH2.getWritableDatabase();
                        String query = "SELECT * from " + dbHelp.TABLE_NAME + " WHERE " + dbHelp.ADNO2 + "=" + adno;
                        Cursor c = db2.rawQuery(query, null);
                        c.moveToFirst();
                        Integer pid = c.getInt(c.getColumnIndex(dbHelp.ID));
                        String sname = c.getString(c.getColumnIndex(dbHelp.NAME));
                        db2.close();


                        dbHelp dbH3 = new dbHelp(getApplicationContext());
                        SQLiteDatabase db3 = dbH3.getWritableDatabase();
                        String query3 = "SELECT * from " + dbHelp.TABLE_NAME1+ " WHERE "+dbHelp.PID+"="+pid+" AND "+dbHelp.DOL+"='"+date+"'";
                        Cursor cursor = db3.rawQuery(query3, null);

                        cursor.moveToFirst();
//                        Toast.makeText(StudentOutpass.this, "" + query3 + " count=" + cursor.getCount(), Toast.LENGTH_LONG).show();
                        db3.close();
                        if (cursor.getCount() == 0) {


                            dbHelp dbH4 = new dbHelp(getApplicationContext());
                            SQLiteDatabase db4 = dbH4.getWritableDatabase();
                            ContentValues value = new ContentValues();
                            value.put(dbHelp.PID, pid);
                            value.put(dbHelp.SNAME, sname);
                            value.put(dbHelp.DOL, dol);
                            value.put(dbHelp.DOR, dor);
                            value.put(dbHelp.PUR, pur);
                            value.put(dbHelp.TOL, tol);
                            value.put(dbHelp.TOR, tor);
                            value.put(dbHelp.STATUS, status);
                            value.put(dbHelp.DATE, date);
                            db4.insert(dbHelp.TABLE_NAME1, null, value);
                            db4.close();
                            Intent intent = new Intent(StudentOutpass.this, StudentHome.class);
                            intent.putExtra("admno", admno);
                            startActivity(intent);
                            // Toast.makeText(StudentRegistration.this,"Inserted in profile", Toast.LENGTH_LONG).show();
                            clearText();

//                            dbHelp dbH1 = new dbHelp(getApplicationContext());
//                            SQLiteDatabase db1 = dbH1.getWritableDatabase();
//                            Cursor c1 = db1.rawQuery("SELECT * from " + dbHelp.TABLE_NAME1, null);
//
//                            StringBuffer buffer = new StringBuffer();
//                            while (c1.moveToNext()) {
//                                buffer.append(" Oid:" + c1.getString(c1.getColumnIndex(dbHelp.OID)) + "\n");
//                                buffer.append("Pid:" + c1.getString(c1.getColumnIndex(dbHelp.PID)) + "\n");
//                                buffer.append("DOL:" + c1.getString(c1.getColumnIndex(dbHelp.DOL)) + "\n");
//                                buffer.append("DOR:" + c1.getString(c1.getColumnIndex(dbHelp.DOR)) + "\n");
//                                buffer.append("PURPOSE:" + c1.getString(c1.getColumnIndex(dbHelp.PUR)) + "\n");
//                                buffer.append("Date:" + c1.getString(c1.getColumnIndex(dbHelp.DATE)) + "\n");
//                                buffer.append("Status:" + c1.getString(c1.getColumnIndex(dbHelp.STATUS)) + "\n");
//                                buffer.append("" + query3 + "count=" + cursor.getCount() + "\n\n");
//                            }
//                            showMessege("Student details", buffer.toString());
//                            db1.close();
                        } else {
                            TextView error = (TextView) findViewById(R.id.textView22);
                            error.setText("Today you are already applied for an Outpass.");
//                            Toast.makeText(StudentOutpass.this, "" + query3 + " count=" + cursor.getCount(), Toast.LENGTH_LONG).show();
                        }
                    }
                    catch (Exception e){
                        TextView error = (TextView) findViewById(R.id.textView22);
                        error.setText("Today you are already applied for an Outpass.");
                    }
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

    /*Calender code */
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                DatePickerDialog dialog = new DatePickerDialog(this, from_dateListener, sYear, sMonth, sDay);
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.getDatePicker().setMinDate(new Date().getTime());

                return dialog;
            case END_DATE_DIALOG_ID:
                DatePickerDialog dialog1 = new DatePickerDialog(this, to_dateListener, sYear, sMonth, sDay);
                dialog1.getDatePicker().setMinDate(new Date().getTime());
                long now = System.currentTimeMillis() - 1000;
                dialog1.getDatePicker().setMaxDate(now+(1000*60*60*24*21));
                return dialog1;
        }
        return null;
    }

    //update month day year
    private void fromupdateDisplay() {
        et1 = (EditText)findViewById(R.id.editText);
        et1.setText(//this is the edit text where you want to show the selected date
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(sDay).append("-")
                        .append(sMonth + 1).append("-")
                        .append(sYear).append(""));
    }
    private void toupdateDisplay() {
        et2 = (EditText)findViewById(R.id.editText5);

        et2.setText(//this is the edit text where you want to show the selected dated
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(eDay).append("-")
                        .append(eMonth + 1).append("-")
                        .append(eYear).append(""));
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
    private DatePickerDialog.OnDateSetListener to_dateListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    eYear = year;
                    eMonth = monthOfYear;
                    eDay = dayOfMonth;
                    toupdateDisplay();
                }
            };

    public void clearText(){
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et1.requestFocus();
    }

    public void showMessege(String title,String message) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}

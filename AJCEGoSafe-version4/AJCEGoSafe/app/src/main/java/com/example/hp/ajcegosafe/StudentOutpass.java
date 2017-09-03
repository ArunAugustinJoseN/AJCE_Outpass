package com.example.hp.ajcegosafe;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
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
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class StudentOutpass extends AppCompatActivity {
    public int layoutpressed = -1;
    private Button mPickDate;
    private int sYear,eYear;
    private int sMonth,eMonth;
    private int sDay,eDay;
    static final int DATE_DIALOG_ID = 0;
    static final int END_DATE_DIALOG_ID = 1;

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
        EditText et1 = (EditText)findViewById(R.id.editText);
        et1.setText(//this is the edit text where you want to show the selected date
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(sDay).append("-")
                        .append(sMonth + 1).append("-")
                        .append(sYear).append(""));
    }
    private void toupdateDisplay() {
        EditText et2 = (EditText)findViewById(R.id.editText5);
        et2.setText(//this is the edit text where you want to show the selected date
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

}

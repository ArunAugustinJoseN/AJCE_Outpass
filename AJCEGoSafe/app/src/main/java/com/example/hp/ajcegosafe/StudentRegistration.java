package com.example.hp.ajcegosafe;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class StudentRegistration extends AppCompatActivity {
    private String[] branchSpinner;

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

        ImageView ib = (ImageView)findViewById(R.id.imageView);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
                fromupdateDisplay();

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
        EditText et1 = (EditText)findViewById(R.id.editText7);
        et1.setText(//this is the edit text where you want to show the selected date
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

}

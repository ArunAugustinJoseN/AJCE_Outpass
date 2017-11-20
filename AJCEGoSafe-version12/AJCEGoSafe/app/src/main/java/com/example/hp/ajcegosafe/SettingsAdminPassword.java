package com.example.hp.ajcegosafe;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsAdminPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_admin_password);
        setTitle("Change Password");

        final EditText pass = (EditText) findViewById(R.id.edtCurrentPass);
        final EditText npass = (EditText) findViewById(R.id.edtNewPass);
        final EditText rpass = (EditText) findViewById(R.id.edtRepass);
        TextView updated = (TextView) findViewById(R.id.textView24);
        Button update = (Button) findViewById(R.id.button5);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pass.getText().toString().trim().length() == 0) {
                    pass.setError("Enter Current password.");
                    pass.requestFocus();
                } else if (npass.getText().toString().trim().length() == 0) {
                    npass.setError("Enter new password..");
                    npass.requestFocus();
                } else if (rpass.getText().toString().trim().length() == 0) {
                    rpass.setError("Re-Enter new password..");
                    rpass.requestFocus();
                } else {
                    String Cpass = pass.getText().toString();
                    String Npass = npass.getText().toString();
                    String Rpass = rpass.getText().toString();

                    dbHelp dbH3 = new dbHelp(getApplicationContext());
                    SQLiteDatabase db3 = dbH3.getWritableDatabase();
                    String query2= "SELECT * from " + dbHelp.TABLE_START ;
                    Cursor c3 = db3.rawQuery(query2, null);
                    c3.moveToFirst();
                    String admno = c3.getString(c3.getColumnIndex(dbHelp.ADNO3));
                    db3.close();

                    dbHelp dbH = new dbHelp(getApplicationContext());
                    SQLiteDatabase db = dbH.getWritableDatabase();
                    String query = "SELECT " + dbHelp.PASS + " from " + dbHelp.TABLE_NAME3 + " WHERE " + dbHelp.ADNO + " = '" + admno+"'";
                    Cursor c = db.rawQuery(query, null);
                    c.moveToFirst();
                    String password = c.getString(0).toString();
                    db.close();
                    if (!password.equals(Cpass)) {
                        pass.setError("Password Incorrect..!");
                        pass.requestFocus();
                        npass.setText("");
                        rpass.setText("");
                    } else if (!Npass.equals(Rpass)) {
                        npass.setError("Password don't Match..");
                        npass.requestFocus();
                        npass.setText("");
                        rpass.setText("");
                    } else if (npass.getText().toString().trim().length() < 4 || npass.getText().toString().trim().length() > 8) {
                        pass.setError("Password should have 4-8 digits..");
                        npass.requestFocus();
                        npass.setText("");
                        rpass.setText("");
                    } else {
                        dbHelp dbH1 = new dbHelp(getApplicationContext());
                        SQLiteDatabase db1 = dbH1.getWritableDatabase();
                        db1.execSQL("UPDATE " + dbHelp.TABLE_NAME3 + " SET " + dbHelp.PASS + " = '" + Npass + "' WHERE " + dbHelp.ADNO + " = '" + admno + "'");
                        db1.close();

                        TextView updated = (TextView) findViewById(R.id.textView24);
                        updated.setText("Your Password is Updated.");
                        pass.setText("");
                        npass.setText("");
                        rpass.setText("");
                        Toast.makeText(SettingsAdminPassword.this,"Your Password is Updated.", Toast.LENGTH_LONG).show();
//                        dbHelp dbh1=new dbHelp(SettingsAdminPassword.this);
//                        dbh1.deleteStartTableContent();
//                        finish();
//                        System.exit(0);

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
}
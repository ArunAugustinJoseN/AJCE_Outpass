package com.example.hp.ajcegosafe;

/**
 * Created by hp on 07-09-2017.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SecurityCustomAdapter extends ArrayAdapter<SecurityOutpassData> {

    int resource;
    String response;
    Context context;
    private List<SecurityOutpassData> items;
    private SecurityCustomAdapter adapter;

    public SecurityCustomAdapter(Context context, int resource, List<SecurityOutpassData> items) {
        super(context, resource, items);
        this.resource = resource;
        this.items=items;
        this.adapter = this;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout contactsView;
        final SecurityOutpassData contact = getItem(position);
        if (convertView == null) {
            contactsView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater) getContext().getSystemService(inflater);
            vi.inflate(resource, contactsView, true);
        } else {
            contactsView = (LinearLayout) convertView;
        }
        final TextView sName = (TextView) contactsView.findViewById(R.id.SecTxtName);
//        final TextView purpose = (TextView)contactsView.findViewById(R.id.HiddenOid);
        final TextView oid = (TextView) contactsView.findViewById(R.id.HiddenOid);
        final TextView pid = (TextView) contactsView.findViewById(R.id.HiddenPid);

        try {
            if (pid != null) {
                pid.setText((contact.getPid()));
                Integer id = Integer.parseInt(contact.getPid());

                dbHelp dbH2 = new dbHelp(getContext());
                SQLiteDatabase db2 = dbH2.getWritableDatabase();
                String query = "SELECT " + dbHelp.GEN + " from " + dbHelp.TABLE_NAME + " WHERE " + dbHelp.ID + "=" + id;
                Cursor c = db2.rawQuery(query, null);
                c.moveToFirst();
                String gender = c.getString(0).toString();
                db2.close();

                if (gender.equals("Male")) {
                    final ImageView img = (ImageView) contactsView.findViewById(R.id.imgPerson);
                    img.setImageResource(R.drawable.person);
                } else {
                    final ImageView img = (ImageView) contactsView.findViewById(R.id.imgPerson);
                    img.setImageResource(R.drawable.women);
                }
            }
            if (sName != null) {
                sName.setText((contact.getName()));
                if (contact.getName() == "Nothing to show"){
                    Button approve = (Button) contactsView.findViewById(R.id.btnGoOut);
                    approve.setEnabled(false);
                }
            }

            if (oid != null) {
                oid.setText(contact.getOid());
            }
            if (oid == null || sName == null || pid == null ) {
                sName.setText("Nothing to show..!!");
            }

        }
        catch (Exception e) {
            Toast.makeText(getContext(), "Nothing to show..!",
                    Toast.LENGTH_LONG).show();
        }


        Button approve = (Button) contactsView.findViewById(R.id.btnGoOut);
        approve.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                String formattedDate = df.format(c.getTime());

                // formattedDate have current date/time
                Integer oid2 = Integer.parseInt(oid.getText().toString());
                String na = sName.getText().toString();
                dbHelp db = new dbHelp(getContext());
                db.changeStatusToThree(oid2,formattedDate);
                items.remove(contact); //Actually change your list of items here
                adapter.notifyDataSetChanged();
                Snackbar.make(v, na+" Gone out.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();


//                Toast.makeText(getContext(), formattedDate+" List View Clicked:" + oid2, Toast.LENGTH_LONG).show();
            }
        });
        return contactsView;
    }
}

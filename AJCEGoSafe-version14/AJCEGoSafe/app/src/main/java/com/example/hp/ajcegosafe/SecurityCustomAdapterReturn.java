package com.example.hp.ajcegosafe;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by hp on 21-09-2017.
 */

public class SecurityCustomAdapterReturn extends ArrayAdapter<SecurityOutpassReturn> {

    int resource;
    String response;
    Context context;
    private List<SecurityOutpassReturn> items;
    private SecurityCustomAdapterReturn adapter;


    public SecurityCustomAdapterReturn(Context context, int resource, List<SecurityOutpassReturn> items) {
        super(context, resource, items);
        this.resource=resource;
        this.items=items;
        this.context=context;
        this.adapter = this;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout contactsView;
        final SecurityOutpassReturn contact = getItem(position);
        if(convertView==null) {
            contactsView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, contactsView, true);
        } else {
            contactsView = (LinearLayout) convertView;
        }
        final TextView R_Name =(TextView)contactsView.findViewById(R.id.SecTxtName2);
        final TextView R_oid = (TextView)contactsView.findViewById(R.id.HiddenOid2);
        final TextView pid = (TextView) contactsView.findViewById(R.id.HiddenPid2);

        if (pid != null) {
            pid.setText((contact.getR_pid()));
            Integer id = Integer.parseInt(contact.getR_pid());
            try {
                dbHelp dbH2 = new dbHelp(getContext());
                SQLiteDatabase db2 = dbH2.getWritableDatabase();
                String query = "SELECT " + dbHelp.GEN + " from " + dbHelp.TABLE_NAME + " WHERE " + dbHelp.ID + "=" + id;
                Cursor c = db2.rawQuery(query, null);
                c.moveToFirst();
                String gender = c.getString(0).toString();
                db2.close();

                if (gender.equals("Male")) {
                    final ImageView img1 = (ImageView) contactsView.findViewById(R.id.imgPerson1);
                    img1.setImageResource(R.drawable.person);
                } else {
                    final ImageView img1 = (ImageView) contactsView.findViewById(R.id.imgPerson1);
                    img1.setImageResource(R.drawable.women);
                }
            }
            catch (Exception e){

            }
        }
        if (R_Name != null) {
            R_Name.setText((contact.getR_name()));
            if (contact.getR_name() == "Nothing to show"){
                Button approve = (Button) contactsView.findViewById(R.id.btnApprove);
                approve.setEnabled(false);
            }
        }

        if (R_oid != null) {
            R_oid.setText(contact.getR_oid());
        }



        Button approve = (Button) contactsView.findViewById(R.id.btnApprove);
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                String formattedDate = df.format(c.getTime());

                Integer hour=Integer.parseInt(formattedDate.substring(0,2));
                if (hour >= 19) {

                    Integer oid2 = Integer.parseInt(R_oid.getText().toString());
                    String nam = R_Name.getText().toString();
//                    dbHelp db = new dbHelp(getContext());
//                    db.changeStatusToFour(oid2, formattedDate);
                    items.remove(contact); //Actually change your list of items here
                    adapter.notifyDataSetChanged();
                    Intent intent = new Intent(getContext(), StudentLateReturn.class);
                    intent.putExtra("oid", oid2);
                    context.startActivity(intent);


                }
                else {
                    Integer oid2 = Integer.parseInt(R_oid.getText().toString());
                    String nam = R_Name.getText().toString();
                    dbHelp db = new dbHelp(getContext());
                    db.changeStatusToFour(oid2, formattedDate);
                    items.remove(contact); //Actually change your list of items here
                    adapter.notifyDataSetChanged();

                    Snackbar.make(v, nam + " Return back.", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
//                    Toast.makeText(getContext(), " List View Clicked:" + hour, Toast.LENGTH_LONG).show();
                }
            }
        });
        return contactsView;
    }
}

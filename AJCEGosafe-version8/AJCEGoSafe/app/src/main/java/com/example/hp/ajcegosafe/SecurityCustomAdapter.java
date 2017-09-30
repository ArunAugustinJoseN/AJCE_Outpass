package com.example.hp.ajcegosafe;

/**
 * Created by hp on 07-09-2017.
 */

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
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
        sName.setText(contact.getName());
//        purpose.setText(contact.getPurpose());
        oid.setText(contact.getOid());
        Button approve = (Button) contactsView.findViewById(R.id.btnGoOut);
        approve.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer oid2 = Integer.parseInt(oid.getText().toString());
                String na = sName.getText().toString();
                dbHelp db = new dbHelp(getContext());
                db.changeStatusToThree(oid2);
                items.remove(contact); //Actually change your list of items here
                adapter.notifyDataSetChanged();
                Snackbar.make(v, na+" Gone out.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                Toast.makeText(getContext(), "List View Clicked:" + oid2, Toast.LENGTH_LONG).show();
            }
        });
        return contactsView;
    }
}

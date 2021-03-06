package com.example.hp.ajcegosafe;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
        R_Name.setText(contact.getR_name());
        R_oid.setText(contact.getR_oid());
        Button approve = (Button) contactsView.findViewById(R.id.btnApprove);
        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer oid2 = Integer.parseInt(R_oid.getText().toString());
                String nam = R_Name.getText().toString();
//                dbHelp db = new dbHelp(getContext());
//                db.changeStatusToFour(oid2);
                items.remove(contact); //Actually change your list of items here
                adapter.notifyDataSetChanged();

                Snackbar.make(v, nam+" Return back.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                Toast.makeText(getContext(), "List View Clicked:" + oid2, Toast.LENGTH_LONG).show();
            }
        });
        return contactsView;
    }
}

package com.example.hp.ajcegosafe;

/**
 * Created by hp on 07-09-2017.
 */

    import java.util.ArrayList;
    import java.util.List;

    import android.content.Context;
    import android.graphics.Typeface;
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

public class UserCustomAdapter extends ArrayAdapter<OutpassData> {

    int resource;
    String response;
    Context context;

    public UserCustomAdapter(Context context, int resource, List<OutpassData> items) {
        super(context, resource, items);
        this.resource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout contactsView;
        OutpassData contact = getItem(position);
        if(convertView==null) {
            contactsView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, contactsView, true);
        } else {
            contactsView = (LinearLayout) convertView;
        }
        final TextView sName =(TextView)contactsView.findViewById(R.id.txtName);
        final TextView purpose = (TextView)contactsView.findViewById(R.id.txtPurpose);
        final TextView oid = (TextView)contactsView.findViewById(R.id.txtHidden);
        sName.setText(contact.getName());
        purpose.setText(contact.getPurpose());
        oid.setText(contact.getOid());
        Button approve = (Button) contactsView.findViewById(R.id.btnApprove);
        approve.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String oid2 = oid.getText().toString();
                Toast.makeText(getContext(), "List View Clicked:" + oid2, Toast.LENGTH_LONG).show();
            }
        });
        return contactsView;
    }
    }

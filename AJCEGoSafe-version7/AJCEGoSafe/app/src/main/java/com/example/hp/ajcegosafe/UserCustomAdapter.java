package com.example.hp.ajcegosafe;

/**
 * Created by hp on 07-09-2017.
 */

    import java.util.ArrayList;
    import java.util.List;

    import android.content.Context;
    import android.content.Intent;
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

    import static com.example.hp.ajcegosafe.R.drawable.view;

public class UserCustomAdapter extends ArrayAdapter<OutpassData> {

    int resource;
    String response;
    Context context;
    private List<OutpassData> items;
    private UserCustomAdapter adapter;


    public UserCustomAdapter(Context context, int resource, List<OutpassData> items) {
        super(context, resource, items);
        this.resource=resource;
        this.context = context;
        this.items=items;
        this.adapter = this;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout contactsView;
        final OutpassData contact = getItem(position);
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
                Integer oid2 = Integer.parseInt(oid.getText().toString());
                String nm = sName.getText().toString();
                dbHelp db = new dbHelp(getContext());
                db.changeStatusToOne(oid2);
                items.remove(contact); //Actually change your list of items here
                adapter.notifyDataSetChanged();
                Snackbar.make(v, nm+"'s Request is Approved.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

//                Toast.makeText(getContext(), "List View Clicked:" + oid2, Toast.LENGTH_LONG).show();
            }
        });
        sName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AdminProfileOfStudent.class);
                Integer oid2 = Integer.parseInt(oid.getText().toString());
                String name = sName.getText().toString();
                String pur = purpose.getText().toString();
                intent.putExtra("oid", oid2);
                intent.putExtra("name", name);
                intent.putExtra("purpose", pur);
                context.startActivity(intent);
            }
        });
        return contactsView;
    }
    }

package com.example.hp.ajcegosafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hp on 30-09-2017.
 */

public class OutpassHistorypage_listAdapter extends ArrayAdapter<OutpassHistory> {
    private LayoutInflater mInflater;
    private ArrayList<OutpassHistory> outpass;
    private int mViewResourceId;

    public OutpassHistorypage_listAdapter(Context context, int textViewResourceId,ArrayList<OutpassHistory> outpass){
        super(context,textViewResourceId,outpass);
        this.outpass = outpass;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parents) {

        convertView = mInflater.inflate(mViewResourceId,null);

        OutpassHistory outpassHistory = outpass.get(position);

        if(outpassHistory != null){
            TextView txt_purpose = (TextView) convertView.findViewById(R.id.purpose);
            TextView txt_date = (TextView) convertView.findViewById(R.id.Txt_date);
            TextView txt_status = (TextView) convertView.findViewById(R.id.Txt_status);

            if (txt_purpose != null){
                txt_purpose.setText((outpassHistory.getPurpose()));
            }
            if (txt_date != null){
                txt_date.setText((outpassHistory.getDate()));
            }
            if (txt_status != null){
                txt_status.setText((outpassHistory.getStatus()));
            }
        }
        return convertView;
    }
}

package com.example.principal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class itemAdapter extends ArrayAdapter<Gisement> {

    private Context ctx;
    private int resource;
    private ArrayList<Gisement> ls;

    public itemAdapter(@NonNull Context context, int resource, @NonNull List<Gisement> objects) {
        super(context, resource, objects);

        this.ctx = context;
        this.resource = resource;
        this.ls = new ArrayList<>();
        this.ls = (ArrayList<Gisement>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        dbConn myDb = new dbConn(getContext(),"gisem",null,11);
        myDb.Open();

        Gisement s = this.ls.get(position);

        convertView = LayoutInflater.from(this.ctx).inflate(this.resource,
                parent,false);


        LinearLayout background = convertView.findViewById(R.id.background);
        TextView id = (TextView) convertView.findViewById(R.id.lblId);
        TextView type = (TextView) convertView.findViewById(R.id.lblType);


        id.setText("ID : "+String.valueOf(s.getId()));
        type.setText("Type : "+String.valueOf(s.getType()));


       ColorDrawable colorDrawable = new ColorDrawable(Color.rgb(192,188,255));
       String val=myDb.getStatus(s.getId());

        if(val.equals("ok"))
        {
            background.setBackgroundDrawable(colorDrawable);
        }

        return convertView;
    }
}

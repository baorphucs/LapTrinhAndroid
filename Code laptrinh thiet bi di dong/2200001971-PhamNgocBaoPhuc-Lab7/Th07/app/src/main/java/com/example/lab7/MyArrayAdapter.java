package com.example.lab7;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Employee> {
    Activity context = null;
    ArrayList<Employee> myArray = null;
    int layoutId;
    public MyArrayAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<Employee> objects){
        super(context, resource, objects);
        this.context = context;
        this.layoutId = resource;
        this.myArray = objects;
    }
    @NonNull

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutId, null);
        Employee emp = myArray.get(position);
        TextView txtdisplay=(TextView)convertView.findViewById(R.id.txtItemHoTen);
        txtdisplay.setText(emp.toString());
        ImageView imgitem=(ImageView)convertView.findViewById(R.id.imvAvatar);
        if (emp.isGioiTinh())
            imgitem.setImageResource(R.drawable.boy);
        else
            imgitem.setImageResource(R.drawable.girl);
        return convertView;
    }
}

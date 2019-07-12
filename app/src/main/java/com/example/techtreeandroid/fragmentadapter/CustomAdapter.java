package com.example.techtreeandroid.fragmentadapter;


import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.techtreeandroid.R;

import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends BaseAdapter {

    private List<BluetoothDevice> arrayList;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, List<BluetoothDevice> arrayList) {
        this.arrayList = arrayList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.list_view, null);
            holder = new ViewHolder();
            holder.txt_name = view.findViewById(R.id.txt_name);
            holder.txt_id = view.findViewById(R.id.txt_id);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.txt_name.setText(arrayList.get(i).getName());
        holder.txt_id.setText(arrayList.get(i).getAddress());

        return view;
    }

    static class ViewHolder {
        TextView txt_name;
        TextView txt_id;

    }
}

package com.example.myjson;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomContentList extends BaseAdapter {

    Activity context;
    ArrayList<Main2Activity.Country>countries;

    public CustomContentList(Activity context, ArrayList<Main2Activity.Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    @Override
    public int getCount() {
        if(countries.size()<=0)
            return 1;
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row=convertView;
        LayoutInflater inflater=context.getLayoutInflater();
                ViewHolder vh = null;
                if(convertView==null) {
                    row = inflater.inflate(R.layout.activity_main2, null, true);
                    vh.textViewId = (TextView) row.findViewById(R.id.textViewID1);
                    vh.textViewCountry=(TextView)row.findViewById(R.id.textViewID2);
                    row.setTag(vh);
                }else{
                    vh=(ViewHolder)convertView.getTag();
                }
                vh.textViewId.setText(""+countries.get(position).getId());
                vh.textViewCountry.setText(countries.get(position).getName());
        return row;
    }

    static class ViewHolder {
        TextView textViewId;
        TextView textViewCountry;
    }

}

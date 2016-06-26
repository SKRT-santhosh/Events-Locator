package com.example.user.eventsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by User on 22-02-2016.
 */
public class CustomAdapter extends BaseAdapter{

    private static LayoutInflater inflater = null;
    public Context mContext;
    String[][] values;
    Event[] eventsList;
    String city;
    int length;

    public CustomAdapter(MainActivity mainActivity, String itemcity, Event[] events) {
        mContext = mainActivity;
        city=itemcity;
        eventsList = events;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return eventsList.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder {
        ImageView img;
        Button button;
        TextView tv;  // where tv is the textview used in holder
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        holder = new Holder();
        inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        convertView=inflater.inflate(R.layout.list_item,parent,false);
        holder.img = (ImageView) convertView.findViewById(R.id.imageView1);
        holder.img.setImageResource(eventsList[position].getImage());
        holder.tv=(TextView)convertView.findViewById(R.id.textView2);
        holder.tv.setText(eventsList[position].getEventName()+eventsList[position].getCategory());
        return convertView;
    }
}

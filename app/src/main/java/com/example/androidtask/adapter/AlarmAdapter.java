package com.example.androidtask.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.androidtask.R;
import com.example.androidtask.database.Alarm;

import java.text.DateFormat;

import java.util.List;

public class AlarmAdapter extends BaseAdapter {

    private Context context;
    private List<Alarm> alarms;

    public AlarmAdapter(Context context, List<Alarm> alarms) {
        this.context = context;
        this.alarms = alarms;
    }

    @Override
    public int getCount() {
        return alarms.size();
    }

    @Override
    public Object getItem(int position) {
        return alarms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (position + 1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.custom_alarm_item, null);

        TextView tvName = convertView.findViewById(R.id.custom_alarm_tv_name);
        TextView tvDate = convertView.findViewById(R.id.custom_alarm_tv_date);
        TextView tvTime = convertView.findViewById(R.id.custom_alarm_tv_time);

        tvName.setText(alarms.get(position).getName());
        tvDate.setText(DateFormat.getDateInstance(DateFormat.DEFAULT).format(alarms.get(position).getCalender().getTime()));
        tvTime.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(alarms.get(position).getCalender().getTime()));

        return convertView;
    }
}

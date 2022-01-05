package com.example.androidtask.fragments;

import static android.content.Context.ALARM_SERVICE;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.androidtask.activities.PermissionsActivity;
import com.example.androidtask.recievers.MyReceiver;
import com.example.androidtask.R;
import com.example.androidtask.activities.MainActivity;
import com.example.androidtask.adapter.AlarmAdapter;
import com.example.androidtask.database.Alarm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CurrentFragment extends Fragment {

    private ListView listView;
    private List<Alarm> alarms;

    public CurrentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current, container, false);
        listView = view.findViewById(R.id.current_lv);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        alarms = new ArrayList<>();
        if (!PermissionsActivity.allAlarms.isEmpty()) {
            for (Alarm a : PermissionsActivity.allAlarms) {
                if (a.getCalender().after(Calendar.getInstance())) {
                    alarms.add(new Alarm(a.getName(), a.getCalender()));
                }
            }
            AlarmAdapter adapter = new AlarmAdapter(getActivity(), alarms);
            listView.setAdapter(adapter);

            for (Alarm a : alarms) {
                Calendar alarmFor = a.getCalender();
                Intent MyIntent = new Intent(getActivity(), MyReceiver.class);
                MyIntent.putExtra("name", a.getName());
                PendingIntent MyPendIntent = PendingIntent.getBroadcast(getActivity(), a.getId(), MyIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager myAlarm = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
                myAlarm.setExact(AlarmManager.RTC_WAKEUP, alarmFor.getTimeInMillis(), MyPendIntent);
            }
        }

    }
}
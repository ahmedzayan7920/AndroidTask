package com.example.androidtask.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.androidtask.R;
import com.example.androidtask.activities.MainActivity;
import com.example.androidtask.activities.PermissionsActivity;
import com.example.androidtask.adapter.AlarmAdapter;
import com.example.androidtask.database.Alarm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class HistoryFragment extends Fragment {

    private ListView listView;
    private List<Alarm> alarms;
    private Calendar c;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        listView = view.findViewById(R.id.history_lv);
        c = Calendar.getInstance();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        alarms = new ArrayList<>();
        if (!PermissionsActivity.allAlarms.isEmpty()) {
            alarms = new ArrayList<>();

            for (Alarm a : PermissionsActivity.allAlarms){
                if (a.getCalender().before(Calendar.getInstance())){
                    alarms.add( new Alarm(a.getName(), a.getCalender()) );
                }
            }
            AlarmAdapter adapter = new AlarmAdapter(getActivity(), alarms);
            listView.setAdapter(adapter);
        }

    }
}
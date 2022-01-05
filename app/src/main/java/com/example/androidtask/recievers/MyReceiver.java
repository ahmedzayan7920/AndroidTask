package com.example.androidtask.recievers;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.androidtask.activities.DialogActivity;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent in = new Intent(context, DialogActivity.class);
        in.addFlags(FLAG_ACTIVITY_NEW_TASK);
        in.putExtra("name", intent.getStringExtra("name"));
        context.startActivity(in);
    }
}
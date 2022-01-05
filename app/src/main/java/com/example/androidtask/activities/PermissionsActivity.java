package com.example.androidtask.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

import com.example.androidtask.R;
import com.example.androidtask.database.Alarm;
import com.example.androidtask.database.AlarmDB;

import java.util.ArrayList;
import java.util.List;


public class PermissionsActivity extends AppCompatActivity {

    private Button btnOverlay;

    public static List<Alarm> allAlarms;
    private AlarmDB dbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);

        btnOverlay = findViewById(R.id.permission_btn_overlay);

        btnOverlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                startActivity(myIntent);
            }
        });

        dbConnection= AlarmDB.getDbConnection(PermissionsActivity.this);
        allAlarms = new ArrayList<>();
        AlarmDB.EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                allAlarms = dbConnection.getDao().getAllAlarms();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(this)) {
            startActivity(new Intent(PermissionsActivity.this, MainActivity.class));
            finish();
        }
    }
}
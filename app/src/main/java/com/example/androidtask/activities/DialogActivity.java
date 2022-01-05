package com.example.androidtask.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidtask.R;
import com.example.androidtask.database.Alarm;
import com.example.androidtask.database.AlarmDB;
import com.example.androidtask.services.MyService;

import java.util.Calendar;

public class DialogActivity extends Activity {

    private TextView tvName;
    private Button btnClose;

    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        this.setFinishOnTouchOutside(false);

        tvName = findViewById(R.id.dialog_tv_name);
        btnClose = findViewById(R.id.dialog_btn_close);

        Intent i = getIntent();
        name = i.getStringExtra("name");

        tvName.setText(name);

        startService(new Intent(this, MyService.class));

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(DialogActivity.this, MyService.class));
                finish();
            }
        });
    }
}
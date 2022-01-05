package com.example.androidtask.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.androidtask.R;
import com.example.androidtask.database.Alarm;
import com.example.androidtask.database.AlarmDB;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddAndEditActivity extends AppCompatActivity{

    private EditText etName;
    private TextView tvDate;
    private TextView tvTime;
    private Button btnSave;
    private Calendar calendar;
    private AlarmDB dbConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit);

        etName = findViewById(R.id.add_et_name);
        tvDate = findViewById(R.id.add_tv_date);
        tvTime = findViewById(R.id.add_tv_time);
        btnSave = findViewById(R.id.add_btn_save);
        calendar = Calendar.getInstance();

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);
                DatePickerDialog d = new DatePickerDialog(AddAndEditActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        //tvDate.setText(i + "/" + (i1 + 1) + "/" + i2);
                        calendar.set(Calendar.YEAR, i);
                        calendar.set(Calendar.MONTH, i1);
                        calendar.set(Calendar.DAY_OF_MONTH, i2);

                        tvDate.setText(DateFormat.getDateInstance(DateFormat.DEFAULT).format(calendar.getTime()));
                    }
                }, year, month, day);
                d.show();
            }
        });


        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog t = new TimePickerDialog(AddAndEditActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        calendar.set(Calendar.HOUR_OF_DAY, i);
                        calendar.set(Calendar.MINUTE, i1);
                        calendar.set(Calendar.SECOND, 0);
                        calendar.set(Calendar.MILLISECOND, 0);

                        tvTime.setText(DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime()));

                    }
                }, hour, minute, false);
                t.show();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etName.getText().toString().isEmpty() && !tvDate.getText().toString().equals("Set Date") && !tvTime.getText().toString().equals("Set Time")) {
                    dbConnection = AlarmDB.getDbConnection(AddAndEditActivity.this);
                    AlarmDB.EXECUTOR_SERVICE.execute(new Runnable() {
                        @Override
                        public void run() {
                            dbConnection.getDao().insert(new Alarm(etName.getText().toString(), calendar));
                        }
                    });
                    startActivity(new Intent(AddAndEditActivity.this, MainActivity.class));
                    finish();
                } else {
                    if (etName.getText().toString().isEmpty()) {
                        etName.setError("Please Enter Alarm Name");
                    } else if (tvDate.getText().toString().equals("Set Date")) {
                        tvDate.setError("Please Set Alarm Date");
                    }else if (tvTime.getText().toString().equals("Set Time")){
                        tvTime.setError("Please Set Alarm Time");
                    }
                }
            }
        });
    }

}
package com.example.androidtask.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import com.example.androidtask.R;
import com.example.androidtask.database.Alarm;
import com.example.androidtask.database.AlarmDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AlarmDB dbConnection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            startActivity(new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS, Uri.parse("package:"+getPackageName())));
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbConnection= AlarmDB.getDbConnection(MainActivity.this);
        PermissionsActivity.allAlarms = new ArrayList<>();
        AlarmDB.EXECUTOR_SERVICE.execute(new Runnable() {
            @Override
            public void run() {
                PermissionsActivity.allAlarms = dbConnection.getDao().getAllAlarms();
            }
        });
    }



    @Override
    protected void onResume() {
        super.onResume();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        Toolbar toolbar = findViewById(R.id.toolbar);

        NavigationView navView = findViewById(R.id.navigation_view);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(drawer)
                .build();

        NavigationUI.setupWithNavController(toolbar,navController,appBarConfiguration);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        NavigationUI.setupWithNavController(navView,navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_menu:
                Intent intent = new Intent(getBaseContext(), AddAndEditActivity.class);
                startActivity(intent);
                return true;
        }
        return false;
    }
}
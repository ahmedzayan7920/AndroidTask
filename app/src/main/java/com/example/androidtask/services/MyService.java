package com.example.androidtask.services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;

public class MyService extends Service {

    private Uri alert;
    private Ringtone r;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        new Handler().post(new Runnable() {
            @Override
            public void run() {
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                r = RingtoneManager.getRingtone(getApplicationContext(), alert);
                r.setAudioAttributes(new AudioAttributes
                        .Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build());
                r.play();
            }
        });


        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        r.stop();
    }
}
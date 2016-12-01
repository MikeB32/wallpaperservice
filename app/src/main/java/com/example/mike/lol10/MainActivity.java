package com.example.mike.lol10;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    PendingIntent pintent;
    Intent intent;
    SharedPreferences preference;
    AlarmManager alarm;
    Button rd1, rd2, rd3, rd4, rd5, rd6;


    private final static int  a = 1000*60*5; //5 min
    private final static int b = 1000 * 60 * 10; // 10 min
    private final static int c = 1000 * 60 * 30; // 30 min
    private final static int d = 1000 * 60 * 120; // 2 hours
    private final static int e = 1000 * 60 * 360; // 6 hours
    private final static int f = 1000*60*1440; // 1 day
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rd1 = (Button) findViewById(R.id.btn1);
        rd2 = (Button) findViewById(R.id.btn2);
        rd3 = (Button) findViewById(R.id.btn3);
        rd4 = (Button) findViewById(R.id.btn4);
        rd5 = (Button) findViewById(R.id.btn5);
        rd6 = (Button) findViewById(R.id.btn6);


        preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pintent);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1: {
                intent = new Intent(MainActivity.this, WallService.class);
                pintent = PendingIntent.getService(MainActivity.this, 0, intent, 0);

                preference.edit().putInt("timeInterval", a).apply();
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), a, pintent);
                startService(intent);
                break;
            }
            case R.id.btn2: {
                intent = new Intent(MainActivity.this, WallService.class);
                pintent = PendingIntent.getService(MainActivity.this, 0, intent, 0);

                preference.edit().putInt("timeInterval", b).apply();
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), b, pintent);
                startService(intent);
                break;
            }
            case R.id.btn3: {
                intent = new Intent(MainActivity.this, WallService.class);
                pintent = PendingIntent.getService(MainActivity.this, 0, intent, 0);

                preference.edit().putInt("timeInterval", c).apply();
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), c, pintent);
                startService(intent);
                break;

            }
            case R.id.btn4: {
                intent = new Intent(MainActivity.this, WallService.class);
                pintent = PendingIntent.getService(MainActivity.this, 0, intent, 0);

                preference.edit().putInt("timeInterval", d).apply();
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), d, pintent);
                startService(intent);
                break;

            }
            case R.id.btn5: {
                intent = new Intent(MainActivity.this, WallService.class);
                pintent = PendingIntent.getService(MainActivity.this, 0, intent, 0);

                preference.edit().putInt("timeInterval", e).apply();
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), e, pintent);
                startService(intent);
                break;

            }
            case R.id.btn6: {
                intent = new Intent(MainActivity.this, WallService.class);
                pintent = PendingIntent.getService(MainActivity.this, 0, intent, 0);

                preference.edit().putInt("timeInterval", f).apply();
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), f, pintent);
                startService(intent);
                break;


            }

        }
    }
}


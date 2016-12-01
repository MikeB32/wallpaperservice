package com.example.mike.lol10;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;

/**
 * Created by Mike on 11/1/2016.
 */

public class BootCompletedIntentReceiver extends BroadcastReceiver {

    private final static int a = 1000*60*5; //5 min
    private final static int b = 1000 * 60 * 10; // 10 min
    private final static int c = 1000 * 60 * 30; // 30 min
    private final static int d = 1000 * 60 * 120; // 2 hours
    private final static int e = 1000 * 60 * 360; // 6 hours
    private final static int f = 1000*60*1440; // 1 day

    @Override
    public void onReceive(Context context, Intent intent) {

        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {

            SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
            int interval = preference.getInt("timeInterval", a);
            int interval2 = preference.getInt("timeInterval", b);
            int interval3 = preference.getInt("timeInterval", c);
            int interval4 = preference.getInt("timeInterval", d);
            int interval5 = preference.getInt("timeInterval", e);
            int interval6 = preference.getInt("timeInterval", f);

            Intent pushIntent = new Intent(context, WallService.class);
            PendingIntent pintent = PendingIntent.getService(context, 0,pushIntent , 0);
            AlarmManager alarm = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),interval, pintent);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),interval2, pintent);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),interval3, pintent);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),interval4, pintent);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),interval5, pintent);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),interval6, pintent);



            context.startService(pushIntent);
        }
    }
}

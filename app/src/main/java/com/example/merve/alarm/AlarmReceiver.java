package com.example.merve.alarm;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class AlarmReceiver extends BroadcastReceiver {
    String value;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"UYANNN!!!",Toast.LENGTH_LONG).show();
        Uri alarmUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alarmUri==null)
        {
            alarmUri=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        final Ringtone ringtone=RingtoneManager.getRingtone(context,alarmUri);
        ringtone.play();


        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Intent ıntent = new Intent(".Main2Activity");
                ıntent.getAction();
                Bundle extras = ıntent.getExtras();
                value = extras.getString("send_string");
                if(value=="Alarmkapa")
                {
                    ringtone.stop();
                }

            }
        },2*2000);


    }
}

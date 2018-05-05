package com.example.merve.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity {
    private Button startAlarmBtn;
    private TimePickerDialog timePickerDialog;
    final static int REQUEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startAlarmBtn= (Button) findViewById(R.id.startAlarmBtn);
        startAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPickerDiaog(false);
            }
        });

    }
    private void openPickerDiaog(boolean is24hour)
    {
        Calendar calendar=Calendar.getInstance();
        timePickerDialog=new TimePickerDialog(
                MainActivity.this,
                onTimeSetListener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                is24hour);
        timePickerDialog.setTitle("Alarm ayarla");
        timePickerDialog.show();

    }
    TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calNow=Calendar.getInstance();
            Calendar caLSet=(Calendar) calNow.clone();

            caLSet.set(Calendar.HOUR_OF_DAY,hourOfDay);
            caLSet.set(Calendar.MINUTE,minute);
            caLSet.set(Calendar.SECOND,0);
            caLSet.set(Calendar.MILLISECOND,0);

            if(caLSet.compareTo(calNow)<=0){
                caLSet.add(Calendar.DATE,1);
            }
            setAlarm(caLSet);

        }
        private void setAlarm(Calendar alarmCalendar){
            Toast.makeText(getApplicationContext(),"Alarm ayarlandı",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getBaseContext(),AlarmReceiver.class);
            PendingIntent pendingIntent=PendingIntent.getBroadcast(getBaseContext(),REQUEST_CODE,intent,0);
            AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,alarmCalendar.getTimeInMillis(),pendingIntent);


        }
    };
}

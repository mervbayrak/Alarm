package com.example.merve.alarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        String s = "Alarmkapa";
        Intent i = new Intent(getApplicationContext(), AlarmReceiver.class);
        i.putExtra("send_string",s);
        startActivity(i);
    }
}

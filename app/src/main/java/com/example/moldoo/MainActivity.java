package com.example.moldoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    public static long TOTAL_MOLDOO_TIME = 0;

    private static int MINUTE = 0;
    private static int SECOND = 0;
    private static int TIMER_LENGTH = 60;

    private TimerView mTimerView;
    private TextView mDurationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimerView = (TimerView) findViewById(R.id.timer);

        final Button timerStartButton = (Button) findViewById(R.id.btn_timer_start);
        timerStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerStartButton.getText() != "초기화") {
                    timerStartButton.setText("초기화");
                    mTimerView.start(TIMER_LENGTH);
                } else {
                    timerStartButton.setText("시작");
                    Toast.makeText(getApplicationContext(), secondsToTimeString(TOTAL_MOLDOO_TIME), Toast.LENGTH_SHORT).show();
                    mTimerView.stop();
                }
            }
        });

        final Button recordButton = (Button) findViewById(R.id.btn_record);
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recordIntent = new Intent(getApplicationContext(), RecordActivity.class);
                startActivity(recordIntent);
            }
        });

        final Button rankButton = (Button) findViewById(R.id.btn_rank);
        rankButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rankIntent = new Intent(getApplicationContext(), RankActivity.class);
                startActivity(rankIntent);

            }
        });

        mDurationView = findViewById(R.id.durationView);
        final SeekBar mSetMinute = findViewById(R.id.setMinute);
        final SeekBar mSetSecond = findViewById(R.id.setSecond);

        mSetMinute.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MINUTE = mSetMinute.getProgress();
                mDurationView.setText(String.format("%d m  %d s", MINUTE, SECOND));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                MINUTE = mSetMinute.getProgress();
                mDurationView.setText(String.format("%d m  %d s", MINUTE, SECOND));
            }

            @Override
            public void onStopTrackingTouch(SeekBar setBar) {
                MINUTE = mSetMinute.getProgress();
                mDurationView.setText(String.format("%d m  %d s", MINUTE, SECOND));
                TIMER_LENGTH = MINUTE * 60 + SECOND;
            }
        });

        mSetSecond.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SECOND = mSetSecond.getProgress();
                mDurationView.setText(String.format("%d m  %d s", MINUTE, SECOND));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                SECOND = mSetSecond.getProgress();
                mDurationView.setText(String.format("%d m  %d s", MINUTE, SECOND));
            }

            @Override
            public void onStopTrackingTouch(SeekBar setBar) {
                SECOND = mSetSecond.getProgress();
                mDurationView.setText(String.format("%d m  %d s", MINUTE, SECOND));
                TIMER_LENGTH = MINUTE * 60 + SECOND;
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
        mTimerView.stop();
    }

    protected static String secondsToTimeString(long secs){
        int seconds = (int) (secs % 60);
        int minutes = (int) ((secs / 60) % 60);
        int hours = (int) ((secs / 3600) % 24);
        String secondsTxt = (seconds < 10 ? "0": "") + seconds;
        String minutesTxt = (minutes < 10 ? "0" : "") + minutes;
        String hoursTxt = (hours < 10 ? "0" : "") + hours;
        return new String(hoursTxt + " : " + minutesTxt + " : " + secondsTxt );
    }

}


package com.example.moldoo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

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
                    mTimerView.stop();
                }
            }
        });

        mDurationView = findViewById(R.id.durationView);
        SeekBar mSetMinute = findViewById(R.id.setMinute);
        SeekBar mSetSecond = findViewById(R.id.setSecond);

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
}
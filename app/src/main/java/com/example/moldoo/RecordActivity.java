package com.example.moldoo;

import static com.example.moldoo.MainActivity.TOTAL_MOLDOO_TIME;
import static com.example.moldoo.MainActivity.secondsToTimeString;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RecordActivity extends AppCompatActivity {
    public String curDate;
    private TextView mTimeRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        mTimeRecord = findViewById(R.id.timeRecord);
        mTimeRecord.setText(secondsToTimeString(TOTAL_MOLDOO_TIME));
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                String datestr = format.format(calendarView.getDate());
                System.out.println(datestr);
            }
        });
    }
}
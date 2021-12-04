package com.example.android_resapi.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.android_resapi.R;
import com.example.android_resapi.ui.apicall.GetLog;
import com.example.android_resapi.ui.apicall.GetLog2;
import com.example.android_resapi.ui.apicall.GetThingShadow;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ChartActivity extends AppCompatActivity {
    String getLogsURL;
    Timer timer;
    private TextView textView_Date1;
    private TextView textView_Date2;
    private DatePickerDialog.OnDateSetListener callbackMethod;
    final static String TAG = "  IoT 화재 위험 감지기  ";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        Intent intent = getIntent();
        getLogsURL = intent.getStringExtra("getLogsURL");
        Log.i(TAG, "getLogsURL="+getLogsURL);

        Button startDateBtn = findViewById(R.id.start_date_button);
        startDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbackMethod = new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        textView_Date1 = (TextView)findViewById(R.id.textView_date1);
                        textView_Date1.setText(String.format("%d-%d-%d ", year ,monthOfYear+1,dayOfMonth));
                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(ChartActivity.this, callbackMethod, 2021, 11, 0);

                dialog.show();


            }
        });



        Button endDateBtn = findViewById(R.id.end_date_button);
        endDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbackMethod = new DatePickerDialog.OnDateSetListener()
                {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        textView_Date2 = (TextView)findViewById(R.id.textView_date2);
                        textView_Date2.setText(String.format("%d-%d-%d ", year ,monthOfYear+1,dayOfMonth));
                    }
                };

                DatePickerDialog dialog = new DatePickerDialog(ChartActivity.this, callbackMethod, 2021, 12, 0);

                dialog.show();


            }
        });


        Button start = findViewById(R.id.chart_start_button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        new GetLog2(ChartActivity.this,getLogsURL).execute();
            }
        });



    }


}

package com.example.android_resapi.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_resapi.R;
import com.example.android_resapi.ui.apicall.GetThingShadow;
import com.example.android_resapi.ui.apicall.UpdateShadow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    String urlStr;
    final static String TAG = "IoT 화재 위험 감지기";
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String urlstr = "https://xxxxxx.execute-api.ap-northeast-1.amazonaws.com/prod/devices/xxxxxxx";
        if (urlstr == null || urlstr.equals("")) {
            Toast.makeText(MainActivity.this, "사물상태 조회/변경 API URI 입력이 필요합니다.", Toast.LENGTH_SHORT).show();
            return;
        }




        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new GetThingShadow(MainActivity.this, urlstr).execute();
                TextView v = (TextView)findViewById(R.id.text_view_state);
                TextView v2 = (TextView)findViewById(R.id.reported_temp);
                String s = v2.getText().toString();
                v.setText(s);
                try{
                    Double temper = Double.parseDouble(v.getText().toString());
                    if(temper>=29.9){
                        v.setText("!!!화재 위험 발생!!!");
                    }
                    else if(temper>=29.7){
                        v.setText("~~~ 화재 위험 경고 ~~~");
                    }else{
                        v.setText("****    양호    ****");
                    }

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            }
        },0,1000);


        Button updateBtn = findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit_led = findViewById(R.id.edit_led);
                String urlstr = "https://xxxxxx.execute-api.ap-northeast-1.amazonaws.com/prod/devices/xxxxxx";
                JSONObject payload = new JSONObject();

                try {
                    JSONArray jsonArray = new JSONArray();
                    String led_input = edit_led.getText().toString();
                    if (led_input != null && !led_input.equals("")) {
                        JSONObject tag2 = new JSONObject();
                        tag2.put("tagName", "LED");
                        tag2.put("tagValue", led_input);
                        jsonArray.put(tag2);
                    }
                    if (jsonArray.length() > 0)
                        payload.put("tags", jsonArray);
                } catch (JSONException e) {
                    Log.e(TAG, "JSONEXception");
                }
                Log.i(TAG,"payload="+payload);

                    if (payload.length() >0 )
                        new UpdateShadow(MainActivity.this,urlstr).execute(payload);
                    else
                        Toast.makeText(MainActivity.this,"변경할 상태 정보 입력이 필요합니다", Toast.LENGTH_SHORT).show();
            }
        });


        Button listLogsBtn = findViewById(R.id.listLogsBtn);
        listLogsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlstr = "https://xxxxxx.execute-api.ap-northeast-1.amazonaws.com/prod/devices/xxxxxxx/log";
                if (urlstr == null || urlstr.equals("")) {
                    Toast.makeText(MainActivity.this, "사물로그 조회 API URI 입력이 필요합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, LogActivity.class);
                intent.putExtra("getLogsURL", urlstr);
                startActivity(intent);
            }
        });


        Button chartBtn = findViewById(R.id.chartBtn);
        chartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String urlstr = "https://xxxxxxx.execute-api.ap-northeast-1.amazonaws.com/prod/devices/xxxxxx/log";
                if (urlstr == null || urlstr.equals("")) {
                    Toast.makeText(MainActivity.this, "사물로그 조회 API URI 입력이 필요합니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, ChartActivity.class);
                intent.putExtra("getLogsURL", urlstr);
                startActivity(intent);
            }
        });
    }


}



